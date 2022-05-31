package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Shipping implements Initializable {

    public static String Caller = "";

    @FXML
    private TextField Address;

    @FXML
    private Button Approve;

    @FXML
    private Button Back;

    @FXML
    private TextField Blessing;

    @FXML
    private ImageView CartButton;

    @FXML
    private DatePicker Date;

    @FXML
    private Label InvalidAdderss;

    @FXML
    private Label InvalidDate;

    @FXML
    private Label InvalidName;

    @FXML
    private Label InvalidPhoneNumber;

    @FXML
    private MenuItem MenuAbout;

    @FXML
    private ImageView MenuBtn;

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
    private TextField Name;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private Label Saved;

    @FXML
    private Label Tax;

    @FXML
    private Label Total;

    @FXML
    private Label UserName;

    @FXML
    private MenuBar menu;

    @FXML
    private CheckBox ForSomeoneId;

    @FXML
    private CheckBox deliveryid;

    @FXML
    private Label AddressLB;

    @FXML
    private Label NameLB;

    @FXML
    private Label PhoneNumberLB;



    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }

    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }
    @FXML
    void CartClick(MouseEvent event) {

    }

    @FXML
    void CloseMenu(MouseEvent event) {

    }

    @FXML
    void Delivery(MouseEvent event) {
        if(deliveryid.isSelected()) {
            ForSomeoneId.setVisible(true);
            Address.setVisible(true);
            AddressLB.setVisible(true);
            if(ForSomeoneId.isSelected()){
                Name.setVisible(true);
                NameLB.setVisible(true);
                PhoneNumber.setVisible(true);
                PhoneNumberLB.setVisible(true);
            }else {
                Name.setVisible(false);
                NameLB.setVisible(false);
                PhoneNumber.setVisible(false);
                PhoneNumberLB.setVisible(false);
            }
        }else{
            ForSomeoneId.setVisible(false);
            Address.setVisible(false);
            AddressLB.setVisible(false);
            Name.setVisible(false);
            NameLB.setVisible(false);
            PhoneNumber.setVisible(false);
            PhoneNumberLB.setVisible(false);
        }
    }

    @FXML
    void ForSomeone(MouseEvent event) {
        if(deliveryid.isSelected()){
            if(ForSomeoneId.isSelected()){
                Name.setVisible(true);
                NameLB.setVisible(true);
                PhoneNumber.setVisible(true);
                PhoneNumberLB.setVisible(true);
            }else{
                Name.setVisible(false);
                NameLB.setVisible(false);
                PhoneNumber.setVisible(false);
                PhoneNumberLB.setVisible(false);
            }
        }else{
            Name.setVisible(false);
            NameLB.setVisible(false);
            PhoneNumber.setVisible(false);
            PhoneNumberLB.setVisible(false);
        }

    }

    @FXML
    void GoToAbout(ActionEvent event) throws IOException {
        About.setCaller("Shipping");
        App.setRoot("About");
    }

    @FXML
    void GoToAccount(MouseEvent event) throws IOException {
        About.setCaller("Shipping");
        App.setRoot("Account");
    }

    @FXML
    void GoToCart(ActionEvent event) {

    }

    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        About.setCaller("Shipping");
        App.setRoot("Account");
    }


    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        App.setUser(null);
        App.setRoot("LogIn");
    }

    @FXML
    void GoToSignUp(ActionEvent event) throws IOException {
        SignUp.setCaller("Shipping");
        App.setRoot("SignUp");
    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }
    @FXML
    void Approve(MouseEvent event) throws IOException {
        boolean flag;
        InvalidAdderss.setVisible(false);
        InvalidDate.setVisible(true);
        InvalidPhoneNumber.setVisible(false);
        Date now = new Date(java.time.LocalDate.now().getYear(), java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getDayOfMonth());
        Date date = new Date(Date.getValue().getYear(), Date.getValue().getMonthValue(), Date.getValue().getDayOfMonth());
        InvalidDate.setVisible(!Utilities.checkValidDate(now, date));
        flag=(!Utilities.checkValidDate(now, date));
        if(deliveryid.isSelected()){
            InvalidAdderss.setVisible(Address.getText().equals(""));
            flag=flag||Address.getText().equals("");
            if(ForSomeoneId.isSelected()){
                InvalidName.setVisible(!Utilities.check_Validate_String(Name.getText()) || Name.getText().equals(""));
                InvalidPhoneNumber.setVisible(!Utilities.check_Validate_Phone(PhoneNumber.getText()));
                flag=flag||!Utilities.check_Validate_String(Name.getText()) || Name.getText().equals("");
                flag=flag||!Utilities.check_Validate_Phone(PhoneNumber.getText());
            }
        }
        if(flag)
            return;
        if(deliveryid.isSelected()) {
            if(ForSomeoneId.isSelected()) {
                Message ms = new Message(date, "#Deliveryandforsomeone " + Address.getText() + " " + Name.getText() + " " + PhoneNumber.getText()+ " " +Blessing.getText());
                SimpleClient.getClient().sendToServer(ms);
                SimpleClient.getClient().shippingControl = this;
            }else{
                Message ms = new Message(date, "#Deliveryandnotforsomeone " + Address.getText()+ " " +Blessing.getText());
                SimpleClient.getClient().sendToServer(ms);
                SimpleClient.getClient().shippingControl = this;
            }
        }else{
            Message ms = new Message(date,"#NoDelivery "+Blessing.getText());
            SimpleClient.getClient().sendToServer(ms);
            SimpleClient.getClient().shippingControl = this;
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date.setValue(java.time.LocalDate.now());

        if (App.getUser() == null)
            UserName.setText("Welcome guest");
        else
            UserName.setText("Welcome " + App.getUser().getFirstname());
    }

}
