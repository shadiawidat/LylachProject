package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Report implements Initializable {

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
    private  Label SecondReportBranchLable;

    @FXML
    private DatePicker SecondReportFrom;

    @FXML
    private  Label SecondReportFromDateLable;

    @FXML
    private  TextField SecondReportFromWhite;

    @FXML
    private DatePicker SecondReportTo;

    @FXML
    private  Label SecondReportToDateLable;

    @FXML
    private TextField SecondReportToWhite;

    @FXML
    private MenuButton SecondReportType;

    @FXML
    private  Label SecondReportTypeLable;

    @FXML
    private Button View;

    @FXML
    void GoToCart(MouseEvent event) {

    }

    @FXML
    void MenuClick(MouseEvent event) {

    }

    @FXML
    void TwoReports(MouseEvent event) {
        if(CompareTo.isSelected()){
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
        }else {
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
}

