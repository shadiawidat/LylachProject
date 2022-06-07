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
import net.bytebuddy.asm.Advice;

import java.io.IOException;
import java.net.URL;
import java.security.Permission;
import java.util.*;


class SortByPrice implements Comparator<Item> {
    // Used for sorting in ascending order of ID
    public int compare(Item a, Item b)
    {
        if (a.getPrice() < b.getPrice()) return -1;
        if (a.getPrice() > b.getPrice()) return 1;
        return 0;
    }
}

class SortByLetters implements Comparator<Item> {
    // Used for sorting in ascending order of ID
    public int compare(Item a, Item b)
    {
        return a.getName().compareTo(b.getName());
    }
}

public class Catalog implements Initializable {

    public static String Caller = "";
    public static List<Item> Catalog = new ArrayList<>();
    public static List<Item> CatalogShow = new ArrayList<>();

    @FXML
    private Button X_button;

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
    public TextField SearchField;

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

    @FXML
    private MenuItem Reports;
    @FXML
    private MenuItem Complains;

    public static String searchin;


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
    void GoToComplains(ActionEvent event) throws IOException {
        AllComplains.setCaller("Catalog");
        App.setRoot("AllComplains");
    }

    @FXML
    void GoToReports(ActionEvent event) throws IOException {
        Report.setCaller("Catalog");
        App.setRoot("Report");
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

        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getName().toLowerCase().startsWith(SearchField.getText().toLowerCase()))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }
    public void ItemShowSearch(MouseEvent event) {
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
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getColor().equals("Black"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void Multicolor(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getColor().equals("MultiColor"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void Orange(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getColor().equals("Orange"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void CreateNew(MouseEvent event) {
        ItemShow.setCaller("CatalogNew");
        System.out.println("here");
        Item item=new Item("",0.0,"","",0.0);
        App.setOnscreen(item);
        try {
            System.out.println("here1");
            App.setRoot("ItemShow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Pink(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getColor().equals("Pink"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void Purple(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getColor().equals("Purple"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void Red(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getColor().equals("Red"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void White(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getColor().equals("White"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void Yellow(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getColor().equals("Yellow"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void SFlower(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getType().equals("Flower"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }


    @FXML
    void SortHL(ActionEvent event) {
//        CatalogShow.clear();
//        CatalogShow.addAll(Catalog);
        CatalogShow.sort(new SortByPrice());
        Collections.reverse(CatalogShow);
        LoadList(CatalogShow);
        X_button.setVisible(true);
    }

    @FXML
    void SortLH(ActionEvent event) {
//        CatalogShow.clear();
//        CatalogShow.addAll(Catalog);
        CatalogShow.sort(new SortByPrice());
        LoadList(CatalogShow);
        X_button.setVisible(true);
    }

    @FXML
    void SortAZ(ActionEvent event) {
//        CatalogShow.clear();
//        CatalogShow.addAll(Catalog);
        CatalogShow.sort(new SortByLetters());
        LoadList(CatalogShow);
        X_button.setVisible(true);
    }

    @FXML
    void SortZA(ActionEvent event) {
//        CatalogShow.clear();
//        CatalogShow.addAll(Catalog);
        CatalogShow.sort(new SortByLetters());
        Collections.reverse(CatalogShow);
        LoadList(CatalogShow);
        X_button.setVisible(true);
    }

    @FXML
    void Vases(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getType().equals("Vase"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void Weddings(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getType().equals("Wedding"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }



    @FXML
    void OnSale(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getDiscount()>0)
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void GardeningTools(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getType().equals("Gardening"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void Bouquets(ActionEvent event) {
        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getType().equals("Bouquet"))
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);
        X_button.setVisible(true);
    }

    @FXML
    void Price(ActionEvent event) {
        Min.setVisible(true);
        Max.setVisible(true);
        Filter.setVisible(true);
        X_button.setVisible(true);
    }

    @FXML
    void FilterByPrices(MouseEvent event) {

        if(Utilities.check_Validate_Price(Min.getText())==false||Utilities.check_Validate_Price(Max.getText())==false) {
            InvalidPrice.setVisible(true);
            Min.setText("");
            Max.setText("");
            return;
        }
        if(Integer.parseInt(Min.getText()) > Integer.parseInt(Max.getText())){
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


        List<Item> tempo=new ArrayList<>();
        for(Item item:CatalogShow)
        {
            if(item.getPrice()<=max&&item.getPrice()>=min)
            {
                tempo.add(item);
            }
        }
        CatalogShow=tempo;
        LoadList(tempo);

        Min.setText("");
        Max.setText("");
        InvalidPrice.setVisible(false);
        X_button.setVisible(true);

    }

    @FXML
    void Cancel_filters(MouseEvent event) {
        resetShow();
        LoadList(Catalog);
        CatalogShow=Catalog;
        Min.setVisible(false);
        Max.setVisible(false);
        Filter.setVisible(false);
        InvalidPrice.setVisible(false);
        X_button.setVisible(false);
    }
    public void resetShow()
    {
        CatalogShow=Catalog;
    }
    public void LoadList(List<Item> items)
    {
        if(App.getUser()!=null)
        if(App.getUser().getPermission()== permissions.WORKER||App.getUser().getPermission()== permissions.MANAGER||App.getUser().getPermission()== permissions.ADMIN||App.getUser().getPermission()== permissions.CorpManager)
            CartB.setImage(null);
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
                if(App.getUser()!=null)
                if(App.getUser().getPermission()!=permissions.CLIENT)
                    itemController.getAddCart().setImage(null);

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
        if (searchin != null && searchin.equals("") == false) {
            SearchField.setText(searchin);
            ClickSearch(null);
            return;
        }
        if (App.getUser() == null)
            UserName.setText("Welcome guest");
        else
            UserName.setText("Welcome " + App.getUser().getFirstname());
        if (App.getUser() != null) {
            MenuSignIn.setVisible(false);
            if (App.getUser().getPermission() == permissions.WORKER || App.getUser().getPermission() == permissions.MANAGER || App.getUser().getPermission() == permissions.CorpManager) {
                MenuSignUp.setVisible(false);
                CartB.setVisible(false);
                MenuCart.setVisible(false);

            }
            if (App.getUser().getPermission() == permissions.MANAGER || App.getUser().getPermission() == permissions.CorpManager) {
                Reports.setVisible(true);
                Complains.setVisible(true);
            } else {
                Reports.setVisible(false);
                Complains.setVisible(false);
            }
        }
        else {
            MenuSignOut.setVisible(false);
            Reports.setVisible(false);
            Complains.setVisible(false);
        }
        if (App.getUser() != null)
            if (App.getUser().getPermission() == permissions.MANAGER || App.getUser().getPermission() == permissions.WORKER || App.getUser().getPermission() == permissions.CorpManager)
                addItemBtn.setVisible(true);
            try {
                getData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//        if(App.getUser()!=null && App.getUser().getPermission()==permissions.WORKER) {
//            Complains.setVisible(true);
//        }


        }
}
