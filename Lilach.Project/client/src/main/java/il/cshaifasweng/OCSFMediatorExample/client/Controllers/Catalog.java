package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.MyListener;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.application.Platform;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static com.sun.javafx.application.PlatformImpl.runLater;

public class Catalog implements Initializable {

		@FXML
		private ResourceBundle resources;

		@FXML
		private MenuItem YellowColor;

		private boolean YellowFlag;

		@FXML
		private URL location;

		@FXML
		private ScrollPane ScrollPane;

		@FXML
		private Button SearchBtn;

		@FXML
		public TextField SearchField;

		@FXML
		private Label UserName;

		@FXML
		private GridPane grid;

		private MyListener myListener;

	public static List<Item> Catalog=new ArrayList<>();
	public static List<Item> CatalogShow=new ArrayList<>();
		@FXML
		void ClickSearch(MouseEvent event) throws IOException {
			List<Item> searchitem=new ArrayList<>();
			for(int i=0;i<Catalog.size();i++)
			{
				String name=Catalog.get(i).getName().toLowerCase();
				if(name.startsWith(SearchField.getText().toLowerCase()))
				{
					CatalogShow.add(Catalog.get(i));
				}
			}

			SearchField.setText("");
			grid.getChildren().clear();
			Platform.runLater(()-> {
				int column = 0;
				int row = 1;
				try {
					for (int i = 0; i < CatalogShow.size(); i++) {
						FXMLLoader fxmlLoader = new FXMLLoader();
						fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
						AnchorPane anchorPane = fxmlLoader.load();
						ItemView itemController = fxmlLoader.getController();
						itemController.setItemView(CatalogShow.get(i));
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
		void MenuClick(MouseEvent event) {

		}
		@FXML
		void GoToAccount(MouseEvent event) throws IOException {
			App.setRoot("Account");
		}

		@FXML
		void GoToCart(MouseEvent event) throws IOException {
			App.setRoot("Cart");
		}
		
		public void setData() throws IOException {

		}
		private void getData() throws IOException {

			Message ms=new Message(null,"#LoadCatalog");
           SimpleClient.getClient().sendToServer(ms);



		}

		private void setCatalog(List<Item> items)
		{

		}

		@FXML
		void SortHL(ActionEvent event) {
			List<Item> SortHL=new ArrayList<>();

			Catalog=SortHL;
			SearchField.setText("");
			grid.getChildren().clear();
			Platform.runLater(()-> {
				int column = 0;
				int row = 1;
				try {
					for (int i = 0; i < Catalog.size(); i++) {
						FXMLLoader fxmlLoader = new FXMLLoader();
						fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
						AnchorPane anchorPane = fxmlLoader.load();
						ItemView itemController = fxmlLoader.getController();
						itemController.setItemView(Catalog.get(i));
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
		List<Item> Purple=new ArrayList<>();
		for(int i=0;i<Catalog.size();i++)
		{
			String color=Catalog.get(i).getColor();
			if(color.equals("Purple"))
			{
				CatalogShow.add(Catalog.get(i));
			}
		}
		SearchField.setText("");
		grid.getChildren().clear();
		Platform.runLater(()-> {
			int column = 0;
			int row = 1;
			try {
				for (int i = 0; i < CatalogShow.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
					AnchorPane anchorPane = fxmlLoader.load();
					ItemView itemController = fxmlLoader.getController();
					itemController.setItemView(CatalogShow.get(i));
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
	void Black(ActionEvent event) {
		List<Item> Purple=new ArrayList<>();
		for(int i=0;i<Catalog.size();i++)
		{
			String color=Catalog.get(i).getColor();
			if(color.equals("Black"))
			{
				CatalogShow.add(Catalog.get(i));
			}
		}
		SearchField.setText("");
		grid.getChildren().clear();
		Platform.runLater(()-> {
			int column = 0;
			int row = 1;
			try {
				for (int i = 0; i < CatalogShow.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
					AnchorPane anchorPane = fxmlLoader.load();
					ItemView itemController = fxmlLoader.getController();
					itemController.setItemView(CatalogShow.get(i));
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
	void Back(MouseEvent event) {

	}

	@FXML
	void Multicolor(ActionEvent event) {
		List<Item> Purple=new ArrayList<>();
		for(int i=0;i<Catalog.size();i++)
		{
			String color=Catalog.get(i).getColor();
			if(color.equals("MultiColor"))
			{
				CatalogShow.add(Catalog.get(i));
			}
		}
		SearchField.setText("");
		grid.getChildren().clear();
		Platform.runLater(()-> {
			int column = 0;
			int row = 1;
			try {
				for (int i = 0; i < CatalogShow.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
					AnchorPane anchorPane = fxmlLoader.load();
					ItemView itemController = fxmlLoader.getController();
					itemController.setItemView(CatalogShow.get(i));
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
	void Pink(ActionEvent event) {
		List<Item> Purple=new ArrayList<>();
		for(int i=0;i<Catalog.size();i++)
		{
			String color=Catalog.get(i).getColor();
			if(color.equals("Pink"))
			{
				CatalogShow.add(Catalog.get(i));
			}
		}
		SearchField.setText("");
		grid.getChildren().clear();
		Platform.runLater(()-> {
			int column = 0;
			int row = 1;
			try {
				for (int i = 0; i < CatalogShow.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
					AnchorPane anchorPane = fxmlLoader.load();
					ItemView itemController = fxmlLoader.getController();
					itemController.setItemView(CatalogShow.get(i));
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
		List<Item> Red=new ArrayList<>();
		for(int i=0;i<Catalog.size();i++)
		{
			String color=Catalog.get(i).getColor();
			if(color.equals("Red"))
			{
				CatalogShow.add(Catalog.get(i));
			}
		}

		SearchField.setText("");
		grid.getChildren().clear();
		Platform.runLater(()-> {
			int column = 0;
			int row = 1;
			try {
				for (int i = 0; i < CatalogShow.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
					AnchorPane anchorPane = fxmlLoader.load();
					ItemView itemController = fxmlLoader.getController();
					itemController.setItemView(CatalogShow.get(i));
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
	void White(ActionEvent event) {
		List<Item> White=new ArrayList<>();
		for(int i=0;i<Catalog.size();i++)
		{
			String color=Catalog.get(i).getColor();
			if(color.equals("White"))
			{
				CatalogShow.add(Catalog.get(i));
			}
		}

		SearchField.setText("");
		grid.getChildren().clear();
		Platform.runLater(()-> {
			int column = 0;
			int row = 1;
			try {
				for (int i = 0; i < CatalogShow.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
					AnchorPane anchorPane = fxmlLoader.load();
					ItemView itemController = fxmlLoader.getController();
					itemController.setItemView(CatalogShow.get(i));
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
		for(int i=0;i<Catalog.size();i++)
		{
			String color=Catalog.get(i).getColor();
			if(color.equals("Orange"))
			{
				CatalogShow.add(Catalog.get(i));
			}
		}

		grid.getChildren().clear();
		Platform.runLater(()-> {
			int column = 0;
			int row = 1;
			try {
				for (int i = 0; i < CatalogShow.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
					AnchorPane anchorPane = fxmlLoader.load();
					ItemView itemController = fxmlLoader.getController();
					itemController.setItemView(CatalogShow.get(i));
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
			if(YellowFlag)
				return;
		for(int i=0;i<Catalog.size();i++)
		{
			String color=Catalog.get(i).getColor();
			if(color.equals("Yellow"))
			{
				CatalogShow.add(Catalog.get(i));
			}
		}
		grid.getChildren().clear();
		Platform.runLater(()-> {
			int column = 0;
			int row = 1;
			try {
				for (int i = 0; i < CatalogShow.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
					AnchorPane anchorPane = fxmlLoader.load();
					ItemView itemController = fxmlLoader.getController();
					itemController.setItemView(CatalogShow.get(i));
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
		YellowFlag=true;
	}

		@FXML
		@Override
		public void initialize(URL location, ResourceBundle resources) {

			YellowFlag=false;


			try {
				getData();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}


			Platform.runLater(()-> {
				if(App.getUser()==null)
				{
					UserName.setText("Welcome guest");

				}
				else
				{
					UserName.setText("Welcome "+App.getUser().getFirstname());
				}

					try {
						int column = 0;
						int row = 1;
						for (int i = 0; i < Catalog.size(); i++) {
							FXMLLoader fxmlLoader = new FXMLLoader();
							fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
							AnchorPane anchorPane = fxmlLoader.load();
							ItemView itemController = fxmlLoader.getController();

							itemController.setItemView(Catalog.get(i));
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

	}