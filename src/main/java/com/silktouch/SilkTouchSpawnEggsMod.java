package com.silktouch;

import com.silktouch.config.ModConfig;
import com.silktouch.handler.MobDeathHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SilkTouchSpawnEggsMod implements ModInitializer {
    public static final String MOD_ID = "silktouch-spawn-eggs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Silk Touch Spawn Eggs Mod");

        // Load configuration
        ModConfig.load();

        // Register entity death event
        ServerEntityEvents.ENTITY_UNLOAD.register((entity, world) -> {
            if (entity instanceof LivingEntity livingEntity && livingEntity.isDead()) {
                DamageSource lastDamageSource = livingEntity.getRecentDamageSource();
                if (lastDamageSource != null) {
                    MobDeathHandler.handleMobDeath(livingEntity, lastDamageSource, world);
                }
            }
        });

        LOGGER.info("Silk Touch Spawn Eggs Mod initialized successfully");
    }
}
