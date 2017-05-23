import java.awt.Rectangle;

public class SnakeGame implements Runnable {
    private boolean started = false;
    private boolean finishedBlink1 = false;
    private boolean finishedBlink2 = false;
    private boolean finished = false;
    private int score = 0;
    private int maxScoreLeft = 0;
    private int seconds = 0;
    private int minutes = 0;
    private boolean pause = false;
    private boolean restart = false;
    private boolean startMenu = true;
    private int difficultyLevel = 0;
    private int difficultyLevel_thread;
    private String menuSelection = "Start game";
    private Snake snake;
    private SnakeGame snakeGame;
    private Food food;

    public SnakeGame() {
    }

    public void startGame(SnakeGame snakeGame) {
        this.started = true;
        Thread t = new Thread(snakeGame);
        this.menuSelection = "Restart game";
        switch(snakeGame.getDifficultyLevel()) {
            case 0:
                snakeGame.setDifficultyLevel_thread(300);
                break;
            case 1:
                snakeGame.setDifficultyLevel_thread(200);
                break;
            case 2:
                snakeGame.setDifficultyLevel_thread(100);
        }

        t.start();
    }

    public void restartGame() {
        this.snake.setSnakeX(100);
        this.snake.setSnakeY(100);
        this.snake.setSnakeLeft(false);
        this.snake.setSnakeUp(false);
        this.snake.setSnakeDown(false);
        this.snake.setSnakeRight(true);
        this.snakeGame.setFinished(false);
        this.snakeGame.setScore(0);
        this.snakeGame.setSeconds(0);
        this.snakeGame.setMinutes(0);
        this.snakeGame.setMaxScoreLeft(100);
        this.snakeGame.setPause(false);
        this.snake.getList().clear();
        this.snake.getList().add(new Rectangle(this.snake.getSnakeX(), this.snake.getSnakeY(), 10, 10));
    }

    public void finishGame() {
        this.started = false;

        for(int i = 0; i < 4; ++i) {
            this.finishedBlink1 = true;
            GUI.f1.repaint();

            try {
                Thread.sleep(500L);
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }

            this.finishedBlink1 = false;
            this.finishedBlink2 = true;
            GUI.f1.repaint();

            try {
                Thread.sleep(500L);
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }

            this.finishedBlink2 = false;
        }

        this.finishedBlink1 = false;
        this.finishedBlink2 = false;
        this.finished = true;
        this.menuSelection = "Restart game";
        GUI.f1.repaint();
    }

    public void run() {
        this.snake = new Snake();
        this.food = new Food();
        this.food.setFood(this.food);
        App.gui.setFood(this.food);
        App.gui.setSnake(this.snake);
        this.snake.getList().add(new Rectangle(this.snake.getSnakeX(), this.snake.getSnakeY(), 10, 10));
        long startTime = System.currentTimeMillis();
        long currentTime = 0L;

        while(this.snakeGame.started) {
            if(!this.snakeGame.isPause()) {
                this.collisionWall();
                this.placeFood();
                if(this.snakeGame.getMaxScoreLeft() > 0) {
                    this.snakeGame.setMaxScoreLeft(this.snakeGame.getMaxScoreLeft() - 1);
                }

                this.collisionFood();
                this.collisionSnake();
                this.snake.getList().add(new Rectangle(this.snake.getSnakeX(), this.snake.getSnakeY(), 10, 10));
                this.snake.getList().remove(0);
                currentTime = System.currentTimeMillis();
                if(currentTime - startTime >= 901L) {
                    if(this.snakeGame.getSeconds() < 59) {
                        this.snakeGame.setSeconds(this.snakeGame.getSeconds() + 1);
                    } else if(this.snakeGame.getSeconds() >= 59) {
                        this.snakeGame.setSeconds(0);
                        this.snakeGame.setMinutes(this.snakeGame.getMinutes() + 1);
                    }

                    startTime += currentTime - startTime + 100L;
                }
            }

            GUI.f1.repaint();

            try {
                Thread.sleep((long)this.snakeGame.getDifficultyLevel_thread());
            } catch (InterruptedException var6) {
                var6.printStackTrace();
            }
        }

    }

