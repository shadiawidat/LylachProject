package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Catalog implements Initializable {

    public static String Caller = "";
    public static List<Item> Catalog = new ArrayList<>();
    public static List<Item> CatalogShow = new ArrayList<>();
    @FXML
    private Button Back;

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

    public static String getCaller() {
        return Caller;
    }

    public static void setCaller(String caller) {
        Caller = caller;
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
    void CloseMenu(MouseEvent event) {
        menu.setVisible(false);
    }

    @FXML
    void GoToSignUp(ActionEvent event) {

    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }

    @FXML
    void Black(ActionEvent event) {

    }

    @FXML
    void ClickSearch(MouseEvent event) {
        List<Item> searchitem = new ArrayList<>();
        for (Item value : Catalog) {
            String name = value.getName().toLowerCase();
            if (name.startsWith(SearchField.getText().toLowerCase())) {
                CatalogShow.add(value);
            }
        }

        SearchField.setText("");
        grid.getChildren().clear();
        Platform.runLater(() -> {
            int column = 0;
            int row = 1;
            try {
                for (Item item : CatalogShow) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemView itemController = fxmlLoader.getController();
                    itemController.setItemView(item);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
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

        });
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
    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    @FXML
    void Multicolor(ActionEvent event) {
        for (Item value : Catalog) {
            String color = value.getColor();
            if (color.equals("MultiColor")) {
                CatalogShow.add(value);
            }
        }

        grid.getChildren().clear();
        Platform.runLater(() -> {
            int column = 0;
            int row = 1;
            try {
                for (Item item : CatalogShow) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemView itemController = fxmlLoader.getController();
                    itemController.setItemView(item);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
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

        });
    }

    @FXML
    void Orange(ActionEvent event) {
        for (Item value : Catalog) {
            String color = value.getColor();
            if (color.equals("Orange")) {
                CatalogShow.add(value);
            }
        }
        grid.getChildren().clear();
        Platform.runLater(() -> {
            int column = 0;
            int row = 1;
            try {
                for (Item item : CatalogShow) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemView itemController = fxmlLoader.getController();
                    itemController.setItemView(item);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
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

        });
    }

    @FXML
    void CreateNew(MouseEvent event) {

    }

    @FXML
    void Pink(ActionEvent event) {
        for (Item value : Catalog) {
            String color = value.getColor();
            if (color.equals("Pink")) {
                CatalogShow.add(value);
            }
        }

        grid.getChildren().clear();
        Platform.runLater(() -> {
            int column = 0;
            int row = 1;
            try {
                for (Item item : CatalogShow) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemView itemController = fxmlLoader.getController();
                    itemController.setItemView(item);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
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

        });
    }

    @FXML
    void Purple(ActionEvent event) {
        for (Item value : Catalog) {
            String color = value.getColor();
            if (color.equals("Purple")) {
                CatalogShow.add(value);
            }
        }

        grid.getChildren().clear();
        Platform.runLater(() -> {
            int column = 0;
            int row = 1;
            try {
                for (Item item : CatalogShow) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemView itemController = fxmlLoader.getController();
                    itemController.setItemView(item);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
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

        });
    }

    @FXML
    void Red(ActionEvent event) {
        for (Item value : Catalog) {
            String color = value.getColor();
            if (color.equals("Red")) {
                CatalogShow.add(value);
            }
        }

        grid.getChildren().clear();
        Platform.runLater(() -> {
            int column = 0;
            int row = 1;
            try {
                for (Item item : CatalogShow) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemView itemController = fxmlLoader.getController();
                    itemController.setItemView(item);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
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

        });
    }

    @FXML
    void SortHL(ActionEvent event) {

    }

    @FXML
    void White(ActionEvent event) {
        for (Item value : Catalog) {
            String color = value.getColor();
            if (color.equals("White")) {
                CatalogShow.add(value);
            }
        }

        grid.getChildren().clear();
        Platform.runLater(() -> {
            int column = 0;
            int row = 1;
            try {
                for (Item item : CatalogShow) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemView itemController = fxmlLoader.getController();
                    itemController.setItemView(item);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
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

        });
    }

    @FXML
    void Yellow(ActionEvent event) {
        for (Item item : Catalog) {
            String color = item.getColor();
            if (color.equals("Yellow")) {
                CatalogShow.add(item);
            }
        }

        grid.getChildren().clear();
        Platform.runLater(() -> {
            int column = 0;
            int row = 1;
            try {
                for (Item item : CatalogShow) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemView itemController = fxmlLoader.getController();
                    itemController.setItemView(item);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
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

        });
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        YellowFlag = false;
        try {
            getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Platform.runLater(() -> {
            if (App.getUser() == null)
                UserName.setText("Welcome guest");
            else
                UserName.setText("Welcome " + App.getUser().getFirstname());
            try {
                int column = 0;
                int row = 1;
                for (Item item : Catalog) {
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
        });

    }
}
