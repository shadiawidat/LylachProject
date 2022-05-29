package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class About {

    public static String Caller = "";

    @FXML
    private Button Back;
    @FXML
    private ImageView CartB;
    @FXML
    private ImageView MenuBtn;
    @FXML
    private Label UserName;
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
    void GoToCart(ActionEvent event) throws IOException {
        Cart.setCaller("About");
        App.setRoot("Cart");
    }

    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        Account.setCaller("About");
        App.setRoot("Account");
    }

    @FXML
    void GoToSignIn(ActionEvent event) throws IOException {
        App.setRoot("LogIn");
    }

    @FXML
    void CloseMenu(MouseEvent event) {
        menu.setVisible(false);
    }

    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        App.setUser(null);
        App.setRoot("Login");
    }

    @FXML
    void GoToSignUp(ActionEvent event) throws IOException {
        SignUp.setCaller("About");
        App.setRoot("SignUp");
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }

    @FXML
    void GoToAccount(MouseEvent event) throws IOException {
        Account.setCaller("About");
        App.setRoot("Account");
    }

    @FXML
    void GoToCart(MouseEvent event) throws IOException {
        Cart.setCaller("About");
        App.setRoot("Cart");
    }

    @FXML
    void GoToAbout(MouseEvent event) throws IOException {
        About.setCaller("LogIn");
        App.setRoot("About");
    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

}
