package com.ibefehdi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
    static int speed = 5;
    static int foodColor = 0;
    static int width = 20;
    static int height = 20;
    static int foodX = 0;
    static int foodY = 0;
    static int cornerSize = 25;
    static List<Corner> snake = new ArrayList<>();
    static Dir direction;
    static boolean gameOver = false;
    static Random rand = new Random();
    static int dir=rand.nextInt(4)+1;

    public void start(Stage window) {
        try {
            newFood();
            initialDirection(dir);
            VBox gameBox = new VBox();
            Canvas canvas = new Canvas(width * cornerSize, height * cornerSize);
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            gameBox.getChildren().add(canvas);
            new AnimationTimer() {
                long lastTick = 0;

                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(graphicsContext);
                        return;
                    }
                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick(graphicsContext);
                    }
                }

            }.start();

            Scene scene = new Scene(gameBox, width * cornerSize, height * cornerSize);


            // control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if (key.getCode() == KeyCode.W||key.getCode() == KeyCode.UP&&direction!=Dir.DOWN) {
                    direction = Dir.UP;
                }
                if (key.getCode() == KeyCode.A||key.getCode() == KeyCode.LEFT&&direction!=Dir.RIGHT) {
                    direction = Dir.LEFT;
                }
                if (key.getCode() == KeyCode.S||key.getCode() == KeyCode.DOWN&&direction!=Dir.UP) {
                    direction = Dir.DOWN;
                }
                if (key.getCode() == KeyCode.D||key.getCode() == KeyCode.RIGHT&&direction!=Dir.LEFT) {
                    direction = Dir.RIGHT;
                }
            });

            // add start snake parts
            snake.add(new Corner(width / 2, height / 2));
            snake.add(new Corner(width / 2, height / 2));
            snake.add(new Corner(width / 2, height / 2));
            //Display the game
            window.setScene(scene);
            window.setTitle("Snake Game");
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tick
    public static void tick(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.PURPLE);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            return;
        }

        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (direction) {
            case UP:
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    gameOver = true;
                }
                break;
            case DOWN:
                snake.get(0).y++;
                if (snake.get(0).y > height) {
                    gameOver = true;
                }
                break;
            case LEFT:
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    gameOver = true;
                }
                break;
            case RIGHT:
                snake.get(0).x++;
                if (snake.get(0).x > width) {
                    gameOver = true;
                }
                break;

        }

        // eat food
        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Corner(-1, -1));
            newFood();
        }

        // self destroy
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
                break;
            }
        }

        // fill
        // background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * cornerSize, height * cornerSize);

        // score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (speed - 6), 10, 30);

        // random foodColor
        Color cc = Color.WHITE;

        switch (foodColor) {
            case 0:
                cc = Color.PURPLE;
                break;
            case 1:
                cc = Color.BLUE;
                break;
            case 2:
                cc = Color.YELLOW;
                break;
            case 3:
                cc = Color.RED;
                break;
            case 4:
                cc = Color.ORANGE;
                break;
        }
        gc.setFill(cc);
        gc.fillOval(foodX * cornerSize, foodY * cornerSize, cornerSize, cornerSize);

        // snake
        for (Corner c : snake) {
            gc.setFill(Color.CYAN);
            gc.fillRect(c.x * cornerSize, c.y * cornerSize, cornerSize - 1, cornerSize - 1);
            gc.setFill(Color.CADETBLUE);
            gc.fillRect(c.x * cornerSize, c.y * cornerSize, cornerSize - 2, cornerSize - 2);
        }
    }
    // food

    public static void newFood() {
        start:
        while (true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);

            for (Corner c : snake) {
                if (c.x == foodX && c.y == foodY) {
                    continue start;
                }
            }
            foodColor = rand.nextInt(5);
            speed++;
            break;

        }
    }


    public static void initialDirection(int direction1){
        switch (direction1){
            case 1:
                direction=Dir.UP;
                break;
            case 2:
                direction=Dir.LEFT;
                break;
            case 3:
                direction=Dir.DOWN;
                break;
            case 4:
                direction=Dir.RIGHT;
                break;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}