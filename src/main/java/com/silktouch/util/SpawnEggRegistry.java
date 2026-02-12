package com.silktouch.util;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;

import java.util.HashMap;
import java.util.Map;

public class SpawnEggRegistry {
    private static final Map<EntityType<?>, SpawnEggItem> SPAWN_EGG_MAP = new HashMap<>();

    static {
        // Passive mobs
        register(EntityType.COW, Items.COW_SPAWN_EGG);
        register(EntityType.PIG, Items.PIG_SPAWN_EGG);
        register(EntityType.SHEEP, Items.SHEEP_SPAWN_EGG);
        register(EntityType.CHICKEN, Items.CHICKEN_SPAWN_EGG);
        register(EntityType.RABBIT, Items.RABBIT_SPAWN_EGG);
        register(EntityType.COD, Items.COD_SPAWN_EGG);
        register(EntityType.SALMON, Items.SALMON_SPAWN_EGG);
        register(EntityType.TROPICAL_FISH, Items.TROPICAL_FISH_SPAWN_EGG);
        register(EntityType.PUFFERFISH, Items.PUFFERFISH_SPAWN_EGG);
        register(EntityType.SQUID, Items.SQUID_SPAWN_EGG);
        register(EntityType.GLOW_SQUID, Items.GLOW_SQUID_SPAWN_EGG);
        register(EntityType.BAT, Items.BAT_SPAWN_EGG);
        register(EntityType.TURTLE, Items.TURTLE_SPAWN_EGG);
        register(EntityType.DOLPHIN, Items.DOLPHIN_SPAWN_EGG);
        register(EntityType.POLAR_BEAR, Items.POLAR_BEAR_SPAWN_EGG);
        register(EntityType.OCELOT, Items.OCELOT_SPAWN_EGG);
        register(EntityType.CAT, Items.CAT_SPAWN_EGG);
        register(EntityType.WOLF, Items.WOLF_SPAWN_EGG);
        register(EntityType.PARROT, Items.PARROT_SPAWN_EGG);
        register(EntityType.FOX, Items.FOX_SPAWN_EGG);
        register(EntityType.PANDA, Items.PANDA_SPAWN_EGG);
        register(EntityType.MOOSHROOM, Items.MOOSHROOM_SPAWN_EGG);
        register(EntityType.GOAT, Items.GOAT_SPAWN_EGG);
        register(EntityType.AXOLOTL, Items.AXOLOTL_SPAWN_EGG);
        register(EntityType.BEE, Items.BEE_SPAWN_EGG);
        register(EntityType.STRIDER, Items.STRIDER_SPAWN_EGG);
        register(EntityType.HOGLIN, Items.HOGLIN_SPAWN_EGG);
        register(EntityType.PIGLIN, Items.PIGLIN_SPAWN_EGG);
        register(EntityType.SNIFFER, Items.SNIFFER_SPAWN_EGG);
        register(EntityType.ARMADILLO, Items.ARMADILLO_SPAWN_EGG);

        // Hostile mobs
        register(EntityType.ZOMBIE, Items.ZOMBIE_SPAWN_EGG);
        register(EntityType.SKELETON, Items.SKELETON_SPAWN_EGG);
        register(EntityType.SPIDER, Items.SPIDER_SPAWN_EGG);
        register(EntityType.CAVE_SPIDER, Items.CAVE_SPIDER_SPAWN_EGG);
        register(EntityType.ENDERMAN, Items.ENDERMAN_SPAWN_EGG);
        register(EntityType.DROWNED, Items.DROWNED_SPAWN_EGG);
        register(EntityType.STRAY, Items.STRAY_SPAWN_EGG);
        register(EntityType.HUSK, Items.HUSK_SPAWN_EGG);
        register(EntityType.PHANTOM, Items.PHANTOM_SPAWN_EGG);
        register(EntityType.SILVERFISH, Items.SILVERFISH_SPAWN_EGG);
        register(EntityType.ENDERMITE, Items.ENDERMITE_SPAWN_EGG);
        register(EntityType.PILLAGER, Items.PILLAGER_SPAWN_EGG);
        register(EntityType.VINDICATOR, Items.VINDICATOR_SPAWN_EGG);
        register(EntityType.EVOKER, Items.EVOKER_SPAWN_EGG);
        // ILLUSIONER spawn egg not available in this version

        register(EntityType.WITCH, Items.WITCH_SPAWN_EGG);
        register(EntityType.VEX, Items.VEX_SPAWN_EGG);
        register(EntityType.PIGLIN_BRUTE, Items.PIGLIN_BRUTE_SPAWN_EGG);
        register(EntityType.ZOMBIFIED_PIGLIN, Items.ZOMBIFIED_PIGLIN_SPAWN_EGG);
        register(EntityType.ZOGLIN, Items.ZOGLIN_SPAWN_EGG);
        register(EntityType.BOGGED, Items.BOGGED_SPAWN_EGG);
        // CREAKING spawn egg not available in this version


        // Rare/utility mobs
        register(EntityType.SLIME, Items.SLIME_SPAWN_EGG);
        register(EntityType.MAGMA_CUBE, Items.MAGMA_CUBE_SPAWN_EGG);
        register(EntityType.GUARDIAN, Items.GUARDIAN_SPAWN_EGG);
        register(EntityType.ELDER_GUARDIAN, Items.ELDER_GUARDIAN_SPAWN_EGG);
        register(EntityType.BLAZE, Items.BLAZE_SPAWN_EGG);
        register(EntityType.GHAST, Items.GHAST_SPAWN_EGG);
        register(EntityType.SHULKER, Items.SHULKER_SPAWN_EGG);
        register(EntityType.WITHER_SKELETON, Items.WITHER_SKELETON_SPAWN_EGG);
        register(EntityType.RAVAGER, Items.RAVAGER_SPAWN_EGG);
        register(EntityType.WARDEN, Items.WARDEN_SPAWN_EGG);

        // Villagers and traders
        register(EntityType.VILLAGER, Items.VILLAGER_SPAWN_EGG);
        register(EntityType.WANDERING_TRADER, Items.WANDERING_TRADER_SPAWN_EGG);
        register(EntityType.TRADER_LLAMA, Items.TRADER_LLAMA_SPAWN_EGG);
        register(EntityType.LLAMA, Items.LLAMA_SPAWN_EGG);

        // Golems
        register(EntityType.IRON_GOLEM, Items.IRON_GOLEM_SPAWN_EGG);
        register(EntityType.SNOW_GOLEM, Items.SNOW_GOLEM_SPAWN_EGG);

        // Bosses (registered but won't drop due to 0% chance)
        register(EntityType.ENDER_DRAGON, Items.ENDER_DRAGON_SPAWN_EGG);
        register(EntityType.WITHER, Items.WITHER_SPAWN_EGG);

        // Other
        register(EntityType.ALLAY, Items.ALLAY_SPAWN_EGG);
        register(EntityType.CAMEL, Items.CAMEL_SPAWN_EGG);
        register(EntityType.FROG, Items.FROG_SPAWN_EGG);
        register(EntityType.TADPOLE, Items.TADPOLE_SPAWN_EGG);
        register(EntityType.MULE, Items.MULE_SPAWN_EGG);
        register(EntityType.DONKEY, Items.DONKEY_SPAWN_EGG);
        register(EntityType.HORSE, Items.HORSE_SPAWN_EGG);
        register(EntityType.SKELETON_HORSE, Items.SKELETON_HORSE_SPAWN_EGG);
        register(EntityType.ZOMBIE_HORSE, Items.ZOMBIE_HORSE_SPAWN_EGG);
    }

    private static void register(EntityType<?> entityType, Item spawnEggItem) {
        if (spawnEggItem instanceof SpawnEggItem) {
            SPAWN_EGG_MAP.put(entityType, (SpawnEggItem) spawnEggItem);
        }
    }

    public static SpawnEggItem getSpawnEggForEntity(EntityType<?> entityType) {
        return SPAWN_EGG_MAP.get(entityType);
    }

    public static boolean hasSpawnEgg(EntityType<?> entityType) {
        return SPAWN_EGG_MAP.containsKey(entityType);
    }
}
