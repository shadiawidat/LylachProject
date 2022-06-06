package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.AccountTypes;
import il.cshaifasweng.OCSFMediatorExample.entities.Cart;
import il.cshaifasweng.OCSFMediatorExample.entities.Client;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.hibernate.type.LocalDateTimeType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

public class CartView implements Initializable {
    public Label getRemaining() {
        return Remaining;
    }



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

    public Cart cartt;

    public static int i=0;

    public void setInfo(Cart cart){
        cartt=cart;
        final DecimalFormat df = new DecimalFormat("0.00");


        FirstName.setText(App.getUser().getFirstname());
        LastName.setText(App.getUser().getLastname());
        ItemsNumber.setText(String.valueOf(cart.getItems().size()));
        Client c = (Client) (App.getUser());
        if(c.getAccounttype().equals(AccountTypes.Premium) && cart.getPrice() > 50) {
            Total.setText(String.valueOf(df.format((cart.getPrice()*0.9))) + "$");
        }
        else
        {
            Total.setText(String.valueOf(df.format((cart.getPrice()))) + "$");
        }
        OrderID.setText(String.valueOf(cart.getId()));

    }

    public void Deleted (boolean flag) throws IOException {
        if(!flag){
            Alert a = new Alert(Alert.AlertType.ERROR);

            a.setContentText("Canceling Order Failed!");

            a.showAndWait();
            return;
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION);

        a.setContentText("Canceling Order Succeeded!");

        Optional<ButtonType> result = a.showAndWait();
        if(!result.isPresent()) {}
        else if(result.get() == ButtonType.OK)
        {
            Platform.runLater(()->{
                try {
                    App.setRoot("Catalog");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
        return;

    }


    @FXML
    void CancelOrder(MouseEvent event) throws IOException {

        LocalDateTime dd=LocalDateTime.now();
        Date d=new Date(dd.getYear(),dd.getMonth().getValue(),dd.getDayOfMonth(),dd.getHour(),dd.getMinute());
        if(cartt.getDeliverydate().after(d))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Canceling time expired!");
            a.showAndWait();
            return;
        }
        Message ms = new Message(null, "#CancelOrder " + OrderID.getText());
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().cartView = this;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
