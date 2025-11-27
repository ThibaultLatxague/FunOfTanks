# 🎮 FunOfTanks

_A **fun**, **mechanic** and **competitive** multiplayer tank game_

[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## 📋 Table of Contents
- [About](#about)
- [Features](#features)
- [Installation](#installation)
- [How to Play](#how-to-play)
- [Game Modes](#game-modes)
- [Controls](#controls)
- [Screenshots](#screenshots)
- [Contributing](#contributing)

## 🎯 About

FunOfTanks is a dynamic multiplayer tank battle game where strategy meets action. Battle against up to 7 other players in local network matches, utilizing various skills, building mechanics, and tactical gameplay to dominate the battlefield!

## ✨ Features

- 🌐 **Local Multiplayer** - Host or join games with up to 8 players on the same network
- 🎨 **Multiple Game Modes** - Including Domination mode with strategic objectives
- 🏗️ **Building Mechanics** - Construct defenses and tactical structures during battle
- ⚡ **Skill System** - Use unique abilities to outplay your opponents
- 🎮 **Customizable Controls** - Rebind all inputs to your preference in the Settings menu
- 📊 **Real-time Leaderboard** - Track player performance during matches

## 🚀 Installation

### Prerequisites

Make sure you have the Java Development Kit (JDK) installed on your system.

**Test your installation:**
```bash
java --version
javac --version
jar --version
```

**If not installed:**

- **Linux:**
  ```bash
  sudo apt install default-jre default-jdk
  ```

- **Windows:**
  Download and install from [Oracle's official website](https://www.oracle.com/java/technologies/downloads/#jdk23-windows)
  
  ⚠️ **Important:** If you get `jar: command not found`, add the JDK to your PATH:
  ```bash
  export PATH="/c/Program Files/Java/jdk-XX/bin:$PATH"
  ```
  (Replace `XX` with your JDK version number)

### Clone the Repository

**SSH:**
```bash
git clone git@github.com:zaofromage/FunOfTanks.git
```

**HTTPS:**
```bash
git clone https://github.com/zaofromage/FunOfTanks.git
```

### Compile and Run

**Linux / macOS:**
```bash
cd FunOfTanks
./run.sh
```

**Windows (Git Bash):**
```bash
cd FunOfTanks
./run.sh
```

**Windows (Command Prompt / PowerShell):**
```cmd
cd FunOfTanks
javac -d bin src/**/*.java
jar cfm FunOfTanks.jar manifest.txt -C bin . -C res .
java -jar FunOfTanks.jar
```

## 🎮 How to Play

1. **Launch the game** using the instructions above
2. **Choose your mode:**
   - **Host Game** - Create a new game session for others to join
   - **Join Game** - Connect to an existing game on your local network
3. **Customize your settings** - Adjust controls and preferences in the Settings menu
4. **Battle!** - Use strategy, skills, and teamwork to dominate the battlefield

![Menu Screenshot](images/menu.png)

## 🏆 Game Modes

### Domination
Capture and hold strategic points on the map to accumulate points. The first team to reach the score limit wins!

![Domination Mode](images/domination.png)

_More game modes coming soon!_

## ⌨️ Controls

> 💡 **Tip:** All controls can be customized in the **Settings** menu

| Action  | Default Input |
|---------|:-------------:|
| **Move** | `Z Q S D` / `W A S D` |
| **Dash** | `Space Bar` |
| **Shoot** | `Left Click` (hold) |
| **Build Mode** | `Right Click` (hold) |
| **Place Building** | `Left Click` (in build mode) |
| **Skill 1** | `E` |
| **Skill 2** | `R` |
| **Skill 3** | `A` |
| **Leaderboard** | `Tab` |

## 📸 Screenshots

### Gameplay
Experience intense tank battles with up to 8 players!

![Gameplay Screenshot](images/gameplay.png)

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Report bugs by opening an issue
- Suggest new features
- Submit pull requests

## 📝 License

This project is open source. Please check the repository for license details.

## 👥 Authors

- Original Repository: [@zaofromage](https://github.com/zaofromage)
- Current Repository: [@ThibaultLatxague](https://github.com/ThibaultLatxague)

---

**Enjoy the game and may the best tank win!** 🎖️