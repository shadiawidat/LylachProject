package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportView implements Initializable {

    public static String Caller = "";
    @FXML
    private Button Back;
    @FXML
    private ImageView CartButton;
    @FXML
    private ImageView MenuBtn;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;
    @FXML
    private MenuItem MenuAbout;
    @FXML
    private MenuItem MenuCart;
    @FXML
    private MenuItem MenuProfile;
    @FXML
    private MenuItem MenuSignIn;
    @FXML
    private MenuItem MenuSignOut;
    @FXML
    private MenuItem MenuSignUp;
    @FXML
    private MenuBar menu;

    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }

    @FXML
    void GoToAbout(ActionEvent event) {

    }

    @FXML
    void GoToCart(ActionEvent event) {

    }

    @FXML
    void GoToProfile(ActionEvent event) {

    }

    @FXML
    void GoToSignIn(ActionEvent event) {

    }

    @FXML
    void GoToSignOut(ActionEvent event) {

    }

    @FXML
    void CloseMenu(MouseEvent event) {
        menu.setVisible(false);
    }

    @FXML
    void GoToSignUp(ActionEvent event) {

    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }

    @FXML
    void GoToCart(MouseEvent event) {

    }


    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
