import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI {
    static JFrame f1;
    static JPanel p1;
    private Snake snake;
    private SnakeGame snakeGame = new SnakeGame();
    private Food food;
    private Font fontMenu;
    private Font fontHeader;

    public GUI() {
        this.snakeGame.setSnakeGame(this.snakeGame);
    }

    public void createGameWindow() {
        f1 = new JFrame("Snake");
        f1.setSize(675, 395);
        f1.setDefaultCloseOperation(3);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException var2) {
            var2.printStackTrace();
        }

        f1.setLocationRelativeTo((Component)null);
        p1 = new GUI.Draw();
        p1.setLayout((LayoutManager)null);
        f1.add(p1);
        this.fontMenu = new Font("SansSerif", 0, 12);
        this.fontHeader = new Font("SansSerif", 1, 16);
        f1.requestFocus();
        f1.repaint();
        f1.setVisible(true);
        f1.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if(GUI.this.snakeGame.isStarted()) {
                    if(GUI.this.snakeGame.isPause()) {
                        GUI.this.checkPauseMenuInputs(e);
                    } else if(!GUI.this.snakeGame.isPause()) {
                        GUI.this.checkCurrentGameInputs(e);
                    }
                } else if(GUI.this.snakeGame.isFinished()) {
                    GUI.this.checkGameOverInputs(e);
                } else if(GUI.this.snakeGame.isStartmenu()) {
                    GUI.this.checkStartMenuInputs(e);
                }

            }

            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public void checkPauseMenuInputs(KeyEvent e) {
        if(e.getKeyCode() == 27) {
            this.snakeGame.setPause(false);
        } else if(e.getKeyCode() == 38) {
            if(this.snakeGame.getMenuSelection().equals("Exit game")) {
                this.snakeGame.setMenuSelection("Back to main menu");
                f1.repaint();
            } else if(this.snakeGame.getMenuSelection().equals("Back to main menu")) {
                this.snakeGame.setMenuSelection("Restart game");
                f1.repaint();
            }
        } else if(e.getKeyCode() == 40) {
            if(this.snakeGame.getMenuSelection().equals("Restart game")) {
                this.snakeGame.setMenuSelection("Back to main menu");
                f1.repaint();
            } else if(this.snakeGame.getMenuSelection().equals("Back to main menu")) {
                this.snakeGame.setMenuSelection("Exit game");
                f1.repaint();
            }
        } else if(e.getKeyCode() == 10) {
            String var2;
            switch((var2 = this.snakeGame.getMenuSelection()).hashCode()) {
                case -1100760454:
                    if(var2.equals("Back to main menu")) {
                        this.snakeGame.setPause(false);
                        this.snakeGame.setStarted(false);
                        this.snakeGame.restartGame();
                        this.snakeGame.setStartmenu(true);
                        this.snakeGame.setMenuSelection("Start game");
                        f1.repaint();
                    }
                    break;
                case -740334717:
                    if(var2.equals("Restart game")) {
                        this.snakeGame.restartGame();
                    }
                    break;
                case 575841684:
                    if(var2.equals("Exit game")) {
                        System.exit(0);
                    }
            }
        }

    }

    public void checkCurrentGameInputs(KeyEvent e) {
        if(e.getKeyCode() == 38) {
            if(!this.snake.isSnakeDown()) {
                this.snake.setSnakeDown(false);
                this.snake.setSnakeRight(false);
                this.snake.setSnakeLeft(false);
                this.snake.setSnakeUp(true);
            }
        } else if(e.getKeyCode() == 40) {
            if(!this.snake.isSnakeUp()) {
                this.snake.setSnakeUp(false);
                this.snake.setSnakeRight(false);
                this.snake.setSnakeLeft(false);
                this.snake.setSnakeDown(true);
            }
        } else if(e.getKeyCode() == 37) {
            if(!this.snake.isSnakeRight()) {
                this.snake.setSnakeUp(false);
                this.snake.setSnakeRight(false);
                this.snake.setSnakeDown(false);
                this.snake.setSnakeLeft(true);
            }
        } else if(e.getKeyCode() == 39) {
            if(!this.snake.isSnakeLeft()) {
                this.snake.setSnakeUp(false);
                this.snake.setSnakeLeft(false);
                this.snake.setSnakeDown(false);
                this.snake.setSnakeRight(true);
            }
        } else if(e.getKeyCode() == 27) {
            this.snakeGame.setPause(true);
        }

    }

    public void checkGameOverInputs(KeyEvent e) {
        if(e.getKeyCode() == 38) {
            if(this.snakeGame.getMenuSelection().equals("Exit game")) {
                this.snakeGame.setMenuSelection("Restart game");
                f1.repaint();
            }
        } else if(e.getKeyCode() == 40) {
            if(this.snakeGame.getMenuSelection().equals("Restart game")) {
                this.snakeGame.setMenuSelection("Exit game");
                f1.repaint();
            }
        } else if(e.getKeyCode() == 10) {
            String var2;
            switch((var2 = this.snakeGame.getMenuSelection()).hashCode()) {
                case -740334717:
                    if(var2.equals("Restart game")) {
                        this.snakeGame.restartGame();
                        this.snakeGame.startGame(this.snakeGame);
                    }
                    break;
                case 575841684:
                    if(var2.equals("Exit game")) {
                        System.exit(0);
                    }
            }
        }

    }

    public void checkStartMenuInputs(KeyEvent e) {
        if(e.getKeyCode() == 38) {
            if(this.snakeGame.getMenuSelection().equals("Exit game")) {
                this.snakeGame.setMenuSelection("Difficulty");
                f1.repaint();
            } else if(this.snakeGame.getMenuSelection().equals("Difficulty")) {
                this.snakeGame.setMenuSelection("Start game");
                f1.repaint();
            }
        } else if(e.getKeyCode() == 40) {
            if(this.snakeGame.getMenuSelection().equals("Start game")) {
                this.snakeGame.setMenuSelection("Difficulty");
                f1.repaint();
            } else if(this.snakeGame.getMenuSelection().equals("Difficulty")) {
                this.snakeGame.setMenuSelection("Exit game");
                f1.repaint();
            }
        } else if(e.getKeyCode() == 39) {
            if(this.snakeGame.getMenuSelection().equals("Difficulty") && this.snakeGame.getDifficultyLevel() != 2) {
                this.snakeGame.setDifficultyLevel(this.snakeGame.getDifficultyLevel() + 1);
                f1.repaint();
            }
        } else if(e.getKeyCode() == 37) {
            if(this.snakeGame.getMenuSelection().equals("Difficulty") && this.snakeGame.getDifficultyLevel() != 0) {
                this.snakeGame.setDifficultyLevel(this.snakeGame.getDifficultyLevel() - 1);
                f1.repaint();
            }
        } else if(e.getKeyCode() == 10) {
            String var2;
            switch((var2 = this.snakeGame.getMenuSelection()).hashCode()) {
                case 361742608:
                    if(var2.equals("Start game")) {
                        this.snakeGame.startGame(this.snakeGame);
                    }
                    break;
                case 575841684:
                    if(var2.equals("Exit game")) {
                        System.exit(0);
                    }
            }
        }

    }

    public void drawPauseMenu(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, 400, 400);
        g2.setColor(Color.WHITE);
        g2.setFont(this.fontHeader);
        g2.drawString("Pause menu", 100, 100);
        g2.setFont(this.fontMenu);
        g2.drawString("Restart game", 100, 200);
        g2.drawString("Back to main menu", 100, 250);
        g2.drawString("Exit game", 100, 300);
        String var2;
        switch((var2 = this.snakeGame.getMenuSelection()).hashCode()) {
            case -1100760454:
                if(var2.equals("Back to main menu")) {
                    g2.fillOval(70, 240, 10, 10);
                }
                break;
            case -740334717:
                if(var2.equals("Restart game")) {
                    g2.fillOval(70, 190, 10, 10);
                }
                break;
            case 575841684:
                if(var2.equals("Exit game")) {
                    g2.fillOval(70, 290, 10, 10);
                }
        }

    }

    public void drawCurrentGame(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 400, 400);
        g2.setColor(Color.BLACK);
        g2.fillRect(395, 0, 10, 400);
        g2.fillRect(0, 0, 10, 400);
        g2.fillRect(0, 0, 400, 10);
        g2.fillRect(0, 350, 400, 10);

        for(int i = 1; i <= this.snake.getList().size(); ++i) {
            g2.fillRect(((Rectangle)this.snake.getList().get(i - 1)).x, ((Rectangle)this.snake.getList().get(i - 1)).y, 10, 10);
        }

        if(this.food.isFoodPlaced()) {
            g2.setColor(Color.GREEN);
            g2.fillRect(this.food.getFoodX(), this.food.getFoodY(), 10, 10);
        }

        g2.setColor(Color.BLACK);
        g2.setFont(this.fontMenu);
        if(this.snakeGame.getMinutes() < 10 && this.snakeGame.getSeconds() < 10) {
            g2.drawString("0" + this.snakeGame.getMinutes() + ":0" + this.snakeGame.getSeconds(), 420, 50);
        } else if(this.snakeGame.getMinutes() < 10 && this.snakeGame.getSeconds() > 9) {
            g2.drawString("0" + this.snakeGame.getMinutes() + ":" + this.snakeGame.getSeconds(), 420, 50);
        } else if(this.snakeGame.getMinutes() > 9 && this.snakeGame.getSeconds() > 9) {
            g2.drawString(this.snakeGame.getMinutes() + ":" + this.snakeGame.getSeconds(), 420, 50);
        } else if(this.snakeGame.getMinutes() > 9 && this.snakeGame.getSeconds() < 10) {
            g2.drawString(this.snakeGame.getMinutes() + ":0" + this.snakeGame.getSeconds(), 420, 50);
        }

        g2.drawString("Score: " + this.snakeGame.getScore(), 420, 100);
        g2.drawString("Max score left: " + this.snakeGame.getMaxScoreLeft(), 420, 150);
    }

    public void drawBlinkAnimation1(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 400, 400);
        g2.setColor(Color.BLACK);
        g2.setColor(Color.BLACK);
        g2.fillRect(395, 0, 10, 400);
        g2.fillRect(0, 0, 10, 400);
        g2.fillRect(0, 0, 400, 10);
        g2.fillRect(0, 350, 400, 10);

        for(int i = 1; i <= this.snake.getList().size(); ++i) {
            g2.fillRect(((Rectangle)this.snake.getList().get(i - 1)).x, ((Rectangle)this.snake.getList().get(i - 1)).y, 10, 10);
        }

        if(this.food.isFoodPlaced()) {
            g2.setColor(Color.GREEN);
            g2.fillRect(this.food.getFoodX(), this.food.getFoodY(), 10, 10);
        }

    }

    public void drawBlinkAnimation2(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(395, 0, 10, 400);
        g2.fillRect(0, 0, 10, 400);
        g2.fillRect(0, 0, 400, 10);
        g2.fillRect(0, 350, 400, 10);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 400, 400);
        g2.setColor(Color.BLACK);
        g2.fillRect(395, 0, 10, 400);
        g2.fillRect(0, 0, 10, 400);
        g2.fillRect(0, 0, 400, 10);
        g2.fillRect(0, 350, 400, 10);
    }

    public void drawGameOver(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, 400, 400);
        g2.setColor(Color.WHITE);
        g2.setFont(this.fontHeader);
        g2.drawString("Game over! \n Your score: " + this.snakeGame.getScore(), 100, 100);
        g2.setFont(this.fontMenu);
        g2.drawString("Restart game", 100, 200);
        g2.drawString("Exit game", 100, 250);
        String var2;
        switch((var2 = this.snakeGame.getMenuSelection()).hashCode()) {
            case -740334717:
                if(var2.equals("Restart game")) {
                    g2.fillOval(70, 190, 10, 10);
                }
                break;
            case -472001573:
                if(var2.equals("Difficulty")) {
                    g2.fillOval(70, 290, 10, 10);
                }
                break;
            case 575841684:
                if(var2.equals("Exit game")) {
                    g2.fillOval(70, 240, 10, 10);
                }
        }

    }

    public void drawStartMenu(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, 400, 400);
        g2.setColor(Color.WHITE);
        g2.setFont(this.fontHeader);
        g2.drawString("Snake", 100, 100);
        g2.setFont(this.fontMenu);
        g2.drawString("Start game", 100, 200);
        g2.drawString("Difficulty:", 100, 250);
        g2.drawString("Exit game", 100, 300);
        g2.drawString("Easy", 205, 250);
        g2.drawString("Normal", 255, 250);
        g2.drawString("Hard", 305, 250);
        String var2;
        switch((var2 = this.snakeGame.getMenuSelection()).hashCode()) {
            case -472001573:
                if(var2.equals("Difficulty")) {
                    g2.fillOval(70, 240, 10, 10);
                }
                break;
            case 361742608:
                if(var2.equals("Start game")) {
                    g2.fillOval(70, 190, 10, 10);
                }
                break;
            case 575841684:
                if(var2.equals("Exit game")) {
                    g2.fillOval(70, 290, 10, 10);
                }
        }

        switch(this.snakeGame.getDifficultyLevel()) {
            case 0:
                g2.drawRect(202, 239, 31, 14);
                break;
            case 1:
                g2.drawRect(252, 239, 45, 14);
                break;
            case 2:
                g2.drawRect(302, 239, 32, 14);
        }

    }

    public SnakeGame getSnakeGame() {
        return this.snakeGame;
    }

    public void setSnakeGame(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    class Draw extends JPanel {
        private static final long serialVersionUID = 1L;

        Draw() {
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            if(GUI.this.snakeGame.isStarted()) {
                if(GUI.this.snakeGame.isPause()) {
                    GUI.this.drawPauseMenu(g2);
                } else if(!GUI.this.snakeGame.isPause()) {
                    GUI.this.drawCurrentGame(g2);
                }
            } else if(GUI.this.snakeGame.isFinishedBlink1()) {
                GUI.this.drawBlinkAnimation1(g2);
            } else if(GUI.this.snakeGame.isFinishedBlink2()) {
                GUI.this.drawBlinkAnimation2(g2);
            } else if(GUI.this.snakeGame.isFinished()) {
                GUI.this.drawGameOver(g2);
            } else if(GUI.this.snakeGame.isStartmenu()) {
                GUI.this.drawStartMenu(g2);
            }

        }
    }
}
