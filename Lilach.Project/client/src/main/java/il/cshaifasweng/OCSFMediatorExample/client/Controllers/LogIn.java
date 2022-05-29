package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
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
        System.out.println("here");
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
        Message ms = new Message(null, "#identify " + UserName.getText() + " " + Password.getText());
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().logControl=this;

    }
    public void Sign(){
            if (App.getUser() != null)
            {
                try {
                    Catalog.setCaller("LogIn");
                    App.setRoot("Catalog");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("here");
                Incorrect.setVisible(true);
                UserName.setText("");
                Password.setText("");
                return;

            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.setUser(null);
    }
}
