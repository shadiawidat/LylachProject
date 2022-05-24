package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Account {

    public static String Caller="";

    public static String getCaller() {
        return Caller;
    }
    public static void setCaller(String caller) {
        Caller = caller;
    }
    @FXML
    private PasswordField AccountType;

    @FXML
    private TextField Address;

    @FXML
    private PasswordField BirthDate;

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
    private TextField Phone;

    @FXML
    private Label UserName;

    @FXML
    private TextField Username;

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }


    @FXML
    void GoToAccount(MouseEvent event) {

    }

    @FXML
    void GoToCart(MouseEvent event) {

    }

    @FXML
    void MenuClick(MouseEvent event) {

    }

    @FXML
    void Myorders(MouseEvent event) {

    }

}
