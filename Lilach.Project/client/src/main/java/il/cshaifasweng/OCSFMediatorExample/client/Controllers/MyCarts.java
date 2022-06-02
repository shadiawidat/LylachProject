package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MyCarts {
    public static String Caller = "";
    @FXML
    private Button Back;

    @FXML
    private ImageView CartButton;

    @FXML
    private MenuItem MenuAbout;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private MenuItem MenuProfile;

    @FXML
    private MenuItem MenuSignOut;

    @FXML
    private MenuItem MenuSignUp;

    @FXML
    private Label UserName;

    @FXML
    private GridPane gridPane;

    @FXML
    private MenuBar menu;

    @FXML
    private ScrollPane scroll;

    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }


    @FXML
    void CloseMenu(MouseEvent event) {

    }

    @FXML
    void GoToAbout(ActionEvent event) throws IOException {
        About.setCaller("MyCarts");
        App.setRoot("About");
    }

    @FXML
    void GoToAccount(MouseEvent event) throws IOException {
        About.setCaller("MyCarts");
        App.setRoot("Account");
    }

    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        About.setCaller("MyCarts");
        App.setRoot("Account");
    }

    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        if(App.getUser()!=null)
            SimpleClient.getClient().sendToServer(new Message(null,"#SignOut "+App.getUser().getUsername()));
        App.setUser(null);
        App.setRoot("LogIn");
    }

    @FXML
    void GoToSignUp(ActionEvent event) throws IOException {
        About.setCaller("MyCarts");
        App.setRoot("SignUp");
    }
    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }
}
