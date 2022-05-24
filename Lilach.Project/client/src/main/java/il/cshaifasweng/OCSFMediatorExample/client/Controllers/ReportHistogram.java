package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportHistogram implements Initializable {

    @FXML
    private TextField Branch;

    @FXML
    private TextField DateFrom;

    @FXML
    private TextField DateTo;

    @FXML
    private BarChart<?, ?> Histogram;

    @FXML
    private TextField Type;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
