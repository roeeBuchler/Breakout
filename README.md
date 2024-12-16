# Brick Breaker Game

## Overview
This project is a **Brick Breaker Game** implemented in Java, showcasing object-oriented design principles and advanced game mechanics. The game features a paddle, multiple balls, and colorful blocks to break, offering a fun and interactive gaming experience. It demonstrates my proficiency in Java, software design patterns, and game development concepts.

---

## Features

### Gameplay Mechanics
- **Blocks**: Each block is destructible when hit by a ball.
- **Balls**: Multiple balls move across the screen, bouncing off surfaces and blocks.
- **Paddle**: A user-controlled paddle moves left and right to bounce the balls back.
- **Scoring**: Players earn points by breaking blocks.

### Advanced Features
- **Collision Detection**: Accurate detection and response for ball and block collisions.
- **Game Environment**: Manages all collidable objects and ensures seamless interactions.
- **Randomized Gameplay**: Blocks and ball colors are randomized for a fresh experience every time.
- **Game Over Conditions**: The game ends when all blocks are destroyed or the player runs out of balls.

---

## Code Structure

### Key Components
- **Game.java**: The main class that initializes and runs the game loop.
- **Ball.java**: Handles the ball's behavior, including movement and collision responses.
- **Block.java**: Represents the destructible blocks and manages hit events.
- **Paddle.java**: The player-controlled paddle, with keyboard interaction.
- **Collision Detection**:
  - `CollisionInfo.java` and `GameEnvironment.java` handle collision points and interactions.
- **Score Tracking**:
  - `ScoreIndicator.java` and `ScoreTrackingListener.java` manage and display the player's score.

### Object-Oriented Design
- Utilizes interfaces (`Collidable`, `Sprite`) for flexible and reusable components.
- Event-driven design with listener interfaces (`HitListener`, `HitNotifier`) to handle game events like scoring and block removal.

---

## How to Run

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or above.
- **biuoop Library**: A Java library for simple graphics and keyboard handling. Ensure it is included in your project's classpath.

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/roeeBuchler/BrickBreakerGame.git
   ```
2. Compile the project:
   ```bash
   javac -cp biuoop.jar *.java
   ```
3. Run the game:
   ```bash
   java -cp .:biuoop.jar General.Ass5Game
   ```

---

## Showcase

### Screenshots
Include screenshots or GIFs of the game in action (e.g., during gameplay, showing score updates).

---

## Skills Demonstrated
- **Object-Oriented Programming**: Use of classes, interfaces, and inheritance.
- **Game Development**: Building interactive components and managing game states.
- **Problem Solving**: Implementing accurate collision detection and smooth gameplay mechanics.
- **Graphics Handling**: Using biuoop for drawing, animations, and user input.

---

## Future Enhancements
- Add power-ups for additional gameplay variety.
- Implement multiple levels with increasing difficulty.
- Enhance graphics and animations for a more immersive experience.

---

**Project by Roee Buchler**
