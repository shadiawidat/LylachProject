package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.permissions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class About implements Initializable {

    public static String Caller = "";

    @FXML
    private Button Back;
    @FXML
    private ImageView CartB;


    @FXML
    private ImageView MenuBtn;
    @FXML
    private Label UserName;
    @FXML
    private MenuItem MenuAbout;
    @FXML
    private MenuItem MenuCart;
    @FXML
    private MenuItem MenuProfile;
    @FXML
    private MenuItem CatalogBtn;
    @FXML
    private MenuItem MenuSignIn;
    @FXML
    private MenuItem MenuSignOut;
    @FXML
    private MenuItem MenuSignUp;
    @FXML
    private MenuBar menu;
    @FXML
    private Label UserNameConnected; @FXML
    private MenuItem Reports;
    @FXML
    private MenuItem Complains;


    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }

    @FXML
    void GoToCartMN(ActionEvent event) throws IOException {
        if (App.getUser() == null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);

            a.setContentText("Please sign in first");

            a.showAndWait();
            return;
        }
        Cart.setCaller("About");
        App.setRoot("Cart");
    }

    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        Account.setCaller("About");
        App.setRoot("Account");
    }

    @FXML
    void GoToSignIn(ActionEvent event) throws IOException {
        App.setRoot("LogIn");
    }

    @FXML
    void CloseMenu(MouseEvent event) {
        // menu.setVisible(false);
    }

    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        if (App.getUser() != null)
            SimpleClient.getClient().sendToServer(new Message(null, "#SignOut " + App.getUser().getUsername()));
        App.setUser(null);
        App.setRoot("Login");
    }

    @FXML
    void GoToSignUp(ActionEvent event) throws IOException {
        SignUp.setCaller("About");
        App.setRoot("SignUp");
    }

    @FXML
    void GoToCatalog(ActionEvent event) throws IOException {
        Catalog.setCaller("LogIn");
        App.setRoot("Catalog");
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }

    @FXML
    void GoToAccount(MouseEvent event) throws IOException {
        if (App.getUser() == null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);

            a.setContentText("Please sign in first");

            a.showAndWait();
            return;
        }
        Account.setCaller("Catalog");
        App.setRoot("Account");
    }

    @FXML
    void GoToCart(MouseEvent event) throws IOException {
        if (App.getUser() == null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);

            a.setContentText("Please sign in first");

            a.showAndWait();
            return;
        }

        Cart.setCaller("About");
        App.setRoot("Cart");
    }
    @FXML
    void GoToComplains(ActionEvent event) throws IOException {
        AllComplains.setCaller("Catalog");
        App.setRoot("AllComplains");
    }

    @FXML
    void GoToReports(ActionEvent event) throws IOException {
        Report.setCaller("Catalog");
        App.setRoot("Report");
    }


    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (App.getUser() == null) {
            MenuSignOut.setVisible(false);
            UserNameConnected.setText("Welcome guest");
        } else {
            MenuSignIn.setVisible(false);
            MenuSignUp.setVisible(false);
            Reports.setVisible(false);
            Complains.setVisible(false);
            UserNameConnected.setText("Welcome " + App.getUser().getFirstname());
        }
        if ((App.getUser() != null) && (!App.getUser().getPermission().equals(permissions.CLIENT))) {
            CartB.setVisible(false);
            MenuCart.setVisible(false);
        }
        if ((App.getUser() != null) && ((App.getUser().getPermission().equals(permissions.MANAGER)) || (App.getUser().getPermission().equals(permissions.CorpManager)))) {
            Reports.setVisible(true);
            Complains.setVisible(true);
        }
        else {
            Reports.setVisible(false);
            Complains.setVisible(false);
        }
        if((App.getUser()!=null) && (App.getUser().getPermission().equals(permissions.ADMIN)))
            CatalogBtn.setVisible(false);
    }
}
