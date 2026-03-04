<!-- 
# Changelog

All notable changes to this project will be documented in this file.

Do not remove this comment and keep it at the top of the changelog.

Use: Added, Changed, Fixed, Updated Dependencies, Technical. Feel free to exclude or include them when needed. Use ## [Version] - [Supported Minecraft Versions (currently 1.21-1.21.1)] for changelog titles.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).
-->


## [1.2.0] - 1.21-1.21.1

### Added
- Loot table clearing - suppress vanilla mob drops (XP, leather, bones, arrows) when killing mobs with Silk Touch enchanted weapons
- Rare/Utility mobs now require Looting II+ with Silk Touch to drop eggs

### Fixed
- Null safety for entity eye position to prevent crashes on dead entities
- Corrected advancement IDs for Enderman, Blaze, and Guardian

### Improved
- Spawner mob detection using isAiDisabled() and custom name checks

## [1.1.0] - 1.21-1.21.1

### Added
- Support for 4 new mobs with spawn egg drops:
  - **Breeze** - Rare/Utility category (10% base chance)
  - **Camel** - Common Passive category (70% base chance)
  - **Creeper** - Neutral/Hostile category (30% base chance)
  - **Zombie Villager** - Neutral/Hostile category (30% base chance)

### Changed
- Updated drop rate percentages for clarity in documentation

## [1.0.0] - 1.21-1.21.1

### Added
- Initial release of Silk Harvest mod
- Mobs drop their corresponding spawn eggs when killed with a Silk Touch enchanted weapon
- Configurable drop chances based on mob category:
  - **Common Passive**: 15% base chance (cows, sheep, pigs, chickens, etc.)
  - **Neutral/Hostile**: 8% base chance (zombies, skeletons, spiders, etc.)
  - **Rare/Utility**: 3% base chance (iron golems, mooshrooms, etc.)
- Looting enchantment bonus: +2% per level to drop chance
- Baby mob penalty: -10% drop chance
- Advancement gating system for special mobs:
  - Endermen require "Free the End" advancement
  - Blazes require "Into Fire" advancement
  - Guardians require "The Deep End" advancement
- Special conditions:
  - Tamed animals don't drop eggs (configurable)
  - Spawner mobs don't drop eggs (configurable)
  - Shulkers must be directly killed by player
- Extra durability cost on weapon when egg drops (configurable)
- Configuration file support (`config/silk-harvest.json`)
- Support for all vanilla spawn eggs

### Technical
- Built for Minecraft 1.21.11
- Requires Fabric Loader 0.18.4+
- Requires Fabric API 0.141.3+1.21.11
- Server-side only mod (works on singleplayer and dedicated servers)
- Clients don't need the mod installed when connecting to a server

### Dependencies
| Dependency | Version |
|------------|---------|
| Minecraft | 1.21.11 |
| Fabric Loader | 0.18.4 |
| Fabric API | 0.141.3+1.21.11 |
| Java | 21+ |
