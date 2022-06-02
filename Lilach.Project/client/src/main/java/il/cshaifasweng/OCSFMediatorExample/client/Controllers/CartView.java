package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CartView {

    @FXML
    private Button CancelOrder;

    @FXML
    private TextField Complain;

    @FXML
    private Label FirstName;

    @FXML
    private Label ItemsNumber;

    @FXML
    private Label LastName;

    @FXML
    private Label OrderID;

    @FXML
    private Label Remaining;

    @FXML
    private Label Total;

    @FXML
    void CancelOrder(MouseEvent event) throws IOException {
       // SimpleClient.getClient().sendToServer(new Message(null,"#CancelOrder "+OrderID+" "+Remaining));
    }
}
