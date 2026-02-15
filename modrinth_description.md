
### **Silk Harvest**

In vanilla Minecraft, spawn eggs are creative-only items. This mod allows you to obtain them in survival.

### Core Mechanic

When a mob is killed by a player using a **Silk Touch enchanted item**, there's a chance it will drop its **vanilla spawn egg**. The drop rate depends on the mob type among other factors.

### Features

#### Tiered Drop Chances

| Category | Mobs | Base Chance | Special Requirements |
|----------|------|--------------|---------------------|
| **Common Passive** | Cow, Pig, Sheep, Chicken, Rabbit, Cod, Salmon, Tropical Fish | 70% | Baby penalty: -20% |
| **Neutral/Hostile** | Zombie, Skeleton, Spider, Enderman, Drowned, Stray, Husk, Wolf, Bee | 30% | No spawner/tamed drops |
| **Rare/Utility** | Slime, Witch, Guardian, Blaze, Ghast, Endermite, Shulker | 10% | Requires Looting II+ |
| **Bosses** | Ender Dragon, Wither | 0% | Excluded for balance |

#### Balancing Mechanics

- **Durability Tax**: Your Silk Touch tool takes +10 durability damage when an egg drops
- **Looting Bonus**: Each level of Looting adds +10% to the drop chance
- **Spawner Protection**: Mobs from spawners never drop eggs
- **Tamed Protection**: Your pets are safe - tamed mobs won't drop eggs
- **Advancement Gating**: Certain rare mobs require progression advancements:
  - **Enderman** eggs require "Free the End"
  - **Blaze** eggs require "Into Fire"  
  - **Guardian** eggs require "The Deep End"

### Configuration

Everything is customizable via `config/silktouch-spawn-eggs.json`:

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

### Usage Tips

1. **Combine Enchantments**: Silk Touch + Looting III on the same tool maximizes your egg collection
3. **Farm Smart**: Passive mobs are easiest to farm for eggs with their 70% base rate
4. **Progress First**: Unlock the advancements above before hunting rare mobs for their eggs

### Compatibility

- **Minecraft**: 1.21.1
- **Loader**: Fabric
- **Requirements**: Fabric API, Java 21+
- **No new assets**: Uses only vanilla spawn eggs - no resource packs needed

### Installation

1. Install Fabric Loader for Minecraft 1.21.1
2. Download and install Fabric API
3. Place the mod jar in your `mods` folder
4. Configure to your liking in `config/silktouch-spawn-eggs.json`