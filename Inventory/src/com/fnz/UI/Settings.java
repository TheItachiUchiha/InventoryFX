package com.fnz.UI;

import com.fnz.VO.ItemVO;
import com.fnz.common.CommonConstants;
import com.fnz.service.UtiliesService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class Settings 
{
	UtiliesService utiliesService;
	Animation animation;
	public Settings()
	{
		utiliesService = new UtiliesService();
		animation = new Animation();
	}
	/*public GridPane addCategory()
	{
		GridPane settings = new GridPane();
		Label lAddCategory = new Label("Name Of The Category");
		final TextField categoryName = new TextField();
		Button add = new Button("Add Category");
		final Label lmsg = new Label();
		add.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				try 
 				{
					utiliesService.addCategory(categoryName.getText());
					lmsg.setText("Category added successfully");
				} catch (Exception e1) 
				{
					lmsg.setText("Some Error Occured !!");
					e1.printStackTrace();
				}
 			}
 		});
		
		
		
		
		settings.setAlignment(Pos.CENTER);
		
		settings.add(lAddCategory, 1, 1);
		settings.add(categoryName, 2, 1);
		settings.add(add, 2, 2);
		settings.add(lmsg,2,3);
		
		
		
		return settings;
		
	}
	
	public GridPane deleteCategory() throws Exception
	{
		GridPane settings = new GridPane();
		//list Creation
		final ObservableList<String> listOfCategory = FXCollections.observableArrayList();
		listOfCategory.addAll(utiliesService.fetchCategory());
		final ObservableMap<String, String> mapCategory = FXCollections.observableHashMap();
		mapCategory.putAll(utiliesService.fetchCategoryDetails());
		
 		Label lAddItem = new Label("Select Category");
 		final ChoiceBox<String> cbItem = new  ChoiceBox<String>(listOfCategory);
 		Button delete = new Button("Delete Category");
		final Label lmsg = new Label();
		delete.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				try 
 				{
					utiliesService.deleteCategory(mapCategory.get(cbItem.getValue()));
					lmsg.setText("Category Deleted Successfully");
					listOfCategory.clear();
					listOfCategory.addAll(utiliesService.fetchCategory());
				} 
 				catch (Exception e1) 
				{
					lmsg.setText("Please Delete All Items Present In Category "+ cbItem.getValue());
				}
 			}
 		});
		
		settings.setAlignment(Pos.CENTER);
		
		settings.add(lAddItem, 1, 1);
		settings.add(cbItem, 2, 1);
		settings.add(delete,2,3);
		settings.add(lmsg, 2, 4);
		return settings;
	}*/
	public StackPane viewSettings(String buttonClicked) 
	{
		StackPane stack = new StackPane();
		
		GridPane grid = new GridPane();
		GridPane grid2 = new GridPane();
		try{
			if (buttonClicked.equalsIgnoreCase("add")){
		grid=addItem();
		grid2=deleteItem();
			}
			else if(buttonClicked.equalsIgnoreCase("delete")){
				grid=deleteItem();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
        grid.setVgap(8);
        grid.setPadding(new Insets(30));
		ObservableList<ItemVO> dataTable;
		
		Rectangle roundRect = RectangleBuilder.create()
	    .x(50)
	    .y(50)
	    .width(Screen.getPrimary().getVisualBounds().getWidth()-162)
	    .height(Screen.getPrimary().getVisualBounds().getHeight()-150)
	    .arcWidth(30)
	    .arcHeight(30)
	    .build();
		
		roundRect.setFill(Color.DARKGRAY);
		roundRect.setOpacity(0.2);
		roundRect.setStroke(Color.TRANSPARENT);
		/*
		HBox hlabel= new HBox();
		hlabel.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()-160);
		hlabel.setMaxHeight(30);
		hlabel.setStyle("-fx-background-color:black;");
		hlabel.setOpacity(0.3);
		hlabel.setLayoutX(20);*/
		
		Rectangle roundRectsub1 = RectangleBuilder.create()
			    .x(50)
			    .y(50)
			    .width(Screen.getPrimary().getVisualBounds().getWidth()-875)
			    .height(Screen.getPrimary().getVisualBounds().getHeight()-550)
			    .arcWidth(30)
			    .arcHeight(30)
			    .build();
				
		roundRectsub1.setFill(Color.BLACK);
		roundRectsub1.setOpacity(0.2);
		roundRectsub1.setStroke(Color.TRANSPARENT);
		
		Rectangle roundRectsub2 = RectangleBuilder.create()
			    .x(50)
			    .y(50)
			    .width(Screen.getPrimary().getVisualBounds().getWidth()-875)
			    .height(Screen.getPrimary().getVisualBounds().getHeight()-550)
			    .arcWidth(30)
			    .arcHeight(30)
			    .build();
				
		roundRectsub2.setFill(Color.BLACK);
		roundRectsub2.setOpacity(0.2);
		roundRectsub2.setStroke(Color.TRANSPARENT);
		
		try
		{
			/*dataTable = FXCollections.observableArrayList();
			dataTable = stockDetailsService.viewStock(categoryId);*/
			
			/*final Label label = new Label(buttonClicked);*/
			/*label.setAlignment(Pos.CENTER_LEFT);*/
		 	//grid.add(label,1,0);
			final Text text5 = new Text(25, 175, "Settings");  
		      text5.setFill(Color.DARKORANGE);  
		      text5.setFont(Font.font ("Edwardian Script ITC", 50));
		      final Light.Distant light = new Light.Distant();  
		      light.setAzimuth(-135.0);  
		      final Lighting lighting = new Lighting();  
		      lighting.setLight(light);  
		      lighting.setSurfaceScale(9.0);  
		      text5.setEffect(lighting);  
		 	
		      final Text textAdd = new Text(25, 175, "Add Items");  
		      textAdd.setFill(Color.PURPLE);  
		      textAdd.setFont(Font.font ("Edwardian Script ITC", 30));
		      final Light.Distant lightAdd = new Light.Distant();  
		      lightAdd.setAzimuth(-135.0);  
		      final Lighting lightingAdd = new Lighting();  
		      lighting.setLight(lightAdd);  
		      lighting.setSurfaceScale(9.0);  
		      textAdd.setEffect(lighting);
		      
		      final Text textDelete = new Text(25, 175, "Delete Items");
		      textDelete.setFill(Color.PURPLE);  
		      textDelete.setFont(Font.font ("Edwardian Script ITC", 30));
		      textDelete.setEffect(lighting);
		      
		     
		      
			/*grid.add(table1,0,12);
			grid.add(table2,1,12);
			grid.setAlignment(Pos.TOP_CENTER);*/
		     
			StackPane.setAlignment(roundRect, Pos.TOP_CENTER);
			
			StackPane.setAlignment(roundRectsub1, Pos.CENTER_LEFT);
			StackPane.setMargin(roundRectsub1, new Insets(00,200,200,100));
			
			StackPane.setAlignment(roundRectsub2, Pos.CENTER_RIGHT);
			StackPane.setMargin(roundRectsub2, new Insets(0,100,200,60));
			
			StackPane.setMargin(text5, new Insets(50,8,8,8));
			StackPane.setAlignment(text5, Pos.TOP_CENTER);
			
			StackPane.setAlignment(textAdd, Pos.CENTER_LEFT);
			StackPane.setMargin(textAdd, new Insets(0,600,300,300));
			
			
			
			StackPane.setAlignment(textDelete,Pos.CENTER_RIGHT);
			StackPane.setMargin(textDelete, new Insets(0,300,300,200));
			
			StackPane.setAlignment(grid, Pos.CENTER_LEFT);
			StackPane.setMargin(grid, new Insets(200,0,200,175));
			
			StackPane.setAlignment(grid2, Pos.CENTER_RIGHT);
			StackPane.setMargin(grid2, new Insets(225,0,100,800));
			
			stack.getChildren().addAll(roundRectsub1,roundRectsub2,textAdd,textDelete,text5,roundRect,grid,grid2);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return stack;
	
	}
	public GridPane addItem() throws Exception
	{
		GridPane settings = new GridPane();
		//list Creation
		ObservableList<String> listOfCategories = FXCollections.observableArrayList();
		listOfCategories = utiliesService.fetchCategory();
		final ObservableMap<String, String> mapCategories = FXCollections.observableHashMap();
		mapCategories.putAll(utiliesService.fetchCategoryDetails());
		
 		Label lAddItem = new Label("Name Of The Item");
		final TextField itemName = new TextField();
		Button add = new Button("Add Item");
		Label lCategoryName = new Label("Select Category");
		final ChoiceBox<String> cbcategory = new  ChoiceBox<String>(listOfCategories);
		final Label ldp = new Label("Distributor Price");
		final TextField dp = new TextField();
		final Label lmrp = new Label("MRP");
		final TextField mrp = new TextField();
		final Label lhp = new Label("Hotel Price");
		final TextField hp = new TextField();
		
		final Label lTypes = new Label("Types");
		
		final String[] types = new String[]{CommonConstants.QUAD, CommonConstants.PINT, CommonConstants.NIP};
		final CheckBox cb1 = new CheckBox(CommonConstants.QUAD);
		final CheckBox cb2 = new CheckBox(CommonConstants.PINT);
		final CheckBox cb3 = new CheckBox(CommonConstants.NIP);
		
		final Label lmsg = new Label();
		add.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				try 
 				{
					utiliesService.addItem(itemName.getText(), mapCategories.get(cbcategory.getValue()),Integer.parseInt(dp.getText()),
							Integer.parseInt(mrp.getText()),Integer.parseInt(hp.getText()),
							cb1.isSelected(), cb2.isSelected(), cb3.isSelected());
					lmsg.setText("Item added successfully");
				} catch (Exception e1) 
				{
					lmsg.setText("Some Error Occured !!");
					e1.printStackTrace();
				}
 			}
 		});
		
		
		
		
		//settings.setAlignment(Pos.CENTER);
		settings.setVgap(8);
		settings.setHgap(10);
		settings.add(lAddItem, 1, 1);
		settings.add(itemName, 2, 1);
		settings.add(lCategoryName, 1, 2);
		settings.add(cbcategory,2,2);
		
		
		settings.add(ldp,1,3);
		settings.add(dp,2,3);
		settings.add(lmrp,1,4);
		settings.add(mrp,2,4);
		settings.add(lhp,1,5);
		settings.add(hp,2,5);
		settings.add(lTypes,1,6);
		settings.add(cb1,2,6);	
		settings.add(cb2,3,6);	
		settings.add(cb3,4,6);	
		
		settings.add(add,2,7);
		settings.add(lmsg,2,8);
		return settings;
	}
	
	public GridPane deleteItem() throws Exception
	{
		GridPane settings = new GridPane();
		//list Creation
		final ObservableList<String> listOfItems = FXCollections.observableArrayList();
		listOfItems.addAll(utiliesService.fetchItem());
		final ObservableMap<String, String> mapItem = FXCollections.observableHashMap();
		mapItem.putAll(utiliesService.fetchItemDetails());
		
 		Label lAddItem = new Label("Select Item");
 		final ChoiceBox<String> cbItem = new  ChoiceBox<String>(listOfItems);
 		Button delete = new Button("Delete Item");
		final Label lmsg = new Label();
		delete.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				try 
 				{
					utiliesService.deleteItem(mapItem.get(cbItem.getValue()));
					lmsg.setText("Item Deleted successfully");
					listOfItems.clear();
					listOfItems.addAll(utiliesService.fetchItem());
				} catch (Exception e1) 
				{
					lmsg.setText("Some Error Occured !!");
					e1.printStackTrace();
				}
 			}
 		});
		
		//settings.setAlignment(Pos.CENTER);
		settings.setVgap(8);
		settings.setHgap(10);
		settings.add(lAddItem, 1, 1);
		settings.add(cbItem, 2, 1);
		settings.add(delete,2,3);
		settings.add(lmsg, 2, 4);
		return settings;
	}
	
	public BorderPane viewSetting()
	{
		
		final BorderPane borderPane = new BorderPane();
		borderPane.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth());
        borderPane.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight());
        borderPane.setPadding(new Insets(15,0,0,20));
        borderPane.setId("borderxx");
        
		final GridPane gsettings = new GridPane();
    	gsettings.setVgap(8);
    	gsettings.setPadding(new Insets(30,0,0,0));
    	
    	ToggleGroup settingsGroup=new ToggleGroup();
    	
    	final ToggleButton bAddItem= new ToggleButton("Settings");
    	bAddItem.setToggleGroup(settingsGroup);
    	bAddItem.setId("drinkName");
    	bAddItem.setMaxSize(250,250);
    	gsettings.add(bAddItem,0,1);
    	
    	
    	bAddItem.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('settings.jpg');");
 				try
 				{
 					animation.animateSettings(bAddItem, 0, 0);
					borderPane.setCenter(viewSettings("add"));
				}
 				catch (Exception e1)
 				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 			}
 		});
    	
    	borderPane.setLeft(gsettings);
		
		
    	return borderPane;
	}
	
	
}
