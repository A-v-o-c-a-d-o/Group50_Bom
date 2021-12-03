package Code.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Menu/Menu.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}