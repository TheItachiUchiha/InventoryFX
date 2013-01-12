package com.fnz.UI;

import java.util.List;

import org.w3c.dom.ls.LSInput;

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
	
	
	public BorderPane viewStockDrinkList(final ObservableList<String> listCategory, final ObservableMap<String, String> mapCategoryIdName)
	{
		final BorderPane borderPane = new BorderPane();
		
		 borderPane.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth());
	        borderPane.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight());
	        borderPane.setPadding(new Insets(15,0,0,20));
	        borderPane.setId("borderxx");
		GridPane typesOfDrink = new GridPane();
		
		typesOfDrink.setVgap(20);
    	typesOfDrink.setPadding(new Insets(130,0,0,0));

    	ToggleGroup groupDrink=new ToggleGroup();
    	
    	GridPane typesOfDrink2 = new GridPane();
		
		typesOfDrink2.setVgap(20);
    	typesOfDrink2.setPadding(new Insets(130,30,0,0));

    	
    	
    	final ToggleButton bt1= new ToggleButton(listCategory.get(0));
    	bt1.setToggleGroup(groupDrink);
    	bt1.setId("drinkName");
    	bt1.setMaxSize(250,250);
    	typesOfDrink2.add(bt1,0,0); //premium whisky
    	
    	bt1.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('wine.jpeg');");
 				/*borderPane.setCenter(drinks.viewWineStock());
 				chkRect()*/
 				borderPane.setCenter(viewStock(mapCategoryIdName.get(listCategory.get(0)),listCategory.get(0)));
 				mainWindow.animateLeft(bt1,0,0);
 			}
 		});
    	
    	final ToggleButton bt2= new ToggleButton(listCategory.get(1));
    	bt2.setToggleGroup(groupDrink);
    	bt2.setId("drinkName");
    	bt2.setMaxSize(250,250);
    	typesOfDrink.add(bt2,0,0); //regular whisky
    	bt2.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('vodka.jpg');");
 				//borderPane.setCenter(drinks.viewVodkaStock());
 				borderPane.setCenter(viewStock(mapCategoryIdName.get(listCategory.get(1)),listCategory.get(1)));
 				mainWindow.animateRight(bt2,0,0);
 			}
 		});
    	
    	final ToggleButton bt3= new ToggleButton(listCategory.get(2));
    	bt3.setToggleGroup(groupDrink);
    	bt3.setId("drinkName");
    	bt3.setMaxSize(250,250);
    	typesOfDrink2.add(bt3,0,1);//Premium Vodka
    	bt3.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('beer2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get(listCategory.get(2)),listCategory.get(2)));
 				mainWindow.animateLeft(bt3,0,1);
 			}
 		});

    	final ToggleButton bt4= new ToggleButton(listCategory.get(3));
    	bt4.setToggleGroup(groupDrink);
    	bt4.setId("drinkName");
    	bt4.setMaxSize(250,250);
    	typesOfDrink.add(bt4,0,1);//Regular Vodka
    	bt4.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('whisky.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get("Whisky"),"Whisky"));
 				mainWindow.animateRight(bt4,0,1);
 			}
 		});
    	
    	final ToggleButton bt5= new ToggleButton(listCategory.get(4));
    	bt5.setToggleGroup(groupDrink);
    	bt5.setId("drinkName");
    	bt5.setMaxSize(250,250);
    	typesOfDrink.add(bt5,0,4);//Brandy
    	bt5.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('rum2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get("Rum"),"Rum"));
 				mainWindow.animateRight(bt5,0,4);
 			}
 		});
    	
    	final ToggleButton bt6= new ToggleButton(listCategory.get(5));
    	bt6.setToggleGroup(groupDrink);
    	bt6.setId("drinkName");
    	bt6.setMaxSize(250,250);
    	typesOfDrink.add(bt6,0,5);//Gin
    	bt6.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('Scotch.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get(listCategory.get(5)),listCategory.get(5)));
 				mainWindow.animateRight(bt6,0,5);
 			}
 		});
    	
    	final ToggleButton bt7= new ToggleButton(listCategory.get(6).replace('&','\n' ));
    	bt7.setToggleGroup(groupDrink);
    	bt7.setId("drinkName");
    	bt7.setMaxSize(250,250);
    	typesOfDrink2.add(bt7,0,5);//White Rum and Premixes
    	bt7.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get(listCategory.get(6)),listCategory.get(6)));
 				mainWindow.animateLeft(bt7,0,5);
 			}
 		});
    	
    	final ToggleButton bt8= new ToggleButton(listCategory.get(7));
    	bt8.setToggleGroup(groupDrink);
    	bt8.setId("drinkName");
    	bt8.setMaxSize(250,250);
    	typesOfDrink.add(bt8,0,3);//Regular Rum
    	bt8.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get(listCategory.get(6)),listCategory.get(6)));
 				mainWindow.animateRight(bt8,0,3);
 			}
 		});
    	
    	final ToggleButton bt9= new ToggleButton(listCategory.get(8));
    	bt9.setToggleGroup(groupDrink);
    	bt9.setId("drinkName");
    	bt9.setMaxSize(250,250);
    	typesOfDrink.add(bt9,0,6);//Beer
    	bt9.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get(listCategory.get(8)),listCategory.get(8)));
 				mainWindow.animateRight(bt9,0,6);
 			}
 		});
    	
    	final ToggleButton bt10= new ToggleButton(listCategory.get(9));
    	bt10.setToggleGroup(groupDrink);
    	bt10.setId("drinkName");
    	bt10.setMaxSize(250,250);
    	typesOfDrink2.add(bt10,0,3);//Wine
    	bt10.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get(listCategory.get(9)),listCategory.get(9)));
 				mainWindow.animateLeft(bt10,0,3);
 			}
 		});
    	
    	final ToggleButton bt11= new ToggleButton(listCategory.get(10));
    	bt11.setToggleGroup(groupDrink);
    	bt11.setId("drinkName");
    	bt11.setMaxSize(250,250);
    	typesOfDrink2.add(bt11,0,2);//Premium Scotch
    	bt11.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get(listCategory.get(10)),listCategory.get(10)));
 				mainWindow.animateLeft(bt11,0,2);
 			}
 		});
    	
    	final ToggleButton bt12= new ToggleButton(listCategory.get(11));
    	bt12.setToggleGroup(groupDrink);
    	bt12.setId("drinkName");
    	bt12.setMaxSize(250,250);
    	typesOfDrink.add(bt12,0,2); //regular Scotch
    	bt12.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get(listCategory.get(11)),listCategory.get(11)));
 				mainWindow.animateRight(bt12,0,2);
 			}
 		});
    	
    	final ToggleButton bt13= new ToggleButton(listCategory.get(12));
    	bt13.setToggleGroup(groupDrink);
    	bt13.setId("drinkName");
    	bt13.setMaxSize(250,250);
    	typesOfDrink2.add(bt13,0,4);//Beverages
    	bt13.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(mapCategoryIdName.get(listCategory.get(12)),listCategory.get(12)));
 				mainWindow.animateLeft(bt13,0,4);
 			}
 		});
       // typesOfDrink.getChildren().addAll(tbWine,tbVodka,tbBeer,tbWisky,tbRum,tbScotch,tbOther);
    	borderPane.setLeft(typesOfDrink);
    	borderPane.setRight(typesOfDrink2);
    	bt2.fire();
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
	    .width(Screen.getPrimary().getVisualBounds().getWidth()-428)
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
