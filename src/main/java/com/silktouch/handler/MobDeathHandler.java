package com.silktouch.handler;

import com.silktouch.config.ModConfig;
import com.silktouch.util.MobCategory;
import com.silktouch.util.SpawnEggRegistry;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class MobDeathHandler {
    private static final Random RANDOM = new Random();

    public static void handleMobDeath(LivingEntity entity, DamageSource damageSource, ServerWorld world) {
        // Check if killer is a player
        Entity attacker = damageSource.getAttacker();
        if (!(attacker instanceof ServerPlayerEntity player)) {
            return;
        }

        // Check if player has Silk Touch on main hand
        ItemStack mainHandItem = player.getMainHandStack();
        if (getEnchantmentLevel(world, net.minecraft.enchantment.Enchantments.SILK_TOUCH, mainHandItem) <= 0) {
            return;
        }

        // Get mob category
        MobCategory category = MobCategory.getCategory(entity);
        
        // Bosses never drop eggs
        if (category == MobCategory.BOSS) {
            return;
        }

        // Check if mob has a vanilla spawn egg
        SpawnEggItem spawnEgg = SpawnEggRegistry.getSpawnEggForEntity(entity.getType());
        if (spawnEgg == null) {
            return;
        }

        // Calculate drop chance
        double chance = calculateDropChance(entity, player, category, mainHandItem, world);
        if (chance <= 0) {
            return;
        }

        // Check advancement gating for specific mobs
        if (!checkAdvancementGating(entity, player)) {
            return;
        }

        // Check special conditions
        if (!checkSpecialConditions(entity, category, damageSource)) {
            return;
        }

        // Roll for drop
        if (RANDOM.nextDouble() > chance) {
            return;
        }

        // Create spawn egg item with correct variant
        ItemStack eggStack = createVariantCorrectEgg(spawnEgg, entity);

        // Drop the egg using ItemEntity for MC 1.21.11 compatibility
        dropItem(world, entity, eggStack);

        // Apply durability tax
        applyDurabilityTax(player, mainHandItem);
    }

    /**
     * Drop an item at the entity's position
     */
    private static void dropItem(ServerWorld world, LivingEntity entity, ItemStack stack) {
        Vec3d pos = entity.getEyePos();
        ItemEntity itemEntity = new ItemEntity(world, pos.x, pos.y, pos.z, stack);
        itemEntity.setToDefaultPickupDelay();
        world.spawnEntity(itemEntity);
    }

    private static double calculateDropChance(LivingEntity entity, ServerPlayerEntity player, 
                                            MobCategory category, ItemStack weapon, ServerWorld world) {
        ModConfig config = ModConfig.get();
        double baseChance;

        switch (category) {
            case COMMON_PASSIVE:
                baseChance = config.commonPassiveChance;
                break;
            case NEUTRAL_HOSTILE:
                baseChance = config.neutralHostileChance;
                break;
            case RARE_UTILITY:
                baseChance = config.rareUtilityChance;
                break;
            default:
                return 0.0;
        }

        // Baby penalty
        if (entity.isBaby()) {
            baseChance += config.babyPenalty;
        }

        // Looting bonus
        int lootingLevel = getEnchantmentLevel(world, net.minecraft.enchantment.Enchantments.LOOTING, weapon);
        baseChance += lootingLevel * config.lootingBonusPerLevel;

        // Rare mobs require Looting II+ with Silk Touch
        if (category == MobCategory.RARE_UTILITY && lootingLevel < 2) {
            return 0.0;
        }

        return Math.max(0.0, Math.min(1.0, baseChance));
    }

    private static boolean checkAdvancementGating(LivingEntity entity, ServerPlayerEntity player) {
        if (!ModConfig.get().advancementGating) {
            return true;
        }

        Identifier requiredAdvancement = null;

        if (entity.getType() == EntityType.ENDERMAN) {
            requiredAdvancement = Identifier.of("minecraft", "end/kill_dragon"); // Free the End
        } else if (entity.getType() == EntityType.BLAZE) {
            requiredAdvancement = Identifier.of("minecraft", "story/enter_the_nether"); // Into Fire (approximate)
        } else if (entity.getType() == EntityType.GUARDIAN || entity.getType() == EntityType.ELDER_GUARDIAN) {
            requiredAdvancement = Identifier.of("minecraft", "adventure/kill_a_mob"); // The Deep End (approximate)
        }

        if (requiredAdvancement != null) {
            // Use the entity's world to get the server
            var server = ((ServerWorld) entity.getEntityWorld()).getServer();
            if (server != null) {
                AdvancementEntry advancement = server.getAdvancementLoader().get(requiredAdvancement);
                if (advancement != null) {
                    return player.getAdvancementTracker().getProgress(advancement).isDone();
                }
            }
        }

        return true;
    }

    private static boolean checkSpecialConditions(LivingEntity entity, MobCategory category, DamageSource damageSource) {
        ModConfig config = ModConfig.get();

        // Check if from spawner - simplified for MC 1.21.11 compatibility
        if (config.noSpawnerDrops && entity instanceof MobEntity mob) {
            if (mob.isAiDisabled() || mob.hasCustomName()) {
                // Skip mobs with disabled AI or custom names (likely from spawners)
                return false;
            }
        }

        // Check if tamed
        if (config.noTamedDrops && entity instanceof TameableEntity tameable) {
            if (tameable.isTamed()) {
                return false;
            }
        }

        // Check natural spawn only for rare mobs
        if (config.naturalSpawnOnlyForRare && category == MobCategory.RARE_UTILITY) {
            // Check if summoned (has NoAI or other summon indicators)
            if (entity instanceof MobEntity mob) {
                if (mob.isAiDisabled()) {
                    return false;
                }
            }
        }

        // Shulker special condition: must be directly hit by player
        if (entity.getType() == EntityType.SHULKER) {
            Entity attacker = damageSource.getAttacker();
            if (!(attacker instanceof PlayerEntity)) {
                return false;
            }
        }

        return true;
    }

    private static ItemStack createVariantCorrectEgg(SpawnEggItem spawnEgg, LivingEntity entity) {
        ItemStack eggStack = new ItemStack(spawnEgg);

        // Variant-correct eggs disabled for MC 1.21.11 compatibility
        // The writeNbt method is no longer available in this version
        // Spawn eggs will be generic (default variant) but still functional
        
        return eggStack;
    }

    private static void applyDurabilityTax(ServerPlayerEntity player, ItemStack weapon) {
        ModConfig config = ModConfig.get();
        if (config.extraDurabilityCost > 0 && weapon.isDamageable()) {
            weapon.damage(config.extraDurabilityCost, player, EquipmentSlot.MAINHAND);
        }
    }

    /**
     * Helper method to get enchantment level from an item stack
     */
    private static int getEnchantmentLevel(ServerWorld world, RegistryKey<Enchantment> enchantmentKey, ItemStack stack) {
        var enchantmentRegistry = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
        var enchantmentEntry = enchantmentRegistry.getOptional(enchantmentKey);
        if (enchantmentEntry.isPresent()) {
            return EnchantmentHelper.getLevel(enchantmentEntry.get(), stack);
        }
        return 0;
    }
}
