package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Utilities;
import il.cshaifasweng.OCSFMediatorExample.entities.permissions;
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
import java.security.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Catalog implements Initializable {

    public static String Caller = "";
    public static List<Item> Catalog = new ArrayList<>();
    public static List<Item> CatalogShow = new ArrayList<>();
    @FXML
    private Button Back;

    @FXML
    private Button addItemBtn;

    @FXML
    private ImageView CartB;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private Button SearchBtn;

    @FXML
    private TextField SearchField;

    @FXML
    private Label UserName;

    @FXML
    private MenuItem YellowColor;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    private boolean YellowFlag;
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
    private Label Matched;

    @FXML
    private TextField Max;

    @FXML
    private TextField Min;

    @FXML
    private Button Filter;

    @FXML
    private Label InvalidPrice;




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
        Cart.setCaller("Catalog");
        App.setRoot("Cart");

    }

    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        if(App.getUser()==null)
            return;
        Account.setCaller("Catalog");
        App.setRoot("Account");
    }

    @FXML
    void GoToSignIn(ActionEvent event) throws IOException {
        App.setUser(null);
        App.setRoot("LogIn");
    }

    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        App.setUser(null);
        App.setRoot("LogIn");
    }

    @FXML
    void CloseMenu(MouseEvent event) {

        //menu.setVisible(false);
    }

    @FXML
    void GoToSignUp(ActionEvent event) throws IOException {
        SignUp.setCaller("Catalog");
        App.setRoot("SignUp");
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }

    @FXML
    void GoToAccount(MouseEvent event) throws IOException {
        Account.setCaller("Catalog");
        App.setRoot("Account");
    }

    @FXML
    void GoToCart(MouseEvent event) throws IOException {
        Cart.setCaller("Catalog");
        App.setRoot("Cart");
    }


    private void getData() throws IOException {
        Message ms = new Message(null, "#LoadCatalog");
        SimpleClient.getClient().sendToServer(ms);
        SimpleClient.getClient().catalogControl=this;
    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    public TextField getSearchField() {
        return SearchField;
    }

    public void setSearchField(TextField searchField) {
        SearchField = searchField;
    }

    @FXML
    void ClickSearch(MouseEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getName().toLowerCase().startsWith(SearchField.getText().toLowerCase()))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
        SearchField.setText("");
    }

    @FXML
    void Black(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getColor().equals("Black"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void Multicolor(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getColor().equals("MultiColor"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void Orange(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getColor().equals("Orange"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void CreateNew(MouseEvent event) {
        ItemShow.setCaller("CatalogNew");
        try {
            App.setRoot("ItemShow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Pink(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getColor().equals("Pink"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void Purple(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getColor().equals("Purple"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void Red(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getColor().equals("Red"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void White(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getColor().equals("White"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void Yellow(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getColor().equals("Yellow"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void SFlower(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getType().equals("Flower"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void SortAZ(ActionEvent event) {

    }

    @FXML
    void SortHL(ActionEvent event) {
//        SortLH(null);
        List<Item> temp=new ArrayList<>();
        for(int i=CatalogShow.size()-1;i>=0;i--)
        {
            temp.add(CatalogShow.get(i));
        }
        CatalogShow=temp;
    }

    @FXML
    void SortLH(ActionEvent event) {
        System.out.println("here");
        CatalogShow.clear();
        CatalogShow.add(Catalog.get(0));
        for(Item itemS:Catalog)
            for(Item itemB:CatalogShow)
            {
                if(itemS.getPrice()>itemB.getPrice())
                {
                    Item temp=itemS;
                    itemS=itemB;
                    itemB=temp;
                }
            }
        LoadList(CatalogShow);
    }

    @FXML
    void SortZA(ActionEvent event) {

    }

    @FXML
    void Vases(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getType().equals("Vase"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void Weddings(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getType().equals("Wedding"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }



    @FXML
    void OnSale(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getDiscount()>0)
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void GardeningTools(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getType().equals("Gardening"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void Bouquets(ActionEvent event) {
        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getType().equals("Bouquet"))
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);
    }

    @FXML
    void Price(ActionEvent event) {
        Min.setVisible(true);
        Max.setVisible(true);
        Filter.setVisible(true);
    }

    @FXML
    void FilterByPrices(MouseEvent event) {

        if(Utilities.check_Validate_Price(Min.getText())==false||Utilities.check_Validate_Price(Max.getText())==false) {
            InvalidPrice.setVisible(true);
            Min.setText("");
            Max.setText("");
            return;
        }
        double min=Double.parseDouble(Min.getText()),max=Double.parseDouble(Max.getText());
        if(min>max)
        {
            Min.setText("");
            Max.setText("");
            return;
        }

        CatalogShow.clear();
        for(Item item:Catalog)
        {
            if(item.getPrice()<=max&&item.getPrice()>=min)
            {
                CatalogShow.add(item);
            }
        }
        LoadList(CatalogShow);

        Min.setText("");
        Max.setText("");
        Min.setVisible(false);
        Max.setVisible(false);
        Filter.setVisible(false);
        InvalidPrice.setVisible(false);


    }

    public void LoadList(List<Item> items)
    {
        Matched.setVisible(false);
        scroll.setVisible(true);
        if(items.size()==0) {
            scroll.setVisible(false);
            Matched.setVisible(true);
        }
        grid.getChildren().clear();
        try {
            int column = 0;
            int row = 1;
            for (Item item : items) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemView itemController = fxmlLoader.getController();

                itemController.setItemView(item);
                if (column == 3) {
                    column = 0;
                    row++;
                }
                if (item.getDiscount() == 0)
                    itemController.getSaleImage().setImage(null);
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

            if (App.getUser() == null)
                UserName.setText("Welcome guest");
            else
                UserName.setText("Welcome " + App.getUser().getFirstname());

        if(App.getUser()!=null)
        if(App.getUser().getPermission()== permissions.MANAGER||App.getUser().getPermission()==permissions.WORKER)
            addItemBtn.setVisible(true);
        try {
            getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
