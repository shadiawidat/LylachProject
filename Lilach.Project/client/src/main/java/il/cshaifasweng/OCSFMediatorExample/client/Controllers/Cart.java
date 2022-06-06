package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Client;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.permissions;
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
import org.hibernate.id.factory.IdentifierGeneratorFactory;

import java.text.DecimalFormat;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static java.lang.Math.round;

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
    public Label Saved;
    @FXML
    private Button Shipping;
    @FXML
    public Label Tax;
    @FXML
    private Label Matched;
    @FXML
    public Label Total;
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

    public double subTotalG;

    public double subTotalD;

    public double getSubTotalD() {
        return subTotalD;
    }

    public void setSubTotalD(double subTotalD) {
        this.subTotalD = subTotalD;
    }

    public double getSubTotalG() {
        return subTotalG;
    }

    public void setSubTotalG(double subTotalG) {
        this.subTotalG = subTotalG;
    }

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
    void Continue_to_shipping(MouseEvent event) throws IOException {
        il.cshaifasweng.OCSFMediatorExample.client.Controllers.Shipping.setCaller("Cart");
        App.setRoot("Shipping");
    }

    @FXML
    void GoToAbout(ActionEvent event) throws IOException {
        About.setCaller("Cart");
        App.setRoot("About");
    }

    @FXML
    void GoToCatalog(ActionEvent event) throws IOException {
        Catalog.setCaller("LogIn");
        App.setRoot("Catalog");
    }

    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        Account.setCaller("Cart");
        App.setRoot("Account");
    }


    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        if(App.getUser()!=null)
            SimpleClient.getClient().sendToServer(new Message(null,"#SignOut "+App.getUser().getUsername()));
        App.setUser(null);
        App.setRoot("LogIn");
    }

    @FXML
    void GoToSignUp(ActionEvent event) throws IOException {
        SignUp.setCaller("Cart");
        App.setRoot("SignUp");
    }

    @FXML
    void GoToAccount(MouseEvent event) throws IOException {
        Account.setCaller("Cart");
        App.setRoot("Account");
    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    public void setCart(il.cshaifasweng.OCSFMediatorExample.entities.Cart cart, Client client)
    {

    }
    public void loadCart(List<Item> Cart)
    {
        Map<Integer,Integer> map=new HashMap<>();
        for (Item item : Cart)
        {
            if(map.containsKey(item.getId())) {

                map.put(item.getId(), map.get(item.getId()) + 1);
            }
            else
                map.put(item.getId(),1);
        }

        double subTotal=0.0;
        double TotalDiscount=0.0;
        final DecimalFormat df = new DecimalFormat("0.00");
        Matched.setVisible(false);
        scroll.setVisible(true);
        if(Cart.size()==0) {
            Tax.setText(df.format(((subTotal-TotalDiscount)/1.17)*0.17)+"$");
            Saved.setText(df.format((TotalDiscount))+"$");
            Total.setText(df.format((subTotal))+"$");
            scroll.setVisible(false);
            Matched.setVisible(true);
            Shipping.setVisible(false);
            return;
        }
        Shipping.setVisible(true);
        gridPane.getChildren().clear();
        try {
            int column = 0;
            int row = 1;
            for (Item item : Cart) {
                if(map.get(item.getId())==0)
                    continue;
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(SimpleClient.class.getResource("ItemCart.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CartItem CartItemController = fxmlLoader.getController();
                CartItemController.setItemView(item,map.get(item.getId()));
                subTotal+=map.get(item.getId())*item.getPrice();
                TotalDiscount+= map.get(item.getId())*item.getPrice()*(item.getDiscount()/100);
                CartItemController.getQuantity().getValueFactory().setValue(map.get(item.getId()));

                map.put(item.getId(), 0);
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


        Tax.setText(df.format(((subTotal-TotalDiscount)/1.17)*0.17)+"$");
        Saved.setText(df.format((TotalDiscount))+"$");
        Total.setText(df.format((subTotal))+"$");
        setSubTotalG(subTotal);
        setSubTotalD(TotalDiscount);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (App.getUser() == null)
            UserName.setText("Welcome guest");
        else
            UserName.setText("Welcome " + App.getUser().getFirstname());
        if(App.getUser()!=null) {
            try {
                SimpleClient.getClient().sendToServer(new Message(App.getUser(), "#GetCart " + App.getUser().getUsername()));
                SimpleClient.getClient().cartControl = this;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}


