---
name: Bug report
about: Report an issue with Silk Harvest mod
title: ''
labels: bug
assignees: ''
---

## Description
A clear and concise description of what the bug is.

## Environment

### Minecraft
- **Minecraft Version:** (e.g., 1.21.1)
- **Mod Version:** (e.g., 1.0.0)

### Mod Loader
- **Fabric Loader Version:** (e.g., 0.16.0)
- **Fabric API Version:** (e.g., 0.102.0)

### Other Mods
Please list any other mods you have installed, especially those that modify:
- Mob drops or loot tables
- Enchantments
- Spawn eggs
- Entity behavior

```
Paste your mod list here, or attach a screenshot of your mods folder
```

## Steps to Reproduce
1. Go to '...'
2. Equip item with Silk Touch enchantment '...'
3. Kill mob '...'
4. Observe the result

**Expected behavior:** A clear description of what you expected to happen.

**Actual behavior:** A clear description of what actually happened.

## Mob Details
If the issue involves a specific mob, please provide:

- **Mob Type:** (e.g., Zombie, Enderman, Horse, etc.)
- **Mob Category:** (Common Passive / Neutral-Hostile / Rare-Utility / Boss)
- **Was the mob a baby?** (Yes/No/N/A)
- **Was the mob tamed?** (Yes/No/N/A)
- **Was the mob from a spawner?** (Yes/No/Unknown)
- **Was the mob a natural spawn?** (Yes/No/Unknown)

## Configuration
Please share your current config settings if you've modified them from defaults.

Config file location: `config/silktouch-spawn-eggs.json`

```json
Paste your config file contents here
```

**Note:** If you haven't modified the config, the defaults are:
- commonPassiveChance: 0.70
- neutralHostileChance: 0.30
- rareUtilityChance: 0.10
- bossChance: 0.0
- babyPenalty: -0.20
- lootingBonusPerLevel: 0.10
- extraDurabilityCost: 10
- naturalSpawnOnlyForRare: true
- noSpawnerDrops: true
- noTamedDrops: true
- advancementGating: true
- variantCorrectEggs: true

## Item Used
- **Item:** (e.g., Diamond Pickaxe, Netherite Sword, etc.)
- **Enchantments:** (e.g., Silk Touch I, Looting III, etc.)
- **Did the item take extra durability damage?** (Yes/No)

## Advancements
If the issue involves a rare mob (Enderman, Blaze, Guardian), please check:
- [ ] "Free the End" advancement completed (for Enderman eggs)
- [ ] "Into Fire" advancement completed (for Blaze eggs)
- [ ] "The Deep End" advancement completed (for Guardian eggs)

## Logs
Please check the following locations for relevant logs:

### Crash Report
If the game crashed, paste the crash report here or attach the file.
Crash reports are located in: `.minecraft/crash-reports/`

```
Paste crash report here (if applicable)
```

### Latest Log
Even if there was no crash, the latest.log may contain useful error messages.
Log location: `.minecraft/logs/latest.log`

Look for lines containing `[silktouch-spawn-eggs]` and paste relevant sections:

```
Paste relevant log entries here
```

## Screenshots
If applicable, add screenshots to help explain your problem.

## Additional Context
Add any other context about the problem here.

- Does this happen consistently or intermittently?
- Did this work in a previous version of the mod?
- Have you tried with only this mod and Fabric API installed?
