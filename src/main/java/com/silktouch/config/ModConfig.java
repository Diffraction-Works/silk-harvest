package com.silktouch.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silktouch.SilkTouchSpawnEggsMod;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "silktouch-spawn-eggs.json");

    // Default values
    public double commonPassiveChance = 0.70;
    public double neutralHostileChance = 0.30;
    public double rareUtilityChance = 0.10;
    public double bossChance = 0.0;
    public double babyPenalty = -0.20;
    public double lootingBonusPerLevel = 0.10;
    public int extraDurabilityCost = 10;
    public boolean naturalSpawnOnlyForRare = true;
    public boolean noSpawnerDrops = true;
    public boolean noTamedDrops = true;
    public boolean advancementGating = true;
    public boolean variantCorrectEggs = true;

    private static ModConfig INSTANCE;

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(reader, ModConfig.class);
                SilkTouchSpawnEggsMod.LOGGER.info("Loaded configuration from {}", CONFIG_FILE.getAbsolutePath());
            } catch (IOException e) {
                SilkTouchSpawnEggsMod.LOGGER.error("Failed to load config, using defaults", e);
                INSTANCE = new ModConfig();
            }
        } else {
            INSTANCE = new ModConfig();
            save();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(INSTANCE, writer);
            SilkTouchSpawnEggsMod.LOGGER.info("Saved configuration to {}", CONFIG_FILE.getAbsolutePath());
        } catch (IOException e) {
            SilkTouchSpawnEggsMod.LOGGER.error("Failed to save config", e);
        }
    }

    public static ModConfig get() {
        if (INSTANCE == null) {
            load();
        }
        return INSTANCE;
    }
}
