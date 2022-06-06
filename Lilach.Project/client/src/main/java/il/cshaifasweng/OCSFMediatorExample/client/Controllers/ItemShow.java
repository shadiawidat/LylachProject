package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Utilities;
import il.cshaifasweng.OCSFMediatorExample.entities.permissions;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ItemShow implements Initializable {

    public static String Caller = "";

    public static List<Item> related=new ArrayList<>();

    @FXML
    public Item ITEM;
    private static int ToShow;
    @FXML
    private MenuButton TypeText;


    @FXML
    private Button AddItembtn;

    @FXML
    private ImageView SaleImage;
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
    private ImageView CartB;
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
    private Label InvalidColor;

    @FXML
    private Label InvalidDiscount;

    @FXML
    private Label InvalidImage;

    @FXML
    private Label InvalidName;

    private Image imgs;

    @FXML
    private Label InvalidPrice;

    @FXML
    private Label InvalidType;

    @FXML
    private AnchorPane ItemPrev;

    @FXML
    private Label nameside;
    @FXML
    private ImageView sideAddCart;

    @FXML
    private Label priceside;

    @FXML
    private ImageView imageid;

    @FXML
    private Button NextItembtn;

    @FXML
    private Button PrevItembtn;
    @FXML
    private MenuItem Reports;
    @FXML
    private MenuItem Complains;

    private Image img;



    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
    }

    @FXML
    void GoToAbout(ActionEvent event) throws IOException {
        About.setCaller("Catalog");
        App.setRoot("About");
    }

    @FXML
    void GoToCartMN(ActionEvent event) throws IOException {
        if(App.getUser()==null)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Please sign in first");
            a.showAndWait();
            return;
        }

        Cart.setCaller("Catalog");
        App.setRoot("Cart");
    }

    @FXML
    void GoToCatalog(ActionEvent event) throws IOException {
        Catalog.setCaller("LogIn");
        App.setRoot("Catalog");
    }

    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        if(App.getUser()==null)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Please sign in first");

            a.showAndWait();
            return;
        }
        Account.setCaller("Catalog");
        App.setRoot("Account");
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
    void GoToSignIn(ActionEvent event) throws IOException {
        if(App.getUser()!=null)
            SimpleClient.getClient().sendToServer(new Message(null,"#SignOut "+App.getUser().getUsername()));
        App.setUser(null);
        App.setRoot("LogIn");
    }

    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        if(App.getUser()!=null)
            SimpleClient.getClient().sendToServer(new Message(null,"#SignOut "+App.getUser().getUsername()));
        App.setUser(null);
        App.setRoot("LogIn");

    }


    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    @FXML
    void GoToSignUp(ActionEvent event) throws IOException {
        SignUp.setCaller("Catalog");
        App.setRoot("SignUp");
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

        if(item.getDiscount()!=0.0)
            discount.setVisible(true);

    }

    @FXML
    void ChangePrice(MouseEvent event) throws IOException {

        SimpleClient.getClient().sendToServer("#changePrice " + ITEM.getId() + " " + PriceFiled.getText());

    }

    @FXML
    void CloseItem(MouseEvent event) throws IOException {
        related.clear();
        App.setRoot("Catalog");
    }

    @FXML
    void GoToAccount(MouseEvent event) throws IOException {
        if(App.getUser()==null)
        {
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
        if(App.getUser()==null)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Please sign in first");
            a.showAndWait();
            return;
        }
        Cart.setCaller("Catalog");
        App.setRoot("Cart");
    }

    @FXML
    void OpenMenu(MouseEvent event) {

    }

    @FXML
    void SearchItem(MouseEvent event) throws IOException {
        Catalog.searchin=SearchField.getText();
        Platform.runLater(()->{
            try {
                App.setRoot("Catalog");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
    public void getRelated(String relate)
    {
        for (Item item:Catalog.Catalog)
        {
            if(item.getType().equals(relate))
                related.add(item);
            else if(item.getType().equals("Vase")&&relate.equals("Flower"))
                related.add(item);
        }
        nameside.setText(related.get(0).getName());
        priceside.setText(Double.toString(related.get(0).getPrice())+"$");
        Image image = new Image(SimpleClient.class.getResourceAsStream(related.get(0).getImagesrc()));
        imageid.setImage(image);
        priceside.setVisible(true);
        nameside.setVisible(true);
        if(related.get(0).getDiscount()!=0)
            SaleImage.setVisible(true);
        ToShow=0;
        if(App.getUser()!=null) {
            if (!App.getUser().getPermission().equals(permissions.CLIENT))
                sideAddCart.setVisible(false);
        }

        PrevItembtn.setVisible(false);
        NextItembtn.setVisible(related.size() != 1);
    }
    @FXML
    void Bouquet(ActionEvent event) {
        related.clear();
        TypeText.setText("Bouquet");
        getRelated("Bouquet");

    }

    @FXML
    void Flower(ActionEvent event) {
        related.clear();
        TypeText.setText("Flower");
        getRelated("Flower");

    }

    @FXML
    void Gardening(ActionEvent event) {
        related.clear();
        TypeText.setText("Gardening");
        getRelated("Gardening");
    }

    @FXML
    void Vase(ActionEvent event) {
        related.clear();
        TypeText.setText("Vase");
        getRelated("Vase");
    }

    @FXML
    void Wedding(ActionEvent event) {
        related.clear();
        TypeText.setText("Wedding");
        getRelated("Wedding");

    }

    @FXML
    void AddItem(MouseEvent event) throws IOException {
        System.out.println("here");
        boolean flag=false;
        InvalidName.setVisible(false);
        InvalidColor.setVisible(false);
        InvalidPrice.setVisible(false);
        InvalidType.setVisible(false);
        InvalidDiscount.setVisible(false);
        InvalidImage.setVisible(false);

        InvalidName.setVisible(!Utilities.check_Validate_String(NameText.getText())||NameText.getText().equals(""));
        InvalidColor.setVisible(!Utilities.check_Validate_String(ColorText.getText())||ColorText.getText().equals(""));
        InvalidPrice.setVisible(!Utilities.check_Validate_Price(PriceText.getText())||PriceText.getText().equals(""));
        InvalidType.setVisible(TypeText.getText().equals(""));
        InvalidDiscount.setVisible(!Utilities.check_Validate_Discount(DiscountText.getText())||DiscountText.getText().equals(""));
        InvalidImage.setVisible(imgid.getImage()==null);
        flag=!Utilities.check_Validate_String(NameText.getText())||NameText.getText().equals("");
        flag=flag||!Utilities.check_Validate_String(ColorText.getText())||ColorText.getText().equals("");
        flag=flag||!Utilities.check_Validate_Price(PriceText.getText())||PriceText.getText().equals("");
        flag=flag||TypeText.getText().equals("");
        flag=flag||imgid.getImage()==null;
        flag=flag||!Utilities.check_Validate_Discount(DiscountText.getText())||DiscountText.getText().equals("");
        if(flag)
            return;
        String s=SimpleClient.class.getResource("/Media/").toString();

        String[] a=s.split("/");
        s="";
        a[0]="";
        String t="";
        for(String b:a)
        {
            if(b.equals("target"))
                b="src//main";
            if(b.equals("classes"))
                b="resources";
            s=s+b+"//";
            t=t+b+"//";
        }
        s=s+TypeText.getText()+"//"+NameText.getText()+".png";
        t=t+TypeText.getText()+"//"+NameText.getText()+".png";
        System.out.println(s);
        ImageIO.write(SwingFXUtils.fromFXImage(imgid.getImage(),null),"png",new File(s));
        ImageIO.write(SwingFXUtils.fromFXImage(imgid.getImage(),null),"png",new File(t));
        Item item = new Item(NameText.getText(),Double.parseDouble(PriceText.getText()),TypeText.getText(),ColorText.getText(),Double.parseDouble(DiscountText.getText()));

        Message ms =new Message(item,"#AddItem");
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().itemshowControl=this;
        App.setRoot("LogIn");
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
        priceside.setVisible(true);
        nameside.setVisible(true);
        SaleImage.setVisible(false);
        if(related.get(ToShow).getDiscount()!=0)
            SaleImage.setVisible(true);

        if(ToShow>0)
            PrevItembtn.setVisible(true);
        else
            PrevItembtn.setVisible(false);
        if(ToShow==related.size()-1)
            NextItembtn.setVisible(false);
        else
            NextItembtn.setVisible(true);
        if(App.getUser()!=null) {
            if (!App.getUser().getPermission().equals(permissions.CLIENT))
                sideAddCart.setVisible(false);
        }

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
        priceside.setVisible(true);
        nameside.setVisible(true);
        SaleImage.setVisible(false);
        if(related.get(ToShow).getDiscount()!=0)
            SaleImage.setVisible(true);
        if(ToShow>0)
            PrevItembtn.setVisible(true);
        else
            PrevItembtn.setVisible(false);
        if(ToShow==related.size()-1)
            NextItembtn.setVisible(false);
        else
            NextItembtn.setVisible(true);
        if(App.getUser()!=null) {
            if (!App.getUser().getPermission().equals(permissions.CLIENT))
                sideAddCart.setVisible(false);
        }

    }
    @FXML
    void HandleDrop(DragEvent event) throws IOException {
        if(Caller.equals("CatalogNew")&&(App.getUser()!=null&&App.getUser().getPermission()==permissions.CorpManager||App.getUser().getPermission()==permissions.WORKER||App.getUser().getPermission()==permissions.MANAGER) ){
            List<File> files = event.getDragboard().getFiles();
            img=new Image(new FileInputStream(files.get(0).getPath()));
           imgid.setImage(img);
           }}

    @FXML
    void HandleOver(DragEvent event) {
        if(Caller.equals("CatalogNew")&&(App.getUser()!=null&&App.getUser().getPermission()==permissions.CorpManager||App.getUser().getPermission()==permissions.WORKER||App.getUser().getPermission()==permissions.MANAGER) ){
            if(event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(App.getUser()!=null)
            if (!App.getUser().getPermission().equals(permissions.CLIENT))
                sideAddCart.setVisible(false);
        Catalog.searchin="";
        if (App.getUser() == null)
            UserName.setText("Welcome guest");
        else
            UserName.setText("Welcome " + App.getUser().getFirstname());

        if(App.getUser()!=null){
            MenuSignIn.setVisible(false);
            if(App.getUser().getPermission()== permissions.WORKER||App.getUser().getPermission()== permissions.MANAGER||App.getUser().getPermission()== permissions.CorpManager) {
                CartB.setVisible(false);
                MenuSignUp.setVisible(false);
                MenuCart.setVisible(false);
            }
            if(App.getUser().getPermission()== permissions.MANAGER||App.getUser().getPermission()==permissions.CorpManager) {
                Reports.setVisible(true);
                Complains.setVisible(true);
            }else{
                Reports.setVisible(false);
                Complains.setVisible(false);
            }

        }else{
            MenuSignOut.setVisible(false);
            Reports.setVisible(false);
            Complains.setVisible(false);
        }

        if(getCaller().equals("CatalogNew")){
            NameText.setText("");
            nameid.setText("Name:");
            PriceText.setText("");
            price.setText("Price:");
            TypeText.setText("");
            type.setText("Type:");
            ColorText.setText("");
            color.setText("Color:");
            DiscountText.setText("");
            discount.setText("Discount:");
            imageid.setImage(null);
            imgid.setImage(null);
            PrevItembtn.setVisible(false);
            NextItembtn.setVisible(false);
            SaleImage.setVisible(false);
            nameside.setVisible(false);
            priceside.setVisible(false);
            AddItembtn.setVisible(true);
            AddToCartBtn.setVisible(false);
            NameText.setVisible(true);
            PriceText.setVisible(true);
            TypeText.setVisible(true);
            ColorText.setVisible(true);
            DiscountText.setVisible(true);
            return;
        }
        if (App.getUser() == null)
            UserName.setText("Welcome guest");
        else
            UserName.setText("Welcome " + App.getUser().getFirstname());

        try {
            SetItem(App.getOnscreen());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (App.getUser() != null &&( App.getUser().getPermission() == permissions.MANAGER || App.getUser().getPermission() == permissions.WORKER || App.getUser().getPermission() == permissions.CorpManager)) {
            AddToCartBtn.setVisible(false);
            NameText.setVisible(true);
            PriceText.setVisible(true);
            TypeText.setVisible(true);
            ColorText.setVisible(true);
            DiscountText.setVisible(true);
            UpdateInfo.setVisible(true);
            DeleteItem.setVisible(true);
            discount.setVisible(true);
            if(!Caller .equals("CatalogNew")) {
                NameText.setText(ITEM.getName());
                PriceText.setText(Double.toString(ITEM.getPrice()));
                TypeText.setText(ITEM.getType());
                ColorText.setText(ITEM.getColor());
                DiscountText.setText(Double.toString(ITEM.getDiscount()));

            }else{
                NameText.setText("");
                nameid.setText("Name:");
                PriceText.setText("");
                price.setText("Price:");
                TypeText.setText("");
                type.setText("Type:");
                ColorText.setText("");
                color.setText("Color:");
                DiscountText.setText("");
                discount.setText("Discount:");
                imgid.setImage(null);
                imageid.setImage(null);
            }
        }
        if (App.getUser() != null && App.getUser().getPermission() == permissions.CLIENT) {
            AddToCartBtn.setVisible(true);
            NameText.setVisible(false);
            PriceText.setVisible(false);
            TypeText.setVisible(false);
            ColorText.setVisible(false);
            DiscountText.setVisible(false);
            UpdateInfo.setVisible(false);
            DeleteItem.setVisible(false);

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
            NextItembtn.setVisible(related.size() != 1);

    }

    @FXML
    void UpdateInfo(MouseEvent event) throws IOException {

        InvalidColor.setVisible(false);
        InvalidName.setVisible(false);
        InvalidType.setVisible(false);
        InvalidPrice.setVisible(false);
        InvalidDiscount.setVisible(false);

        String NName= NameText.getText();
        String NPrice= PriceText.getText();
        String NType= TypeText.getText();
        String NColor= ColorText.getText();
        String NDiscount = DiscountText.getText();
        boolean flag=false;

        flag = flag || !Utilities.check_Validate_String(NName) || NName.equals("");
        flag = flag || !Utilities.check_Validate_Price(PriceText.getText()) || PriceText.getText().equals("");
        flag = flag || !Utilities.check_Validate_String(NType) || NType.equals("");
        flag = flag || !Utilities.check_Validate_String(NColor) || NColor.equals("");
        flag = flag || !Utilities.check_Validate_Price(NDiscount) || NDiscount.equals("");

        InvalidColor.setVisible(!Utilities.check_Validate_String(NColor) || NColor.equals(""));
        InvalidName.setVisible(!Utilities.check_Validate_String(NName) || NName.equals(""));
        InvalidType.setVisible(!Utilities.check_Validate_String(NType) || NType.equals(""));
        InvalidPrice.setVisible(!Utilities.check_Validate_Price(PriceText.getText()) || PriceText.getText().equals(""));
        InvalidDiscount.setVisible(!Utilities.check_Validate_Price(NDiscount) || NDiscount.equals(""));

        if (flag)
            return;

        Message ms= new Message(ITEM, "#UpdateItemInfo," + NName + " " + NPrice + " " + NType + " " + NColor + " " + NDiscount);
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().itemshowControl= this;


    }

    public void Created (boolean flag) throws IOException {
        if(!flag){
            Alert a = new Alert(Alert.AlertType.ERROR);

            a.setContentText("Updating info failed !");

            a.showAndWait();
            return;
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION);

        a.setContentText("Updating info succeeded");

        Optional<ButtonType> result = a.showAndWait();
        if(!result.isPresent()) {}
        else if(result.get() == ButtonType.OK)
        {
            Platform.runLater(()->{
                try {
                    App.setRoot("Catalog");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
        return;

    }

    public void Deleted (boolean flag) throws IOException {
        if(!flag){
            Alert a = new Alert(Alert.AlertType.ERROR);

            a.setContentText("Deleting item failed !");

            a.showAndWait();
            return;
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION);

        a.setContentText("Deleting item succeeded");

        Optional<ButtonType> result = a.showAndWait();
        if(!result.isPresent()) {}
        else if(result.get() == ButtonType.OK)
        {
            Platform.runLater(()->{
                try {
                    App.setRoot("Catalog");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
        return;

    }

    @FXML
    void DeleteItem(MouseEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message(ITEM,"#DeleteItem"));
        SimpleClient.getClient().itemshowControl=this;
    }

    @FXML
    void SideAddToCart(MouseEvent event) throws IOException {
        if(App.getUser()==null)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);

            a.setContentText("Please sign in first");

            a.showAndWait();
            return;
        }
        Alert a = new Alert(Alert.AlertType.INFORMATION);

        a.setContentText("Item added to cart");

        a.showAndWait();
        if(App.getUser().getPermission()!=permissions.CLIENT)
            return;

        SimpleClient.getClient().sendToServer(new Message(related.get(ToShow),"#AddToCart "+App.getUser().getUsername()));
        SimpleClient.getClient().itemshowControl=this;
    }
    @FXML
    void AddToCart(MouseEvent event) throws IOException {
        if(App.getUser()==null)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);

            a.setContentText("Please sign in first");

            a.showAndWait();
            return;
        }
        Alert a = new Alert(Alert.AlertType.INFORMATION);

        a.setContentText("Item added to cart");

        a.showAndWait();
        if(App.getUser().getPermission()!=permissions.CLIENT)
            return;
        SimpleClient.getClient().sendToServer(new Message(ITEM,"#AddToCart "+App.getUser().getUsername()));
        SimpleClient.getClient().itemshowControl=this;
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        related.clear();

        if (Caller.equals("CatalogNew")) {
            Caller = "Catalog";
        }
        App.setRoot(getCaller());
    }
}
