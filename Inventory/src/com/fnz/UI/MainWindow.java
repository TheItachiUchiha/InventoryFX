package com.fnz.UI;




import com.fnz.dao.DBInteraction;
import com.fnz.panes.Items;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
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
	
	private Node listBtn;
	private int listBtnX;
	private int listBtnY;
	
	public Settings settings;
	public boolean flag;
    /**
     * @param args the command line arguments
     */
	
	public MainWindow()
	{
		settings = new Settings();
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
    		
    	new DBInteraction().createDB();
    		
        border = new BorderPane();
        border.setTop(upperPart());
        
        border.setBottom(addBBox());
        
        scene = new Scene(border);
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
        Label lName=new Label("copyright © something");
        border.setLeft(lName);
        
        
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
    public void unanimate(Node obj,int x, int y) 
    {
    	//System.out.println(obj);
    	if (listBtn==null){
    		
    	}
    	else{
    	
    	final Timeline timeline = new Timeline();
    	timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0
                new KeyValue(listBtn.translateXProperty(), listBtnX),
                new KeyValue(listBtn.translateYProperty(), listBtnY)),
                new KeyFrame(new Duration(150), // set end position at 10s
                new KeyValue(listBtn.translateXProperty(),listBtnX),
                new KeyValue(listBtn.translateYProperty(), listBtnY)));
    	
			timeline.play();
    	}
    	listBtn=obj;
    	listBtnX=x;
    	listBtnY=y;
    }
    
    
    public void animate(Node obj,int x, int y) 
    {
    	if (listBtn!=obj){
    		unanimate(obj,x,y);
    	
    	
    	
    	final Timeline timeline = new Timeline();
    	timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0
                new KeyValue(obj.translateXProperty(), x),
                new KeyValue(obj.translateYProperty(), y)),
                new KeyFrame(new Duration(100), // set end position at 10s
                new KeyValue(obj.translateXProperty(),x+20),
                new KeyValue(obj.translateYProperty(), y)));
    	
			timeline.play();
    	}
    	/*else{
    		unanimate(obj,x,y);
    	}*/
    }
    private BorderPane upperPart() 
    {

    	BorderPane mainPane = new BorderPane();
    	final Items drinks=new Items();
    	
    	TabPane tabPane = new TabPane();
    	tabPane.setId(("MyTabPane"));
    	
    	
        //Create Tabs
        Tab tabA = new Tab();
        tabA.setText("View Stock");
        
        final BorderPane borderPane1 = new BorderPane();
        borderPane1.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth());
        borderPane1.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight());
        borderPane1.setPadding(new Insets(15,0,0,20));
        try {
        	
        	borderPane1.setId("borderxx");
        	 
        	
        	GridPane typesOfDrink = new GridPane();
        	typesOfDrink.setVgap(8);
        	typesOfDrink.setPadding(new Insets(30,0,0,0));
        	ToggleGroup groupDrink=new ToggleGroup();
        	
        	final ToggleButton tbWine= new ToggleButton("Wine");
        	tbWine.setToggleGroup(groupDrink);
        	tbWine.setId("drinkName");
        	tbWine.setMaxSize(250,250);
        	typesOfDrink.add(tbWine,0,0);
        	
        	tbWine.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('wine.jpeg');");
     				borderPane1.setCenter(drinks.viewWineStock());
     				animate(tbWine,0,0);
     			}
     		});
        	
        	final ToggleButton tbVodka= new ToggleButton("Vodka");
        	tbVodka.setToggleGroup(groupDrink);
        	tbVodka.setId("drinkName");
        	tbVodka.setMaxSize(250,250);
        	typesOfDrink.add(tbVodka,0,1);
        	tbVodka.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('vodka.jpg');");
     				borderPane1.setCenter(drinks.viewVodkaStock());
     				animate(tbVodka,0,1);
     			}
     		});
        	
        	final ToggleButton tbBeer= new ToggleButton("Beer");
        	tbBeer.setToggleGroup(groupDrink);
        	tbBeer.setId("drinkName");
        	tbBeer.setMaxSize(250,250);
        	typesOfDrink.add(tbBeer,0,2);
        	tbBeer.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('beer2.jpg');");
     				borderPane1.setCenter(drinks.viewBeerStock());
     				animate(tbBeer,0,2);
     			}
     		});

        	final ToggleButton tbWisky= new ToggleButton("Whisky");
        	tbWisky.setToggleGroup(groupDrink);
        	tbWisky.setId("drinkName");
        	tbWisky.setMaxSize(250,250);
        	typesOfDrink.add(tbWisky,0,3);
        	tbWisky.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('whisky.jpg');");
     				borderPane1.setCenter(drinks.viewWhiskyStock());
     				animate(tbWisky,0,3);
     			}
     		});
        	
        	final ToggleButton tbRum= new ToggleButton("Rum");
        	tbRum.setToggleGroup(groupDrink);
        	tbRum.setId("drinkName");
        	tbRum.setMaxSize(250,250);
        	typesOfDrink.add(tbRum,0,4);
        	tbRum.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('rum2.jpg');");
     				borderPane1.setCenter(drinks.viewRumStock());
     				animate(tbRum,0,4);
     			}
     		});
        	
        	final ToggleButton tbScotch= new ToggleButton("Scotch");
        	tbScotch.setToggleGroup(groupDrink);
        	tbScotch.setId("drinkName");
        	tbScotch.setMaxSize(250,250);
        	typesOfDrink.add(tbScotch,0,5);
        	tbScotch.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('Scotch.jpg');");
     				borderPane1.setCenter(drinks.viewScotchStock());
     				animate(tbScotch,0,5);
     			}
     		});
        	final ToggleButton tbOther= new ToggleButton("Other");
        	tbOther.setToggleGroup(groupDrink);
        	tbOther.setId("drinkName");
        	tbOther.setMaxSize(250,250);
        	typesOfDrink.add(tbOther,0,6);
        	tbOther.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('othertype.jpg');");
     				borderPane1.setCenter(drinks.viewOtherStock());
     				animate(tbOther,0,6);
     			}
     		});
           // typesOfDrink.getChildren().addAll(tbWine,tbVodka,tbBeer,tbWisky,tbRum,tbScotch,tbOther);
           
			borderPane1.setLeft(typesOfDrink);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        tabA.setContent(borderPane1);
        tabPane.getTabs().add(tabA);
       
        Tab tabB = new Tab();
        tabB.setText("Incoming Stock");
        BorderPane borderPane2 = new BorderPane();
        borderPane2.setPadding(new Insets(5,5,5,5));
        try {
			borderPane2.setCenter(new Label("Window2"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        tabB.setContent(borderPane2);
        tabPane.getTabs().add(tabB);
        
        Tab tabC = new Tab();
        tabC.setText("Outgoing Stock");
        BorderPane borderPane3 = new BorderPane();
        borderPane2.setPadding(new Insets(5,5,5,5));
        try {
			borderPane2.setCenter(new Label("Window3"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        tabB.setContent(borderPane3);
        tabPane.getTabs().add(tabC);
    
       
      //Create Tabs
        Tab tabSetting = new Tab();
        tabSetting.setText("Settings");

        final BorderPane borderPaneSettings = new BorderPane();
        borderPaneSettings.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth());
        borderPaneSettings.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight());
        borderPaneSettings.setPadding(new Insets(15,0,0,20));

        	
    	borderPaneSettings.setId("borderxx");
    	 
    	
    	GridPane gsettings = new GridPane();
    	gsettings.setVgap(8);
    	gsettings.setPadding(new Insets(30,0,0,0));
    	ToggleGroup settingsGroup=new ToggleGroup();
    	
    	ToggleButton bAddCategory= new ToggleButton("Add Category");
    	bAddCategory.setToggleGroup(settingsGroup);
    	bAddCategory.setId("drinkName");
    	bAddCategory.setMaxSize(250,250);
    	gsettings.add(bAddCategory,0,0);
    	
    	bAddCategory.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPaneSettings.setStyle("-fx-background-image: url('settings.jpg');");
 				borderPaneSettings.setCenter(settings.addCategory());
 			}
 		});
    	
    	ToggleButton bAddItem= new ToggleButton("Add Item");
    	bAddItem.setToggleGroup(settingsGroup);
    	bAddItem.setId("drinkName");
    	bAddItem.setMaxSize(250,250);
    	gsettings.add(bAddItem,0,1);
    	
    	bAddItem.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPaneSettings.setStyle("-fx-background-image: url('settings.jpg');");
 				try {
					borderPaneSettings.setCenter(settings.addItem());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 			}
 		});
        
    	
    	borderPaneSettings.setLeft(gsettings);
        tabSetting.setContent(borderPaneSettings);
    	
    	tabPane.getTabs().add(tabSetting);
        
        
        mainPane.setLeft(tabPane);
        return mainPane;
    
    }
}