package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LogIn {

    @FXML
    private ImageView CartButton;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField UsaeName;

    @FXML
    void GoToCart(MouseEvent event) {

    }

    @FXML
    void MenuClick(MouseEvent event) {

    }

    @FXML
    void OpenCatalog(MouseEvent event) throws IOException {
        App.setRoot("Catalog");
    }

    @FXML
    void SignIn(MouseEvent event) {





        SimpleClient.getClient().setUsername(UsaeName.getText());
    }

    @FXML
    void SignUP(MouseEvent event) throws IOException {
        App.setRoot("SignUp");
    }

}
