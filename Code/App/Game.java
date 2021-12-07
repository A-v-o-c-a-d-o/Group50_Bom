package Code.App;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Code.Entity.Bom;
import Code.Entity.Moveable.Moveable;
import Code.Entity.Moveable.Player;
import Code.Entity.Non_moveable.Grass;
import Code.Entity.Non_moveable.Non_moveable;
import Code.Entity.Non_moveable.Wall;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Game {
    public static final int WIDTH = 20, HEIGHT = 12, CELLS_SIZE = 30;
    private int speed;
    private Scene scene;
    Non_moveable[][] map;
    Player player;
    List<Moveable> enemys;
    Queue<Bom> boms;

    /** main loop */
    AnimationTimer loop;

    /** play */
    private AnchorPane playPane, playPausePane;
    private Canvas canvas;
    private GraphicsContext gc;
    private Button pauseBtn, resumeBtn, fromPlayToMenuBtn, restartBtn;
    private ImageView health1, health2, health3;

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
        menuNameGameLabel = newLabel("Bomberman", 640, 100, 0, 100);

        menuHelpBtn = newButton("Help", 120, 30, 260, 320);
        menuHelpBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    scene.setRoot(helpPane);
                    loop.start();
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        });

        menuScoreBtn = newButton("Score", 120, 30, 260, 280);

        menuSettingBtn = newButton("Setting", 120, 30, 260, 240);

        menuPlayBtn = newButton("Play", 120, 30, 260, 200);
        menuPlayBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    scene.setRoot(playPane);
                    loop.start();
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        });
        
        menuPane = new AnchorPane();
        menuPane.setPrefSize(640, 400);
        menuPane.getChildren().addAll(menuHelpBtn, menuScoreBtn, menuSettingBtn, menuPlayBtn, menuNameGameLabel);
    }

    private void setupPlayPane() {
        Image hearthIcon = new Image("./Resources/icons/heart.png");
        health1 = newImageView(hearthIcon, 20, 20, 600, 380);
        health2 = newImageView(hearthIcon, 20, 20, 580, 380);
        health3 = newImageView(hearthIcon, 20, 20, 560, 380);

        canvas = new Canvas(CELLS_SIZE*WIDTH, CELLS_SIZE*HEIGHT);
        canvas.setLayoutX(20);
        canvas.setLayoutY(20);
        gc = canvas.getGraphicsContext2D();

        pauseBtn = newButton("", 20, 20, 620, 0);
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
        playPane.setPrefSize(640, 400);
        playPane.getChildren().addAll(canvas, playPausePane, pauseBtn, health1, health2, health3);
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
                pause();
            }
        });

        helpPane = new AnchorPane();
        helpPane.setPrefSize(640, 400);
        helpPane.getChildren().addAll(tutorial, helpUpBtn, helpDownBtn, helpLeftBtn, helpRightBtn, helpSpaceBtn, helpUp, helpDown, helpLeft, helpRight, helpSpace, helpBottomTitle, BTMenu);
    }

    private void setupGame() {
        // cài tốc độ player
        speed = 2;

        // khởi tạo main loop
        loop = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                try {
                    // clear
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    while (!boms.isEmpty() && System.currentTimeMillis() - boms.peek().getTimeExplode() > 0)
                        boms.remove();
                    
                    // draw
                    for (int i = 0; i < HEIGHT; i++)
                        for (int j = 0; j < WIDTH; j++)
                            if (map[i][j] != null)
                                map[i][j].render(gc);
        
                    player.render(gc);
                    boms.forEach(i -> i.render(gc));
                    enemys.forEach(i -> i.render(gc));
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        };

        // khởi tạo player
        player = new Player(CELLS_SIZE, CELLS_SIZE);
        player.setStep(speed);
        
        // khởi tạo map trống
        map = new Non_moveable[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                if (i == 0 || i == HEIGHT-1 || j == 0 || j == WIDTH-1) {
                    map[i][j] = new Wall(j*CELLS_SIZE, i*CELLS_SIZE);
                } else {
                    map[i][j] = new Grass(j*CELLS_SIZE, i*CELLS_SIZE);
                }
        
        enemys = new ArrayList<>();
        boms = new LinkedList<>();
    }

    /** khởi tạo đối tượng game */
    public Game() {
        // khởi tạo các màn hình hiển thị
        setupMenuPane();
        setupPlayPane();
        setupHelpPane();

        // khởi tạo game
        setupGame();
        
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
                        boms.add(new Bom(((player.getX()+CELLS_SIZE/2) / CELLS_SIZE) * CELLS_SIZE,
                                ((player.getY()+CELLS_SIZE/2)/CELLS_SIZE) * CELLS_SIZE));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public Scene getScene() {
        return scene;
    }

    private void start() {
        setupGame();
        loop.start();
    }

    private void pause() {
        loop.stop();
    }

    private void run() {
        loop.start();
    }
}