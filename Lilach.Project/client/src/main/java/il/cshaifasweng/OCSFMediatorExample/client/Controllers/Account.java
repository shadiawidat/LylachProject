package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Account implements Initializable {

    public static String Caller = "";

    @FXML
    private Label InvalidPerm;

    @FXML
    private DatePicker BirthdateMN;

    @FXML
    private Label InvalidAd;

    @FXML
    private Label BranchLB;

    @FXML
    private Label InvalidBranch;

    @FXML
    private MenuButton Branches;

    @FXML
    private Label InvalidBD;

    @FXML
    private Label InvalidEM;
    @FXML
    private Label InvalidPassword;

    @FXML
    private Label InvalidFN;

    @FXML
    private Label InvalidID;

    @FXML
    private Label InvalidLN;

    @FXML
    private Label InvalidPH;

    @FXML
    private Label InvalidUS;

    @FXML
    private Button commplainBTN;


    @FXML
    private MenuButton AccountType;

    @FXML
    private MenuButton PermisionsMN;

    @FXML
    private ImageView FreezeIcon;

    @FXML
    private Label FreezeLB;

    @FXML
    private Label PasswordLB;

    @FXML
    private Label InvalidCrdeitCard;

    @FXML
    private TextField Address;

    @FXML
    private TextField NewBranch;


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
    private Label PermisionsLB;

    @FXML
    private Label BirthDateLB;

    @FXML
    private Label TypeLB;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private MenuItem MenuAbout;
    @FXML
    private MenuItem MenuCart;
    @FXML
    private MenuItem MenuProfile;
    @FXML
    private MenuItem CatalogBtn;
    @FXML
    private MenuItem MenuSignIn;
    @FXML
    private MenuItem MenuSignOut;
    @FXML
    private MenuItem MenuSignUp;
    @FXML
    private MenuItem Reports;
    @FXML
    private MenuItem Complains;
    @FXML
    private MenuBar menu;
    @FXML
    private Button UpdateUser;
    @FXML
    private Button FreezeUser;
    @FXML
    private Button UnFreeze;
    @FXML
    private Button AddUser;
    @FXML
    private Button RemoveUser;
    @FXML
    private Button Search;
    @FXML
    private Button MyOrders;
    @FXML
    private Button Clear;

    private User user;

    private permissions perm;

    private List<Branch> BranchesL = new ArrayList<>();

    private boolean addus=false;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }

    public void fillInfo(User user) {
        //TextFields
        if(user != null) {
            FreezeUser.setVisible(!user.isFreeze());
            FreezeLB.setVisible(user.isFreeze());
            UnFreeze.setVisible(user.isFreeze());
        }
        else{
            FreezeUser.setVisible(false);
            FreezeLB.setVisible(false);
            UnFreeze.setVisible(false);
        }


        Clear.setVisible(true);
        AddUser.setVisible(false);



        //Invalids
        InvalidFN.setVisible(false);
        InvalidLN.setVisible(false);
        InvalidID.setVisible(false);
        InvalidUS.setVisible(false);
        InvalidBD.setVisible(false);
        InvalidAd.setVisible(false);
        InvalidPH.setVisible(false);
        InvalidEM.setVisible(false);
        InvalidPerm.setVisible(false);
        InvalidPassword.setVisible(false);
        InvalidBranch.setVisible(false);
        InvalidCrdeitCard.setVisible(false);



        BranchLB.setVisible(false);
        Branches.setVisible(false);
        PasswordLB.setVisible(false);
        Password.setVisible(false);
        //Decide Later...
        BirthdateMN.setVisible(false);
        BirthDate.setVisible(true);

        UserName.setText("Welcome " + App.getUser().getFirstname());

        if (user == null) {
            user = App.getUser();
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("User Doesn't Exist");
            a.showAndWait();
            Search.setVisible(false);
            UpdateUser.setVisible(false);
            CreditCardLB.setVisible(false);
            TypeLB.setVisible(false);
            CreditCard.setVisible(false);
            AccountType.setVisible(false);

            FreezeIcon.setVisible(false);
            FreezeLB.setVisible(false);

            FreezeUser.setVisible(false);

            RemoveUser.setVisible(false);


            UnFreeze.setVisible(false);
            MyOrders.setVisible(false);
            //decide later
            BirthdateMN.setVisible(false);
            BirthDate.setVisible(true);
            PasswordLB.setVisible(false);
            Password.setVisible(false);
            PermisionsLB.setVisible(true);
            PermisionsMN.setVisible(true);
            PermisionsMN.setText(user.getPermission().name());

        } else
        {

            RemoveUser.setVisible(true);
            UpdateUser.setVisible(true);
            if (user.getPermission() == permissions.CLIENT) {
                CreditCard.setVisible(true);
                CreditCardLB.setVisible(true);
                AccountType.setVisible(true);
                TypeLB.setVisible(true);
                Client c = (Client) user;
                CreditCard.setText(c.getCreditCard());
                AccountType.setText((c.getAccounttype().name()));
                BranchLB.setVisible(true);
                Branches.setVisible(true);
                int i = c.getMybranches().size();
                System.out.println(i);
                if(i == 1){

                Branches.setText(c.getMybranches().get(0).getName());
                }else{
                    Branches.setText("All Branches");
                    Branches.setDisable(true);
                }
            }
            else if (user.getPermission() != permissions.ADMIN ) {
                CreditCard.setVisible(false);
                CreditCardLB.setVisible(false);
                AccountType.setVisible(false);
                TypeLB.setVisible(false);
                PermisionsLB.setVisible(true);
                PermisionsMN.setVisible(true);
                PermisionsMN.setText(user.getPermission().name());
                if (user.getPermission() == permissions.MANAGER) {

                    BranchLB.setVisible(true);
                    Branches.setVisible(true);
                    Branches.setText(((BranchManager) user).getMybranch().getName());

                }
                else if (user.getPermission() == permissions.WORKER) {
                    BranchLB.setVisible(true);
                    Branches.setVisible(true);
                    PermisionsLB.setVisible(true);
                    PermisionsMN.setVisible(true);
                    PermisionsMN.setDisable(false);
                    PermisionsMN.setText(user.getPermission().name());
                }
            }

            if (user.getPermission() == permissions.ADMIN) {
                CreditCardLB.setVisible(false);
                TypeLB.setVisible(false);
                CreditCard.setVisible(false);
                AccountType.setVisible(false);

                FreezeIcon.setVisible(false);
                FreezeLB.setVisible(false);

                FreezeUser.setVisible(false);

                RemoveUser.setVisible(false);
                UpdateUser.setVisible(true);

                UnFreeze.setVisible(false);
                MyOrders.setVisible(false);
                //decide later
                BirthdateMN.setVisible(false);
                BirthDate.setVisible(true);
                PasswordLB.setVisible(false);
                Password.setVisible(false);
                PermisionsLB.setVisible(true);
                PermisionsMN.setVisible(true);
                PermisionsMN.setText(user.getPermission().name());
            }

        }
        FirstName.setText(user.getFirstname());
        LastName.setText(user.getLastname());
        ID.setText(user.getID());
        Username.setText(user.getUsername());

        Date BD = user.getBirthday();

        BirthDate.setText(BD.getDate() + "/" + BD.getMonth() + "/" + BD.getYear());
        Address.setText(user.getAddress());
        Phone.setText(user.getPhonenumber());
        Email.setText(user.getEmail());


    }

    @FXML
    void OneBranch(ActionEvent event) {
        Branches.setVisible(true);
        BranchLB.setVisible(true);
        AccountType.setText(AccountTypes.Basic.name());
    }

    @FXML
    void AllBranches(ActionEvent event) {
        Branches.setVisible(false);
        BranchLB.setVisible(false);
        InvalidBranch.setVisible(false);
        AccountType.setText(AccountTypes.Gold.name());
    }

    public void loadBranches() {
        Branches.getItems().clear();

        for (Branch branch : BranchesL) {
            MenuItem mt = new MenuItem(branch.getName());

            mt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Branches.setText(branch.getName());
                }
            });
            Branches.getItems().add(mt);
        }
    }

    @FXML
    void CorpManagerItem(ActionEvent event) {
        perm = permissions.CorpManager;
        PermisionsMN.setText(permissions.CorpManager.name());
        BranchLB.setVisible(false);
        Branches.setVisible(false);
        InvalidBranch.setVisible(false);
        NewBranch.setVisible(false);
    }

    @FXML
    void WorkerItem(ActionEvent event) {
        perm = permissions.WORKER;
        PermisionsMN.setText(permissions.WORKER.name());
        BranchLB.setVisible(true);
        Branches.setVisible(true);
        NewBranch.setVisible(false);
    }

    @FXML
    void ManagerItem(ActionEvent event) {
        perm = permissions.MANAGER;
        PermisionsMN.setText("BRANCH MANAGER");
        BranchLB.setVisible(true);
        Branches.setVisible(false);
        NewBranch.setVisible(true);

    }

    @FXML
    void UserChanges(KeyEvent event) {

        FreezeUser.setVisible(false);
        RemoveUser.setVisible(false);
        UpdateUser.setVisible(false);
        Clear.setVisible(false);
        UnFreeze.setVisible(false);
        if (App.getUser().getPermission().equals(permissions.ADMIN))
            Clear.setVisible(true);
        Search.setVisible(true);
        if(addus)
        AddUser.setVisible(true);
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
    void GoToCatalog(ActionEvent event) throws IOException {
        Catalog.setCaller("LogIn");
        App.setRoot("Catalog");
    }
    @FXML
    void GoToComplains(ActionEvent event) throws IOException {
        AllComplains.setCaller("Catalog");
        App.setRoot("AllComplains");
    }

    @FXML
    void GoToReports(ActionEvent event) throws IOException {
        Report.setCaller("Catalog");
        App.setRoot("Report");
    }

    @FXML
    void GoToProfile(ActionEvent event) {

    }

    @FXML
    void GoToSignIn(ActionEvent event) {

    }

    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        if (App.getUser() != null)
            SimpleClient.getClient().sendToServer(new Message(null, "#SignOut " + App.getUser().getUsername()));
        App.setUser(null);
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

    public void emptyFields() {
        InvalidFN.setVisible(false);
        InvalidLN.setVisible(false);
        InvalidID.setVisible(false);
        InvalidUS.setVisible(false);
        InvalidBD.setVisible(false);
        InvalidAd.setVisible(false);
        InvalidPH.setVisible(false);
        InvalidEM.setVisible(false);
        InvalidPerm.setVisible(false);
        InvalidPassword.setVisible(false);
        InvalidCrdeitCard.setVisible(false);
        UserName.setText("Welcome " + App.getUser().getFirstname());
        FirstName.setText("");
        LastName.setText("");
        ID.setText("");
        Username.setText("");
        BirthDate.setText("");
        Address.setText("");
        Phone.setText("");
        Email.setText("");
        FreezeIcon.setVisible(true);

        FreezeLB.setVisible(false);
        CreditCard.setVisible(false);
        CreditCardLB.setVisible(false);
        AccountType.setVisible(false);
        TypeLB.setVisible(false);
        BirthDate.setVisible(false);
        PermisionsLB.setVisible(false);
        PermisionsMN.setVisible(false);
        PasswordLB.setVisible(false);
        Password.setVisible(false);
        BirthdateMN.setVisible(false);
        BirthDateLB.setVisible(true);
    }

    @FXML
    void Clear(MouseEvent event) {
        addus=true;
        InvalidFN.setVisible(false);
        InvalidLN.setVisible(false);
        InvalidID.setVisible(false);
        InvalidUS.setVisible(false);
        InvalidBD.setVisible(false);
        InvalidAd.setVisible(false);
        InvalidPH.setVisible(false);
        InvalidEM.setVisible(false);
        InvalidPerm.setVisible(false);
        InvalidPassword.setVisible(false);
        InvalidCrdeitCard.setVisible(false);
        MyOrders.setVisible(false);
        UserName.setText("Welcome " + App.getUser().getFirstname());
        FirstName.setText("");
        LastName.setText("");
        ID.setText("");
        Username.setText("");
        BirthDate.setText("");
        Address.setText("");
        Phone.setText("");
        Email.setText("");
        PermisionsMN.setText("");
        Branches.setText("");
        BirthdateMN.setAccessibleText("");
        Password.setText("");
        FreezeIcon.setVisible(true);
        FreezeLB.setVisible(false);
        CreditCard.setVisible(false);
        CreditCardLB.setVisible(false);
        AccountType.setVisible(false);
        TypeLB.setVisible(false);
        PermisionsLB.setVisible(true);
        PermisionsMN.setVisible(true);
        PasswordLB.setVisible(true);
        Password.setVisible(true);
        BirthdateMN.setVisible(true);
        BirthDate.setVisible(false);
        BirthdateMN.setValue(java.time.LocalDate.now());
        BranchLB.setVisible(false);
        Branches.setVisible(false);
        FreezeIcon.setVisible(false);
        FreezeLB.setVisible(false);
        AddUser.setVisible(false);
        RemoveUser.setVisible(false);
        FreezeUser.setVisible(false);
        UnFreeze.setVisible(false);
        UpdateUser.setVisible(false);
        Search.setVisible(false);
        PermisionsMN.setDisable(false);

        AddUser.setVisible(true);

        // resetFields();

    }

    @FXML
    void UnFreeze(MouseEvent event) throws IOException {
        Message ms = new Message(null, "#FreezeUser " + Username.getText());
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().accountControl = this;
    }

    @FXML
    void Search(MouseEvent event) throws IOException {
        if(!Utilities.check_Validate_Username(Username.getText())){
            InvalidUS.setVisible(true);
            return;
        }
        Message ms = new Message(null, "#SearchUser " + Username.getText());
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().accountControl = this;
    }

    @FXML
    void UpdateUser(MouseEvent event) throws IOException {

        if (AccountType.isVisible()) {
            if (AccountType.getText().equals(AccountTypes.Basic.name())) {
                Client client = new Client(App.getUser().getUsername(), App.getUser().getPassword(), FirstName.getText(), LastName.getText(), Email.getText(), Phone.getText(), App.getUser().getBirthday(), Address.getText(), App.getUser().getPermission(), App.getUser().getID(), CreditCard.getText(), AccountTypes.Basic, 0.0);
                Message ms = new Message(client, "#UpdateUser " + Username.getText());
                SimpleClient.getClient().sendToServer(ms);
                SimpleClient.getClient().accountControl = this;
            } else if (AccountType.getText().equals(AccountTypes.Gold.name())) {
                Client client = new Client(App.getUser().getUsername(), App.getUser().getPassword(), FirstName.getText(), LastName.getText(), Email.getText(), Phone.getText(), App.getUser().getBirthday(), Address.getText(), App.getUser().getPermission(), App.getUser().getID(), CreditCard.getText(), AccountTypes.Gold, 0.0);
                Message ms = new Message(client, "#UpdateUser " + Username.getText());
                SimpleClient.getClient().sendToServer(ms);
                SimpleClient.getClient().accountControl = this;
            } else {
                Client client = new Client(App.getUser().getUsername(), App.getUser().getPassword(), FirstName.getText(), LastName.getText(), Email.getText(), Phone.getText(), App.getUser().getBirthday(), Address.getText(), App.getUser().getPermission(), App.getUser().getID(), CreditCard.getText(), AccountTypes.Premium, 0.0);
                Message ms = new Message(client, "#UpdateUser " + Username.getText());
                SimpleClient.getClient().sendToServer(ms);
                SimpleClient.getClient().accountControl = this;
            }
        } else {
            User user = new User(App.getUser().getUsername(), App.getUser().getPassword(), FirstName.getText(), LastName.getText(), Email.getText(), Phone.getText(), App.getUser().getBirthday(), Address.getText(), App.getUser().getPermission(), App.getUser().getID(), CreditCard.getText(), App.getUser().isFreeze());
            Message ms = new Message(user, "#UpdateUser " + Username.getText());
            SimpleClient.getClient().sendToServer(ms);
            SimpleClient.getClient().accountControl = this;
        }
    }

    @FXML
    void FreezeUser(MouseEvent event) throws IOException {

        Message ms = new Message(null, "#FreezeUser " + Username.getText());
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().accountControl = this;

    }

    @FXML
    void AddUser(MouseEvent event) throws IOException {
        InvalidFN.setVisible(false);
        InvalidLN.setVisible(false);
        InvalidID.setVisible(false);
        InvalidUS.setVisible(false);
        InvalidBD.setVisible(false);
        InvalidAd.setVisible(false);
        InvalidPH.setVisible(false);
        InvalidEM.setVisible(false);
        InvalidPerm.setVisible(false);
        InvalidPassword.setVisible(false);
        InvalidCrdeitCard.setVisible(false);
        InvalidBranch.setVisible(false);
        BranchLB.setVisible(false);
        Branches.setVisible(false);


        if (BirthdateMN.getValue() == null) {
            return;
        }


        InvalidID.setVisible(!Utilities.check_Validate_ID(ID.getText()));
        InvalidFN.setVisible(!Utilities.check_Validate_String(FirstName.getText()) || FirstName.getText().equals(""));
        InvalidLN.setVisible(!Utilities.check_Validate_String(LastName.getText()) || LastName.getText().equals(""));
        InvalidAd.setVisible(!Utilities.check_Validate_String(Address.getText()) || Address.getText() == "");
        InvalidEM.setVisible((Email.getText().equals("")));
        InvalidPH.setVisible((!Utilities.check_Validate_Phone(Phone.getText())));
        InvalidPassword.setVisible(!Utilities.check_Validate_Pass(Password.getText()));
        InvalidUS.setVisible(!Utilities.check_Validate_String(Username.getText()) || Username.getText().equals(""));
        InvalidPerm.setVisible(!Utilities.check_Validate_name(PermisionsMN.getText()) || PermisionsMN.getText() == "");
        InvalidBranch.setVisible(Branches.getText().equals("")&&NewBranch.getText().equals(""));

        Date now = new Date(java.time.LocalDate.now().getYear(), java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getDayOfMonth());
        Date Birth = new Date(BirthdateMN.getValue().getYear(), BirthdateMN.getValue().getMonthValue(), BirthdateMN.getValue().getDayOfMonth());
        InvalidBD.setVisible(!Utilities.checkValidDate(Birth, now));

        boolean flag = !Utilities.check_Validate_ID(ID.getText());
        flag = flag || !Utilities.check_Validate_String(FirstName.getText()) || FirstName.getText().equals("");
        flag = flag || !Utilities.check_Validate_String(LastName.getText()) || LastName.getText().equals("");
        flag = flag || Email.getText().equals("");
        flag = flag || !Utilities.check_Validate_String(Address.getText()) || Address.getText().equals("");
        InvalidPassword.setVisible(!Utilities.check_Validate_Pass(Password.getText()));
        flag = flag || !Utilities.check_Validate_Phone(Phone.getText());
        flag = flag || !Utilities.check_Validate_Pass(Username.getText());
        flag = flag || !Utilities.checkValidDate(Birth, now);
        flag = flag || !Utilities.check_Validate_name(PermisionsMN.getText()) || PermisionsMN.getText().equals("");
        flag = flag || (Branches.getText().equals("")&&NewBranch.getText().equals(""));
        if (PermisionsMN.getText().equals("")) {

            InvalidBranch.setVisible(false);
        }
        if (PermisionsMN.getText().equals(permissions.MANAGER)) {
            BranchLB.setVisible(true);
            NewBranch.setVisible(true);

            InvalidBranch.setVisible(Branches.getText() == "");
        }

        if (PermisionsMN.getText().equals("WORKER")) {
            BranchLB.setVisible(true);
            Branches.setVisible(true);
            InvalidBranch.setVisible(Branches.getText() == "");}

        if (flag) {
            return;
        }
        System.out.println("helloasdas");
        if (PermisionsMN.getText().equals("WORKER")) {

            User nuser = new User(Username.getText(), Password.getText(), FirstName.getText(), LastName.getText(), Email.getText(), Phone.getText(), Birth, Address.getText(), permissions.WORKER, ID.getText(), false);

            Branch bebe = BranchesL.get(0);
            for (Branch branch : BranchesL) {
                if (branch.getName().equals(Branches.getText())) {
                    break;
                }
            }
            Message ms = new Message(nuser, "#AddUser " + Username.getText() + " " + bebe.getName());
            SimpleClient.getClient().sendToServer(ms);
            SimpleClient.getClient().accountControl = this;

        } else if (PermisionsMN.getText().equals("BRANCH MANAGER")) {
            System.out.println("helloasdas");
            BranchManager branchManager = new BranchManager(Username.getText(), Password.getText(), FirstName.getText(), LastName.getText(), Email.getText(), Phone.getText(), Birth, Address.getText(), permissions.MANAGER, ID.getText(), false, null);

            Branch bebe = BranchesL.get(0);
            for (Branch branch : BranchesL) {
                if (branch.getName().equals(Branches.getText())) {
                    break;
                }
            }
            Message ms = new Message(branchManager, "#AddUser " + Username.getText() + " " + NewBranch.getText());
            SimpleClient.getClient().sendToServer(ms);
            SimpleClient.getClient().accountControl = this;
        } else {
//            CoroporationManager coroporationManager = new CoroporationManager(Username.getText(), Password.getText(), FirstName.getText(), LastName.getText(), Email.getText(), Phone.getText(), Birth, Address.getText(), permissions.CorpManager, ID.getText(), false, null);
//            Message ms = new Message(coroporationManager, "#AddUser " + Username.getText());
//            SimpleClient.getClient().sendToServer(ms);
//            SimpleClient.getClient().accountControl = this;
        }

    }

    @FXML
    void Subscription(ActionEvent event) {
        Branches.setVisible(false);
        BranchLB.setVisible(false);
        InvalidBranch.setVisible(false);
        AccountType.setText(AccountTypes.Premium.name());
    }

    public void UserAlreadyExist() {

        Alert a = new Alert(Alert.AlertType.NONE);

        // set alert type
        a.setAlertType(Alert.AlertType.CONFIRMATION);

        a.setContentText("User Already Exist!");

        a.showAndWait();
    }

    public void UserAdded() {

        Alert a = new Alert(Alert.AlertType.NONE);

        // set alert type
        a.setAlertType(Alert.AlertType.CONFIRMATION);

        a.setContentText("User Was added successfully!");

        a.showAndWait();
    }

    public void ShowNote(String note) {
        Alert a = new Alert(Alert.AlertType.NONE);

        // set alert type
        a.setAlertType(Alert.AlertType.ERROR);

        a.setContentText(note);

        a.showAndWait();
    }

    @FXML
    void RemoveUser(MouseEvent event) throws IOException {
        Message ms = new Message(null, "#RemoveUser " + Username.getText());
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().accountControl = this;
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
    void MyOrders(MouseEvent event) throws IOException {
        MyCarts.setCaller("Account");
        App.setRoot("MyCarts");
    }

    public void resetFields() {

            UserName.setText("Welcome " + App.getUser().getFirstname());
            if (!App.getUser().isFreeze()) {
                FreezeIcon.setVisible(false);
                FreezeLB.setVisible(false);
            }
        setUser(App.getUser());
        FirstName.setText(App.getUser().getFirstname());
        LastName.setText(App.getUser().getLastname());
        ID.setText(App.getUser().getID());
        Username.setText(App.getUser().getUsername());

        Date BD = App.getUser().getBirthday();
        BirthDate.setVisible(true);
        BirthdateMN.setVisible(false);
        BirthDate.setText(BD.getDate() + "/" + BD.getMonth() + "/" + BD.getYear());
        Address.setText(App.getUser().getAddress());
        Phone.setText(App.getUser().getPhonenumber());
        Email.setText(App.getUser().getEmail());
        MyOrders.setVisible(false);


        if (App.getUser().getPermission() == permissions.CLIENT)
        {
            Password.setVisible(false);
            PasswordLB.setVisible(false);
            CreditCard.setText(((Client) App.getUser()).getCreditCard());
            AccountType.setText(((Client) App.getUser()).getAccounttype().name());
            UpdateUser.setVisible(false);
            FreezeUser.setVisible(false);
            AddUser.setVisible(false);
            RemoveUser.setVisible(false);
            Search.setVisible(false);
            Clear.setVisible(false);
            UnFreeze.setVisible(false);
            AccountType.setDisable(true);
            MyOrders.setVisible(true);
            AccountType.setOpacity(3000);

        } else if (App.getUser().getPermission() == permissions.WORKER || App.getUser().getPermission() == permissions.MANAGER || App.getUser().getPermission() == permissions.CorpManager) {
            UpdateUser.setVisible(false);
            FreezeUser.setVisible(false);
            AddUser.setVisible(false);
            RemoveUser.setVisible(false);
            Search.setVisible(false);
            Clear.setVisible(false);
            UnFreeze.setVisible(false);
            CreditCard.setVisible(false);
            AccountType.setVisible(false);
            TypeLB.setVisible(false);
            CreditCardLB.setVisible(false);
            Password.setVisible(false);
            PasswordLB.setVisible(false);
            PermisionsLB.setVisible(true);
            PermisionsMN.setVisible(true);
            PermisionsMN.setText(App.getUser().getPermission().name());
        } else if (App.getUser().getPermission() == permissions.ADMIN) {
            System.out.println("hello");
            Password.setVisible(false);
            PasswordLB.setVisible(false);
            CreditCard.setVisible(false);
            CreditCardLB.setVisible(false);
            AccountType.setVisible(false);
            TypeLB.setVisible(false);
            PermisionsLB.setVisible(true);
            PermisionsMN.setVisible(true);
            PermisionsMN.setText(App.getUser().getPermission().name());
            PermisionsMN.setDisable(true);
            PermisionsMN.setOpacity(30000);

        }

        if (App.getUser().getPermission()==permissions.CLIENT) {
            CartB.setVisible(false);
            MenuSignUp.setVisible(false);
            MenuCart.setVisible(false);
            AccountType.setText(((Client)App.getUser()).getAccounttype().name());
        }
        if (App.getUser().getPermission()==permissions.MANAGER) {
            BranchLB.setVisible(true);
            Branches.setVisible(true);
            Branches.setText(((BranchManager) App.getUser()).getMybranch().getName());
        }
        if (App.getUser().getPermission()==permissions.WORKER) {
            BranchLB.setVisible(true);
            Branches.setVisible(true);
        }
    }

    @FXML
    void complain(MouseEvent event) {
        clientComplain.setCaller("Account");
        try {
            App.setRoot("clientComplain");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Branch> getBranchesL() {
        return BranchesL;
    }

    public void setBranchesL(List<Branch> branchesL) {
        BranchesL = branchesL;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SimpleClient.getClient().sendToServer(new Message(null, "#getBranchesA"));
            SimpleClient.getClient().accountControl = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
        resetFields();
        FreezeUser.setVisible(false);
        AddUser.setVisible(false);
        RemoveUser.setVisible(false);
        UpdateUser.setVisible(false);
        UnFreeze.setVisible(false);
        if(App.getUser().getPermission()==permissions.CLIENT){
            CartB.setVisible(false);
            MenuSignUp.setVisible(false);
            MenuCart.setVisible(false);
            if(((Client)App.getUser()).getAccounttype()==AccountTypes.Basic) {
                BranchLB.setVisible(true);
                Branches.setVisible(true);
                Branches.setText(App.getUser().getMybranches().get(0).getName());
                Branches.setDisable(true);
                Branches.setOpacity(3000);
            }
        }
        if(App.getUser().getPermission()==permissions.MANAGER) {
            BranchLB.setVisible(true);
            Branches.setVisible(true);
            Branches.setText(((BranchManager) App.getUser()).getMybranch().getName());
            Branches.setDisable(true);
            Branches.setOpacity(3000);
            PermisionsMN.setDisable(true);
            PermisionsMN.setOpacity(3000);
        }
        if(App.getUser().getPermission()==permissions.WORKER) {
            BranchLB.setVisible(true);
            Branches.setVisible(true);
            Branches.setText( App.getUser().getMybranches().get(0).getName());
            Branches.setDisable(true);
            Branches.setOpacity(3000);
            PermisionsMN.setDisable(true);
            PermisionsMN.setOpacity(3000);
        }
        if(App.getUser().getPermission()==permissions.CorpManager) {
            PermisionsMN.setDisable(true);
            PermisionsMN.setOpacity(3000);
        }
        if (((App.getUser().getPermission()==permissions.MANAGER)) || (App.getUser().getPermission()==permissions.CorpManager)) {
            Reports.setVisible(true);
            Complains.setVisible(true);
        }
        else {
            Reports.setVisible(false);
            Complains.setVisible(false);
        }
        if(App.getUser().getPermission()==(permissions.ADMIN)) {
            CatalogBtn.setVisible(false);

        }

    }



    }

