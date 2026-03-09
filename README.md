# HBM Doors — Fabric 1.20.1

A **Fabric 1.20.1** standalone mod porting the elaborate door system from [HBM-Modernized](https://github.com/Raptor324/HBM-Modernized) (Forge).

**Mod ID:** `hbm_doors`  
**Minecraft Version:** 1.20.1 (Fabric)  
**License:** GPL-3.0

---

## 🚪 Features

### 13 Custom Animated Doors (single-block, rendered via BlockEntityRenderer)
| Door | Visual Size | Animation |
|---|---|---|
| `large_vehicle_door` | 6×5 | Door panel slides upward |
| `round_airlock_door` | 3×3 | Round segments slide apart |
| `transition_seal` | 25×23 | Massive panels retract upward |
| `fire_door` | 3×2 | Two-panel slide up |
| `sliding_blast_door` | 6×3 | Heavy panels slide sideways |
| `sliding_seal_door` | 1×2 | Simple slide along Z |
| `secure_access_door` | 4×4 | Multi-panel lifts up |
| `qe_sliding_door` | 1×2 | Sci-fi slide |
| `qe_containment_door` | 2×2 | Containment panel lifts |
| `water_door` | 2×2 | Rotary wheel mechanism |
| `silo_hatch` | 4×4 | Horizontal hatch slides |
| `silo_hatch_large` | 6×6 | Large horizontal hatch |
| `vault_door` | varies | Heavy vault door swings open |

### 3 Simple Vanilla-style Doors
- `metal_door`
- `door_bunker`
- `door_office`

---

## 🔧 Architecture

Each custom door is a **single block** (no multiblock system). The full-size model is rendered via a `BlockEntityRenderer` — the door visually extends beyond its 1×1×1 placement.

- **Block**: `CustomDoorBlock` — horizontal facing, right-click toggle, redstone support
- **Block Entity**: `DoorBlockEntity` — 4-state machine (CLOSED → OPENING → OPEN → CLOSING)
- **Animation**: per-part translation/rotation via `DoorType` enum, ported from `DoorDecl.java`
- **Rendering**: custom OBJ parser + BER rendering with animated transforms
- **Networking**: C2S toggle packet with server-side distance validation

---

## 📦 Building

Requires:
- Java 17
- Internet access to download Fabric dependencies from https://maven.fabricmc.net/

```bash
./gradlew build
```

The built JAR will be in `build/libs/`.

---

## 📜 Credits & License

This mod is a **Fabric port** of the door system from:
- **[HBM-Modernized](https://github.com/Raptor324/HBM-Modernized)** by Raptor324 — Forge 1.20.1 version
- **[HBM's Nuclear Tech Mod](https://github.com/hbm-minecraft/hbm-ntm)** by The Bobcat — original 1.7.10 mod

All original assets (OBJ models, textures, sounds) are used under GPL-3.0.

This project is licensed under **GPL-3.0**. See [LICENSE](LICENSE) for details.
