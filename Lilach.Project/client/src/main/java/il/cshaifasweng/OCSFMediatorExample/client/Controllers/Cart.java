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
    public static String Caller="";

    public static String getCaller() {
        return Caller;
    }
    public static void setCaller(String caller) {
        Caller = caller;
    }
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
    private Label UserName;

    @FXML
    private GridPane gridPane;

    @FXML
    private MenuBar menu;

    @FXML
    private ScrollPane scroll;

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
    void GoToAccount(MouseEvent event) {

    }

    @FXML
    void MenuClick(MouseEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        items= App.items;
        if(items==null)
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


