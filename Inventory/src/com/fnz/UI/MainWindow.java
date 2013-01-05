package com.fnz.UI;




import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	static DropShadow effect2 = new DropShadow();
	static DropShadow effect1 = new DropShadow();
	static DropShadow effect3 = new DropShadow();
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
        stage.show();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
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
        tabA.setText("Company Details");
        
        BorderPane borderPane1 = new BorderPane();
        borderPane1.setPadding(new Insets(5,5,5,5));
        try {
			borderPane1.setCenter(new Label("Window1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        tabA.setContent(borderPane1);
        tabPane.getTabs().add(tabA);
       
        Tab tabB = new Tab();
        tabB.setText("Tax Details");
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