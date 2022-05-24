package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.permissions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemShow implements Initializable {

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
    private  Label color;

    @FXML
    private  ImageView imgid;

    @FXML
    private  Label nameid;

    @FXML
    private ImageView pressX;

    @FXML
    private  Label price;

    @FXML
    private  Label type;

    @FXML
    public  Item ITEM;

    @FXML
    private TextField NameText;

    @FXML
    private TextField ColorText;

    @FXML
    private TextField TypeText;

    @FXML
    private TextField PriceText;

    @FXML
    private Button UpdateInfo;


    @FXML
    public void SetItem(Item item) throws Exception {
        if(item==null)
            return;
        ITEM=item;
        nameid.setText("Name: "+item.getName());
        price.setText("Price: $"+item.getPrice());
        type.setText("Type : "+item.getType());
        color.setText("Color: "+item.getColor());
        Image image=new Image(SimpleClient.class.getResourceAsStream(item.getImagesrc()));
        imgid.setImage(image);
    }

    @FXML
    void ChangePrice(MouseEvent event) throws IOException {

        SimpleClient.getClient().sendToServer("#changePrice "+ITEM.getId()+" "+PriceFiled.getText());

    }

    @FXML
    void CloseItem(MouseEvent event) throws IOException {
        App.setRoot("catalog");
    }

    @FXML
    void GoToAccount(MouseEvent event) {

    }

    @FXML
    void GoToCart(MouseEvent event) throws IOException {
        App.setRoot("Cart");
    }

    @FXML
    void OpenMenu(MouseEvent event) {

    }

    @FXML
    void SearchItem(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SetItem(App.getOnscreen());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(App.getUser()!=null&&App.getUser().getPermission()== permissions.MANAGER)
        {
            NameText.setVisible(true);
            PriceText.setVisible(true);
            TypeText.setVisible(true);
            ColorText.setVisible(true);
            UpdateInfo.setVisible(true);
        }
    }
    @FXML
    void UpdateInfo(MouseEvent event) throws IOException {

        String Name=ITEM.getName();
        double Price=ITEM.getPrice();
        String Type=ITEM.getType();
        String Colot=ITEM.getColor();

        if(NameText.getText()!=""){

        }
        if(PriceText.getText()!=""){

        }
        if(TypeText.getText()!=""){

        }
        if(ColorText.getText()!=""){

        }
        App.setRoot("Catalog");
    }
}
