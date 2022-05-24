package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Complain {

    private Client client;

    @FXML
    private Label Branch;

    @FXML
    private Label Date;

    @FXML
    private Label FirstName;

    @FXML
    private Button Handlebtn;

    @FXML
    private Label ID;

    @FXML
    private Label LastName;

    @FXML
    private TextField Reason;

    @FXML
    void Decide_refund(MouseEvent event) {

    }

    @FXML
    void ViewAccount(MouseEvent event) throws IOException {
       Account.setClient(client);
        App.setRoot("Account");
    }

    @FXML
    void deleteComplain(MouseEvent event) {

    }

}
