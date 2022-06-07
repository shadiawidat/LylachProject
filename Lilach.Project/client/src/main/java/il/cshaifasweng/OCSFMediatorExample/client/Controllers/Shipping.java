package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

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
    private Label InvalidHour;
    @FXML
    private Label InvalidPhoneNumber;

    @FXML
    private MenuItem MenuAbout;
    @FXML
    private MenuButton Mn;
    @FXML
    private MenuButton Hr;

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

    final DecimalFormat df = new DecimalFormat("0.00");



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
            InvalidAdderss.setVisible(false);
            InvalidDate.setVisible(false);
            InvalidPhoneNumber.setVisible(false);
            InvalidName.setVisible(false);
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
                Name.setText("");
                Name.setVisible(false);
                NameLB.setVisible(false);
                PhoneNumber.setText("");
                PhoneNumber.setVisible(false);
                PhoneNumberLB.setVisible(false);
            }
        }else{
            InvalidAdderss.setVisible(false);
            InvalidPhoneNumber.setVisible(false);
            InvalidName.setVisible(false);
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
        Account.setCaller("Shipping");
        App.setRoot("Account");
    }

    @FXML
    void GoToCart(ActionEvent event) throws IOException {
        Cart.setCaller("Shipping");
        App.setRoot("Cart");
    }

    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        Account.setCaller("Shipping");
        App.setRoot("Account");
    }

    @FXML
    void GoToCatalog(ActionEvent event) throws IOException {
        Catalog.setCaller("LogIn");
        App.setRoot("Catalog");
    }

    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        if(App.getUser()!=null)
            SimpleClient.getClient().sendToServer(new Message(null,"#SignOut "+App.getUser().getUsername()));
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
    void ApproveFunc(MouseEvent event) throws IOException {

        boolean flag=false;
        InvalidHour.setVisible(false);
        InvalidAdderss.setVisible(false);
        InvalidDate.setVisible(false);
        InvalidPhoneNumber.setVisible(false);
        InvalidName.setVisible(false);

        Date now = new Date(java.time.LocalDate.now().getYear(), java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getDayOfMonth());
        Date date = new Date(Date.getValue().getYear(), Date.getValue().getMonthValue(), Date.getValue().getDayOfMonth());
        Date d = new Date(2022,6,6);
        InvalidDate.setVisible(!Utilities.checkAfterValidDate(now, date));
        flag = (!Utilities.checkAfterValidDate(now, date));
        InvalidHour.setVisible(Hr.getText().equals("Hr")||Mn.getText().equals("Mn"));
        flag=flag||Hr.getText().equals("Hr")||Mn.getText().equals("Mn");
        if(date.equals(now)&&!InvalidHour.isVisible()&& Utilities.checkAfterValidDate(now, date)){
            InvalidHour.setVisible(LocalDateTime.now().getHour()>Integer.parseInt(Hr.getText()));
            flag=flag||LocalDateTime.now().getHour()>Integer.parseInt(Hr.getText());
            if(!InvalidHour.isVisible()) {
                InvalidHour.setVisible(LocalDateTime.now().getMinute()>Integer.parseInt(Mn.getText()));
                flag=flag||LocalDateTime.now().getMinute()>Integer.parseInt(Mn.getText());
            }
        }
        flag=flag||Hr.getText().equals("Hr")||Mn.getText().equals("Mn");
        if (deliveryid.isSelected()) {
            InvalidAdderss.setVisible(!Utilities.check_Validate_Address(Address.getText())||Address.getText().equals(""));
            flag = flag || Address.getText().equals("");
            if (ForSomeoneId.isSelected()) {
                InvalidName.setVisible(!Utilities.check_Validate_name(Name.getText()) || Name.getText().equals(""));
                InvalidPhoneNumber.setVisible(!Utilities.check_Validate_Phone(PhoneNumber.getText())||PhoneNumber.getText().equals(""));
                flag = flag || !Utilities.check_Validate_name(Name.getText()) || Name.getText().equals("");
                flag = flag || !Utilities.check_Validate_Phone(PhoneNumber.getText())||PhoneNumber.getText().equals("");
            }
        }
        if (flag) {
            return;
        }
        double lastprice=SimpleClient.getClient().cartControl.subTotalG;

        Message ms = new Message(date, "#ApproveShipping±" + App.getUser().getUsername() + "±" + Address.getText() + "±" + Name.getText() + "±" + PhoneNumber.getText() + "±" + Blessing.getText() + "±" + deliveryid.isSelected() + "±" + Date.getValue().getYear() + "±" + Date.getValue().getMonthValue() + "±" + Date.getValue().getDayOfMonth()+"±" + lastprice+ "±" +Hr.getText()+ "±" +Mn.getText());
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().shippingControl = this;



    }

    public void Approval() throws IOException {

        TilePane r = new TilePane();
        // create a alert
        Alert a = new Alert(Alert.AlertType.INFORMATION);

        a.setContentText("Shipping Approved!");

        Optional<ButtonType> result = a.showAndWait();
        if(!result.isPresent()) {}
        else if(result.get() == ButtonType.OK)
        {
            App.setRoot("Catalog");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        for(MenuItem menuItem : Mn.getItems())
        {
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Mn.setText(menuItem.getText());
                }
            });
        }


        for(MenuItem menuItem : Hr.getItems())
        {
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Hr.setText(menuItem.getText());
                }
            });
        }
        final DecimalFormat df = new DecimalFormat("0.00");
        MenuCart.setVisible(false);
        Date.setValue(java.time.LocalDate.now());

        if (App.getUser() == null) {
            UserName.setText("Welcome guest");
        }
        else {
            UserName.setText("Welcome " + App.getUser().getFirstname());

//                if(((Client) App.getUser()).getAccounttype()==(AccountTypes.Premium)){
//                    Saved.setText(df.format((SimpleClient.getClient().cartControl.subTotalD)+0.10*(SimpleClient.getClient().cartControl.subTotalG))+"$");
//                    Total.setText(df.format((SimpleClient.getClient().cartControl.subTotalG)*0.90)+"$");
//                }
//            else
//            {
                Total.setText(df.format(SimpleClient.getClient().cartControl.subTotalG) + "$");
                Saved.setText(df.format(SimpleClient.getClient().cartControl.subTotalD) + "$");
         //   }
            double x = (((SimpleClient.getClient().cartControl.subTotalG - SimpleClient.getClient().cartControl.subTotalD)/1.17)*0.17);

            Tax.setText(df.format(SimpleClient.getClient().cartControl.subTotalT)+"$");


        }


    }

}
