package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LogIn {

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
    void MenuClick(MouseEvent event) {

    }

    @FXML
    void OpenCatalog(MouseEvent event) throws IOException {
        Catalog.setCaller("LogIn");
        App.setUser(null);
        App.setRoot("Catalog");
    }

    @FXML
    void SignIn(MouseEvent event) throws IOException {
        Message ms=new Message(null,"#identify "+UserName.getText()+" "+Password.getText());
        SimpleClient.getClient().sendToServer(ms);
        if(App.getUser()==null)
        {
            Incorrect.setVisible(true);
            UserName.setText("");
            Password.setText("");
            return;
        }
        Platform.runLater(()->{
            try {
                App.setRoot("Catalog");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void SignUP(MouseEvent event) throws IOException {
        App.setRoot("SingUp");
    }

}
