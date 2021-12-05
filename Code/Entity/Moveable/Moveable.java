package Code.Entity.Moveable;

import Code.App.Game;
import Code.Entity.Entity;
import Code.Entity.Non_moveable.Non_moveable;
import javafx.scene.image.Image;

public abstract class Moveable extends Entity {
    protected Image imgLeft;
    protected Image imgRight;
    protected Image imgUp;
    protected Image imgDown;
    protected Image imgDead;

    public Moveable(int x, int y) {
        super(x, y);
        prevent = false;
        this.x = x;
        this.y = y;
    }

    private boolean canMoveLeft(Non_moveable[][] map) {
        if (x % Game.CELLS_SIZE > 0)  return true;
        if ((y + Game.CELLS_SIZE/5) % Game.CELLS_SIZE > (2*Game.CELLS_SIZE)/5)    return false;
        if (map[(y + Game.CELLS_SIZE/5) / Game.CELLS_SIZE][(x / Game.CELLS_SIZE) - 1].prevent)   return false;
        return true;
    }

    private boolean canMoveRight(Non_moveable[][] map) {
        if (x % Game.CELLS_SIZE < (2*Game.CELLS_SIZE)/5)    return true;
        if ((y + Game.CELLS_SIZE/5) % Game.CELLS_SIZE > (2*Game.CELLS_SIZE)/5)    return false;
        if (map[(y + Game.CELLS_SIZE/5) / Game.CELLS_SIZE][(x / Game.CELLS_SIZE) + 1].prevent)    return false;
        return true;
    }

    private boolean canMoveUp(Non_moveable[][] map) {
        if (y % Game.CELLS_SIZE > 0)   return true;
        if ((x + Game.CELLS_SIZE/5) % Game.CELLS_SIZE > (2*Game.CELLS_SIZE)/5)  return false;
        if (map[(y / Game.CELLS_SIZE) - 1][(x + Game.CELLS_SIZE/5) / Game.CELLS_SIZE].prevent)    return false;
        return true;
    }

    private boolean canMoveDown(Non_moveable[][] map) {
        if (y % Game.CELLS_SIZE < (2*Game.CELLS_SIZE)/5)   return true;
        if ((x + Game.CELLS_SIZE/5) % Game.CELLS_SIZE > (2*Game.CELLS_SIZE)/5)  return false;
        if (map[(y / Game.CELLS_SIZE) + 1][(x + Game.CELLS_SIZE/5) / Game.CELLS_SIZE].prevent)    return false;
        return true;
    }

    public void moveLeft(Non_moveable[][] map) {
        if (canMoveLeft(map))
            x--;
    }

    public void moveRight(Non_moveable[][] map) {
        if (canMoveRight(map))
            x++;
    }

    public void moveUp(Non_moveable[][] map) {
        if (canMoveUp(map))
            y--;
    }

    public void moveDown(Non_moveable[][] map) {
        if (canMoveDown(map))
            y++;
    }
}