package Code.Menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Menu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane actionPane;

    @FXML
    private Button helpBtn;

    @FXML
    private Button scoreBtn;

    @FXML
    private Button settingBtn;

    @FXML
    private Button startBtn;

    @FXML
    void onHelpClicked(MouseEvent event) {

    }

    @FXML
    void onScoreBtnClicked(MouseEvent event) {

    }

    @FXML
    void onSettingClicked(MouseEvent event) {

    }

    @FXML
    void onStartBtnClicked(MouseEvent event) throws Exception {
        actionPane.setVisible(true);
        actionPane = FXMLLoader.load(getClass().getResource("../Play/Play.fxml"));
    }

    @FXML
    void initialize() {
        assert actionPane != null : "fx:id=\"actionPane\" was not injected: check your FXML file 'Menu.fxml'.";
        assert helpBtn != null : "fx:id=\"helpBtn\" was not injected: check your FXML file 'Menu.fxml'.";
        assert scoreBtn != null : "fx:id=\"scoreBtn\" was not injected: check your FXML file 'Menu.fxml'.";
        assert settingBtn != null : "fx:id=\"settingBtn\" was not injected: check your FXML file 'Menu.fxml'.";
        assert startBtn != null : "fx:id=\"startBtn\" was not injected: check your FXML file 'Menu.fxml'.";

    }

}
