package com.fnz.UI;




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

public class MainWindow extends Application 
{
	private BorderPane border;
	private Stage stage;
	private Scene scene;
	
	public boolean flag;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(MainWindow.class, args);
       
        
        
        
       
    }
    
    @Override
    public void start(Stage stage)
    {
    	try{

// Use a border pane as the root for scene
    		
    	
    		
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
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/fnz/styles/icon.png")));
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
    	
    	
        //Create Tabs
        Tab tabA = new Tab();
        tabA.setText("Add/View Items ");
        
        final BorderPane borderPane1 = new BorderPane();
        borderPane1.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth());
        borderPane1.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight());
        borderPane1.setPadding(new Insets(15,0,0,20));
        try {
        	
        	borderPane1.setId("borderxx");
        	
        	VBox typesOfDrink = new VBox();
        	ToggleGroup groupDrink=new ToggleGroup();
        	
        	ToggleButton tbWine= new ToggleButton("Wine");
        	tbWine.setToggleGroup(groupDrink);
        	tbWine.setId("drinkName");
        	tbWine.setMaxSize(250,250);
        	
        	tbWine.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('com/fnz/styles/wine.jpeg');");
     				
     				
     			}
     		});
        	
        	ToggleButton tbVodka= new ToggleButton("Vodka");
        	tbVodka.setToggleGroup(groupDrink);
        	tbVodka.setId("drinkName");
        	tbVodka.setMaxSize(250,250);
        	tbVodka.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('com/fnz/styles/vodka.jpg');");
     				
     				
     			}
     		});
        	
        	ToggleButton tbBeer= new ToggleButton("Beer");
        	tbBeer.setToggleGroup(groupDrink);
        	tbBeer.setId("drinkName");
        	tbBeer.setMaxSize(250,250);
        	tbBeer.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('com/fnz/styles/beer2.jpg');");
     				
     				
     			}
     		});

        	ToggleButton tbWisky= new ToggleButton("Whisky");
        	tbWisky.setToggleGroup(groupDrink);
        	tbWisky.setId("drinkName");
        	tbWisky.setMaxSize(250,250);
        	tbWisky.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('com/fnz/styles/whisky.jpg');");
     				
     				
     			}
     		});
        	
        	ToggleButton tbRum= new ToggleButton("Rum");
        	tbRum.setToggleGroup(groupDrink);
        	tbRum.setId("drinkName");
        	tbRum.setMaxSize(250,250);
        	tbRum.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('com/fnz/styles/rum2.jpg');");
     				
     				
     			}
     		});
        	
        	ToggleButton tbScotch= new ToggleButton("Scotch");
        	tbScotch.setToggleGroup(groupDrink);
        	tbScotch.setId("drinkName");
        	tbScotch.setMaxSize(250,250);
        	tbScotch.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('com/fnz/styles/Scotch.jpg');");
     				
     				
     			}
     		});
        	ToggleButton tbOther= new ToggleButton("Other");
        	tbOther.setToggleGroup(groupDrink);
        	tbOther.setId("drinkName");
        	tbOther.setMaxSize(250,250);
        	tbOther.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				borderPane1.setStyle("-fx-background-image: url('com/fnz/styles/othertype.jpg');");
     				
     				
     			}
     		});
            typesOfDrink.getChildren().addAll(tbWine,tbVodka,tbBeer,tbWisky,tbRum,tbScotch,tbOther);
           
			borderPane1.setLeft(typesOfDrink);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        tabA.setContent(borderPane1);
        tabPane.getTabs().add(tabA);
       
        Tab tabB = new Tab();
        tabB.setText("Delete/Update Items");
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
    
       
        mainPane.setCenter(tabPane);
        
        return mainPane;
    }
}