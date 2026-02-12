package com.silktouch.handler;

import com.silktouch.config.ModConfig;
import com.silktouch.util.MobCategory;
import com.silktouch.util.SpawnEggRegistry;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

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

        // Drop the egg
        entity.dropStack(eggStack);

        // Apply durability tax
        applyDurabilityTax(player, mainHandItem);
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
            AdvancementEntry advancement = player.getServer().getAdvancementLoader().get(requiredAdvancement);
            if (advancement != null) {
                return player.getAdvancementTracker().getProgress(advancement).isDone();
            }
        }

        return true;
    }

    private static boolean checkSpecialConditions(LivingEntity entity, MobCategory category, DamageSource damageSource) {
        ModConfig config = ModConfig.get();

        // Check if from spawner
        if (config.noSpawnerDrops && entity instanceof MobEntity mob) {
            if (mob.isAiDisabled() || mob.hasCustomName()) {
                // Additional checks for spawner-spawned mobs
                NbtCompound nbt = new NbtCompound();
                mob.writeNbt(nbt);
                if (nbt.contains("FromSpawner") && nbt.getBoolean("FromSpawner")) {
                    return false;
                }
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

        if (!ModConfig.get().variantCorrectEggs) {
            return eggStack;
        }

        // Copy relevant NBT data for variant-correct eggs
        NbtCompound entityNbt = new NbtCompound();
        entity.writeNbt(entityNbt);

        NbtCompound eggNbt = new NbtCompound();
        NbtCompound entityTag = new NbtCompound();

        // Copy variant data based on entity type
        if (entity.getType() == EntityType.HORSE) {
            if (entityNbt.contains("Variant")) {
                entityTag.putInt("Variant", entityNbt.getInt("Variant"));
            }
        } else if (entity.getType() == EntityType.CAT) {
            if (entityNbt.contains("variant")) {
                entityTag.putString("variant", entityNbt.getString("variant"));
            }
        } else if (entity.getType() == EntityType.TROPICAL_FISH) {
            if (entityNbt.contains("Variant")) {
                entityTag.putInt("Variant", entityNbt.getInt("Variant"));
            }
        } else if (entity.getType() == EntityType.RABBIT) {
            if (entityNbt.contains("RabbitType")) {
                entityTag.putInt("RabbitType", entityNbt.getInt("RabbitType"));
            }
        } else if (entity.getType() == EntityType.PARROT) {
            if (entityNbt.contains("Variant")) {
                entityTag.putInt("Variant", entityNbt.getInt("Variant"));
            }
        } else if (entity.getType() == EntityType.LLAMA) {
            if (entityNbt.contains("Variant")) {
                entityTag.putInt("Variant", entityNbt.getInt("Variant"));
            }
        } else if (entity.getType() == EntityType.FOX) {
            if (entityNbt.contains("Type")) {
                entityTag.putString("Type", entityNbt.getString("Type"));
            }
        } else if (entity.getType() == EntityType.MOOSHROOM) {
            if (entityNbt.contains("Type")) {
                entityTag.putString("Type", entityNbt.getString("Type"));
            }
        }

        if (!entityTag.isEmpty()) {
            eggNbt.put("EntityTag", entityTag);
            eggStack.set(DataComponentTypes.ENTITY_DATA, NbtComponent.of(eggNbt));
        }

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
        var enchantmentRegistry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT);
        var enchantmentEntry = enchantmentRegistry.getEntry(enchantmentKey);
        if (enchantmentEntry.isPresent()) {
            return EnchantmentHelper.getLevel(enchantmentEntry.get(), stack);
        }
        return 0;
    }
}
