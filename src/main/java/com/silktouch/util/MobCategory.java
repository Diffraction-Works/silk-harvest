package com.silktouch.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

public enum MobCategory {
    COMMON_PASSIVE,
    NEUTRAL_HOSTILE,
    RARE_UTILITY,
    BOSS;

    public static MobCategory getCategory(LivingEntity entity) {
        EntityType<?> type = entity.getType();

        // Common passive mobs
        if (isCommonPassive(type)) {
            return COMMON_PASSIVE;
        }

        // Neutral/basic hostile mobs
        if (isNeutralHostile(type)) {
            return NEUTRAL_HOSTILE;
        }

        // Rare/utility mobs
        if (isRareUtility(type)) {
            return RARE_UTILITY;
        }

        // Bosses and semi-bosses
        if (isBoss(type)) {
            return BOSS;
        }

        // Default to neutral hostile for other hostile mobs
        if (entity instanceof net.minecraft.entity.mob.HostileEntity) {
            return NEUTRAL_HOSTILE;
        }

        // Default to common passive for other passive mobs
        return COMMON_PASSIVE;
    }

    private static boolean isCommonPassive(EntityType<?> type) {
        return type == EntityType.COW ||
               type == EntityType.PIG ||
               type == EntityType.SHEEP ||
               type == EntityType.CHICKEN ||
               type == EntityType.RABBIT ||
               type == EntityType.COD ||
               type == EntityType.SALMON ||
               type == EntityType.TROPICAL_FISH ||
               type == EntityType.PUFFERFISH ||
               type == EntityType.SQUID ||
               type == EntityType.GLOW_SQUID ||
               type == EntityType.BAT ||
               type == EntityType.TURTLE ||
               type == EntityType.DOLPHIN ||
               type == EntityType.POLAR_BEAR ||
               type == EntityType.OCELOT ||
               type == EntityType.CAT ||
               type == EntityType.WOLF ||
               type == EntityType.PARROT ||
               type == EntityType.FOX ||
               type == EntityType.PANDA ||
               type == EntityType.MOOSHROOM ||
               type == EntityType.GOAT ||
               type == EntityType.AXOLOTL ||
               type == EntityType.BEE ||
               type == EntityType.STRIDER ||
               type == EntityType.HOGLIN ||
               type == EntityType.PIGLIN ||
               type == EntityType.SNIFFER ||
               type == EntityType.ARMADILLO ||
               type == EntityType.CAMEL;
    }

    private static boolean isNeutralHostile(EntityType<?> type) {
        return type == EntityType.ZOMBIE ||
               type == EntityType.SKELETON ||
               type == EntityType.SPIDER ||
               type == EntityType.CAVE_SPIDER ||
               type == EntityType.ENDERMAN ||
               type == EntityType.DROWNED ||
               type == EntityType.STRAY ||
               type == EntityType.HUSK ||
               type == EntityType.PHANTOM ||
               type == EntityType.SILVERFISH ||
               type == EntityType.ENDERMITE ||
               type == EntityType.PILLAGER ||
               type == EntityType.VINDICATOR ||
               type == EntityType.EVOKER ||
               type == EntityType.ILLUSIONER ||
               type == EntityType.WITCH ||
               type == EntityType.VEX ||
               type == EntityType.PIGLIN_BRUTE ||
               type == EntityType.ZOMBIFIED_PIGLIN ||
               type == EntityType.ZOGLIN ||
               type == EntityType.BOGGED ||
               type == EntityType.CREEPER ||
               type == EntityType.ZOMBIE_VILLAGER;

    }

    private static boolean isRareUtility(EntityType<?> type) {
        return type == EntityType.SLIME ||
               type == EntityType.MAGMA_CUBE ||
               type == EntityType.WITCH ||
               type == EntityType.GUARDIAN ||
               type == EntityType.ELDER_GUARDIAN ||
               type == EntityType.BLAZE ||
               type == EntityType.GHAST ||
               type == EntityType.ENDERMITE ||
               type == EntityType.SHULKER ||
               type == EntityType.WITHER_SKELETON ||
               type == EntityType.HOGLIN ||
               type == EntityType.PIGLIN ||
               type == EntityType.RAVAGER ||
               type == EntityType.WARDEN ||
               type == EntityType.BREEZE;
    }

    private static boolean isBoss(EntityType<?> type) {
        return type == EntityType.ENDER_DRAGON ||
               type == EntityType.WITHER;
    }
}
