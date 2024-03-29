package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Utilities;
import il.cshaifasweng.OCSFMediatorExample.entities.permissions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogIn implements Initializable {

    @FXML
    private Label Incorrect;

    @FXML
    private ImageView CartButton;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField UserName;

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

    @FXML
    void GoToAbout(MouseEvent event) throws IOException {

        About.setCaller("LogIn");
        App.setRoot("About");
    }

    @FXML
    void GoToSignIn(ActionEvent event) throws IOException {
        App.setRoot("LogIn");
    }

    @FXML
    void GoToSignUpMN(ActionEvent event) throws IOException {
        SignUp.setCaller("LogIn");
        App.setRoot("SignUp");
    }

    @FXML
    void GoToSignUp(MouseEvent event) throws IOException {
        SignUp.setCaller("LogIn");
        App.setRoot("SignUp");
    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    @FXML
    void OpenCatalog(MouseEvent event) throws IOException {
        Catalog.setCaller("LogIn");
        App.setUser(null);
        App.setRoot("Catalog");
    }

    @FXML
    void SignIn(MouseEvent event) throws IOException, InterruptedException {
        if (!Utilities.check_Validate_Username(UserName.getText()) || !Utilities.check_Validate_Pass(Password.getText())) {
            Incorrect.setVisible(true);
            UserName.setText("");
            Password.setText("");
            return;

        }
        Message ms = new Message(null, "#LogIn " + UserName.getText() + " " + Password.getText());
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().logControl = this;

    }

    public void Sign() {
        if (App.getUser() != null) {
            if (App.getUser().isFreeze()) {
                Alert a = new Alert(Alert.AlertType.ERROR);

                a.setContentText("User is freezed.");

                a.showAndWait();
                return;
            }
            if (App.getUser().isLogedIn()) {
                Alert a = new Alert(Alert.AlertType.ERROR);

                a.setContentText("You are already signed in.");

                a.showAndWait();
                return;
            }
            App.getUser().setLogedIn(true);
            if (App.getUser().getPermission() == permissions.ADMIN) {
                try {
                    Account.setCaller("LogIn");
                    App.setRoot("Account");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (App.getUser().getPermission() == permissions.CustomerServiceWorker) {
                try {
                    AllComplains.setCaller("LogIn");
                    App.setRoot("AllComplains");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Catalog.setCaller("LogIn");
                    App.setRoot("Catalog");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {

            Incorrect.setVisible(true);
            UserName.setText("");
            Password.setText("");
            return;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (App.getUser() != null) {
            try {
                SimpleClient.getClient().sendToServer(new Message(null, "#SignOut " + App.getUser().getUsername()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        App.setUser(null);
    }
}
