package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.entities.Cart;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
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

    final DecimalFormat df = new DecimalFormat("00");
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

    public void setInfo(Report rep) {
        DateFrom.setText(df.format(rep.getDatefrom().getDate()) + "/" + df.format(rep.getDatefrom().getMonth() + 1) + "/" + df.format(rep.getDatefrom().getYear() + 1900));
        DateTo.setText(df.format(rep.getDateto().getDate()) + "/" + df.format(rep.getDateto().getMonth() + 1) + "/" + df.format(rep.getDateto().getYear() + 1900));
        Type.setText(rep.getReportType().name());
        Branch.setText(rep.getBranch().getName());
        if (rep.getReportType() == ReportType.INCOME) {
            IncomeReport repo = (IncomeReport) rep;
            XYChart.Series<String, Number> series = new XYChart.Series<>();

            series.getData().add(new XYChart.Data<>("Net Income", repo.getNetincome()));
            series.getData().add(new XYChart.Data<>("Total", repo.getTotalcount()));
            series.getData().add(new XYChart.Data<>("Ordered", repo.getOrderscount()));
            series.getData().add(new XYChart.Data<>("Canceled", repo.getCanceledcount()));
            Histogram.getData().add(series);
        }
        if (rep.getReportType() == ReportType.ORDER) {
            int i = 0;
            Date from = rep.getFrom();

            Date to = rep.getDateto();

            XYChart.Series<String, Number> Flower = new XYChart.Series<>();
            Flower.setName("Flower");

            XYChart.Series<String, Number> Vase = new XYChart.Series<>();
            Vase.setName("Vase");

            XYChart.Series<String, Number> Bouquet = new XYChart.Series<>();
            Bouquet.setName("Bouquet");

            XYChart.Series<String, Number> Gardening = new XYChart.Series<>();
            Gardening.setName("Gardening");

            XYChart.Series<String, Number> Wedding = new XYChart.Series<>();
            Wedding.setName("Wedding");

            while (from.before(to)) {
                int Weddingc = 0;
                int Bouquetc = 0;
                int Gardeningc = 0;
                int flowerc = 0;
                int Vasec = 0;
                Flower.getData().add(new XYChart.Data<>(from.toString(), 0));
                Vase.getData().add(new XYChart.Data<>(from.toString(), 0));
                Bouquet.getData().add(new XYChart.Data<>(from.toString(), 0));
                Gardening.getData().add(new XYChart.Data<>(from.toString(), 0));
                Wedding.getData().add(new XYChart.Data<>(from.toString(), 0));

                for (Cart cart : (((OrderReport) rep).getOrders())) {

                    if (cart.getDate().getYear() == (from.getYear() + 1900)) {
                        if (cart.getDate().getMonthValue() == (from.getMonth() + 1)) {
                            if (cart.getDate().getDayOfMonth() == (from.getDate())) {

                                for (Item item : cart.getItems()) {

                                    if (item.getType().equals("Flower")) {
                                        flowerc++;
                                        Flower.getData().get(i).setYValue(flowerc);
                                    }
                                    if (item.getType().equals("Vase")) {
                                        Vasec++;
                                        Vase.getData().get(i).setYValue(Vasec);
                                    }
                                    if (item.getType().equals("Bouquet")) {
                                        Bouquetc++;
                                        Bouquet.getData().get(i).setYValue(Bouquetc);
                                    }
                                    if (item.getType().equals("Gardening")) {
                                        Gardeningc++;
                                        Gardening.getData().get(i).setYValue(Gardeningc);
                                    }
                                    if (item.getType().equals("Wedding")) {
                                        Weddingc++;
                                        Wedding.getData().get(i).setYValue(Weddingc);
                                    }
                                }
                            }
                        }
                    }
                }
                from.setHours(from.getHours() + 24);
                i++;
            }
            int Weddingc = 0;
            int Bouquetc = 0;
            int Gardeningc = 0;
            int flowerc = 0;
            int Vasec = 0;
            Flower.getData().add(new XYChart.Data<>(from.toString(), 0));
            Vase.getData().add(new XYChart.Data<>(from.toString(), 0));
            Bouquet.getData().add(new XYChart.Data<>(from.toString(), 0));
            Gardening.getData().add(new XYChart.Data<>(from.toString(), 0));
            Wedding.getData().add(new XYChart.Data<>(from.toString(), 0));

            for (Cart cart : (((OrderReport) rep).getOrders())) {

                if (cart.getDate().getYear() == (from.getYear() + 1900)) {
                    if (cart.getDate().getMonthValue() == (from.getMonth() + 1)) {
                        if (cart.getDate().getDayOfMonth() == (from.getDate())) {

                            for (Item item : cart.getItems()) {

                                if (item.getType().equals("Flower")) {
                                    flowerc++;
                                    Flower.getData().get(i).setYValue(flowerc);
                                }
                                if (item.getType().equals("Vase")) {
                                    Vasec++;
                                    Vase.getData().get(i).setYValue(Vasec);
                                }
                                if (item.getType().equals("Bouquet")) {
                                    Bouquetc++;
                                    Bouquet.getData().get(i).setYValue(Bouquetc);
                                }
                                if (item.getType().equals("Gardening")) {
                                    Gardeningc++;
                                    Gardening.getData().get(i).setYValue(Gardeningc);
                                }
                                if (item.getType().equals("Wedding")) {
                                    Weddingc++;
                                    Wedding.getData().get(i).setYValue(Weddingc);
                                }

                            }
                        }
                    }
                }
            }

            Histogram.getData().add(Flower);
            Histogram.getData().add(Vase);
            Histogram.getData().add(Bouquet);
            Histogram.getData().add(Gardening);
            Histogram.getData().add(Wedding);

        }
        if (rep.getReportType() == ReportType.COMPLAIN) {

            int i = 0;
            Date from = rep.getFrom();

            Date to = rep.getDateto();

            XYChart.Series<String, Number> Complain = new XYChart.Series<>();
            Complain.setName("Complain");

            while (from.before(to)) {
                int comp = 0;

                Complain.getData().add(new XYChart.Data<>(from.toString(), 0));

                for (il.cshaifasweng.OCSFMediatorExample.entities.Complain complain : (((ComplainReport) rep).getComplains())) {

                    if (complain.getDate().getYear() == (from.getYear() + 1900)) {

                        if (complain.getDate().getMonth() == (from.getMonth() + 1)) {

                            if (complain.getDate().getDate() == (from.getDate())) {

                                comp++;
                                Complain.getData().get(i).setYValue(comp);
                            }
                        }
                    }
                }
                from.setHours(from.getHours() + 24);
                i++;
            }

            int comp = 0;
            Complain.getData().add(new XYChart.Data<>(from.toString(), 0));

            for (il.cshaifasweng.OCSFMediatorExample.entities.Complain complain : (((ComplainReport) rep).getComplains())) {


                if (complain.getDate().getYear() == (from.getYear())) {

                    if (complain.getDate().getMonth() == (from.getMonth())) {

                        if (complain.getDate().getDate() == (from.getDate() + 1)) {
                            comp++;
                            Complain.getData().get(i).setYValue(comp);
                        }
                    }
                }
            }
            Histogram.getData().add(Complain);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
