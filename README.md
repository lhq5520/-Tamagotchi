# Tamagotchi Game (GUI)

This project is a hybrid implementation of the classic Tamagotchi game, offering both Command Line Interface (CLI) and Graphical User Interface (GUI) modes. Designed to provide an interactive experience, the game combines strategic management of your virtual pet with user-friendly interfaces.

## Project Structure

### `src/`
Contains the source code for the core implementation of the game logic and functionality.

- Includes a `tamagochi` directory, which houses the main logic for managing your Tamagotchi.

### `res/`
Contains resources for the game, including animations, sprites, and documentation.

- **Piskel Files**: `.piskel` files for sprite or animation designs (e.g., `happy.piskel`, `grumpy.piskel`).
- **Documentation**:
  - `tamagochi_updated UML.pdf`: UML diagram detailing the system architecture.
  - `Tamagotchi_Model_Original.pdf`: Original model design specifications.
- **Executable**:
  - `Tamagotchi.jar`: Java executable file for running the GUI version of the game.
- **Images**:
  - Likely stored in the `img` directory for GUI visuals.

### `test/`
Contains test cases to ensure the reliability of the game's logic and interactions.

- Mirrors the structure of the `src` directory for testing components within `tamagochi`.

### Additional Files
- **`.gitignore`**: Specifies files and directories excluded from version control.
- **`MANIFEST.MF`**: Java project manifest file, detailing configurations like the main class.

## Features
- **Dual Interface**: Play the game via GUI for a modern experience.
- **Dynamic Visuals**: Includes animations and sprites for a more engaging interaction.
- **Comprehensive Testing**: Ensures the game operates smoothly across various scenarios.

## Requirements
- Java 8 or higher
- A Java-compatible IDE (e.g., IntelliJ IDEA, Eclipse) for development
- Terminal for CLI mode
- Swing or JavaFX libraries for GUI mode

## Setup and Usage

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo/tamagotchi.git
   cd tamagotchi

2. **Build the project:**
    ```bash
    javac -d out src/**/*.java

3. **Run the game:**
    ```bash
    java -jar res/Tamagotchi.jar

## Testing
    ```java
    javac -d out -cp src:test test/**/*.java
    java -cp out org.junit.runner.JUnitCore tamagochi.PetModelImplTest.java

## Contributions
    Contributions are welcome! Please fork the repository and submit a pull request.

## License
    
    This project is licensed under the MIT License.






