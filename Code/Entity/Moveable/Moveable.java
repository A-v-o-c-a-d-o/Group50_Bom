package Code.Entity.Moveable;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import Code.App.Game;
import Code.Entity.Entity;
import javafx.scene.image.Image;

public abstract class Moveable extends Entity {
    protected String dieSound;
    protected int health;
    protected int step;
    protected Image imgLeft;
    protected Image imgRight;
    protected Image imgUp;
    protected Image imgDown;
    protected Image imgDead;

    public Moveable(int x, int y) {
        super(x, y);
        this.health = 1;
        this.prevent = false;
        this.step = 1;
        this.x = x;
        this.y = y;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int step) {
        this.step = step <= 9 ? step : 9;
    }

    protected boolean checkPoint(Entity[][] map, int x, int y) {
        return map[y / Game.CELLS_SIZE][x / Game.CELLS_SIZE] == null
            || !map[y / Game.CELLS_SIZE][x / Game.CELLS_SIZE].prevent;
    }
    
    protected boolean canMoveLeft(Entity[][] map) {
        return checkPoint(map, x + (Game.CELLS_SIZE/5) - step, y + Game.CELLS_SIZE/5)
            && checkPoint(map, x + (Game.CELLS_SIZE/5) - step, y + 4*Game.CELLS_SIZE/5);
    }

    protected boolean canMoveRight(Entity[][] map) {
        return checkPoint(map, x + (4*Game.CELLS_SIZE/5) + step, y + Game.CELLS_SIZE/5)
            && checkPoint(map, x + (4*Game.CELLS_SIZE/5) + step, y + 4*Game.CELLS_SIZE/5);
    }

    protected boolean canMoveUp(Entity[][] map) {
        return checkPoint(map, x + (Game.CELLS_SIZE/5), y + (Game.CELLS_SIZE/5) - step)
            && checkPoint(map, x + (4*Game.CELLS_SIZE/5), y + (Game.CELLS_SIZE/5) - step);
    }

    protected boolean canMoveDown(Entity[][] map) {
        return checkPoint(map, x + (Game.CELLS_SIZE/5), y + (4*Game.CELLS_SIZE/5) + step)
            && checkPoint(map, x + (4*Game.CELLS_SIZE/5), y + (4*Game.CELLS_SIZE/5) + step);
    }

    public void moveLeft(Entity[][] map) {
        image = imgLeft;
        if (canMoveLeft(map))
            x -= step;
    }

    public void moveRight(Entity[][] map) {
        image = imgRight;
        if (canMoveRight(map))
            x += step;
    }

    public void moveUp(Entity[][] map) {
        image = imgUp;
        if (canMoveUp(map))
            y -= step;
    }

    public void moveDown(Entity[][] map) {
        image = imgDown;
        if (canMoveDown(map))
            y += step;
    }

    public void die() throws Exception {
        if (dieSound != null) {
            String path = (System.getProperty("user.dir") + "/src/Resources/sound/" + dieSound);
            File file = new File(path);
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip.open(inputStream);
            clip.start();
        }
        
        health--;
        if (health < 1) {
            image = imgDead;
            alive = false;
        }
    }

    public boolean canBeBurn() {
        return true;
    }
}