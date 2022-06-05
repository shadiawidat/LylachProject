package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Report implements Initializable {
    public static String Caller = "";
    @FXML
    private ImageView CartButton;

    @FXML
    private CheckBox CompareTo;

    @FXML
    private MenuButton FirstReportBranch;

    @FXML
    private DatePicker FirstReportFrom;

    @FXML
    private TextField FirstReportFromWhite;

    @FXML
    private DatePicker FirstReportTo;

    @FXML
    private TextField FirstReportToWhite;

    @FXML
    private MenuButton FirstReportType;

    @FXML
    private Label InvalidFRBranch;

    @FXML
    private Label InvalidFRFrom;

    @FXML
    private Label InvalidFRTo;

    @FXML
    private Label InvalidFRType;

    @FXML
    private Label InvalidSRBranch;

    @FXML
    private Label InvalidSRFrom;

    @FXML
    private Label InvalidSRTo;

    @FXML
    private Label InvalidSRType;

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
    private MenuItem Complains;

    @FXML
    private MenuButton SecondReportBranch;

    @FXML
    private Label SecondReportBranchLable;

    @FXML
    private DatePicker SecondReportFrom;

    @FXML
    private Label SecondReportFromDateLable;

    @FXML
    private TextField SecondReportFromWhite;

    @FXML
    private DatePicker SecondReportTo;

    @FXML
    private Label SecondReportToDateLable;

    @FXML
    private TextField SecondReportToWhite;

    @FXML
    private MenuButton SecondReportType;

    @FXML
    private Label SecondReportTypeLable;

    @FXML
    private Button View;

    @FXML
    private MenuBar menu;

    public List<il.cshaifasweng.OCSFMediatorExample.entities.Branch> getBranchesL() {
        return BranchesL;
    }

    public void setBranchesL(List<il.cshaifasweng.OCSFMediatorExample.entities.Branch> branchesL) {
        BranchesL = branchesL;
    }

    private List<Branch> BranchesL=new ArrayList<>();
    public void loadBranches(){
        FirstReportBranch.getItems().clear();
        SecondReportBranch.getItems().clear();
        for(Branch branch:BranchesL)
        {
            MenuItem mt=new MenuItem(branch.getName());

            mt.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    FirstReportBranch.setText(branch.getName());
                    SecondReportBranch.setText(branch.getName());
                }
            });
            FirstReportBranch.getItems().add(mt);
            SecondReportBranch.getItems().add(mt);
        }
    }
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
    void CloseMenu(MouseEvent event) {

    }

    @FXML
    void Complain(ActionEvent event) {
        FirstReportType.setText("Complain");
    }

    @FXML
    void Complain2(ActionEvent event) {
        SecondReportType.setText("Complain");
    }

    @FXML
    void GoToAbout(ActionEvent event) throws IOException {
        About.setCaller("Report");
        App.setRoot("About");
    }

    @FXML
    void GoToCart(ActionEvent event) {

    }

    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        Account.setCaller("Report");
        App.setRoot("Account");
    }

    @FXML
    void GoToCatalog(ActionEvent event) throws IOException {
        Catalog.setCaller("LogIn");
        App.setRoot("Catalog");
    }

    @FXML
    void GoToComplains(ActionEvent event) throws IOException {
        AllComplains.setCaller("Report");
        App.setRoot("AllComplains");
    }

    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        if(App.getUser()!=null)
            SimpleClient.getClient().sendToServer(new Message(null,"#SignOut "+App.getUser().getUsername()));
        App.setUser(null);
        App.setRoot("LogIn");
    }


    @FXML
    void Income(ActionEvent event) {
        FirstReportType.setText("Income");
    }

    @FXML
    void Income2(ActionEvent event) {
        SecondReportType.setText("Income");
    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    @FXML
    void Order(ActionEvent event) {
        FirstReportType.setText("Order");
    }

    @FXML
    void Order2(ActionEvent event) {
        SecondReportType.setText("Order");
    }

    @FXML
    void TwoReports(MouseEvent event) {
        if (CompareTo.isSelected()) {
            SecondReportType.setVisible(true);
            SecondReportBranch.setVisible(true);
            SecondReportFrom.setVisible(true);
            SecondReportTo.setVisible(true);
            SecondReportFromWhite.setVisible(true);
            SecondReportToWhite.setVisible(true);
            SecondReportBranchLable.setVisible(true);
            SecondReportFromDateLable.setVisible(true);
            SecondReportToDateLable.setVisible(true);
            SecondReportTypeLable.setVisible(true);
            InvalidSRType.setVisible(false);
            InvalidSRBranch.setVisible(false);
            InvalidSRFrom.setVisible(false);
            InvalidSRTo.setVisible(false);
            InvalidFRType.setVisible(false);
            InvalidFRBranch.setVisible(false);
            InvalidFRFrom.setVisible(false);
            InvalidFRTo.setVisible(false);
        } else {
            SecondReportType.setVisible(false);
            SecondReportBranch.setVisible(false);
            SecondReportFrom.setVisible(false);
            SecondReportTo.setVisible(false);
            SecondReportFromWhite.setVisible(false);
            SecondReportToWhite.setVisible(false);
            SecondReportBranchLable.setVisible(false);
            SecondReportFromDateLable.setVisible(false);
            SecondReportToDateLable.setVisible(false);
            SecondReportTypeLable.setVisible(false);
            InvalidSRType.setVisible(false);
            InvalidSRBranch.setVisible(false);
            InvalidSRFrom.setVisible(false);
            InvalidSRTo.setVisible(false);
            InvalidFRType.setVisible(false);
            InvalidFRBranch.setVisible(false);
            InvalidFRFrom.setVisible(false);
            InvalidFRTo.setVisible(false);
        }
    }

    @FXML
    void ViewReports(MouseEvent event) throws IOException {
        System.out.println("here");
        boolean flag=false;
        InvalidFRType.setVisible(false);
        InvalidFRFrom.setVisible(true);
        InvalidFRTo.setVisible(false);
        InvalidFRBranch.setVisible(false);

        InvalidSRType.setVisible(false);
        InvalidSRFrom.setVisible(false);
        InvalidSRTo.setVisible(false);
        InvalidSRBranch.setVisible(false);
        InvalidFRType.setVisible(FirstReportType.getText().equals(""));
        InvalidFRBranch.setVisible(FirstReportBranch.getText().equals(""));
        if(CompareTo.isSelected()) {
            InvalidSRType.setVisible(SecondReportType.getText().equals(""));
            InvalidSRBranch.setVisible(SecondReportBranch.getText().equals(""));
        }else{
            InvalidSRType.setVisible(false);
            InvalidSRBranch.setVisible(false);
        }

        Date From1 = new Date(FirstReportFrom.getValue().getYear(), FirstReportFrom.getValue().getMonthValue(), FirstReportFrom.getValue().getDayOfMonth());
        Date To1 = new Date(FirstReportTo.getValue().getYear(), FirstReportTo.getValue().getMonthValue(), FirstReportTo.getValue().getDayOfMonth());
        InvalidFRFrom.setVisible(!Utilities.checkValidDate(From1, To1));
        InvalidFRTo.setVisible(!Utilities.checkValidDate(From1, To1));
        Date From2 = new Date(SecondReportFrom.getValue().getYear(), SecondReportFrom.getValue().getMonthValue(), SecondReportFrom.getValue().getDayOfMonth());
        Date To2 = new Date(SecondReportTo.getValue().getYear(), SecondReportTo.getValue().getMonthValue(), SecondReportTo.getValue().getDayOfMonth());
        if(CompareTo.isSelected()) {
            InvalidSRFrom.setVisible(!Utilities.checkValidDate(From2, To2));
            InvalidSRTo.setVisible(!Utilities.checkValidDate(From2, To2));
            flag=flag||!Utilities.checkValidDate(From1, To1);
            flag=flag||!Utilities.checkValidDate(From2, To2);
        }else{
            InvalidSRFrom.setVisible(false);
            InvalidSRTo.setVisible(false);
        }
        flag=flag||FirstReportType.getText().equals("");
        flag=flag||FirstReportBranch.getText().equals("");
        flag=flag||SecondReportType.getText().equals("");
        flag=flag||SecondReportBranch.getText().equals("");
        if(flag)
            return;
        if(!CompareTo.isSelected()) {
            List<Date> DateList = new ArrayList<>();
            DateList.add(From1);
            DateList.add(To1);
            Message ms = new Message(DateList, "#PrepReports1 " + FirstReportType.getText() + " " + FirstReportBranch.getText());
            SimpleClient.getClient().sendToServer(ms);
            SimpleClient.getClient().reportControl=this;
        }else{
            List<Date> DateList = new ArrayList<>();
            DateList.add(From1);
            DateList.add(To1);
            DateList.add(From2);
            DateList.add(To2);
            Message ms = new Message(DateList, "#PrepReports2 " + FirstReportType.getText() + " " + FirstReportBranch+" "+SecondReportType.getText() + " " + SecondReportBranch.getText());
            SimpleClient.getClient().sendToServer(ms);
            SimpleClient.getClient().reportControl=this;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            SimpleClient.getClient().sendToServer(new Message(null,"#getBranchesForReport"));
//            SimpleClient.getClient().reportControl=this;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        FirstReportFrom.setValue(java.time.LocalDate.now());
        FirstReportTo.setValue(java.time.LocalDate.now());
        SecondReportFrom.setValue(java.time.LocalDate.now());
        SecondReportTo.setValue(java.time.LocalDate.now());

    }
}