# Silk Harvest

A **Fabric mod for Minecraft 1.21.1** that adds a balanced system where mobs can drop their **existing vanilla spawn eggs** when killed by a player using an item enchanted with **Silk Touch**.

## Features

- **No new assets** - Only uses existing vanilla spawn eggs
- **Tiered drop chances** based on mob category
- **Configurable** via JSON config file
- **Advancement gating** for rare mobs
- **Variant-correct eggs** (horses, cats, tropical fish, etc.)
- **Durability tax** on Silk Touch items when eggs drop

## Core Mechanic

When any mob dies:
1. Check if the killing blow was dealt by a **player**
2. Check if the player's **main-hand item** has **Silk Touch**
3. If both are true, roll a chance to drop that mob's **vanilla spawn egg**
4. If the mob has no vanilla spawn egg, do nothing

## Drop Chances

### Common Passive Mobs (70% default)
- Cow, Pig, Sheep, Chicken, Rabbit, Cod, Salmon, Tropical Fish
- **Baby penalty:** -20%
- **Looting bonus:** +10% per level

### Neutral/Basic Hostile Mobs (30% default)
- Zombie, Skeleton, Spider, Enderman, Drowned, Stray, Husk, Wolf, Bee
- **No drops** from spawner-spawned mobs
- **No drops** from tamed mobs
- **Looting bonus:** +10% per level (optional)

### Rare/Utility Mobs (10% default)
- Slime, Witch, Guardian, Blaze, Ghast, Endermite, Shulker
- **Requires:** Silk Touch + Looting II+
- **Must be** natural spawn (no summons)
- **Shulkers:** Only drop if directly hit by player

### Bosses (0%)
- Ender Dragon, Wither
- Excluded due to balance

## Additional Balancing

- **Durability Tax:** +10 durability damage to Silk Touch item when egg drops
- **Advancement Gating:**
  - Enderman eggs: Requires "Free the End"
  - Blaze eggs: Requires "Into Fire"
  - Guardian eggs: Requires "The Deep End"
- **Spawner Protection:** Mobs from spawners never drop eggs
- **Variant-Correct Eggs:** Eggs match mob's NBT variant

## Configuration

Config file: `config/silk-harvest.json`

```json
{
  "commonPassiveChance": 0.70,
  "neutralHostileChance": 0.30,
  "rareUtilityChance": 0.10,
  "bossChance": 0.0,
  "babyPenalty": -0.20,
  "lootingBonusPerLevel": 0.10,
  "extraDurabilityCost": 10,
  "naturalSpawnOnlyForRare": true,
  "noSpawnerDrops": true,
  "noTamedDrops": true,
  "advancementGating": true,
  "variantCorrectEggs": true
}
```

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.21.1
2. Download and install [Fabric API](https://modrinth.com/mod/fabric-api)
3. Place this mod's `.jar` file in your `mods` folder

## Building from Source

```bash
./gradlew build
```

The built jar will be in `build/libs/`.

## License

All Rights Reserved
