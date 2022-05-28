package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.permissions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemShow implements Initializable {
    public static String Caller = "";
    public static List<Item> related=new ArrayList<>();
    @FXML
    public Item ITEM;

    private static int ToShow;
    @FXML
    private ImageView CartBtn;
    @FXML
    private Button ChangeBtn;
    @FXML
    private AnchorPane ItemHolder;
    @FXML
    private ImageView MenuBtn;
    @FXML
    private TextField PriceFiled;
    @FXML
    private Button SearchBtn;
    @FXML
    private TextField SearchField;
    @FXML
    private Label UserName;
    @FXML
    private Label color;
    @FXML
    private ImageView imgid;
    @FXML
    private Label nameid;
    @FXML
    private ImageView pressX;
    @FXML
    private Label price;
    @FXML
    private Label type;
    @FXML
    private TextField NameText;
    @FXML
    private TextField ColorText;
    @FXML
    private TextField TypeText;
    @FXML
    private TextField PriceText;
    @FXML
    private TextField DiscountText;
    @FXML
    private Button UpdateInfo;
    @FXML
    private Button AddToCartBtn;
    @FXML
    private Button DeleteItem;
    @FXML
    private Label discount;
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
    private MenuBar menu;
    @FXML
    private AnchorPane ItemPrev;

    @FXML
    private Label nameside;

    @FXML
    private Label priceside;

    @FXML
    private ImageView imageid;

    @FXML
    private Button NextItembtn;

    @FXML
    private Button PrevItembtn;


    private Item item;

    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }

    @FXML
    void GoToAbout(ActionEvent event) throws IOException {
        About.setCaller("ItemShow");
        App.setRoot("About");
    }

    @FXML
    void GoToCartMN(ActionEvent event) throws IOException {
        Cart.setCaller("ItemShow");
        App.setRoot("Cart");

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
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    @FXML
    void GoToSignUp(ActionEvent event) {

    }

    @FXML
    public void SetItem(Item item) throws Exception {
        discount.setVisible(false);
        if (item == null)
            return;
        ITEM = item;
        nameid.setText("Name: " + item.getName());
        price.setText("Price: $" + item.getPrice());
        type.setText("Type : " + item.getType());
        color.setText("Color: " + item.getColor());
        Image image = new Image(SimpleClient.class.getResourceAsStream(item.getImagesrc()));
        imgid.setImage(image);
        discount.setText("Discount: "+item.getDiscount()+"%");
//        if(item.getDiscount()==0.0)
//            discount.setVisible(false);
    }

    @FXML
    void ChangePrice(MouseEvent event) throws IOException {

        SimpleClient.getClient().sendToServer("#changePrice " + ITEM.getId() + " " + PriceFiled.getText());

    }

    @FXML
    void CloseItem(MouseEvent event) throws IOException {
        related.clear();
        App.setRoot("catalog");
    }

    @FXML
    void GoToAccount(MouseEvent event) {

    }

    @FXML
    void GoToCart(MouseEvent event) throws IOException {
        Cart.setCaller("ItemShow");

        App.setRoot("Cart");
    }

    @FXML
    void OpenMenu(MouseEvent event) {

    }

    @FXML
    void SearchItem(MouseEvent event) throws IOException {

    }

    @FXML
    void NextItem(MouseEvent event) {
        if(ToShow+1>=related.size())
            return;
        ToShow++;
        nameside.setText(related.get(ToShow).getName());
        priceside.setText(Double.toString(related.get(ToShow).getPrice())+"$");
        Image image = new Image(SimpleClient.class.getResourceAsStream(related.get(ToShow).getImagesrc()));
        imageid.setImage(image);
        if(ToShow>0)
            PrevItembtn.setVisible(true);
        else
            PrevItembtn.setVisible(false);
        if(ToShow==related.size()-1)
            NextItembtn.setVisible(false);
        else
            NextItembtn.setVisible(true);
    }

    @FXML
    void PrevItem(MouseEvent event) {
        if(ToShow==0)
            return;
        ToShow--;
        nameside.setText(related.get(ToShow).getName());
        priceside.setText(Double.toString(related.get(ToShow).getPrice())+"$");
        Image image = new Image(SimpleClient.class.getResourceAsStream(related.get(ToShow).getImagesrc()));
        imageid.setImage(image);
        if(ToShow>0)
            PrevItembtn.setVisible(true);
        else
            PrevItembtn.setVisible(false);
        if(ToShow==related.size()-1)
            NextItembtn.setVisible(false);
        else
            NextItembtn.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            SetItem(App.getOnscreen());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (App.getUser() != null && App.getUser().getPermission() == permissions.MANAGER) {
            AddToCartBtn.setVisible(false);
            NameText.setVisible(true);
            PriceText.setVisible(true);
            TypeText.setVisible(true);
            ColorText.setVisible(true);
            DiscountText.setVisible(true);
            UpdateInfo.setVisible(true);
            DeleteItem.setVisible(true);

            NameText.setText(ITEM.getName());
            PriceText.setText(Double.toString(ITEM.getPrice()));
            TypeText.setText(ITEM.getType());
            ColorText.setText(ITEM.getColor());
            DiscountText.setText(Double.toString(ITEM.getDiscount()));

        }

//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
//
//            ItemView itemController = fxmlLoader.getController();
//            itemController.setItemView(related.get(0));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

            nameside.setText(related.get(0).getName());
            priceside.setText(Double.toString(related.get(0).getPrice())+"$");
            Image image = new Image(SimpleClient.class.getResourceAsStream(related.get(0).getImagesrc()));
            imageid.setImage(image);
            ToShow=0;
            PrevItembtn.setVisible(false);
            if(related.size()==1)
                NextItembtn.setVisible(false);
    }

    @FXML
    void UpdateInfo(MouseEvent event) throws IOException {

        String Name = ITEM.getName();
        double Price = ITEM.getPrice();
        String Type = ITEM.getType();
        String Colot = ITEM.getColor();

        if (NameText.getText() != "") {

        }
        if (PriceText.getText() != "") {

        }
        if (TypeText.getText() != "") {

        }
        if (ColorText.getText() != "") {

        }
        related.clear();
        App.setRoot("Catalog");
    }

    @FXML
    void DeleteItem(MouseEvent event) {


    }

    @FXML
    void AddToCart(MouseEvent event) {

    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        related.clear();
        App.setRoot(getCaller());
    }

}
