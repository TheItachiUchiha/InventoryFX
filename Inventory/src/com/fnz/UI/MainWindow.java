package com.fnz.UI;




import com.fnz.VO.CategoryVO;
import com.fnz.VO.ItemVO;
import com.fnz.common.CommonConstants;
import com.fnz.dao.DBInteraction;
import com.fnz.dao.UtiliesDAO;
import com.fnz.panes.Items;
import com.fnz.service.UtiliesService;
import com.sai.javafx.calendar.demo.FXCalendarDemo;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainWindow extends Application 
{
	private BorderPane border;
	private Stage stage;
	private Scene scene;
	
	
	
	public Settings settings;
	public StockDetails stockDetails;
	public UtiliesService utiliesService;
	public IncomingStock incomingStock;
	public OutgoingStock outgoingStock;
	public TransactionHistory transactionHistory;
	
	public ObservableList<CategoryVO> categoryList;
	public ObservableList<ItemVO> itemList;
	
	public boolean flag;
    /**
     * @param args the command line arguments
     * @throws Exception 
     */
	
	public MainWindow() throws Exception
	{
		new DBInteraction().createDB();
		settings = new Settings();
		stockDetails = new StockDetails();
		utiliesService = new UtiliesService();
		incomingStock = new IncomingStock();
		outgoingStock = new OutgoingStock();
		transactionHistory = new TransactionHistory();
		categoryList = FXCollections.observableArrayList();
		itemList =FXCollections.observableArrayList();
		itemList = UtiliesDAO.getUtiliesDAO().getItemList();
		
		
		
	}
    public static void main(String[] args)
    {
        launch(MainWindow.class, args); 
    }
    
    @Override
    public void start(Stage stage)
    {
    	try{

// Use a border pane as the root for scene
    		
    	
    	categoryList = UtiliesDAO.getUtiliesDAO().categoryList;
    	
        border = new BorderPane();
        border.setTop(upperPart());
        Text text = new Text("© Kryptcode Technologies Limited");
        text.setFill(Color.DARKGOLDENROD);
        
        border.setBottom(addBBox());
        
        scene = new Scene(border);
        scene.getStylesheets().add(FXCalendarDemo.class.getResource("/com/fnz/styles/calendar_styles.css").toExternalForm());
        scene.getStylesheets().add(
                this.getClass().getClassLoader().getResource("com/fnz/styles/Tab.css").toString());
        stage.setX(0);
	    stage.setY(0);
	    //stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/fnz/styles/Two-storied house.png")));
	    stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
	    stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
	    stage.setScene(this.scene);
        stage.setTitle("Inventory Management");
        stage.getIcons().add(new Image("icon.png"));
        stage.show();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    private BorderPane addBBox(){
    	javafx.scene.layout.
        BorderPane border = new BorderPane();
    	border.setPadding(new Insets(15, 12, 15, 12));
       // hbox.setSpacing(10);   // Gap between nodes
       // hbox.setStyle("-fx-background-color: #336699;");
    	border.getStyleClass().add("lowerHBox");
        
        Text text = new Text("© Kryptcode Technologies Limited");
        text.setFill(Color.DARKGOLDENROD);
        HBox hbase=new HBox();
        hbase.setAlignment(Pos.CENTER);
        hbase.getChildren().add(text);
       
        
       
        border.setLeft(hbase);
        
        
        /*HBox hbox = new HBox();
       
        Button upload = new Button();
        upload.setId("upload");
        Image img1 = new Image("upload.png");
        upload.setGraphic(new ImageView(img1));
        upload.setContentDisplay(ContentDisplay.TOP);
        hbox.getChildren().add(upload);
        
        border.setRight(hbox);*/
        
        return border;
    }
    
    
    /*private GridPane addGridPane() 
    {
        GridPane grid = new GridPane();
       // grid.setStyle(" -fx-background-color: cyan");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(300));
        return grid;
    }*/
    /*Create Method <function name><return type><comments>
	 * <Creator Name><Date Of Creation MM-dd-yyyy>
	 * 
	 * <addHBox()><HBox><Horizontal pane to be placed on the top>
	 * <Abhinay Agarwal><12-10-2012>
	 * 
	 * */
	/**Modification Log
	 * 
	 * <Date> <Name> <Comments>
	 * 
	 */   
   
    private BorderPane upperPart() 
    {

    	BorderPane mainPane = new BorderPane();
    	
     	
    	TabPane tabPane = new TabPane();
    	tabPane.setId(("MyTabPane"));
    	
      
        
        
        final Tab tabA = new Tab();
        tabA.setClosable(false);
        tabA.setText("View Stock");
      
        

        tabA.setContent(stockDetails.viewStockDrinkList(categoryList));
        tabPane.getTabs().add(tabA);
       
        final Tab tabB = new Tab();
        tabB.setClosable(false);
        tabB.setText("Purchase");
      
        
        
        tabPane.getTabs().add(tabB);
        
        final Tab tabC = new Tab();
        tabC.setClosable(false);
        tabC.setText("Sales");
        tabPane.getTabs().add(tabC);
        
        final Tab tabD = new Tab();
        tabD.setClosable(false);
        tabD.setText("History");
        tabPane.getTabs().add(tabD);
        
       
        
      
        final Tab tabSetting = new Tab();
        tabSetting.setClosable(false);
        tabSetting.setText("Settings");
        tabPane.getTabs().add(tabSetting);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	/*ToggleButton bAddCategory= new ToggleButton("Add Category");
    	bAddCategory.setToggleGroup(settingsGroup);
    	bAddCategory.setId("drinkName");
    	bAddCategory.setMaxSize(250,250);
    	gsettings.add(bAddCategory,0,0);
    	
    	bAddCategory.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('settings.jpg');");
 				//borderPane.setCenter(settings.addCategory());
 			}
 		});*/
    	
    	
        
    	
    	/*ToggleButton bDeleteCategory = new ToggleButton("Delete Category");
    	bDeleteCategory.setToggleGroup(settingsGroup);
    	bDeleteCategory.setId("drinkName");
    	bDeleteCategory.setMaxSize(250,250);
    	gsettings.add(bDeleteCategory,0,2);
    	
    	bDeleteCategory.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('settings.jpg');");
 				try {
					borderPane.setCenter(settings.deleteCategory());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 			}
 		});
    	*/
    	
    	/*ToggleButton bDeleteItem= new ToggleButton("Delete Item");
    	bDeleteItem.setToggleGroup(settingsGroup);
    	bDeleteItem.setId("drinkName");
    	bDeleteItem.setMaxSize(250,250);
    	gsettings.add(bDeleteItem,0,3);
    	
    	bDeleteItem.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('settings.jpg');");
 				try {
					borderPane.setCenter(settings.viewSettings("delete"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 			}
 		});*/
    	
    	
    	
    	
        
        
        
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
    		
  		  public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, Tab newTab) {
  		    
  			  System.out.println(newTab.getText());
  			  System.out.println(tabA.getText());
  			  
  			if(newTab.getText().equals(tabA.getText()))
  		    {
  		    	tabA.setContent(stockDetails.viewStockDrinkList(categoryList));
  		    }
  			if(newTab.getText().equals(tabB.getText()))
  			{
  				tabB.setContent(incomingStock.addStockDrinkList(categoryList));
  			}
  			if(newTab.getText().equals(tabC.getText()))
  			{
  				tabC.setContent(outgoingStock.delStockDrinkList(categoryList));
  			}
  			if(newTab.getText().equals(tabD.getText()))
  			{
  				tabD.setContent(transactionHistory.viewHistory());
  			}
  		    if(newTab.getText().equals(tabSetting.getText()))
  		    {
  		    	tabSetting.setContent(settings.viewSetting());
  		    }
  		  }

			
  		});
        
        
        mainPane.setLeft(tabPane);
        return mainPane;
    
    }
    
}