package Code.Entity.Moveable;

import Code.App.Game;
import Code.Entity.Entity;
import Code.Entity.Non_moveable.Non_moveable;
import javafx.scene.image.Image;

public abstract class Moveable extends Entity {
    protected int step;
    protected Image imgLeft;
    protected Image imgRight;
    protected Image imgUp;
    protected Image imgDown;
    protected Image imgDead;

    public Moveable(int x, int y) {
        super(x, y);
        this.prevent = false;
        this.step = 1;
        this.x = x;
        this.y = y;
    }

    public void setStep(int step) {
        this.step = step;
    }

    private boolean checkPoint(Non_moveable[][] map, int x, int y) {
        return !map[y / Game.CELLS_SIZE][x / Game.CELLS_SIZE].prevent;
    }
    
    private boolean canMoveLeft(Non_moveable[][] map) {
        return checkPoint(map, x + (Game.CELLS_SIZE/5) - step, y + Game.CELLS_SIZE/5)
            && checkPoint(map, x + (Game.CELLS_SIZE/5) - step, y + 4*Game.CELLS_SIZE/5);
    }

    private boolean canMoveRight(Non_moveable[][] map) {
        return checkPoint(map, x + (4*Game.CELLS_SIZE/5) + step, y + Game.CELLS_SIZE/5)
            && checkPoint(map, x + (4*Game.CELLS_SIZE/5) + step, y + 4*Game.CELLS_SIZE/5);
    }

    private boolean canMoveUp(Non_moveable[][] map) {
        return checkPoint(map, x + (Game.CELLS_SIZE/5), y + (Game.CELLS_SIZE/5) - step)
            && checkPoint(map, x + (4*Game.CELLS_SIZE/5), y + (Game.CELLS_SIZE/5) - step);
    }

    private boolean canMoveDown(Non_moveable[][] map) {
        return checkPoint(map, x + (Game.CELLS_SIZE/5), y + (4*Game.CELLS_SIZE/5) + step)
            && checkPoint(map, x + (4*Game.CELLS_SIZE/5), y + (4*Game.CELLS_SIZE/5) + step);
    }

    public void moveLeft(Non_moveable[][] map) {
        image = imgLeft;
        if (canMoveLeft(map))
            x -= step;
    }

    public void moveRight(Non_moveable[][] map) {
        image = imgRight;
        if (canMoveRight(map))
            x += step;
    }

    public void moveUp(Non_moveable[][] map) {
        image = imgUp;
        if (canMoveUp(map))
            y -= step;
    }

    public void moveDown(Non_moveable[][] map) {
        image = imgDown;
        if (canMoveDown(map))
            y += step;
    }
}