package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUp {
    public static String Caller="";

    public static String getCaller() {
        return Caller;
    }
    public static void setCaller(String caller) {
        Caller = caller;
    }
    @FXML
    private MenuButton AccountType;

    @FXML
    private TextField Address;

    @FXML
    private DatePicker Birthdate;

    @FXML
    private ImageView CartB;

    @FXML
    private TextField CreditCard;

    @FXML
    private TextField Email;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField ID;

    @FXML
    private TextField LastName;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField Phone;

    @FXML
    private Label UserName;

    @FXML
    private TextField Username;

    @FXML
    void AllBranches(ActionEvent event) {

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

    }

    @FXML
    void OneBranch(ActionEvent event) {

    }

    @FXML
    void SignUp(MouseEvent event) {

    }

    @FXML
    void Subscription(ActionEvent event) {

    }

}
