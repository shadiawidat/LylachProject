package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import com.sun.marlin.stats.Histogram;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
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

    final DecimalFormat df = new DecimalFormat("00");

    public void setInfo(Report rep){
        DateFrom.setText(df.format(rep.getDatefrom().getDate())+"/"+df.format(rep.getDatefrom().getMonth())+"/"+df.format(rep.getDatefrom().getYear()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
