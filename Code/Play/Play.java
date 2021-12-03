package Code.Play;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Play {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Canvas canvas;

    @FXML
    private Button pauseBtn;

    @FXML
    private AnchorPane pausePane;

    @FXML
    void onKeyPressed(KeyEvent event) {

    }

    @FXML
    void onKeyReleased(KeyEvent event) {

    }

    @FXML
    void onPauseBtnClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'Play.fxml'.";
        assert pauseBtn != null : "fx:id=\"pauseBtn\" was not injected: check your FXML file 'Play.fxml'.";
        assert pausePane != null : "fx:id=\"pausePane\" was not injected: check your FXML file 'Play.fxml'.";

    }

}
