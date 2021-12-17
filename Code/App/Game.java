package Code.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import Code.Entity.Entity;
import Code.Entity.Moveable.Player;
import Code.Entity.Moveable.Enemys.*;
import Code.Entity.Non_moveable.Brick;
import Code.Entity.Non_moveable.Non_moveable;
import Code.Entity.Non_moveable.Wall;
import Code.Entity.Non_moveable.Items.bonusHealth;
import Code.Entity.Non_moveable.Items.increaseRange;
import Code.Entity.Non_moveable.Items.speedUp;
import Code.Entity.ShortLife.Bom;
import Code.Entity.ShortLife.Fire;
import edu.princeton.cs.algs4.StdRandom;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;

public class Game {
    private BackgroundImage background = new BackgroundImage(new Image("./Resources/icons/background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    public static final int WIDTH = 20, HEIGHT = 12, CELLS_SIZE = 30;
    private String mapPath;
    private Scene scene;
    private List<Integer> listScore = new ArrayList<>();
    Entity[][] map;
    Player player;
    List<Enemy> enemys;

    /** main loop */
    AnimationTimer loop;

    /** play */
    private AnchorPane playPane, playPausePane;
    private Canvas canvas;
    private GraphicsContext gc;
    private Button resumeBtn, fromPlayToMenuBtn, restartBtn;
    private ImageView health1, health2, health3, pauseBtn;

    /** setting */
    private AnchorPane settingPane;
    private Label settingTitle;
    private ImageView level1, level2;

    /** score */
    private AnchorPane scorePane;
    private Label scoreTitle;

    /** help */
    AnchorPane helpPane;
    Button helpUpBtn, helpDownBtn, helpLeftBtn, helpRightBtn, helpSpaceBtn, BTMenu;
    Label helpUp, helpDown, helpLeft, helpRight, helpSpace, helpBottomTitle, tutorial;

    /** menu */
    private AnchorPane menuPane;
    private Label menuNameGameLabel;
    private Button menuHelpBtn, menuScoreBtn, menuSettingBtn, menuPlayBtn;

    /** tạo Button dễ hơn */
    private Button newButton(String text, double preX, double preY, double layoutX, double layoutY) {
        Button ans = new Button(text);
        ans.setPrefSize(preX, preY);
        ans.setLayoutX(layoutX);
        ans.setLayoutY(layoutY);
        return ans;
    }

    /** tạo Label dễ hơn */
    private Label newLabel(String text, double preX, double preY, double layoutX, double layoutY) {
        Label ans = new Label(text);
        ans.setPrefSize(preX, preY);
        ans.setAlignment(Pos.CENTER);
        ans.setLayoutX(layoutX);
        ans.setLayoutY(layoutY);
        return ans;
    }

    /** tạo ảnh dễ hơn */
    private ImageView newImageView(Image img, double preX, double preY, double layoutX, double layoutY) {
        ImageView ans = new ImageView(img);
        ans.setFitHeight(preY);
        ans.setFitWidth(preX);
        ans.setLayoutX(layoutX);
        ans.setLayoutY(layoutY);
        return ans;
    }

    private void setupMenuPane() {
        menuNameGameLabel = newLabel("Bomberman", 200, 100, 40, 24);

        menuHelpBtn = newButton("Help", 120, 30, 70, 320 - 70);
        menuHelpBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    setupHelpPane();
                    scene.setRoot(helpPane);
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        });

        menuScoreBtn = newButton("Score", 120, 30, 70, 280 - 70);
        menuScoreBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    setupScorePane();
                    scene.setRoot(scorePane);
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        });

        menuSettingBtn = newButton("Setting", 120, 30, 70, 240 - 70);
        menuSettingBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    setupSettingPane();
                    scene.setRoot(settingPane);
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        });

        menuPlayBtn = newButton("Play", 120, 30, 70, 200 - 70);
        menuPlayBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    mapPath = System.getProperty("user.dir") +  "/src/Resources/data/map1.txt";
                    setupPlayPane();
                    scene.setRoot(playPane);
                    start();
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        });
        
        menuPane = new AnchorPane();
        menuPane.setPrefSize(640, 400);
        menuPane.setBackground(new Background(background));
        menuPane.getChildren().addAll(menuHelpBtn, menuScoreBtn, menuSettingBtn, menuPlayBtn, menuNameGameLabel);
    }

    private void setupPlayPane() {
        Image hearthIcon = new Image("./Resources/icons/heart.png");
        health1 = newImageView(hearthIcon, 20, 20, 20, 0);
        health2 = newImageView(hearthIcon, 20, 20, 40, 0);
        health3 = newImageView(hearthIcon, 20, 20, 60, 0);

        canvas = new Canvas(CELLS_SIZE*WIDTH, CELLS_SIZE*HEIGHT);
        canvas.setLayoutX(20);
        canvas.setLayoutY(20);
        gc = canvas.getGraphicsContext2D();

        pauseBtn = newImageView(new Image("./Resources/icons/setting-icon.png"), 20, 20, 620, 0);
        pauseBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                playPausePane.setVisible(true);
                pause();
            }
        });

        restartBtn = newButton("Restart", 100, 20, 0, 40);
        restartBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                playPausePane.setVisible(false);
                start();
            }
        });

        resumeBtn = newButton("Resume", 100, 20, 0, 0);
        resumeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                playPausePane.setVisible(false);
                run();
            }
        });
    
        fromPlayToMenuBtn = newButton("Back to menu", 100, 20, 0, 80);
        fromPlayToMenuBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setRoot(menuPane);
            }
        });

        playPausePane = new AnchorPane();
        playPausePane.setPrefSize(100, 100);
        playPausePane.setLayoutX(270);
        playPausePane.setLayoutY(150);
        playPausePane.setVisible(false);
        playPausePane.getChildren().addAll(resumeBtn, fromPlayToMenuBtn, restartBtn);

        playPane = new AnchorPane();
        playPane.setBackground(new Background(background));
        playPane.setPrefSize(640, 400);
        playPane.getChildren().addAll(canvas, playPausePane, pauseBtn, health1, health2, health3);
    }

    private void setupSettingPane() {
        settingTitle = newLabel("CHOOSE MAP", 240, 100, 200, 0);
        settingTitle.setFont(new Font("Franklin Gothic Heavy", 100));

        level1 = newImageView(new Image("./Resources/icons/Map1.png") ,200, 147, 62, 127);
        level1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    mapPath = System.getProperty("user.dir") +  "/src/Resources/data/map1.txt";
                    setupPlayPane();
                    scene.setRoot(playPane);
                    start();
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        });

        level2 = newImageView(new Image("./Resources/icons/Map2.png") ,200, 147, 371, 127);
        level2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    mapPath = System.getProperty("user.dir") +  "/src/Resources/data/map2.txt";
                    setupPlayPane();
                    scene.setRoot(playPane);
                    start();
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        });

        BTMenu = newButton("Back to menu", 120, 31, 476, 361);
        BTMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setRoot(menuPane);
            }
        });

        settingPane = new AnchorPane();
        settingPane.setBackground(new Background(background));
        settingPane.setPrefSize(640, 400);
        settingPane.getChildren().addAll(settingTitle, level1, level2, BTMenu);
    }

    private int getScore() {
        int ans = 100 + player.getHealth()*20 - enemys.size()*10;
        return ans;
    }

    private void setupScorePane() {
        loadListScore();

        scoreTitle = newLabel("Score",166, 50, 213, 29);
        scoreTitle.setFont(new Font("Franklin Gothic Heavy", 42));

        ListView<Object> lsScore = new ListView<>();
        lsScore.setPrefSize(200, 281);
        lsScore.setLayoutX(69);
        lsScore.setLayoutY(100);
        lsScore.getSelectionModel().selectIndices(0, 9);

        ObservableList<Object> b = FXCollections.observableArrayList();
        for (int i: listScore)
            b.add(i);

        lsScore.setItems(b);

        BTMenu = newButton("Back to menu", 120, 31, 476, 361);
        BTMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setRoot(menuPane);
            }
        });

        scorePane = new AnchorPane();
        scorePane.setBackground(new Background(background));
        scorePane.setPrefSize(640, 400);
        scorePane.getChildren().addAll(scoreTitle, lsScore, BTMenu);
    }

    private void setupHelpPane() {
        tutorial = newLabel("Tutorial",166, 50, 213, 29);
        tutorial.setFont(new Font("Franklin Gothic Heavy", 42));

        helpUpBtn = newButton("^", 44, 31, 150, 118);
        helpDownBtn = newButton("v", 44, 31, 150, 149);
        helpLeftBtn = newButton("v", 44, 31, 106, 149);
        helpRightBtn = newButton("v", 44, 31, 194, 149);
        helpSpaceBtn = newButton("", 187, 31, 74, 215);

        helpUp = newLabel("Press button '^' to go up ", 174, 21, 351, 95);
        helpDown = newLabel("Press button 'v' to go up ", 194, 21, 351, 123);
        helpLeft = newLabel("Press button '<' to go up ", 180, 21, 351, 180);
        helpRight = newLabel("Press button '>' to go up ", 187, 21, 351, 154);
        helpSpace = newLabel("Press button SPACE to drop your bomb", 278, 21, 300, 220);
        helpBottomTitle = newLabel("You must kill all the enemies to win this game!", 426, 27, 107, 308);
        helpBottomTitle.setFont(new Font("Book Antiqua", 20));

        BTMenu = newButton("Back to menu", 120, 31, 476, 361);
        BTMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setRoot(menuPane);
            }
        });

        helpPane = new AnchorPane();
        helpPane.setBackground(new Background(background));
        helpPane.setPrefSize(640, 400);
        helpPane.getChildren().addAll(tutorial, helpUpBtn, helpDownBtn, helpLeftBtn, helpRightBtn, helpSpaceBtn, helpUp, helpDown, helpLeft, helpRight, helpSpace, helpBottomTitle, BTMenu);
    }

    private void setupGame() {
        try {
            // khởi tạo main loop
            loop = new AnimationTimer() {
                @Override
                public void handle(long arg0) {
                    try {
                        if (!player.isAlive() || enemys.isEmpty())
                            end();

                        // back grass
                        for (int i = 0; i < HEIGHT; i++)
                            for (int j = 0; j < WIDTH; j++)
                                gc.drawImage(new Image("./Resources/icons/grass.png"), j*CELLS_SIZE, i*CELLS_SIZE);

                        checkTouchEnemy();

                        // enemys
                        enemys.forEach(i -> {
                            i.move(map);
                            if (!i.isAlive())
                                enemys.remove(i);
                        });

                        // map
                        for (int i = 0; i < HEIGHT; i++)
                            for (int j = 0; j < WIDTH; j++)
                                if (map[i][j] != null)
                                    if (map[i][j].isAlive()) {
                                        map[i][j].update(player);
                                        map[i][j].render(gc);
                                    } else {
                                        if (map[i][j] instanceof Bom) {
                                            ignite(i, j);
                                        } else map[i][j] = null;
                                    }

                        // health symbol
                        health1.setVisible(player.getHealth() >= 1);
                        health2.setVisible(player.getHealth() >= 2);
                        health3.setVisible(player.getHealth() >= 3);

                        // moveable
                        player.render(gc);
                        enemys.forEach(i -> i.render(gc));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                }
            };

            // khởi tạo player
            player = new Player(CELLS_SIZE, CELLS_SIZE);

            // khởi tạo map
            loadMap(mapPath);
            
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    /** khởi tạo game */
    public Game() {
        // khởi tạo menu
        setupMenuPane();

        // load score
        loadListScore();
        
        // setup main scene
        scene = new Scene(menuPane);
        scene.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:
                        player.moveLeft(map);
                        break;
                    case RIGHT:
                        player.moveRight(map);
                        break;
                    case UP:
                        player.moveUp(map);
                        break;
                    case DOWN:
                        player.moveDown(map);
                        break;
                    case SPACE:
                        map[(player.getY()+CELLS_SIZE/2)/CELLS_SIZE][(player.getX()+CELLS_SIZE/2)/CELLS_SIZE]
                            = new Bom(((player.getX()+CELLS_SIZE/2) / CELLS_SIZE) * CELLS_SIZE,
                                ((player.getY()+CELLS_SIZE/2) / CELLS_SIZE) * CELLS_SIZE);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void loadListScore() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir") +  "/src/Resources/data/Score.txt")));
            listScore = (List<Integer>) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public Scene getScene() {
        return scene;
    }

    private void kill(Fire fire) throws Exception {
        fire.burn(player);
        enemys.forEach(i -> {
            try {
                fire.burn(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void ignite(int i, int j) throws Exception {
        // play sound
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/src/Resources/sound/explosion.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        map[i][j] = new Fire(j*CELLS_SIZE, i*CELLS_SIZE);
        kill((Fire) map[i][j]);

        // left
        for (int x = 1; x <= player.getIgniteRange(); x++)
            if (map[i][j-x] == null) {
                map[i][j-x] = new Fire((j-x)*CELLS_SIZE, i*CELLS_SIZE);
                kill((Fire) map[i][j-x]);
            } else if ((map[i][j-x] instanceof Non_moveable) && map[i][j-x].canBeBurn()) {
                map[i][j-x] = brickDestroyed(i, j-x);
                break;
            } else break;

        // right
        for (int x = 1; x <= player.getIgniteRange(); x++)
            if (map[i][j+x] == null) {
                map[i][j+x] = new Fire((j+x)*CELLS_SIZE, i*CELLS_SIZE);
                kill((Fire) map[i][j+x]);
            } else if ((map[i][j+x] instanceof Non_moveable) && map[i][j+x].canBeBurn()) {
                map[i][j+x] = brickDestroyed(i, j+x);
                break;
            } else break;
        
        // up
        for (int x = 1; x <= player.getIgniteRange(); x++)
            if (map[i-x][j] == null) {
                map[i-x][j] = new Fire(j*CELLS_SIZE, (i-x)*CELLS_SIZE);
                kill((Fire) map[i-x][j]);
            } else if ((map[i-x][j] instanceof Non_moveable) && map[i-x][j].canBeBurn()) {
                map[i-x][j] = brickDestroyed(i-x, j);
                break;
            } else break;
        
        // down
        for (int x = 1; x <= player.getIgniteRange(); x++)
            if (map[i+x][j] == null) {
                map[i+x][j] = new Fire(j*CELLS_SIZE, (i+x)*CELLS_SIZE);
                kill((Fire) map[i+x][j]);
            } else if ((map[i+x][j] instanceof Non_moveable) && map[i+x][j].canBeBurn()) {
                map[i+x][j] = brickDestroyed(i+x, j);
                break;
            } else break;
    }

    private Entity brickDestroyed(int i, int j) {
        if (StdRandom.uniform(250) > 24) {
            return new Fire(j*CELLS_SIZE, i*CELLS_SIZE);
        } else {
            int rand = StdRandom.uniform(3);
            switch (rand) {
                case 1:
                    return new bonusHealth(j*CELLS_SIZE, i*CELLS_SIZE);
                case 2:
                    return new increaseRange(j*CELLS_SIZE, i*CELLS_SIZE);
                default:
                    return new speedUp(j*CELLS_SIZE, i*CELLS_SIZE);
            }
        }
    }

    private void start() {
        playPausePane.setVisible(false);
        setupGame();
        loop.start();
    }

    private void pause() {
        loop.stop();
    }

    private void run() {
        loop.start();
    }

    private void end() {
        try {
            loop.stop();
            listScore.add(getScore());
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir") +  "/src/Resources/data/Score.txt")));
            out.writeObject(listScore);
            out.close();
            playPausePane.setVisible(true);
            resumeBtn.setDisable(true);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    private void checkTouchEnemy() {
        for (Enemy i: enemys) {
            double x = Math.abs(player.getX() - i.getX());
            double y = Math.abs(player.getY() - i.getY());
            if (x < (3*CELLS_SIZE)/5 && y < (3*CELLS_SIZE)/5) {
                try {
                    player.die();
                    i.die();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadMap(String path) {
        try {
            map = new Entity[HEIGHT][WIDTH];
            enemys = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader(path));
            String bufferString;
            for (int i = 0; i < HEIGHT; i++) {
                bufferString = br.readLine();
                for (int j = 0; j < WIDTH; j++) {
                    if (bufferString.charAt(j) == 'w') map[i][j] = new Wall(j * CELLS_SIZE, i * CELLS_SIZE);
                    else if (bufferString.charAt(j) == 'b') map[i][j] = new Brick(j * CELLS_SIZE, i * CELLS_SIZE);
                }
            }

            while (br.ready()) {
                String[] a = br.readLine().split(" ");
                int x = Integer.parseInt(a[0]) * CELLS_SIZE;
                int y = Integer.parseInt(a[1]) * CELLS_SIZE;
                createEnemy(x, y);
            }

            br.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    private void createEnemy(int x, int y) {
        switch (StdRandom.uniform(5)) {
            case 1:
                enemys.add(new Balloom(x, y, StdRandom.uniform(3) + 1));
                break;
            case 2:
                enemys.add(new Doll(x, y, StdRandom.uniform(3) + 1));
                break;
            case 3:
                enemys.add(new Kondoria(x, y, StdRandom.uniform(3) + 1));
                break;
            case 4:
                enemys.add(new Minvo(x, y, StdRandom.uniform(3) + 1));
                break;
            default:
                enemys.add(new Oneal(x, y, StdRandom.uniform(3) + 1));
                break;
        }
    }
}