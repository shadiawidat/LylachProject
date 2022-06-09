package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import com.sun.marlin.stats.Histogram;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.IncomeReport;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import il.cshaifasweng.OCSFMediatorExample.entities.ReportType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportHistogram implements Initializable {

    @FXML
    private TextField Branch;

    @FXML
    private TextField DateFrom;

    @FXML
    private TextField DateTo;

    @FXML
    private BarChart<String, Number> Histogram;

    @FXML
    private TextField Type;

    final DecimalFormat df = new DecimalFormat("00");

    public void setInfo(Report rep){
        System.out.println(rep.getBranch().getName());
        DateFrom.setText(df.format(rep.getDatefrom().getDate())+"/"+df.format(rep.getDatefrom().getMonth()+1)+"/"+df.format(rep.getDatefrom().getYear()+1));
        DateTo.setText(df.format(rep.getDateto().getDate())+"/"+df.format(rep.getDateto().getMonth()+1)+"/"+df.format(rep.getDateto().getYear()+1900));
        Type.setText(rep.getReportType().name());
        Branch.setText(rep.getBranch().getName());
        if(rep.getReportType()== ReportType.ORDER)
        {
            IncomeReport repo=(IncomeReport) rep;
            XYChart.Series<String,Number> series=new XYChart.Series<>();
            series.getData().add(new XYChart.Data<>("Net Income",repo.getNetincome()));
            series.getData().add(new XYChart.Data<>("Total",repo.getTotalcount()));
            series.getData().add(new XYChart.Data<>("Ordered",repo.getOrderscount()));
            series.getData().add(new XYChart.Data<>("Canceled",repo.getCanceledcount()));
            Histogram.getData().add(series);
        }
        if(rep.getReportType()== ReportType.INCOME)
        {

            int i=0;
            Date from=rep.getFrom();
            from.setDate(from.getDate()-1);
            Date to=rep.getDateto();
            IncomeReport repo=(IncomeReport) rep;
            while(from.before(to))
            {
                XYChart.Series<String,Number> series=new XYChart.Series<>();
                
                series.getData().add(new XYChart.Data<>("Flower",repo.getNetincome()+5));
                series.getData().add(new XYChart.Data<>("Vase",repo.getTotalcount()+4));
                series.getData().add(new XYChart.Data<>("Bouquet",repo.getOrderscount()+3));
                series.getData().add(new XYChart.Data<>("Gardening",repo.getCanceledcount()+1));
                series.getData().add(new XYChart.Data<>("Wedding",repo.getCanceledcount()+2));

                Histogram.getData().add(series);
                from.setDate(from.getDate()+1);
            }

        }
        if(rep.getReportType()== ReportType.COMPLAIN)
        {
            IncomeReport repo=(IncomeReport) rep;
            XYChart.Series<String,Number> series=new XYChart.Series<>();
            series.getData().add(new XYChart.Data<>("Net Income",repo.getNetincome()));
            series.getData().add(new XYChart.Data<>("Total",repo.getTotalcount()));
            series.getData().add(new XYChart.Data<>("Ordered",repo.getOrderscount()));
            series.getData().add(new XYChart.Data<>("Canceled",repo.getCanceledcount()));
            Histogram.getData().add(series);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