    public void collisionWall() {
        if(this.snake.isSnakeUp()) {
            if(this.snake.getSnakeY() < 20) {
                this.finishGame();
            } else {
                this.snake.setSnakeY(this.snake.getSnakeY() - 15);
            }
        } else if(this.snake.isSnakeDown()) {
            if(this.snake.getSnakeY() > 335) {
                this.finishGame();
            } else {
                this.snake.setSnakeY(this.snake.getSnakeY() + 15);
            }
        } else if(this.snake.isSnakeLeft()) {
            if(this.snake.getSnakeX() < 20) {
                this.finishGame();
            } else {
                this.snake.setSnakeX(this.snake.getSnakeX() - 15);
            }
        } else if(this.snake.isSnakeRight()) {
            if(this.snake.getSnakeX() > 370) {
                this.finishGame();
            } else {
                this.snake.setSnakeX(this.snake.getSnakeX() + 15);
            }
        }

    }

    public void collisionSnake() {
        for(int i = 1; i < this.snake.getList().size() - 1; ++i) {
            if(i + 1 < this.snake.getList().size() && ((Rectangle)this.snake.getList().get(0)).intersects((Rectangle)this.snake.getList().get(i + 1))) {
                this.finishGame();
            }
        }

    }

    public void collisionFood() {
        if(Math.abs(this.food.getFoodX() - this.snake.getSnakeX()) <= 8 && Math.abs(this.food.getFoodY() - this.snake.getSnakeY()) <= 8) {
            this.food.setFoodPlaced(false);
            this.snake.getList().add(new Rectangle(this.snake.getSnakeX(), this.snake.getSnakeY(), 10, 10));
            this.snakeGame.score += this.snakeGame.getMaxScoreLeft();
        }

    }

    public void placeFood() {
        if(!this.food.isFoodPlaced()) {
            this.food.setFoodX((int)(35.0D + Math.random() * 335.0D));
            this.food.setFoodY((int)(35.0D + Math.random() * 315.0D));
            this.food.setFoodPlaced(true);
            this.snakeGame.maxScoreLeft = 100;
        }

    }

    public Snake getSnake() {
        return this.snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public SnakeGame getSnakeGame() {
        return this.snakeGame;
    }

    public void setSnakeGame(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    public boolean isFinishedBlink1() {
        return this.finishedBlink1;
    }

    public void setFinishedBlink1(boolean finishedBlink1) {
        this.finishedBlink1 = finishedBlink1;
    }

    public boolean isFinishedBlink2() {
        return this.finishedBlink2;
    }

    public void setFinishedBlink2(boolean finishedBlink2) {
        this.finishedBlink2 = finishedBlink2;
    }

    public boolean isPause() {
        return this.pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isRestart() {
        return this.restart;
    }

    public void setRestart(boolean restart) {
        this.restart = restart;
    }

    public String getMenuSelection() {
        return this.menuSelection;
    }

    public void setMenuSelection(String menuSelection) {
        this.menuSelection = menuSelection;
    }

    public int getMaxScoreLeft() {
        return this.maxScoreLeft;
    }

    public void setMaxScoreLeft(int maxScoreLeft) {
        this.maxScoreLeft = maxScoreLeft;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean isStartmenu() {
        return this.startMenu;
    }

    public void setStartmenu(boolean startmenu) {
        this.startMenu = startmenu;
    }

    public int getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getDifficultyLevel_thread() {
        return this.difficultyLevel_thread;
    }

    public void setDifficultyLevel_thread(int difficultyLevel_thread) {
        this.difficultyLevel_thread = difficultyLevel_thread;
    }
}
