# SimpleVisuals - Documentation

**SimpleVisuals** is a client-side Fabric mod for Minecraft 1.21+ that adds various visual improvements, HUD elements, and informative tooltips. It is designed to be highly configurable and non-intrusive.

## 📥 Installation & Dependencies

To use SimpleVisuals, install the following:
1.  **Fabric Loader**
2.  **Fabric API**
3.  **Cloth Config API** (Required for configuration)
4.  **Mod Menu** (Recommended for in-game settings)

## 📖 Features & Configuration

All features can be configured via `cloth-config` or the `config/simplevisuals.json` file.

### 🖥️ HUD & Overlay
| Feature | Description | Config Key |
| :--- | :--- | :--- |
| **Speed Lines** | Displays speed lines at screen edges when moving fast. Customizable color, alpha, amount, and threshold. | `visuals.speedLines.*` |
| **Pickup Notifier** | Displays picked-up items/XP on screen. Configurable position (offsets), duration, scale, and style (Vanilla/Rarity). | `visuals.pickupNotifier.*` |
| **Elytra Pitch HUD** | Visual lines indicating optimal flight angles. Default targets are 40° (up) and -40° (down). | `visuals.enableElytraPitchHelper` |
| **Status Effect Bars** | Renders a durability-like bar under status effect icons to show remaining time. | `visuals.enableStatusEffectBars` |
| **Player Locator** | A bossbar-style compass at the top of the screen pointing to other players. | `visuals.enablePlayerLocator` |

### 💬 Chat & Interaction
| Feature | Description | Config Key |
| :--- | :--- | :--- |
| **Chat Heads** | Renders the sender's player head next to chat messages. | `visuals.enableChatHeads` |
| **Death Messages** | Optionally shows player heads in death messages in chat. | `visuals.chatHeadsInDeathMessages` |

### ℹ️ Tooltips
| Feature | Description | Config Key |
| :--- | :--- | :--- |
| **Map Tooltips** | Shows center coordinates and dimension ID when hovering over a Filled Map. | `visuals.enableMapTooltips` |
| **Held Item Info** | Shows durability and enchantments of the currently held item above the hotbar. | `visuals.heldItemTooltips.*` |

## 💻 Commands
Commands allow for runtime configuration changes.

* `/simplevisuals config ...`
    * Allows getting or setting config values without opening the GUI.
    * Example: `/simplevisuals config visuals speedLines enableSpeedLines true`

## 🏗️ Building from Source

1.  Clone the repository.
2.  Navigate to the project directory.
3.  Run the build command:
    * Windows: `gradlew build`
    * Linux/macOS: `./gradlew build`
4.  The compiled `.jar` file will be in `build/libs/`.

## ⚖️ License
This project is licensed under the **CC0 1.0 Universal** license. You are free to use, modify, and distribute this software without restriction.