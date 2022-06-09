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
    public void loadBranches1(){
        FirstReportBranch.getItems().clear();
        for(Branch branch:BranchesL)
        {
            MenuItem mt=new MenuItem(branch.getName());
            mt.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    FirstReportBranch.setText(branch.getName());
                }
            });
            FirstReportBranch.getItems().add(mt);
        }
    }public void loadBranches2(){
        SecondReportBranch.getItems().clear();
        for(Branch branch:BranchesL)
        {
            MenuItem mt=new MenuItem(branch.getName());
            mt.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    SecondReportBranch.setText(branch.getName());
                }
            });
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

    public MenuButton getFirstReportBranch() {
        return FirstReportBranch;
    }

    public void setFirstReportBranch(MenuButton firstReportBranch) {
        FirstReportBranch = firstReportBranch;
    }

    public MenuButton getSecondReportBranch() {
        return SecondReportBranch;
    }

    public void setSecondReportBranch(MenuButton secondReportBranch) {
        SecondReportBranch = secondReportBranch;
    }

    @FXML
    void ViewReports(MouseEvent event) throws IOException {

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
        Date now = new Date(java.time.LocalDate.now().getYear()-1900, java.time.LocalDate.now().getMonthValue()-1, java.time.LocalDate.now().getDayOfMonth());

        Date From1 = new Date(FirstReportFrom.getValue().getYear()-1900, FirstReportFrom.getValue().getMonthValue()-1, FirstReportFrom.getValue().getDayOfMonth());
        Date To1 = new Date(FirstReportTo.getValue().getYear()-1900, FirstReportTo.getValue().getMonthValue()-1, FirstReportTo.getValue().getDayOfMonth());
        InvalidFRFrom.setVisible(!Utilities.checkValidDate(From1, To1)||!Utilities.checkValidDatebeforequalnow(From1, now));
        InvalidFRTo.setVisible(!Utilities.checkValidDate(From1, To1)||!Utilities.checkValidDatebeforequalnow(To1, now));
        if(From1.equals(To1)){
            InvalidFRFrom.setVisible(!Utilities.checkValidDatebeforequalnow(From1, now));
            InvalidFRTo.setVisible(!Utilities.checkValidDatebeforequalnow(To1, now));
            flag=flag||!Utilities.checkValidDatebeforequalnow(From1, now);
            flag=flag||!Utilities.checkValidDatebeforequalnow(To1, now);
        }else{
            flag=flag||!Utilities.checkValidDate(From1, To1)||!Utilities.checkValidDatebeforequalnow(From1, now);
            flag=flag||!Utilities.checkValidDate(From1, To1)||!Utilities.checkValidDatebeforequalnow(To1, now);
        }

        Date From2 = new Date(SecondReportFrom.getValue().getYear()-1900, SecondReportFrom.getValue().getMonthValue()-1, SecondReportFrom.getValue().getDayOfMonth());
        Date To2 = new Date(SecondReportTo.getValue().getYear()-1900, SecondReportTo.getValue().getMonthValue()-1, SecondReportTo.getValue().getDayOfMonth());
        if(CompareTo.isSelected()) {
            InvalidSRFrom.setVisible(!Utilities.checkValidDate(From2, To2)||!Utilities.checkValidDatebeforequalnow(From2, now));
            InvalidSRTo.setVisible(!Utilities.checkValidDate(From2, To2)||!Utilities.checkValidDatebeforequalnow(To2, now));
            if(From1.equals(To1)){
                InvalidFRFrom.setVisible(!Utilities.checkValidDatebeforequalnow(From1, now));
                InvalidFRTo.setVisible(!Utilities.checkValidDatebeforequalnow(To1, now));
                flag=flag||!Utilities.checkValidDatebeforequalnow(From1, now);
                flag=flag||!Utilities.checkValidDatebeforequalnow(To1, now);
            }else{
                flag=flag||!Utilities.checkValidDate(From1, To1)||!Utilities.checkValidDatebeforequalnow(From1, now);
                flag=flag||!Utilities.checkValidDate(From1, To1)||!Utilities.checkValidDatebeforequalnow(To1, now);
            }
            if(From2.equals(To2)){
                InvalidSRFrom.setVisible(!Utilities.checkValidDatebeforequalnow(From2, now));
                InvalidSRTo.setVisible(!Utilities.checkValidDatebeforequalnow(To2, now));
                flag=flag||!Utilities.checkValidDatebeforequalnow(From2, now);
                flag=flag||!Utilities.checkValidDatebeforequalnow(To2, now);
            }else{
                flag=flag||!Utilities.checkValidDate(From2, To2)||!Utilities.checkValidDatebeforequalnow(From2, now);
                flag=flag||!Utilities.checkValidDate(From2, To2)||!Utilities.checkValidDatebeforequalnow(To2, now);
            }
        }else{
            InvalidSRFrom.setVisible(false);
            InvalidSRTo.setVisible(false);
        }
        flag=flag||FirstReportType.getText().equals("");
        flag=flag||FirstReportBranch.getText().equals("");
        flag=flag||(SecondReportType.getText().equals("")&&CompareTo.isSelected());
        flag=flag||(SecondReportBranch.getText().equals("")&&CompareTo.isSelected());
        if(flag)
            return;

        if(!CompareTo.isSelected()) {

            List<Date> DateList = new ArrayList<>();
            DateList.add(From1);
            DateList.add(To1);
            Message ms = new Message(DateList, "#PrepReports1 " + FirstReportType.getText() + " " + FirstReportBranch.getText());
            App.setRoot("ReportView");
            SimpleClient.getClient().sendToServer(ms);
            SimpleClient.getClient().reportControl=this;
        }else{
            List<Date> DateList = new ArrayList<>();
            DateList.add(From1);
            DateList.add(To1);
            DateList.add(From2);
            DateList.add(To2);
            Message ms = new Message(DateList, "#PrepReports2 " + FirstReportType.getText() + " " + FirstReportBranch.getText()+" "+SecondReportType.getText() + " " + SecondReportBranch.getText());
            App.setRoot("ReportView");
            SimpleClient.getClient().sendToServer(ms);
            SimpleClient.getClient().reportControl=this;
        }
        ReportView.setCaller("Report");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SimpleClient.getClient().sendToServer(new Message(null,"#getBranchesR"));
            SimpleClient.getClient().reportControl=this;
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirstReportFrom.setValue(java.time.LocalDate.now());
        FirstReportTo.setValue(java.time.LocalDate.now());
        SecondReportFrom.setValue(java.time.LocalDate.now());
        SecondReportTo.setValue(java.time.LocalDate.now());
        if(App.getUser().getPermission()==permissions.MANAGER)
        {
            CompareTo.setVisible(false);
            FirstReportBranch.setText(((BranchManager)App.getUser()).getMybranch().getName());
            FirstReportBranch.setDisable(true);
            FirstReportBranch.setOpacity(300000000);
            SecondReportBranch.setText(((BranchManager)App.getUser()).getMybranch().getName());
            SecondReportBranch.setDisable(true);
            SecondReportBranch.setOpacity(300000000);
        }

    }
}