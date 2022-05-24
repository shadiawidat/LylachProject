package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportView implements Initializable {

    @FXML
    private Button Backbtn;

    @FXML
    private ImageView CartButton;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    void GoToCart(MouseEvent event) {

    }

    @FXML
    void Goback(ActionEvent event) {

    }

    @FXML
    void MenuClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
