package com.fnz.UI;

import com.fnz.VO.ItemVO;
import com.fnz.service.StockDetailsService;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Screen;

public class StockDetails 
{
	StockDetailsService stockDetailsService;
	Animation mainWindow;
	public StackPane stack;
	public StockDetails()
	{
		stockDetailsService = new StockDetailsService();
		mainWindow = new Animation();
	}
	
	
	public BorderPane viewStockDrinkList(ObservableList<String> listCategory, final ObservableMap<String, String> mapCategoryIdName)
	{
		final BorderPane borderPane = new BorderPane();
		
		 borderPane.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth());
	        borderPane.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight());
	        borderPane.setPadding(new Insets(15,0,0,20));
	        borderPane.setId("borderxx");
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
 				borderPane.setStyle("-fx-background-image: url('wine.jpeg');");
 				/*borderPane.setCenter(drinks.viewWineStock());
 				chkRect()*/
 				borderPane.setCenter(viewStock(mapCategoryIdName.get("Wine"),"Wine"));
 				mainWindow.animate(tbWine,0,0);
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
 				borderPane.setStyle("-fx-background-image: url('vodka.jpg');");
 				//borderPane.setCenter(drinks.viewVodkaStock());
 				borderPane.setCenter(viewStock(mapCategoryIdName.get("Vodka"),"Vodka"));
 				mainWindow.animate(tbVodka,0,1);
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
 				borderPane.setStyle("-fx-background-image: url('beer2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get("Beer"),"Beer"));
 				mainWindow.animate(tbBeer,0,2);
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
 				borderPane.setStyle("-fx-background-image: url('whisky.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get("Whisky"),"Whisky"));
 				mainWindow.animate(tbWisky,0,3);
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
 				borderPane.setStyle("-fx-background-image: url('rum2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get("Rum"),"Rum"));
 				mainWindow.animate(tbRum,0,4);
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
 				borderPane.setStyle("-fx-background-image: url('Scotch.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get("Scotch"),"Scotch"));
 				mainWindow.animate(tbScotch,0,5);
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
 				borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get("Others"),"Others"));
 				mainWindow.animate(tbOther,0,6);
 			}
 		});
       // typesOfDrink.getChildren().addAll(tbWine,tbVodka,tbBeer,tbWisky,tbRum,tbScotch,tbOther);
    	borderPane.setLeft(typesOfDrink);
    	return borderPane;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public StackPane viewStock(String categoryId, String categoryName)
	{
		stack = new StackPane();
		
		GridPane grid = new GridPane();
	
        grid.setVgap(8);
        grid.setPadding(new Insets(30));
		ObservableList<ItemVO> dataTable;
		
		Rectangle roundRect = RectangleBuilder.create()
	    .x(50)
	    .y(50)
	    .width(Screen.getPrimary().getVisualBounds().getWidth()-160)
	    .height(Screen.getPrimary().getVisualBounds().getHeight()-150)
	    .arcWidth(30)
	    .arcHeight(30)
	    .build();
		
		roundRect.setFill(Color.DARKGRAY);
		roundRect.setOpacity(0.2);
		roundRect.setStroke(Color.TRANSPARENT);
		
		HBox hlabel= new HBox();
		hlabel.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()-170);
		hlabel.setMaxHeight(30);
		hlabel.setStyle("-fx-background-color:black;");
		hlabel.setOpacity(0.3);
		hlabel.setLayoutX(20);
		try
		{
			dataTable = FXCollections.observableArrayList();
			dataTable = stockDetailsService.viewStock(categoryId);
			
			final Label label = new Label(categoryName + " Stock");
		
			
			 final Text text5 = new Text(25, 175, categoryName + " Stock");  
		      text5.setFill(Color.DARKORANGE);  
		      text5.setFont(Font.font ("Edwardian Script ITC", 50));
		      final Light.Distant light = new Light.Distant();  
		      light.setAzimuth(-135.0);  
		      final Lighting lighting = new Lighting();  
		      lighting.setLight(light);  
		      lighting.setSurfaceScale(9.0);  
		      text5.setEffect(lighting);  
		       
		
			
			label.setAlignment(Pos.CENTER_LEFT);
		 	//grid.add(label,1,0);
		 	
		 	
		 	TableView<ItemVO> table1 = new TableView<ItemVO>();
		 	table1.setEditable(false);
		 	table1.setMaxSize(400, 300);
		 	table1.setStyle("-fx-background-color: transparent;");
		 	
		 	TableView<ItemVO> table2 = new TableView<ItemVO>();
		 	table2.setEditable(false);
		 	
		 	table2.setMaxSize(400, 300);
		 	table2.setStyle("-fx-background-color: transparent;");
		 	
		 	TableColumn<ItemVO,String> itemName = new TableColumn<ItemVO,String> ("Item");
		 	itemName.setMinWidth(200);
		 	itemName.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, String>("itemName"));
		 	
		 	TableColumn<ItemVO,Integer>  quantity = new TableColumn<ItemVO,Integer> ("Quantity");
		 	quantity.setMinWidth(200);
		 	quantity.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, Integer>("quantity"));
		 	
		 	
		 	table1.setItems(dataTable);
		 	table1.getColumns().addAll(itemName, quantity);
		 	

		 	table2.setItems(dataTable);
		 	table2.getColumns().addAll(itemName, quantity);
		 	
			
			grid.add(table1,0,12);
			grid.add(table2,1,12);
			grid.setAlignment(Pos.TOP_CENTER);
			
			StackPane.setAlignment(roundRect, Pos.TOP_CENTER);
			StackPane.setMargin(text5, new Insets(50,8,8,8));
			StackPane.setAlignment(text5, Pos.TOP_CENTER);
			stack.getChildren().addAll(text5,roundRect,grid);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return stack;
	
	}
}
