package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Shipping implements Initializable {

    @FXML
    private TextField Address;

    @FXML
    private Button Approve;

    @FXML
    private Button Back;

    @FXML
    private TextField Blessing;

    @FXML
    private ImageView CartButton;

    @FXML
    private DatePicker Date;

    @FXML
    private Label InvalidAdderss;

    @FXML
    private Label InvalidDate;

    @FXML
    private Label InvalidName;

    @FXML
    private Label InvalidPhoneNumber;

    @FXML
    private MenuItem MenuAbout;

    @FXML
    private ImageView MenuBtn;

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
    private TextField Name;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private Label Saved;

    @FXML
    private Label Tax;

    @FXML
    private Label Total;

    @FXML
    private Label UserName;

    @FXML
    private MenuBar menu;

    @FXML
    void Approve(MouseEvent event) {

    }

    @FXML
    void Back(MouseEvent event) {

    }

    @FXML
    void CartClick(MouseEvent event) {

    }

    @FXML
    void CloseMenu(MouseEvent event) {

    }

    @FXML
    void Delivery(MouseEvent event) {

    }

    @FXML
    void ForSomeone(MouseEvent event) {

    }

    @FXML
    void GoToAbout(ActionEvent event) {

    }

    @FXML
    void GoToAccount(MouseEvent event) {

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
    void GoToSignUp(ActionEvent event) {

    }

    @FXML
    void MenuClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (App.getUser() == null)
            UserName.setText("Welcome guest");
        else
            UserName.setText("Welcome " + App.getUser().getFirstname());
    }

}
