package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Cart;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.permissions;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AllComplains implements Initializable {
    public static String Caller = "";
    @FXML
    private Button Back;

    @FXML
    private ImageView CartButton;

    @FXML
    private MenuItem MenuAbout;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private MenuItem MenuCart;

    @FXML
    private MenuItem MenuProfile;

    @FXML
    private MenuItem MenuSignIn;

    @FXML
    private MenuItem MenuSignOut;


    @FXML
    private Label UserName;

    @FXML
    private Label Matched;

    @FXML
    private GridPane gridPane;

    @FXML
    private MenuBar menu;

    @FXML
    private ScrollPane scroll;

    public boolean isRunning=false;
    List<Complain> controllers=new ArrayList<>();
    public Thread timerthread=new Thread(this::handleThread);
    final DecimalFormat df = new DecimalFormat("00");
    public Thread onOff=new Thread(this::handleThreadonOff);

    public void handleThread()
    {
        while(isRunning)
        {
            Platform.runLater(()->{
                for (Complain complain:controllers)
                {
                    complain.tickRemaining(0);
                }
            });
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void handleThreadonOff()
    {

        while(isRunning)
        {
            Platform.runLater(()->{
                for (Complain complain:controllers)
                {
                    complain.setOnOff();
                }
            });
            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        App.setRoot(getCaller());
    }

    public static String getCaller() {
        return Caller;
    }
    public static void setCaller(String caller) {
        Caller = caller;
    }

    @FXML
    void CloseMenu(MouseEvent event) {

    }

    @FXML
    void GoToAbout(ActionEvent event) throws IOException {
        About.setCaller("AllComplains");
        App.setRoot("About");
    }

    @FXML
    void GoToAccount(MouseEvent event) throws IOException {
        Account.setCaller("AllComplains");
        App.setRoot("Account");
    }



    @FXML
    void GoToProfile(ActionEvent event) throws IOException {
        Account.setCaller("AllComplains");
        App.setRoot("Account");
    }

    @FXML
    void GoToCatalog(ActionEvent event) throws IOException {
        Catalog.setCaller("LogIn");
        App.setRoot("Catalog");
    }

    @FXML
    void GoToReports(ActionEvent event) throws IOException {
        Report.setCaller("AllComplains");
        App.setRoot("Report");
    }

    @FXML
    void GoToSignOut(ActionEvent event) throws IOException {
        if(App.getUser()!=null)
            SimpleClient.getClient().sendToServer(new Message(null,"#SignOut "+App.getUser().getUsername()));
        App.setUser(null);
        App.setRoot("LogIn");
    }

    public void loadComplains(List<il.cshaifasweng.OCSFMediatorExample.entities.Complain> complains){
        gridPane.getChildren().clear();
        Matched.setVisible(false);
        int i=0;
        try {
            int column = 0;
            int row = 1;
            if(complains.size() == 0){
                Matched.setVisible(true);
                scroll.setVisible(false);
            }
            for (il.cshaifasweng.OCSFMediatorExample.entities.Complain complain : complains) {
                if(complain.isHandled())
                    continue;
                if(App.getUser().getPermission()==permissions.MANAGER){
                    System.out.println(((BranchManager)App.getUser()).getMybranch().getName());
                    System.out.println(complain.getBranch().getName());
                    if(!(((BranchManager)App.getUser()).getMybranch().getName().equals(complain.getBranch().getName()))){
                        continue;
                    }
                }else if(App.getUser().getPermission()==permissions.WORKER){
                    if(App.getUser().getMybranches().size()==1) {
                        if (!(App.getUser().getMybranches().get(0).getName() .equals( complain.getBranch().getName()))) {
                            continue;
                        }
                    }
                }
                i++;
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(SimpleClient.class.getResource("Complain.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Complain comp = fxmlLoader.getController();
                controllers.add(comp);
                comp.setInfo(complain);


                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridPane.add(anchorPane, column++, row); //(child,column,row)
                //set gridPane width
                gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxWidth(Region.USE_PREF_SIZE);
                //set gridPane height
                gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxHeight(Region.USE_PREF_SIZE);
                gridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(i == 0){
            scroll.setVisible(false);
            Matched.setVisible(true);
        }
    }

    @FXML
    void MenuClick(MouseEvent event) {
        menu.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        isRunning=true;
        timerthread.start();
        onOff.start();
        UserName.setText("Welcome " + App.getUser().getFirstname());
        if(App.getUser().getPermission()!=permissions.MANAGER||App.getUser().getPermission()!=permissions.CorpManager)
            menu.getMenus().get(0).getItems().get(1).setVisible(false);
        if(App.getUser()!=null) {
            try {
                SimpleClient.getClient().sendToServer(new Message(App.getUser(), "#GetComplains "));
                SimpleClient.getClient().allComplains = this;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
