package Code.App;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Game game = new Game();
            stage.setScene(game.getScene());
            stage.setTitle("Bom");
            stage.show();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}