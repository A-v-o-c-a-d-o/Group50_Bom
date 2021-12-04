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

    public Moveable() {
        image = imgDown;
        prevent = false;
    }

    public Moveable(int x, int y) {
        image = imgDown;
        prevent = false;
        this.x = x;
        this.y = y;
    }

    public boolean canMoveLeft(Non_moveable[][] map) {
        int X = x % Game.CELLS_SIZE;
        if (X > 0)  return true;
        if (x == 0)  return false;
        if (map[(x-1) / Game.CELLS_SIZE][y / Game.CELLS_SIZE].prevent)    return false;
        return true;
    }

    public boolean canMoveRight(Non_moveable[][] map) {
        int X = x % Game.CELLS_SIZE;
        if (X > 0)  return true;
        if (x == Game.WIDTH - Game.CELLS_SIZE)  return false;
        if (map[x / Game.CELLS_SIZE][y / Game.CELLS_SIZE].prevent)    return false;
        return true;
    }

    public boolean canMoveUp(Non_moveable[][] map) {
        int Y = y % Game.CELLS_SIZE;
        if (Y > 0)  return true;
        if (y == 0)  return false;
        if (map[x / Game.CELLS_SIZE][(y-1) / Game.CELLS_SIZE].prevent)    return false;
        return true;
    }

    public boolean canMoveDown(Non_moveable[][] map) {
        int Y = y % Game.CELLS_SIZE;
        if (Y > 0)  return true;
        if (y == Game.HEIGHT - Game.CELLS_SIZE)  return false;
        if (map[x / Game.CELLS_SIZE][y / Game.CELLS_SIZE].prevent)    return false;
        return true;
    }
}