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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

class LeftTime implements Runnable{

    LocalDateTime time;
    CartView controller;
    int count=0;
    public LeftTime(CartView contorller,LocalDateTime time)
    {
        this.time=time;
        this.controller=contorller;
        run();
    }
    @Override
    public void run() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            private int i;
            @Override
            public void run() {
                controller.setRemaining(count++);

            }
        }, 0, 100);

    }
}

public class CartView implements Initializable {
    public Label getRemaining() {
        return Remaining;
    }

    public void setRemaining(int s){
        Remaining.setText(String.valueOf(s));
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

    private LeftTime leftTime;
    public void setInfo(Cart cart){
//        leftTime=new LeftTime(this,cart.getDate());
//        leftTime.run();
//        int count=0;

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
        System.out.println("anm7a");
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

        Message ms = new Message(null, "#CancelOrder " + OrderID.getText());
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().cartView = this;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
