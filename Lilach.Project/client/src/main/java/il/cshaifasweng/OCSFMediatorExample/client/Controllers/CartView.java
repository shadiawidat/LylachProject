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
import java.time.Year;
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

    public int day,hour,minutes,sec;

    final DecimalFormat df = new DecimalFormat("00");

    boolean onOff=false;

    public int i=0;

    public void setOnOff()
    {
        if(onOff)
        {
            Remaining.setVisible(true);
            onOff=false;
        }
        else
        {
            Remaining.setVisible(false);
            onOff=true;
        }
    }
    public void tickRemaining(int remaining) {

        if(sec>0)
        {
            sec--;
        }
        else if(minutes>0)
        {
            minutes--;
            sec=59;
        }
        else if(hour>0)
        {
            hour--;
            minutes=59;
            sec=59;
        }
        else if(day>0)
        {
            day--;
            hour=23;
            minutes=59;
            sec=59;
        }
        else
        {
            day=0;
            hour=0;
            minutes=0;
            sec=0;
        }
        Remaining.setText(day+":"+df.format(hour)+":"+df.format(minutes)+":"+df.format(sec));
    }

    public void setInfo(Cart cart){

        Date d1=cart.getDeliverydate();

        int hr=cart.getHour(),mn=cart.getMinute();
        d1.setHours(hr);
        d1.setMinutes(mn);
        d1.setSeconds(0);
        LocalDateTime d2=LocalDateTime.now();
        Date d3=new Date(d2.getYear()-1900, d2.getMonth().getValue()-1, d2.getDayOfMonth());
        d3.setHours(d2.getHour());
        d3.setMinutes(d2.getMinute());
        d3.setSeconds(d2.getSecond());
        d1.setDate(d1.getDate()-1);
        int secs=0;
        if(d1.before(d3))
        {
            day=0;
            hour=0;
            minutes=0;
            sec=0;
        }
        else
        {

            while(d1.after(d3))
            {
                secs++;
                d3.setSeconds(d3.getSeconds()+1);
            }
        }
        sec=secs%60;
        minutes=(secs/60)%60;
        hour=(secs/60/60)%24;
        day=(secs/60/60/24);
        final DecimalFormat df = new DecimalFormat("0.00");
        FirstName.setText(App.getUser().getFirstname());
        LastName.setText(App.getUser().getLastname());
        ItemsNumber.setText(String.valueOf(cart.getItems().size()));
        Client c = (Client) (App.getUser());
        if(c.getAccounttype().equals(AccountTypes.Premium) && cart.getPrice() > 50) {
            Total.setText(df.format((cart.getPrice() * 0.9)) + "$");
        }
        else
        {
            Total.setText(df.format((cart.getPrice())) + "$");
        }
        cartt=cart;
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


        double timeleft=(((((double)sec/60.0)+minutes)/60) +hour)+day*24;

        if(timeleft>=3)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("You will be refunded 100%");

            Optional<ButtonType> result = a.showAndWait();
            if(!result.isPresent()) {}
            else if(result.get() == ButtonType.OK)
            {
                Message ms = new Message(null, "#CancelOrder " + App.getUser().getUsername()+" "+OrderID.getText()+" 100");
                SimpleClient.getClient().sendToServer(ms);
                SimpleClient.getClient().cartView = this;
                return;
            }
            return;
        }
        else if(timeleft>=1)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("You will be refunded 50%");

            Optional<ButtonType> result = a.showAndWait();
            if(!result.isPresent()) {}
            else if(result.get() == ButtonType.OK)
            {
                Message ms = new Message(null, "#CancelOrder " +App.getUser().getUsername()+" "+OrderID.getText()+" 50");
                SimpleClient.getClient().sendToServer(ms);
                SimpleClient.getClient().cartView = this;
                return;
            }
            return;
        }
        else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Canceling time expired!");
            Optional<ButtonType> result = a.showAndWait();
            if(!result.isPresent()) {}
            else if(result.get() == ButtonType.OK)
            {
                Message ms = new Message(null, "#CancelOrder " + App.getUser().getUsername()+" "+OrderID.getText()+" 0");
                SimpleClient.getClient().sendToServer(ms);
                SimpleClient.getClient().cartView = this;
                return;
            }
            return;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
