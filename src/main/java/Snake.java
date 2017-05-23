import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {
    private int snakeX = 100;
    private int snakeY = 100;
    private boolean snakeUp = false;
    private boolean snakeDown = false;
    private boolean snakeLeft = false;
    private boolean snakeRight = true;
    private ArrayList<Rectangle> list = new ArrayList();

    public Snake() {
    }

    public ArrayList<Rectangle> getList() {
        return this.list;
    }

    public void setList(ArrayList<Rectangle> list) {
        this.list = list;
    }

    public int getSnakeX() {
        return this.snakeX;
    }

    public void setSnakeX(int snakeX) {
        this.snakeX = snakeX;
    }

    public int getSnakeY() {
        return this.snakeY;
    }

    public void setSnakeY(int snakeY) {
        this.snakeY = snakeY;
    }

    public boolean isSnakeUp() {
        return this.snakeUp;
    }

    public void setSnakeUp(boolean snakeUp) {
        this.snakeUp = snakeUp;
    }

    public boolean isSnakeDown() {
        return this.snakeDown;
    }

    public void setSnakeDown(boolean snakeDown) {
        this.snakeDown = snakeDown;
    }

    public boolean isSnakeLeft() {
        return this.snakeLeft;
    }

    public void setSnakeLeft(boolean snakeLeft) {
        this.snakeLeft = snakeLeft;
    }

    public boolean isSnakeRight() {
        return this.snakeRight;
    }

    public void setSnakeRight(boolean snakeRight) {
        this.snakeRight = snakeRight;
    }
}

