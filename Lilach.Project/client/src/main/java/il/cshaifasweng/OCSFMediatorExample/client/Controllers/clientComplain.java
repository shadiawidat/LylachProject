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
import java.util.*;

public class clientComplain implements Initializable {

    public static String Caller = "";



    @FXML
    private Button Approve;

    @FXML
    private MenuButton Branches;

    @FXML
    private Button Back;

    @FXML
    private ImageView CartButton;

    @FXML
    private TextField Date;

    @FXML
    private TextField Branch;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField ID;

    @FXML
    private TextField LastName;

    @FXML
    private Label Matched;

    @FXML
    private Label InvalidBranch;

    @FXML
    private Label InvalidReason;

    @FXML
    private MenuItem MenuAbout;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private MenuItem MenuProfile;

    @FXML
    private MenuItem MenuSignOut;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private Label UserName;

    @FXML
    private TextField complainBox;

    @FXML
    private MenuBar menu;

    private List<il.cshaifasweng.OCSFMediatorExample.entities.Branch> BranchesL = new ArrayList<>();

    public List<Branch> getBranchesL() {
        return BranchesL;
    }

    public void setBranchesL(List<Branch> branchesL) {
        BranchesL = branchesL;
    }

    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }

    public static void showNote(){
        TilePane r = new TilePane();
        // create a alert
        Alert a = new Alert(Alert.AlertType.INFORMATION);

        a.setContentText("Complaint Sent Successfully!");

        Optional<ButtonType> result = a.showAndWait();
        if(!result.isPresent()) {}
        else if(result.get() == ButtonType.OK)
        {
            try {
                Account.setCaller("Catalog");
                App.setRoot("Account");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    void ApproveFunc(MouseEvent event) {
        if(complainBox.getText().equals("")) {
            InvalidReason.setVisible(true);
            return;
        }
        if(Branches.getText().equals("")) {
            InvalidBranch.setVisible(true);
            return;
        }
        else
        {
            java.util.Date d = new Date(java.time.LocalDateTime.now().getYear()-1900, java.time.LocalDateTime.now().getMonthValue(), java.time.LocalDateTime.now().getDayOfMonth());
            Message ms = new Message(d, "#SendingComplain±" + App.getUser().getUsername() + "±" + complainBox.getText() + "±" + Branches.getText());
            try {
                SimpleClient.getClient().sendToServer(ms);
            } catch (IOException e) {
                e.printStackTrace();
            }
            SimpleClient.getClient().clientComplainControl = this;

        }

    }

    @FXML
    void Back(MouseEvent event) {
        Account.setCaller("Catalog");
        try {
            App.setRoot("Account");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void CloseMenu(MouseEvent event) {

    }

    @FXML
    void GoToAbout(ActionEvent event) {
        Account.setCaller("clientComplain");
        try {
            App.setRoot("About");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void GoToAccount(MouseEvent event) {
        Account.setCaller("Catalog");
        try {
            App.setRoot("Account");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void GoToCatalog(ActionEvent event) {
        Account.setCaller("Catalog");
        try {
            App.setRoot("Catalog");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void GoToProfile(ActionEvent event) {
        Account.setCaller("Catalog");
        try {
            App.setRoot("Account");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void GoToReports(ActionEvent event) {

    }

    @FXML
    void GoToSignOut(ActionEvent event) {

    }

    @FXML
    void MenuClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            SimpleClient.getClient().sendToServer(new Message(null, "#getBranchesC"));
            SimpleClient.getClient().clientComplainControl=this;
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserName.setText("Welcome " + App.getUser().getFirstname());
        FirstName.setText(App.getUser().getFirstname());
        LastName.setText(App.getUser().getLastname());
        ID.setText(App.getUser().getID());
        PhoneNumber.setText(App.getUser().getPhonenumber());
        String[]s = String.valueOf(java.time.LocalDateTime.now()).split("T");
        s=s[0].split("-");
        Date.setText(s[2]+"/"+s[1]+"/"+s[0]);
        UserName.setDisable(true);
        UserName.setOpacity(50000000);
        FirstName.setDisable(true);
        FirstName.setOpacity(50000000);
        LastName.setDisable(true);
        LastName.setOpacity(50000000);
        ID.setDisable(true);
        ID.setOpacity(50000000);
        PhoneNumber.setDisable(true);
        PhoneNumber.setOpacity(50000000);
        Date.setDisable(true);
        Date.setOpacity(50000000);
        InvalidReason.setVisible(false);
        InvalidBranch.setVisible(false);
        if(((Client)App.getUser()).getAccounttype()==AccountTypes.Basic){
            Branches.setText(App.getUser().getMybranches().get(0).getName());
            Branches.setDisable(true);
            Branches.setOpacity(50000000);
        }
    }
}
