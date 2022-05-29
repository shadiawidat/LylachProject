package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
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

public class SignUp implements Initializable {
    public static String Caller = "";
    @FXML
    private MenuButton AccountType;
    @FXML
    private TextField Address;
    @FXML
    private DatePicker Birthdate;
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
    private PasswordField Password;
    @FXML
    private TextField Phone;
    @FXML
    private Label UserNameConnected;
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
    private Menu GoTo;


    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }

    @FXML
    private Label InvalidAccount;

    @FXML
    private Label InvalidAddress;

    @FXML
    private Label InvalidCard;

    @FXML
    private Label InvalidDate;

    @FXML
    private Label InvalidEmail;


    @FXML
    private Label InvalidFname;

    @FXML
    private Label InvalidID;

    @FXML
    private Label InvalidLName;

    @FXML
    private Label InvalidPassword;

    @FXML
    private Label InvalidPhone;

    @FXML
    private Label InvalidUserName;


    @FXML
    void CloseMenu(MouseEvent event) {

//        menu.setVisible(false);
    }

    @FXML
    void AllBranches(ActionEvent event) {
        AccountType.setText(AccountTypes.Gold.name());
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }

    @FXML
    void GoToCart(MouseEvent event) throws IOException {
        Cart.setCaller("SignUp");
        App.setRoot("Cart");
    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    @FXML
    void OneBranch(ActionEvent event) {
        AccountType.setText(AccountTypes.Basic.name());
    }
    @FXML
    void GoToAbout(ActionEvent event) throws IOException {
        About.setCaller("LogIn");
        App.setRoot("About");
    }

    @FXML
    void GoToSignIn(ActionEvent event) throws IOException {
        App.setRoot("LogIn");
    }

    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        About.setCaller("SignUp");
        App.setRoot("Account");
    }

    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        App.setRoot("LogIn");
    }

    @FXML
    void SignUp(MouseEvent event) throws IOException {
        System.out.println("here");
        InvalidAddress.setVisible(false);
        InvalidEmail.setVisible(true);

        InvalidID.setVisible(false);
        InvalidFname.setVisible(false);
        InvalidLName.setVisible(false);
        InvalidPassword.setVisible(false);
        InvalidPhone.setVisible(false);
        InvalidUserName.setVisible(false);
        InvalidAccount.setVisible(false);
        InvalidCard.setVisible(false);
        InvalidDate.setVisible(false);



        InvalidID.setVisible(!Utilities.check_Validate_ID(ID.getText()));
        InvalidFname.setVisible(!Utilities.check_Validate_String(FirstName.getText()) || FirstName.getText().equals(""));
        InvalidLName.setVisible(!Utilities.check_Validate_String(LastName.getText()) || LastName.getText().equals(""));
        InvalidAddress.setVisible(!Utilities.check_Validate_String(Address.getText()) || Address.getText() == "");
        InvalidEmail.setVisible((Email.getText().equals("")));
        InvalidPassword.setVisible(!Utilities.check_Validate_Pass(Password.getText()));
        InvalidPhone.setVisible((!Utilities.check_Validate_Phone(Phone.getText())));

        InvalidUserName.setVisible(!Utilities.check_Validate_Username(Username.getText()));

        InvalidAccount.setVisible(AccountType.getText().equals(""));
        InvalidCard.setVisible(!Utilities.check_Validate_Card(CreditCard.getText()));

        Date now = new Date(java.time.LocalDate.now().getYear(), java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getDayOfMonth());
        Date Birth = new Date(Birthdate.getValue().getYear(), Birthdate.getValue().getMonthValue(), Birthdate.getValue().getDayOfMonth());
        InvalidDate.setVisible(!Utilities.checkValidDate(Birth, now));

        boolean flag=!Utilities.check_Validate_ID(ID.getText());
        flag=flag||!Utilities.check_Validate_String(FirstName.getText()) || FirstName.getText().equals("");
        flag=flag||!Utilities.check_Validate_String(LastName.getText()) || LastName.getText().equals("");
        flag=flag||Email.getText().equals("");
        flag=flag||!Utilities.check_Validate_String(Address.getText()) || Address.getText().equals("");
        flag=flag||!Utilities.check_Validate_Pass(Password.getText());
        flag=flag||!Utilities.check_Validate_Phone(Phone.getText());
        flag=flag||!Utilities.check_Validate_Pass(Username.getText());
        flag=flag||AccountType.getText().equals("");
        flag=flag||!Utilities.check_Validate_Card(CreditCard.getText());
        flag=flag||!Utilities.checkValidDate(Birth, now);

        if(flag)
            return;
        Client nClient=new Client(Username.getText(),Password.getText(),FirstName.getText(),LastName.getText(),Email.getText(),Phone.getText(),Birth,
                Address.getText(), permissions.CLIENT,ID.getText(),CreditCard.getText(),AccountTypes.Basic,0.0);
        Message ms = new Message(nClient, "#UserExist " + Username.getText());
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().signUpControl=this;
    }

    public void NewUser(User user) throws IOException {
        App.setUser(user);
        App.setRoot("Catalog");
        Catalog.setCaller("LogIn");
    }
    public void UserExist() {
        System.out.println("User Already exist");
        Alert a = new Alert(Alert.AlertType.NONE);

        // set alert type
        a.setAlertType(Alert.AlertType.ERROR);

        a.setContentText("User already exists!");

        a.showAndWait();
    }

    @FXML
    void Subscription(ActionEvent event) {
        AccountType.setText(AccountTypes.Premium.name());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Birthdate.setValue(java.time.LocalDate.now());
        if (App.getUser() == null) {
            MenuSignOut.setVisible(false);
            MenuProfile.setVisible(false);
            MenuCart.setVisible(false);
            GoTo.setVisible(false);
            UserNameConnected.setText("Welcome guest");
        }
        else {
            MenuSignIn.setVisible(false);
            UserNameConnected.setText("Welcome " + App.getUser().getFirstname());
        }
    }
}
