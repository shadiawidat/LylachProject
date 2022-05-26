package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Report implements Initializable {

    public static String Caller = "";
    @FXML
    private ImageView CartButton;
    @FXML
    private CheckBox CompareTo;
    @FXML
    private TextField FirstReportBranch;
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
    private ImageView MenuBtn;
    @FXML
    private TextField SecondReportBranch;
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

    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }

    @FXML
    void GoToAbout(javafx.event.ActionEvent event) {

    }

    @FXML
    void GoToCart(javafx.event.ActionEvent event) {

    }

    @FXML
    void GoToProfile(javafx.event.ActionEvent event) {

    }

    @FXML
    void GoToSignIn(javafx.event.ActionEvent event) {

    }

    @FXML
    void GoToSignOut(javafx.event.ActionEvent event) {

    }

    @FXML
    void GoToSignUp(ActionEvent event) {

    }

    @FXML
    void CloseMenu(MouseEvent event) {
        menu.setVisible(false);
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }

    @FXML
    void GoToCart(MouseEvent event) {

    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
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
        }
    }

    @FXML
    void ViewReports(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void Order(javafx.event.ActionEvent actionEvent) {
    }

    public void Income(javafx.event.ActionEvent actionEvent) {
    }

    public void Complain(javafx.event.ActionEvent actionEvent) {
    }

    public void Order2(javafx.event.ActionEvent actionEvent) {
    }

    public void Income2(javafx.event.ActionEvent actionEvent) {
    }

    public void Complain2(javafx.event.ActionEvent actionEvent) {
    }
//    @FXML
//    void Order(ActionEvent event) {
//
//    }
//
//    @FXML
//    void Order2(ActionEvent event) {
//
//    }
//
//    @FXML
//    void Income(ActionEvent event) {
//
//    }
//
//    @FXML
//    void Income2(ActionEvent event) {
//
//    }
//
//    @FXML
//    void Complain(ActionEvent event) {
//
//    }
//
//    @FXML
//    void Complain2(ActionEvent event) {
//
//    }
}

