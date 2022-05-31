package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Client;
import il.cshaifasweng.OCSFMediatorExample.entities.permissions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Account implements Initializable {

    public static String Caller = "";
    @FXML
    private TextField AccountType;

    @FXML
    private ImageView FreezeIcon;

    @FXML
    private Label FreezeLB;

    @FXML
    private TextField Address;
    @FXML
    private TextField BirthDate;
    @FXML
    private ImageView CartB;
    @FXML
    private TextField CreditCard;
    @FXML
    private TextField Email;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField ID;
    @FXML
    private TextField LastName;
    @FXML
    private ImageView MenuBtn;
    @FXML
    private TextField Phone;
    @FXML
    private Label UserName;

    @FXML
    private Label CreditCardLB;

    @FXML
    private Label TypeLB;
    @FXML
    private TextField Username;
    @FXML
    private MenuItem MenuAbout;
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
    private MenuBar menu;
    @FXML
    private Button UpdateUser;
    @FXML
    private Button FreezeUser;
    @FXML
    private Button AddUser;
    @FXML
    private Button RemoveUser;
    @FXML
    private Button Search;
    @FXML
    private Button MyOrders;


    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }

    @FXML
    void GoToAbout(ActionEvent event) throws IOException {
        About.setCaller("Account");
        App.setRoot("About");
    }

    @FXML
    void GoToCartMN(ActionEvent event) throws IOException {
        Cart.setCaller("Account");
        App.setRoot("Cart");
    }

    @FXML
    void GoToProfile(ActionEvent event) {

    }

    @FXML
    void GoToSignIn(ActionEvent event) {

    }

    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {

        App.setRoot("LogIn");
    }

    @FXML
    void GoToSignUp(ActionEvent event) throws IOException {
        SignUp.setCaller("Account");
        App.setRoot("SignUp");
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }

    @FXML
    void CloseMenu(MouseEvent event) {
       // menu.setVisible(false);
    }

    @FXML
    void GoToAccount(MouseEvent event) {

    }

    @FXML
    void Search(MouseEvent event) {

    }

    @FXML
    void UpdateUser(MouseEvent event) {

    }

    @FXML
    void FreezeUser(MouseEvent event) {

    }

    @FXML
    void AddUser(MouseEvent event) {

    }

    @FXML
    void RemoveUser(MouseEvent event) {

    }

    @FXML
    void GoToCart(MouseEvent event) throws IOException {
        Cart.setCaller("Account");
        App.setRoot("Cart");
    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    @FXML
    void MyOrders(MouseEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UserName.setText("Welcome " + App.getUser().getFirstname());
        if(!App.getUser().isFreeze())
        {
            FreezeIcon.setImage(null);
            FreezeLB.setVisible(false);
        }

        FirstName.setText(App.getUser().getFirstname());
        LastName.setText(App.getUser().getLastname());
        FirstName.setText(App.getUser().getFirstname());
        ID.setText(App.getUser().getID());
        Username.setText(App.getUser().getUsername());

        Date BD=App.getUser().getBirthday();

        BirthDate.setText(BD.getDate()+"/"+BD.getMonth()+"/"+BD.getYear());
        Address.setText(App.getUser().getAddress());
        Phone.setText(App.getUser().getPhonenumber());
        Email.setText(App.getUser().getEmail());




        if(App.getUser().getPermission() == permissions.CLIENT){
            CreditCard.setText(((Client)App.getUser()).getCreditCard());
            AccountType.setText(((Client)App.getUser()).getAccounttype().name());
            UpdateUser.setVisible(false);
            FreezeUser.setVisible(false);
            AddUser.setVisible(false);
            RemoveUser.setVisible(false);
            Search.setVisible(false);
        }
        else if(App.getUser().getPermission() == permissions.WORKER || App.getUser().getPermission() == permissions.MANAGER) {
            UpdateUser.setVisible(false);
            FreezeUser.setVisible(false);
            AddUser.setVisible(false);
            RemoveUser.setVisible(false);
            Search.setVisible(false);
            MyOrders.setVisible(false);
            CreditCard.setVisible(false);
            AccountType.setVisible(false);
            TypeLB.setVisible(false);
            CreditCardLB.setVisible(false);
        }
        else if(App.getUser().getPermission() == permissions.ADMIN){
            CreditCard.setVisible(false);
            AccountType.setVisible(false);
            TypeLB.setVisible(false);
            CreditCardLB.setVisible(false);
            MyOrders.setVisible(false);
        }


        }
}
