package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Complain implements Initializable {
    private il.cshaifasweng.OCSFMediatorExample.entities.Complain complain;

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
    private Label OrderID;

    @FXML
    private TextField Reason;

    @FXML
    private TextField Refund;

    @FXML
    private Label Remaining;

    @FXML
    void Approve_refund(MouseEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message(complain.getClient(),"Refund "+Refund.getText()));
        SimpleClient.getClient().complainControl=this;
    }

    @FXML
    void Decide_refund(MouseEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message(complain.getClient(),"Refund "+"0"));
        SimpleClient.getClient().complainControl=this;
    }

    @FXML
    void deleteComplain(MouseEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message(null,"DeleteComplain "+complain.getId()));
        SimpleClient.getClient().complainControl=this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Refund.setText("0");
    }

}
