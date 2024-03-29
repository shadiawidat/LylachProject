package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MyCarts implements Initializable {
    public static String Caller = "";
    final DecimalFormat df = new DecimalFormat("0.00");
    public boolean isRunning = false;
    List<CartView> controllers = new ArrayList<>();
    public Thread timerthread = new Thread(this::handleThread);
    public Thread onOff = new Thread(this::handleThreadonOff);
    @FXML
    private Button Back;
    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView CartButton;
    @FXML
    private MenuItem MenuAbout;
    @FXML
    private ImageView MenuBtn;
    @FXML
    private Label Matched;
    @FXML
    private MenuItem MenuProfile;
    @FXML
    private MenuItem MenuSignOut;
    @FXML
    private MenuItem MenuSignUp;
    @FXML
    private Label UserName;
    @FXML
    private MenuBar menu;
    @FXML
    private ScrollPane scroll;

    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }

    public void handleThread() {

        while (isRunning) {
            Platform.runLater(() -> {
                for (CartView cartView : controllers) {
                    cartView.tickRemaining(0);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleThreadonOff() {

        while (isRunning) {
            Platform.runLater(() -> {
                for (CartView cartView : controllers) {
                    cartView.setOnOff();
                }
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }


    @FXML
    void CloseMenu(MouseEvent event) {

    }

    @FXML
    void GoToAbout(ActionEvent event) throws IOException {
        About.setCaller("MyCarts");
        App.setRoot("About");
    }

    @FXML
    void GoToAccount(MouseEvent event) throws IOException {
        About.setCaller("MyCarts");
        App.setRoot("Account");
    }

    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        About.setCaller("MyCarts");
        App.setRoot("Account");
    }

    @FXML
    void GoToCatalog(ActionEvent event) throws IOException {
        Catalog.setCaller("LogIn");
        App.setRoot("Catalog");
    }

    @FXML
    void GoToCartMN(ActionEvent event) throws IOException {
        if (App.getUser() == null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Please sign in first");
            a.showAndWait();
            return;
        }
        Cart.setCaller("Catalog");
        App.setRoot("Cart");
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
        About.setCaller("MyCarts");
        App.setRoot("SignUp");
    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }


    public void loadOrders(List<il.cshaifasweng.OCSFMediatorExample.entities.Cart> orders) {
        gridPane.getChildren().clear();
        Matched.setVisible(false);
        scroll.setVisible(true);
        int i = 0;

        try {
            int column = 0;
            int row = 1;
            if (orders.size() == 0) {
                scroll.setVisible(false);
                Matched.setVisible(true);
            }
            for (il.cshaifasweng.OCSFMediatorExample.entities.Cart cart : orders) {
                if (cart.getCanceled())
                    continue;
                i++;
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(SimpleClient.class.getResource("CartView.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CartView cartView = fxmlLoader.getController();
                controllers.add(cartView);
                cartView.setInfo(cart);


                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridPane.add(anchorPane, column++, row); //(child,column,row)
                //set gridPane width
                gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxWidth(Region.USE_PREF_SIZE);
                //set gridPane height
                gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxHeight(Region.USE_PREF_SIZE);
                gridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (i == 0) {
            scroll.setVisible(false);
            Matched.setVisible(true);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isRunning = true;
        timerthread.start();
        onOff.start();

        UserName.setText("Welcome " + App.getUser().getFirstname());
        isRunning = true;
        if (App.getUser() != null) {
            try {
                SimpleClient.getClient().sendToServer(new Message(App.getUser(), "#GetOrders " + App.getUser().getUsername()));
                SimpleClient.getClient().myCartsControl = this;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
