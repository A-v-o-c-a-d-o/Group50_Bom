package Code.App;

import java.util.ArrayList;
import java.util.List;
import Code.Entity.Moveable.Moveable;
import Code.Entity.Moveable.Player;
import Code.Entity.Non_moveable.Non_moveable;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Game {
    public static final int WIDTH = 20, HEIGHT = 12, CELLS_SIZE = 30;
    private Scene scene;
    Non_moveable[][] map = new Non_moveable[HEIGHT][WIDTH];
    Player player = new Player(0, 0);
    List<Moveable> enemys = new ArrayList<>();

    /** play */
    private AnchorPane playPane, playPausePane;
    private Canvas canvas = new Canvas(600, 360);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Button pauseBtn, resumeBtn, fromPlayToMenuBtn, restartBtn;
    private ImageView health1, health2, health3;

    /** menu */
    private AnchorPane menuPane;
    private Label menuNameGameLabel;
    private Button menuHelpBtn, menuScoreBtn, menuSettingBtn, PlayBtn;

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

        menuScoreBtn = newButton("Score", 120, 30, 260, 280);

        menuSettingBtn = newButton("Setting", 120, 30, 260, 240);

        PlayBtn = newButton("Play", 120, 30, 260, 200);
        PlayBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    scene.setRoot(playPane);
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        });
        
        menuPane = new AnchorPane();
        menuPane.setPrefSize(640, 400);
        menuPane.getChildren().addAll(menuHelpBtn, menuScoreBtn, menuSettingBtn, PlayBtn, menuNameGameLabel);
    }

    private void setupPlayPane() {
        Image hearthIcon = new Image("./Resources/icons/heart.png");
        health1 = newImageView(hearthIcon, 20, 20, 600, 380);
        health2 = newImageView(hearthIcon, 20, 20, 580, 380);
        health3 = newImageView(hearthIcon, 20, 20, 560, 380);

        canvas.setLayoutX(20);
        canvas.setLayoutY(20);

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
                pause();
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

    public Game() {
        // khởi tạo các chế độ
        setupMenuPane();
        setupPlayPane();
        
        // mặc định
        scene = new Scene(menuPane);
    }
    
    public Scene getScene() {
        return scene;
    }

    public void start() {}

    public void pause() {}

    public void run() {}
}
