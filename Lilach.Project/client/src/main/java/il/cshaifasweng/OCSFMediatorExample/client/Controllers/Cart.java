package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
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
import java.util.List;
import java.util.ResourceBundle;

public class Cart implements Initializable {
    public static String Caller = "";
    @FXML
    public List<Item> items;
    @FXML
    private Button Back;
    @FXML
    private ImageView CartButton;
    @FXML
    private Menu CloseMenu;
    @FXML
    private MenuItem LogIn;
    @FXML
    private ImageView MenuBtn;
    @FXML
    private Label Saved;
    @FXML
    private Button Shipping;
    @FXML
    private Label Tax;
    @FXML
    private Label Total;
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
    private Label UserName;
    @FXML
    private GridPane gridPane;
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

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }


    @FXML
    void CartClick(MouseEvent event) {

    }

    @FXML
    void CloseMN(ActionEvent event) {

    }

    @FXML
    void Continue_to_shipping(MouseEvent event) {

    }

    @FXML
    void GoToAbout(ActionEvent event) {

    }

    @FXML
    void GoToCart(ActionEvent event) {

    }

    @FXML
    void GoToProfile(ActionEvent event) {

    }

    @FXML
    void GoToSignIn(ActionEvent event) {

    }

    @FXML
    void GoToSignOut(ActionEvent event) {

    }

    @FXML
    void GoToSignUp(ActionEvent event) {

    }

    @FXML
    void GoToAccount(MouseEvent event) {

    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (App.getUser() == null)
            UserName.setText("Welcome guest");
        else
            UserName.setText("Welcome " + App.getUser().getFirstname());


        items = App.items;
        scroll.setVisible(true);
        if(items.size()==0) {
            scroll.setVisible(false);
        }
        if (items == null)
            return;
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < items.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(SimpleClient.class.getResource("ItemCart.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CartItem CartItemController = fxmlLoader.getController();

                CartItemController.setItemView(items.get(i));
                if (column == 1) {
                    column = 0;
                    row++;
                }
                gridPane.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


