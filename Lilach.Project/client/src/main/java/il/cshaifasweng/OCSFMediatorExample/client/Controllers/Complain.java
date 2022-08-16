package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Utilities;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class Complain implements Initializable {
    final DecimalFormat df = new DecimalFormat("00");
    public il.cshaifasweng.OCSFMediatorExample.entities.Complain complain;
    public int day, hour, minutes, sec;
    public int i = 0;
    boolean onOff = false;
    @FXML
    private Label InvalidRefund;
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

    public void setOnOff() {
        if (onOff) {
            Remaining.setVisible(true);
            onOff = false;
        } else {
            Remaining.setVisible(false);
            onOff = true;
        }
    }

    public void tickRemaining(int remaining) {

        if (sec > 0) {
            sec--;
        } else if (minutes > 0) {
            minutes--;
            sec = 59;
        } else if (hour > 0) {
            hour--;
            minutes = 59;
            sec = 59;
        } else if (day > 0) {
            day--;
            hour = 23;
            minutes = 59;
            sec = 59;
        } else {
            day = 0;
            hour = 0;
            minutes = 0;
            sec = 0;
            try {
                Decide_refund(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Remaining.setText(df.format(hour) + ":" + df.format(minutes) + ":" + df.format(sec));
    }

    @FXML
    void Approve_refund(MouseEvent event) throws IOException {
        InvalidRefund.setVisible(!Utilities.check_Validate_Price(Refund.getText()));
        if (!Utilities.check_Validate_Price(Refund.getText())) {
            return;
        }
        SimpleClient.getClient().sendToServer(new Message(complain.getClient(), "#Refund±" + Refund.getText() + "±" + complain.getId()));
        SimpleClient.getClient().complainControl = this;
        Platform.runLater(() -> {
            try {
                SimpleClient.getClient().sendToServer(new Message(App.getUser(), "#GetComplains "));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void Decide_refund(MouseEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message(complain.getClient(), "#Refund±" + "0" + "±" + complain.getId()));
        SimpleClient.getClient().complainControl = this;

        Platform.runLater(() -> {
            try {
                SimpleClient.getClient().sendToServer(new Message(App.getUser(), "#GetComplains "));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void deleteComplain(MouseEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message(null, "DeleteComplain " + complain.getId()));
        SimpleClient.getClient().complainControl = this;
    }

    public void setInfo(il.cshaifasweng.OCSFMediatorExample.entities.Complain complain) {

        java.util.Date d1 = complain.getDate();

        int hr = complain.getHour(), mn = complain.getMinute();
        d1.setHours(hr);
        d1.setMinutes(mn);
        d1.setSeconds(0);
        LocalDateTime d2 = LocalDateTime.now();
        Date d3 = new Date(d2.getYear() - 1900, d2.getMonth().getValue() - 1, d2.getDayOfMonth());
        d3.setHours(d2.getHour());
        d3.setMinutes(d2.getMinute());
        d3.setSeconds(d2.getSecond());
//        d1.setDate(d1.getDate()-1);
        int secs = 0;
        if (d1.before(d3)) {
            day = 0;
            hour = 0;
            minutes = 0;
            sec = 0;
        } else {
            while (d1.after(d3)) {
                secs++;
                d3.setSeconds(d3.getSeconds() + 1);
            }
        }
        sec = secs % 60;
        minutes = (secs / 60) % 60;
        hour = (secs / 60 / 60) % 24;
        day = (secs / 60 / 60 / 24);
        final DecimalFormat df = new DecimalFormat("00");

        FirstName.setText(complain.getClient().getFirstname());
        LastName.setText(complain.getClient().getLastname());

        String[] s1 = complain.getDate().toString().split(" ");
        int year = Integer.parseInt(s1[5]);
        int day = Integer.parseInt(s1[2]);
        String[] s2 = complain.getDate().toGMTString().split(" ");
        int month = Integer.parseInt(s2[0]);
        Date.setText(df.format(d1.getMonth() + 1) + "/" + (d1.getDate() - 1) + "/" + d1.getYear());


        ID.setText(complain.getClient().getID());
        Branch.setText(complain.getBranch().getName());///get(0) branches
        Reason.setText(complain.getReason());
        // OrderID.setText(complain.getClient().);

//        Client c = (Client) (App.getUser());
//        if(c.getAccounttype().equals(AccountTypes.Premium) && cart.getPrice() > 50) {
//            Total.setText(String.valueOf(df.format((cart.getPrice()*0.9))) + "$");
//        }
//        else
//        {
//            Total.setText(String.valueOf(df.format((cart.getPrice()))) + "$");
//        }
//        OrderID.setText(String.valueOf(cart.getId()));
        this.complain = complain;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final DecimalFormat df = new DecimalFormat("0.00");
        Refund.setText(df.format(0));
        InvalidRefund.setVisible(false);
    }

}
