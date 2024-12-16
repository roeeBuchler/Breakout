//212067466 Roee Buchler
package General;

import Hit.BallRemover;
import Hit.BlockRemover;
import Hit.ScoreTrackingListener;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import Geometry.Velocity;
import Geometry.Point;
import Geometry.Ball;
import Geometry.Block;
import Geometry.Paddle;
import Geometry.Collidable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Hit.PrintingHitListener;

/**
 * The Game class represents a game with a collection of sprites and a game environment.
 * It includes methods to add collidable and sprite objects to the game, initialize the game, and run the game loop.
 */
public class Game {
    //fields
    private SpriteCollection spritesCollection;
    private GameEnvironment environment;
    private int numOfBlocks;
    private GUI gui;
    private Sleeper sleeper;
    private Counter remainingBlocks;
    private Counter availableBalls;
    private Counter score;

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to be added
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game's sprite collection.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        spritesCollection.addSprite(s);
    }

    /**
     * Initializes and returns a new ball for the game.
     * Sets up two balls with predefined starting points, velocities, and adds them to the game.
     *
     * @return the first initialized ball
     */
    private Ball initBall() {
        Ball ball = new Ball(new Point(600, 400), 4, getRandomColor());
        Ball ball1 = new Ball(new Point(600, 400), 4, getRandomColor());
        Ball ball2 = new Ball(600, 400, 4, getRandomColor());
        ball.setGameEnvironment(this.environment);
        ball1.setGameEnvironment(this.environment);
        ball2.setGameEnvironment(this.environment);
        Velocity velocity;
        velocity = Velocity.fromAngleAndSpeed(-90, 7);
        ball.setVelocity(velocity);
        velocity = Velocity.fromAngleAndSpeed(-80, 7);
        ball1.setVelocity(velocity);
        velocity = Velocity.fromAngleAndSpeed(-70, 7);
        ball2.setVelocity(velocity);
        ball.addToGame(this);
        ball1.addToGame(this);
        ball2.addToGame(this);
        return ball;
    }

    /**
     * Generates a random color for the blocks in the game.
     *
     * @return a random color
     */
    public static Color getRandomColor() {
        Random random = new Random();
        // Create a random color by generating random RGB values
        float r = random.nextFloat();  // Generate a random number between 0.0 and 1.0
        float g = random.nextFloat();
        float b = random.nextFloat();

        // Return the new color created from these values
        return new Color(r, g, b);
    }

    /**
     * Initializes blocks for the game with varying colors for each row.
     * Positions blocks in rows and ensures they do not overflow the screen width.
     *
     * @param blocks      the list of blocks to be initialized
     * @param numOfBlocks the number of blocks to initialize
     * @return the list of initialized blocks
     */
    private List<Block> initBlocks(List<Block> blocks, int numOfBlocks) {
        int xBlock = 0, yBlock = 100;  // Start position for the first block
        int blockWidth = 50, blockHeight = 20;  // Block dimensions
        int screenWidth = 800;  // Width of the screen
        Color rowColor = getRandomColor();  // Random color for the row of blocks
        for (int i = 0; i < numOfBlocks; i++) {
            if (xBlock + blockWidth > screenWidth - 20 || xBlock < 20) {
                yBlock += blockHeight;  // Move to next row
                rowColor = getRandomColor();  // Get a new random color for the row
                xBlock = 20;  // Reset x position for the new row
            }
            blocks.add(new Block(new Point(xBlock, yBlock), blockWidth, blockHeight, rowColor));
            blocks.get(i).addToGame(this);
            xBlock += blockWidth;  // Move x position to the right for the next block
        }
        return blocks;
    }

    /**
     * Initializes the borders of the game screen.
     * Creates and adds border blocks to the game.
     */
    private void initBorder() {
        Block block1 = new Block(new Point(0, 0), 800, 20, Color.GRAY);
        block1.addToGame(this);
        Block block2 = new Block(new Point(0, 0), 20, 600, Color.GRAY);
        block2.addToGame(this);
        Block block3 = new Block(new Point(780, 0), 20, 600, Color.GRAY);
        block3.addToGame(this);
        Block block4 = new Block(new Point(0, 580), 800, 20, Color.GRAY);
        block4.addToGame(this);
    }

    /**
     * Initializes the game by setting up the environment,
     * sprite collection, GUI, paddle, and starting elements like balls and blocks.
     */
    public void initialize() {
        environment = new GameEnvironment(new ArrayList<>());
        spritesCollection = new SpriteCollection();
        gui = new GUI("Game", 800, 600);
        numOfBlocks = 50;
        initBall();
        Paddle paddle = new Paddle(new Point(400, 560), 100, 20, gui.getKeyboardSensor());
        paddle.addToGame(this);
        sleeper = new Sleeper();
        List<Block> blocks = new ArrayList<>();
        initBlocks(blocks, numOfBlocks);
        remainingBlocks = new Counter(numOfBlocks);
        availableBalls = new Counter(3);
        score = new Counter(0);
        for (Block block : blocks) {
            block.addHitListener(new PrintingHitListener());
            block.addHitListener(new BlockRemover(this, remainingBlocks));
            block.addHitListener(new ScoreTrackingListener(this, score));
        }
        Block deathRegion = new Block(new Point(0, 580), 800, 20, Color.GRAY);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(new BallRemover(this, availableBalls));
        initBorder();
        ScoreIndicator scoreIndicator = new ScoreIndicator(this, score);
        spritesCollection.addSprite(scoreIndicator);
    }

    /**
     * Starts and manages the game loop, including drawing sprites and timing frame updates.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.spritesCollection.drawAllOn(d);
            gui.show(d);
            this.spritesCollection.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (remainingBlocks.getValue() == 0) {
                score.increase(100);
            }
            if (remainingBlocks.getValue() == 0 || availableBalls.getValue() == 0) {
                gui.close();
                System.exit(0);
            }
        }
    }
    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        environment.getCollidables().remove(c);
    }
    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        spritesCollection.getSprites().remove(s);
    }
}
