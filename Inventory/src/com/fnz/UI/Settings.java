package com.fnz.UI;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.RoundEnvironment;

import com.fnz.VO.CategoryTypeVO;
import com.fnz.VO.CategoryVO;
import com.fnz.VO.ItemTypeVO;
import com.fnz.VO.ItemVO;
import com.fnz.Validation.Validation;
import com.fnz.common.CommonConstants;
import com.fnz.dao.UtiliesDAO;
import com.fnz.service.UtiliesService;
import com.mytdev.javafx.scene.control.AutoCompleteTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
import javafx.util.Callback;

public class Settings 
{
	UtiliesService utiliesService;
	Animation animation;
	ObservableList<CategoryVO> listOfCategories;
	ItemTypeVO itemTypeVO;
	Validation validate=new Validation();
	public Settings()
	{
		utiliesService = new UtiliesService();
		animation = new Animation();
		listOfCategories = UtiliesDAO.getUtiliesDAO().categoryList;
	}

	public ItemTypeVO getItemTypeVO() {
		return itemTypeVO;
	}

	public void setItemTypeVO(ItemTypeVO itemTypeVO) {
		this.itemTypeVO = itemTypeVO;
	}

	/*public GridPane addCategory()
	{
		GridPane settings = new GridPane();
		final CategoryVO categoryVO = new CategoryVO();
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
			if (buttonClicked.equalsIgnoreCase("add"))
			{
				grid=addItem();
				grid2=editDeleteItem();
			}
			else if(buttonClicked.equalsIgnoreCase("delete")){
				grid=editDeleteItem();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
        grid.setVgap(8);
        grid.setPadding(new Insets(30));
		
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
		
		Rectangle roundRect1 = RectangleBuilder.create()
			    .x(50)
			    .y(50)
			    .width(roundRect.getWidth()/2.5)
			    .height(roundRect.getHeight()/3)
			    .arcWidth(30)
			    .arcHeight(30)
			    .build();
				
				roundRect1.setFill(Color.DARKGRAY);
				roundRect1.setOpacity(0.2);
				roundRect1.setStroke(Color.TRANSPARENT);
				
				Rectangle roundRect2 = RectangleBuilder.create()
					    .x(50)
					    .y(50)
					    .width(roundRect.getWidth()/2.5)
					    .height(roundRect.getHeight()/3)
					    .arcWidth(30)
					    .arcHeight(30)
					    .build();
						
						roundRect2.setFill(Color.DARKGRAY);
						roundRect2.setOpacity(0.2);
						roundRect2.setStroke(Color.TRANSPARENT);
		/*
		HBox hlabel= new HBox();
		hlabel.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()-160);
		hlabel.setMaxHeight(30);
		hlabel.setStyle("-fx-background-color:black;");
		hlabel.setOpacity(0.3);
		hlabel.setLayoutX(20);*/
		
		
		try
		{
			/*dataTable = FXCollections.observableArrayList();
			dataTable = stockDetailsService.viewStock(categoryId);*/
			
			/*final Label label = new Label(buttonClicked);*/
			/*label.setAlignment(Pos.CENTER_LEFT);*/
		 	//grid.add(label,1,0);
			final Text text5 = new Text(25, 175, "Settings Items");  
		      text5.setFill(Color.DARKORANGE);  
		      text5.setFont(Font.font ("Edwardian Script ITC", 50));
		      final Light.Distant light = new Light.Distant();  
		      light.setAzimuth(-135.0);  
		      final Lighting lighting = new Lighting();  
		      lighting.setLight(light);  
		      lighting.setSurfaceScale(9.0);  
		      text5.setEffect(lighting);  
		 	
		      final Text textAdd = new Text(25, 175, "Add Items");  
		      textAdd.setFill(Color.DARKGOLDENROD);  
		      textAdd.setFont(Font.font ("Arial", 16));
		      final Light.Distant lightAdd = new Light.Distant();  
		      lightAdd.setAzimuth(-135.0);  
		      final Lighting lightingAdd = new Lighting();  
		      lighting.setLight(lightAdd);  
		      lighting.setSurfaceScale(9.0);  
		    //  textAdd.setEffect(lighting);
		      
		      final Text textDelete = new Text(25, 175, "Delete Items");
		      textDelete.setFill(Color.DARKGOLDENROD);  
		      textDelete.setFont(Font.font ("Arial", 16));
		      //textDelete.setEffect(lighting);
		      
		     
		      
			/*grid.add(table1,0,12);
			grid.add(table2,1,12);
			grid.setAlignment(Pos.TOP_CENTER);*/
		  	Text man_text=new Text(CommonConstants.STAR_MSG);
			man_text.setFill(Color.MAROON);  
			man_text.setFont(Font.font ("Arial", 12));
			StackPane.setMargin(man_text, new Insets(210,18,20,120));
			StackPane.setAlignment(man_text, Pos.BASELINE_LEFT);
			
		  	StackPane.setMargin(roundRect, new Insets(0,0,0,69)); 
			StackPane.setAlignment(roundRect, Pos.TOP_LEFT);
			
			StackPane.setMargin(roundRect1, new Insets(0,0,roundRect.getHeight()/3.6,roundRect.getWidth()/7)); 
			StackPane.setAlignment(roundRect1, Pos.CENTER_LEFT);
			
			StackPane.setMargin(roundRect2, new Insets(0,0,roundRect.getHeight()/3.6,roundRect.getWidth()/1.65)); 
			StackPane.setAlignment(roundRect2, Pos.CENTER_LEFT);
			
			StackPane.setMargin(text5, new Insets(50,0,0,Screen.getPrimary().getVisualBounds().getWidth()/3));
			StackPane.setAlignment(text5, Pos.TOP_LEFT);
			
			StackPane.setAlignment(textAdd, Pos.CENTER_LEFT);
			StackPane.setMargin(textAdd, new Insets(0,700,300,roundRect1.getWidth()/1.3));
			
			
			
			StackPane.setAlignment(textDelete,Pos.CENTER_RIGHT);
			StackPane.setMargin(textDelete, new Insets(0,roundRect2.getWidth()/0.86,300,0));
			
			StackPane.setAlignment(grid, Pos.CENTER_LEFT);
			StackPane.setMargin(grid, new Insets(200,0,200,roundRect1.getWidth()/2.6));
			
			StackPane.setAlignment(grid2, Pos.CENTER_RIGHT);
			StackPane.setMargin(grid2, new Insets(230,0,100,roundRect2.getWidth()/0.60));
			
			stack.getChildren().addAll(textAdd,textDelete,text5,roundRect,roundRect1,roundRect2,grid,grid2,man_text);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return stack;
	
	}
	
	
	
	/* Veiw Edit Delete */
	
	public StackPane viewAddDeleteCategories(String buttonClicked) throws Exception 
	{
		StackPane stack = new StackPane();
		
		GridPane grid = new GridPane();
		GridPane grid2 = new GridPane();
		
		grid=addTypeToCategory();
		grid2=editDeleteTypeToCategory();
			
		
		
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
		
		Rectangle roundRect1 = RectangleBuilder.create()
			    .x(50)
			    .y(50)
			    .width(roundRect.getWidth()/2.5)
			    .height(roundRect.getHeight()/3)
			    .arcWidth(30)
			    .arcHeight(30)
			    .build();
				
				roundRect1.setFill(Color.DARKGRAY);
				roundRect1.setOpacity(0.2);
				roundRect1.setStroke(Color.TRANSPARENT);
				
				Rectangle roundRect2 = RectangleBuilder.create()
					    .x(50)
					    .y(50)
					    .width(roundRect.getWidth()/2.5)
					    .height(roundRect.getHeight()/3)
					    .arcWidth(30)
					    .arcHeight(30)
					    .build();
						
						roundRect2.setFill(Color.DARKGRAY);
						roundRect2.setOpacity(0.2);
						roundRect2.setStroke(Color.TRANSPARENT);
		/*
		HBox hlabel= new HBox();
		hlabel.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()-160);
		hlabel.setMaxHeight(30);
		hlabel.setStyle("-fx-background-color:black;");
		hlabel.setOpacity(0.3);
		hlabel.setLayoutX(20);*/
		
		
		
		try
		{
			/*dataTable = FXCollections.observableArrayList();
			dataTable = stockDetailsService.viewStock(categoryId);*/
			
			/*final Label label = new Label(buttonClicked);*/
			/*label.setAlignment(Pos.CENTER_LEFT);*/
		 	//grid.add(label,1,0);
			final Text text5 = new Text(25, 175, "Settings Categories");  
		      text5.setFill(Color.DARKORANGE);  
		      text5.setFont(Font.font ("Edwardian Script ITC", 50));
		      final Light.Distant light = new Light.Distant();  
		      light.setAzimuth(-135.0);  
		      final Lighting lighting = new Lighting();  
		      lighting.setLight(light);  
		      lighting.setSurfaceScale(9.0);  
		      text5.setEffect(lighting);  
		 	
		      final Text textAdd = new Text(25, 175, "Add Category Types");  
		      textAdd.setFill(Color.DARKGOLDENROD);  
		      textAdd.setFont(Font.font ("Arial", 16));
		      final Light.Distant lightAdd = new Light.Distant();  
		      lightAdd.setAzimuth(-135.0);  
		      final Lighting lightingAdd = new Lighting();  
		      lighting.setLight(lightAdd);  
		      lighting.setSurfaceScale(9.0);  
		     // textAdd.setEffect(lighting);
		      
		      final Text textDelete = new Text(25, 1000, "Edit/Delete Category Types");
		      textDelete.setFill(Color.DARKGOLDENROD);  
		      textDelete.setFont(Font.font ("Arial", 16));
		    //  textDelete.setEffect(lighting);
		      
		     
		      
			/*grid.add(table1,0,12);
			grid.add(table2,1,12);
			grid.setAlignment(Pos.TOP_CENTER);*/
		      StackPane.setMargin(roundRect, new Insets(0,0,0,69)); 
				StackPane.setAlignment(roundRect, Pos.TOP_LEFT);
				
				StackPane.setMargin(roundRect1, new Insets(0,0,roundRect.getHeight()/3.6,roundRect.getWidth()/7)); 
				StackPane.setAlignment(roundRect1, Pos.CENTER_LEFT);
				
				StackPane.setMargin(roundRect2, new Insets(0,0,roundRect.getHeight()/3.6,roundRect.getWidth()/1.65)); 
				StackPane.setAlignment(roundRect2, Pos.CENTER_LEFT);
				
				StackPane.setMargin(text5, new Insets(50,0,0,Screen.getPrimary().getVisualBounds().getWidth()/3.5));
				StackPane.setAlignment(text5, Pos.TOP_LEFT);
				
				StackPane.setAlignment(textAdd, Pos.CENTER_LEFT);
				StackPane.setMargin(textAdd, new Insets(0,700,300,roundRect1.getWidth()/1.40));
			
			
				StackPane.setAlignment(textDelete,Pos.CENTER_RIGHT);
				StackPane.setMargin(textDelete, new Insets(0,roundRect2.getWidth()/1.1,300,0));
				
				StackPane.setAlignment(grid, Pos.CENTER_LEFT);
				StackPane.setMargin(grid, new Insets(210,0,200,roundRect1.getWidth()/2.3));
				
				StackPane.setAlignment(grid2, Pos.CENTER_RIGHT);
				StackPane.setMargin(grid2, new Insets(230,0,100,roundRect2.getWidth()/0.60));
			
			stack.getChildren().addAll(textAdd,textDelete,text5,roundRect,roundRect1,roundRect2,grid,grid2);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return stack;
	
	}
	public GridPane addItem() throws Exception
	{
		final GridPane settings = new GridPane();
		//list Creation
		
		final ItemVO itemVO = new ItemVO();
		
		final ObservableList<CategoryTypeVO> listOfTypes = FXCollections.observableArrayList();
		
		final ObservableMap<String, String> mapCategories = FXCollections.observableHashMap();
		mapCategories.putAll(utiliesService.fetchCategoryDetails());
		
		//final ObservableList<ItemTypeVO> listItemTypes  = FXCollections.observableArrayList();
		
 		Label lAddItem = new Label("Name Of The Item");
 		lAddItem.setTextFill(Color.DARKGOLDENROD);
		final TextField itemName = new TextField();
		final Button add = new Button("Add");
		add.setId("buttonall");
		final Label lCategoryName = new Label("Select Category");
		lCategoryName.setTextFill(Color.DARKGOLDENROD);
		final ComboBox<CategoryVO> cbcategory = new  ComboBox<CategoryVO>(listOfCategories);

		
		final Label lmsg = new Label();
		
		
		
		
		
		//settings.setAlignment(Pos.CENTER);
		settings.setVgap(8);
		settings.setHgap(10);
		settings.add(lAddItem, 1, 2);
		settings.add(itemName, 2, 2);
		settings.add(lCategoryName, 1, 1);
		settings.add(cbcategory,2,1);
			
		
		settings.add(add,2,4);
		settings.add(lmsg,2,5);
		
		
		cbcategory.valueProperty().addListener(new ChangeListener<CategoryVO>() {

			@Override
			public void changed(ObservableValue<? extends CategoryVO> observable,
					CategoryVO oldValue, CategoryVO newValue) 
			{
				listOfTypes.clear();
				try
				{
					listOfTypes.addAll(UtiliesDAO.getUtiliesDAO().fetchTypes(newValue.getCategotyId()));
					if(listOfTypes.size()==0)
					{
						lmsg.setText("Please set a Type \nFor The Category");
						add.setDisable(true);
						itemName.setDisable(true);
					}
					else
					{
						lmsg.setText("");
						add.setDisable(false);
						itemName.setDisable(false);
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			});
		
		add.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				try 
 				{
 					if (validate.isEmpty(itemName.getText())){
 						lmsg.setTextFill(Color.MAROON);
 						itemName.getStyleClass().add("error");
 						lmsg.setText(CommonConstants.EMPTY_MSG);
 					}
 					else if((cbcategory.getValue()) == null){
 						lmsg.setTextFill(Color.MAROON);
 						itemName.getStyleClass().remove("error");
 						//cbcategory.getStyleClass().add("error");
 						lmsg.setText(CommonConstants.COMBO_MSG);
 						
 						
 					}
 					else{
 						
 					itemVO.setItemName(itemName.getText());
 					itemVO.setCategoryId(cbcategory.getValue().getCategotyId());
 					//
 					
 					itemName.getStyleClass().remove("error");
					utiliesService.addItem(itemVO);
					lmsg.setTextFill(Color.GREENYELLOW);
					lmsg.setText(CommonConstants.ITEMADD_MSG);
 					}
				} 
 				catch (Exception e1) 
				{
 					
 					lmsg.setTextFill(Color.MAROON);
					lmsg.setText(CommonConstants.ALREADY_EXISTS);
					e1.printStackTrace();
				}
 			}
 		});
		return settings;
	}
	
/*	public GridPane EditItem() throws Exception
	{
		final GridPane settings = new GridPane();
		//list Creation
		
		final ItemVO itemVO = new ItemVO();
	
	
		final ObservableMap<String, String> mapItem = FXCollections.observableHashMap();
		//mapItem.putAll(utiliesService.fetchItemDetails());
		ObservableList<String> listOfTypes = FXCollections.observableArrayList();
		//listOfTypes = utiliesService.fetchTypes();
		final ObservableMap<String, String> mapCategories = FXCollections.observableHashMap();
		mapCategories.putAll(utiliesService.fetchCategoryDetails());
		
		final ObservableList<ItemTypeVO> listItemTypes  = FXCollections.observableArrayList();
		
 		Label lAddItem = new Label("Name Of The Item");
		final TextField itemName = new TextField();
		Button add = new Button("Add Item");
		Label lCategoryName = new Label("Select Category");
		final ChoiceBox<CategoryVO> cbcategory = new  ChoiceBox<CategoryVO>(listOfCategories);
		final Label ldp = new Label("Distributor Price");
		
		final Label lmrp = new Label("MRP");
		
		final Label lhp = new Label("Hotel Price");
		
		
		
		final Label lTypes = new Label("Types");
		
		final AutoCompleteTextField<String> type1 = new AutoCompleteTextField<String>();
		type1.setItems(listOfTypes);
		final AutoCompleteTextField<String> type2 = new AutoCompleteTextField<String>();
		type2.setItems(listOfTypes);
		final AutoCompleteTextField<String> type3 = new AutoCompleteTextField<String>();
		type3.setItems(listOfTypes);
		
		final TextField tdp1 = new TextField();
		final TextField tdp2 = new TextField();
		final TextField tdp3 = new TextField();
		final TextField tmrp1 = new TextField();
		final TextField tmrp2 = new TextField();
		final TextField tmrp3 = new TextField();
		final TextField thp1 = new TextField();
		final TextField thp2 = new TextField();
		final TextField thp3 = new TextField();
		
		final Label lmsg = new Label();
		
		
		
		
		
		//settings.setAlignment(Pos.CENTER);
		settings.setVgap(8);
		settings.setHgap(10);
		settings.add(lAddItem, 1, 1);
		settings.add(itemName, 2, 1);
		settings.add(lCategoryName, 1, 2);
		settings.add(cbcategory,2,2);
		
		settings.add(lTypes,1,3);
		settings.add(type1,2,3);	
		settings.add(type2,3,3);	
		settings.add(type3,4,3);
		
		settings.add(ldp,1,4);
		settings.add(lmrp,1,5);	
		settings.add(lhp,1,6);
		
		settings.add(tdp1,2,4);
		settings.add(tdp2,3,4);
		settings.add(tdp3,4,4);
		
		settings.add(tmrp1,2,5);
		settings.add(tmrp2,3,5);
		settings.add(tmrp3,4,5);
		
		settings.add(thp1,2,6);
		settings.add(thp2,3,6);
		settings.add(thp3,4,6);
		
				
		
		settings.add(add,2,7);
		settings.add(lmsg,2,8);
		
		add.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				try 
 				{
 					ItemTypeVO itemTypeVO;
 					if(!type1.getText().equals(""))
 					{
 						itemTypeVO = new ItemTypeVO();
 						
 						itemTypeVO.setType(type1.getText());
 						itemTypeVO.setQuantity(CommonConstants.ZERO);
 						itemTypeVO.setDp(Integer.parseInt(tdp1.getText()));
 						itemTypeVO.setMrp(Integer.parseInt(tmrp1.getText()));
 						itemTypeVO.setHp(Integer.parseInt(thp1.getText()));
 						
 						listItemTypes.add(itemTypeVO);
 					}
 					if(!type2.getText().equals(""))
 					{
 						itemTypeVO = new ItemTypeVO();
 						
 						itemTypeVO.setType(type2.getText());
 						itemTypeVO.setQuantity(CommonConstants.ZERO);
 						itemTypeVO.setDp(Integer.parseInt(tdp2.getText()));
 						itemTypeVO.setMrp(Integer.parseInt(tmrp2.getText()));
 						itemTypeVO.setHp(Integer.parseInt(thp2.getText()));
 						
 						listItemTypes.add(itemTypeVO);
 					}
 					if(!type3.getText().equals(""))
 					{
 						itemTypeVO = new ItemTypeVO();
 						
 						itemTypeVO.setType(type3.getText());
 						itemTypeVO.setQuantity(CommonConstants.ZERO);
 						itemTypeVO.setDp(Integer.parseInt(tdp3.getText()));
 						itemTypeVO.setMrp(Integer.parseInt(tmrp3.getText()));
 						itemTypeVO.setHp(Integer.parseInt(thp3.getText()));
 						
 						listItemTypes.add(itemTypeVO);
 					}

 					itemVO.setItemName(itemName.getText());
 					itemVO.setCategoryId(mapCategories.get(cbcategory.getValue()));
 					itemVO.setListType(listItemTypes);

					utiliesService.addItem(itemVO);
					lmsg.setText("Item added successfully");
				} catch (Exception e1) 
				{
					lmsg.setText("Some Error Occured !!");
					e1.printStackTrace();
				}
 			}
 		});
		return settings;
	}*/
	
	
	public GridPane editDeleteItem() throws Exception
	{
		final GridPane settings = new GridPane();
		//list Creation
		final ObservableList<ItemVO> listOfItems = FXCollections.observableArrayList();
		listOfItems.addAll(utiliesService.fetchItem());
		
 		Label lAddItem = new Label("Select Item");
 		lAddItem.setTextFill(Color.DARKGOLDENROD);
 		final ComboBox<ItemVO> cbItem = new  ComboBox<ItemVO>(listOfItems);
 		Button edit = new Button("Edit");
 		edit.setId("buttonall");
 		Button delete = new Button("Delete");
 		delete.setId("buttonall");
		final Label lmsg = new Label();
		
		final HBox box = new HBox();
		box.setAlignment(Pos.TOP_LEFT);
		box.setSpacing(10);
		box.getChildren().addAll(edit, delete);
		
		final Label editLabel = new Label("New Item Name");
		editLabel.setTextFill(Color.DARKGOLDENROD);
		final TextField editText = new TextField();
		final Button editFinal =new Button("Edit");
		
		edit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e)
			{
				try 
 				{
					settings.add(editLabel, 1, 3);
					settings.add(editText, 2, 3);
					settings.add(editFinal,2,4);
					box.setVisible(false);
 				}
				catch (Exception e1) 
				{
					lmsg.setText("Some Error Occured !!");
					e1.printStackTrace();
				}
			}
		});
		
		
		editFinal.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e)
			{
				try 
 				{
					utiliesService.editItem(cbItem.getValue().getItemId(),editText.getText());
					lmsg.setTextFill(Color.GREENYELLOW);
					lmsg.setText(CommonConstants.EDIT_MSG);
					settings.add(lmsg, 2, 5);
					listOfItems.clear();
					listOfItems.addAll(utiliesService.fetchItem());
 				}
				catch (Exception e1) 
				{
					lmsg.setText("Some Error Occured !!");
					e1.printStackTrace();
				}
			}
		});
		
		
		delete.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				try 
 				{
						utiliesService.deleteItem(cbItem.getValue().getItemId());
						lmsg.setTextFill(Color.GREENYELLOW);
						lmsg.setText(CommonConstants.DEL_MSG);
						listOfItems.clear();
						settings.add(lmsg, 2, 4);
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
		settings.add(box, 2, 3);
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
    	gsettings.setVgap(20);
    	gsettings.setPadding(new Insets(112,0,0,0));
    	
    	ToggleGroup settingsGroup=new ToggleGroup();
    	
    	final ToggleButton bAddItem= new ToggleButton("Items");
    	bAddItem.setToggleGroup(settingsGroup);
    	bAddItem.setId("drinkName");
    	bAddItem.setMaxSize(250,250);
    	gsettings.add(bAddItem,0,1);
    	
    	
    	bAddItem.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				//borderPane.setStyle("-fx-background-image: url('settings.jpg');");
 				try
 				{
 					animation.animateRightSettings(bAddItem, 0, 0);
					borderPane.setCenter(viewSettings("add"));
				}
 				catch (Exception e1)
 				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 			}
 		});
    	
    	final ToggleButton bCategory= new ToggleButton("Category Types");
    	bCategory.setToggleGroup(settingsGroup);
    	bCategory.setId("drinkName");
    	bCategory.setMaxSize(250,250);
    	gsettings.add(bCategory,0,2);
    	
    	
    	bCategory.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				//borderPane.setStyle("-fx-background-image: url('settings.jpg');");
 				try
 				{
 					animation.animateRightSettings(bCategory, 0, 1);
					//borderPane.setCenter(addTypeToCategory());
 					borderPane.setCenter(viewAddDeleteCategories("AddDeleteEdit"));
				}
 				catch (Exception e1)
 				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 			}
 		});
    	
    	final ToggleButton bItemTypeDetails = new ToggleButton("Items Price");
    	bItemTypeDetails.setToggleGroup(settingsGroup);
    	bItemTypeDetails.setId("drinkName");
    	bItemTypeDetails.setMaxSize(250,250);
    	gsettings.add(bItemTypeDetails,0,3);
    	
    	
    	bItemTypeDetails.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				//borderPane.setStyle("-fx-background-image: url('settings.jpg');");
 				try
 				{
 					animation.animateRightSettings(bItemTypeDetails, 0, 1);
					//borderPane.setCenter(addTypeToCategory());
 					borderPane.setCenter(stackaddDetailsToItems());
				}
 				catch (Exception e1)
 				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 			}
 		});
    	
    	
    	final ToggleButton bItemTypeDetailsEdit = new ToggleButton("Item Type\nDetails Edit");
    	bItemTypeDetailsEdit.setToggleGroup(settingsGroup);
    	bItemTypeDetailsEdit.setId("drinkName");
    	bItemTypeDetailsEdit.setMaxSize(250,250);
    //	gsettings.add(bItemTypeDetailsEdit,0,4);
    	
    	
    	bItemTypeDetailsEdit.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				//borderPane.setStyle("-fx-background-image: url('settings.jpg');");
 				try
 				{
 					animation.animateRightSettings(bItemTypeDetailsEdit, 0, 1);
					//borderPane.setCenter(addTypeToCategory());
 					borderPane.setCenter(updateDetailsToItems());
				}
 				catch (Exception e1)
 				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 			}
 		});
    	
    	
    	
    	
    	borderPane.setLeft(gsettings);
		bAddItem.fire();
		
    	return borderPane;
	}
	
	
	public GridPane addTypeToCategory()
	{
		GridPane gridPane = new GridPane();
		gridPane.setVgap(8);
		gridPane.setHgap(10);
		gridPane.setPadding(new Insets(30,0,0,0));
		//gridPane.setAlignment(Pos.CENTER);
		
		final ComboBox<CategoryVO> categories = new ComboBox<CategoryVO>(listOfCategories);
		
		final TextField type = new TextField();
		Button add = new Button("Add");
		add.setId("buttonall");
		final Label msg = new Label();
		
		
		add.setOnAction(new EventHandler<ActionEvent>() {
		 			
		 			@Override
		 			public void handle(ActionEvent e) 
		 			{
		 				try
		 				{
							utiliesService.addTypes(categories.getValue(), type.getText());
							msg.setText("Type added Successfully to Category");
						}
		 				catch (Exception e1) 
		 				{
		 					msg.setTextFill(Color.MAROON);
							msg.setText(CommonConstants.ALREADY_EXISTS);
							e1.printStackTrace();
						}
		 			}
		 		});
		Label cat=new Label("Category");
		cat.setTextFill(Color.DARKGOLDENROD);
		gridPane.add(cat, 0, 0);
		gridPane.add(categories, 1, 0);
		Label typ=new Label("Type");
		typ.setTextFill(Color.DARKGOLDENROD);
		gridPane.add(typ,0,1);
		gridPane.add(type,1,1);
		gridPane.add(add, 1, 2);
		gridPane.add(msg, 1, 3);
		
		return gridPane;
	}
	
	
	/*public GridPane editTypeToCategory()
	{
		GridPane gridPane = new GridPane();
		gridPane.setVgap(8);
		gridPane.setPadding(new Insets(30,0,0,0));
		gridPane.setAlignment(Pos.CENTER);
		
		final ComboBox<CategoryVO> categories = new ComboBox<CategoryVO>(listOfCategories);
		final ComboBox<CategoryVO> types = new ComboBox<CategoryVO>();
		Button add = new Button("Add Type");
		final Label msg = new Label();
		
		
		add.setOnAction(new EventHandler<ActionEvent>() {
		 			
		 			@Override
		 			public void handle(ActionEvent e) 
		 			{
		 				try
		 				{
							utiliesService.addTypes(categories.getValue(), type.getText());
							msg.setText("Type added Successfully to Category");
						}
		 				catch (Exception e1) 
		 				{
							e1.printStackTrace();
						}
		 			}
		 		});
		gridPane.add(new Label("Category"), 0, 0);
		gridPane.add(categories, 1, 0);
		gridPane.add(new Label("Type"),0,1);
		gridPane.add(type,1,1);
		gridPane.add(add, 1, 2);
		gridPane.add(msg, 1, 3);
		return gridPane;
	}*/
	
	public GridPane editDeleteTypeToCategory()
	{
		final GridPane gridPane = new GridPane();
		gridPane.setVgap(8);
		gridPane.setPadding(new Insets(10,0,0,0));
		gridPane.setHgap(8);
		//gridPane.setAlignment(Pos.CENTER);
		
		final ObservableList<CategoryTypeVO> listTypes = FXCollections.observableArrayList();
		
		
		final ComboBox<CategoryVO> category = new ComboBox<CategoryVO>(listOfCategories);
		final ComboBox<CategoryTypeVO> type = new ComboBox<CategoryTypeVO>(listTypes);
		final TextField typeName = new TextField();
		final Button edit = new Button("Edit");
		edit.setId("buttonall");
		final Button editFinal = new Button("Edit");
		editFinal.setId("buttonall");
		final Button delete = new Button("Delete");
		delete.setId("buttonall");
		final Label msg = new Label();
		
		
		category.valueProperty().addListener(new ChangeListener<CategoryVO>() {

			@Override
			public void changed(
					ObservableValue<? extends CategoryVO> observable,
					CategoryVO oldValue, CategoryVO newValue) {
				// TODO Auto-generated method stub
				listTypes.clear();
				try {
					listTypes.addAll(utiliesService.fetchTypes(newValue.getCategotyId()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
					msg.setText("Some error occured..");
				}
			}
		});
		
		delete.setOnAction(new EventHandler<ActionEvent>() {
		 			
		 			@Override
		 			public void handle(ActionEvent e) 
		 			{
		 				try
		 				{
							utiliesService.deleteCategoryTypes(type.getValue());
							msg.setText("Type deleted Successfully from Category");
						}
		 				catch (Exception e1) 
		 				{
		 					
		 					msg.setText("Some error occured..");
						}
		 			}
		 		});
		Label cat=new Label("Category");
		cat.setTextFill(Color.DARKGOLDENROD);
		gridPane.add(cat, 0, 0);
		gridPane.add(category, 1, 0);
		Label typ=new Label("Category");
		typ.setTextFill(Color.DARKGOLDENROD);
		gridPane.add(typ,0,1);
		gridPane.add(type,1,1);
		HBox htemp=new HBox();
			htemp.getChildren().addAll(edit,delete);
			htemp.setSpacing(2);
		gridPane.add(htemp, 1, 3,2,3);
		//gridPane.add(edit, 0, 3);
		//gridPane.add(delete, 1, 3);
		gridPane.add(msg,1,4);
		
		edit.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				try
 				{
 					Label lTypeName = new Label("New Type Name");
 					lTypeName.setTextFill(Color.DARKGOLDENROD);
 					gridPane.add(lTypeName, 0, 2);
 					gridPane.add(typeName, 1, 2);
 					gridPane.add(editFinal, 0, 3);
 					edit.setVisible(false);
 					delete.setVisible(false);
				}
 				catch (Exception e1) 
 				{
 					msg.setText("Some error occured..");
				}
 			}
 		});
		
		
		editFinal.setOnAction(new EventHandler<ActionEvent>() {
		 			
		 			@Override
		 			public void handle(ActionEvent e) 
		 			{
		 				try
		 				{
		 					utiliesService.editCategoryTypes(type.getValue(),typeName.getText());
		 					msg.setText("New Type Name saved Successfully");
						}
		 				catch (Exception e1) 
		 				{
		 					msg.setText("Some error occured..");
						}
		 			}
		 		});
		
		return gridPane;
	}
	
	public StackPane stackaddDetailsToItems(){
StackPane stack = new StackPane();
		
		GridPane grid = new GridPane();
		GridPane grid2 = new GridPane();
		
		grid=addDetailsToItems();
		grid2=updateDetailsToItems();
			
		
		
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
		
		Rectangle roundRect1 = RectangleBuilder.create()
			    .x(50)
			    .y(50)
			    .width(roundRect.getWidth()/2.5)
			    .height(roundRect.getHeight()/1.75)
			    .arcWidth(30)
			    .arcHeight(30)
			    .build();
				
				roundRect1.setFill(Color.DARKGRAY);
				roundRect1.setOpacity(0.2);
				roundRect1.setStroke(Color.TRANSPARENT);
				
				Rectangle roundRect2 = RectangleBuilder.create()
					    .x(50)
					    .y(50)
					    .width(roundRect.getWidth()/2.5)
					    .height(roundRect.getHeight()/1.75)
					    .arcWidth(30)
					    .arcHeight(30)
					    .build();
						
						roundRect2.setFill(Color.DARKGRAY);
						roundRect2.setOpacity(0.2);
						roundRect2.setStroke(Color.TRANSPARENT);
		/*
		HBox hlabel= new HBox();
		hlabel.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()-160);
		hlabel.setMaxHeight(30);
		hlabel.setStyle("-fx-background-color:black;");
		hlabel.setOpacity(0.3);
		hlabel.setLayoutX(20);*/
		
		
		
		try
		{
			/*dataTable = FXCollections.observableArrayList();
			dataTable = stockDetailsService.viewStock(categoryId);*/
			
			/*final Label label = new Label(buttonClicked);*/
			/*label.setAlignment(Pos.CENTER_LEFT);*/
		 	//grid.add(label,1,0);
			final Text text5 = new Text(25, 175, "Settings Items Price");  
		      text5.setFill(Color.DARKORANGE);  
		      text5.setFont(Font.font ("Edwardian Script ITC", 50));
		      final Light.Distant light = new Light.Distant();  
		      light.setAzimuth(-135.0);  
		      final Lighting lighting = new Lighting();  
		      lighting.setLight(light);  
		      lighting.setSurfaceScale(9.0);  
		      text5.setEffect(lighting);  
		 	
		      final Text textAdd = new Text(25, 175, "Add Items price");  
		      textAdd.setFill(Color.DARKGOLDENROD);  
		      textAdd.setFont(Font.font ("Arial", 16));
		      final Light.Distant lightAdd = new Light.Distant();  
		      lightAdd.setAzimuth(-135.0);  
		      final Lighting lightingAdd = new Lighting();  
		      lighting.setLight(lightAdd);  
		      lighting.setSurfaceScale(9.0);  
		     // textAdd.setEffect(lighting);
		      
		      final Text textDelete = new Text(25, 1000, "Edit/Delete Items price");
		      textDelete.setFill(Color.DARKGOLDENROD);  
		      textDelete.setFont(Font.font ("Arial", 16));
		    //  textDelete.setEffect(lighting);
		      
		     
		      
			/*grid.add(table1,0,12);
			grid.add(table2,1,12);
			grid.setAlignment(Pos.TOP_CENTER);*/
		      StackPane.setMargin(roundRect, new Insets(0,0,0,69)); 
				StackPane.setAlignment(roundRect, Pos.TOP_LEFT);
				
				StackPane.setMargin(roundRect1, new Insets(0,0,roundRect.getHeight()/25,roundRect.getWidth()/7)); 
				StackPane.setAlignment(roundRect1, Pos.CENTER_LEFT);
				
				StackPane.setMargin(roundRect2, new Insets(0,0,roundRect.getHeight()/25,roundRect.getWidth()/1.65)); 
				StackPane.setAlignment(roundRect2, Pos.CENTER_LEFT);
				
				StackPane.setMargin(text5, new Insets(50,0,0,Screen.getPrimary().getVisualBounds().getWidth()/3.5));
				StackPane.setAlignment(text5, Pos.TOP_LEFT);
				
				StackPane.setAlignment(textAdd, Pos.CENTER_LEFT);
				StackPane.setMargin(textAdd, new Insets(0,700,300,roundRect1.getWidth()/1.40));
			
			
				StackPane.setAlignment(textDelete,Pos.CENTER_RIGHT);
				StackPane.setMargin(textDelete, new Insets(0,roundRect2.getWidth()/1.1,300,0));
				
				StackPane.setAlignment(grid, Pos.CENTER_LEFT);
				StackPane.setMargin(grid, new Insets(210,0,200,roundRect1.getWidth()/2.3));
				
				StackPane.setAlignment(grid2, Pos.CENTER_RIGHT);
				StackPane.setMargin(grid2, new Insets(210,0,100,roundRect2.getWidth()/0.60));
			
			stack.getChildren().addAll(textAdd,textDelete,text5,roundRect,roundRect1,roundRect2,grid,grid2);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return stack;
		
		
		
		
	}
	
	public GridPane addDetailsToItems()
	{
		final Label msg = new Label();
		GridPane gridPane = new GridPane();
		gridPane.setVgap(8);
		gridPane.setHgap(10);
		gridPane.setPadding(new Insets(30,0,0,0));
		//gridPane.setAlignment(Pos.CENTER);
		
		final ObservableList<CategoryTypeVO> listTypes = FXCollections.observableArrayList();
		final ObservableList<ItemVO> listItems = FXCollections.observableArrayList();
		
		final ComboBox<CategoryVO> categories = new ComboBox<CategoryVO>(listOfCategories);
		final ComboBox<CategoryTypeVO> types = new ComboBox<CategoryTypeVO>(listTypes);
		final ComboBox<ItemVO> items = new ComboBox<ItemVO>(listItems);
		
		
		final TextField dp = new TextField();
		final TextField mrp = new TextField();
		final TextField hp = new TextField();
		Button add = new Button("Add");
		add.setId("buttonall");
		categories.valueProperty().addListener(new ChangeListener<CategoryVO>() {

			@Override
			public void changed(
					ObservableValue<? extends CategoryVO> observable,
					CategoryVO oldValue, CategoryVO newValue) {
				// TODO Auto-generated method stub
				listItems.clear();
				try {
					listItems.addAll(utiliesService.fetchItemsFromCategory(newValue.getCategotyId()));
				} catch (Exception e) {
				
					// TODO Auto-generated catch block
					
					msg.setText("Some error occured..");
				}
			}
		});
		
		items.valueProperty().addListener(new ChangeListener<ItemVO>() {

			@Override
			public void changed(
					ObservableValue<? extends ItemVO> observable,
					ItemVO oldValue, ItemVO newValue) {
				// TODO Auto-generated method stub
				listTypes.clear();
				try {
					listTypes.addAll(utiliesService.fetchTypes(categories.getValue().getCategotyId()));
				} catch (Exception e) {
				
					// TODO Auto-generated catch block
					msg.setText("Some error occured..");
				}
			}
		});
		
	
		
		add.setOnAction(new EventHandler<ActionEvent>() {
		 			
		 			@Override
		 			public void handle(ActionEvent e) 
		 			{
		 				try
		 				{
		 					ItemTypeVO itemTypeVO = new ItemTypeVO();
		 					itemTypeVO.setTypeId(types.getValue().getTypeId());
		 					itemTypeVO.setItemId(items.getValue().getItemId());
		 					itemTypeVO.setQuantity(CommonConstants.ZERO);
		 					itemTypeVO.setDp(Integer.parseInt(dp.getText()));
		 					itemTypeVO.setMrp(Integer.parseInt(mrp.getText()));
		 					itemTypeVO.setHp(Integer.parseInt(hp.getText()));
							utiliesService.addItemTypes(itemTypeVO);
							msg.setText("Details added Successfully to Category");
						}
		 				catch (Exception e1) 
		 				{
		 					msg.setText("Data Already Exists ! \nPlease select Edit if you want to edit !");
							e1.printStackTrace();
						}
		 			}
		 		});
		gridPane.add(new Label("Category"), 0, 0);
		gridPane.add(categories, 1, 0);
		gridPane.add(new Label("Item"),0,1);
		gridPane.add(items,1,1);
		gridPane.add(new Label("Types"),0,2);
		gridPane.add(types, 1, 2);
		gridPane.add(new Label("Depo Price"),0,3);
		gridPane.add(dp, 1, 3);
		gridPane.add(new Label("M.R.P."),0,4);
		gridPane.add(mrp, 1, 4);
		gridPane.add(new Label("Hotel Price"),0,5);
		gridPane.add(hp, 1, 5);
		gridPane.add(add, 1, 6);
		gridPane.add(msg,1,8);
		return gridPane;
	}
	
	public GridPane updateDetailsToItems()
	{
		final Label msg = new Label();
		GridPane gridPane = new GridPane();
		gridPane.setVgap(8);
		gridPane.setHgap(10);
		gridPane.setPadding(new Insets(30,0,0,0));
		//gridPane.setAlignment(Pos.CENTER);
		
		
		
		
		final ObservableList<CategoryTypeVO> listTypes = FXCollections.observableArrayList();
		final ObservableList<ItemVO> listItems = FXCollections.observableArrayList();
		
		final ComboBox<CategoryVO> categories = new ComboBox<CategoryVO>(listOfCategories);
		final ComboBox<CategoryTypeVO> types = new ComboBox<CategoryTypeVO>(listTypes);
		final ComboBox<ItemVO> items = new ComboBox<ItemVO>(listItems);
		
		
		final TextField dp = new TextField();
		final TextField mrp = new TextField();
		final TextField hp = new TextField();
		Button add = new Button("Edit");
		add.setId("buttonall");
		
		categories.valueProperty().addListener(new ChangeListener<CategoryVO>() {

			@Override
			public void changed(
					ObservableValue<? extends CategoryVO> observable,
					CategoryVO oldValue, CategoryVO newValue) {
				// TODO Auto-generated method stub
				listItems.clear();
				try {
					listItems.addAll(utiliesService.fetchItemsFromCategory(newValue.getCategotyId()));
				} catch (Exception e) {
				
					// TODO Auto-generated catch block
					msg.setText("Some error occured..");
				}
			}
		});
		
		items.valueProperty().addListener(new ChangeListener<ItemVO>() {

			@Override
			public void changed(
					ObservableValue<? extends ItemVO> observable,
					ItemVO oldValue, ItemVO newValue) {
				// TODO Auto-generated method stub
				listTypes.clear();
				try {
					listTypes.addAll(utiliesService.fetchTypes(categories.getValue().getCategotyId()));
				} catch (Exception e) {
				
					// TODO Auto-generated catch block
					msg.setText("Some error occured..");
				}
			}
		});
		
		types.valueProperty().addListener(new ChangeListener<CategoryTypeVO>() {

			@Override
			public void changed(
					ObservableValue<? extends CategoryTypeVO> observable,
					CategoryTypeVO oldValue, CategoryTypeVO newValue) {
				// TODO Auto-generated method stub
				try {
					ItemTypeVO itemTypeVO = new ItemTypeVO();
					itemTypeVO = utiliesService.fetchItemtypeDetails(items.getValue().getItemId(), newValue.getTypeId());
					mrp.setText(itemTypeVO.getMrp().toString());
					dp.setText(itemTypeVO.getDp().toString());
					hp.setText(itemTypeVO.getHp().toString());
					setItemTypeVO(itemTypeVO);
				} catch (Exception e) {
				
					// TODO Auto-generated catch block
					msg.setText("Some error occured..");
				}
			}
		});
		
		
		add.setOnAction(new EventHandler<ActionEvent>() {
		 			
		 			@Override
		 			public void handle(ActionEvent e) 
		 			{
		 				try
		 				{
		 					getItemTypeVO().setDp(Integer.parseInt(dp.getText()));
		 					getItemTypeVO().setMrp(Integer.parseInt(mrp.getText()));
		 					getItemTypeVO().setHp(Integer.parseInt(hp.getText()));
							utiliesService.updateItemTypes(getItemTypeVO());
							msg.setText("Details Updated Successfully to Category");
						}
		 				catch (Exception e1) 
		 				{
		 					msg.setText("Data Already Exists ! \nPlease select Edit if you want to edit !");
							e1.printStackTrace();
						}
		 			}
		 		});
		gridPane.add(new Label("Category"), 0, 0);
		gridPane.add(categories, 1, 0);
		gridPane.add(new Label("Item"),0,1);
		gridPane.add(items,1,1);
		gridPane.add(new Label("Types"),0,2);
		gridPane.add(types, 1, 2);
		gridPane.add(new Label("Depo Price"),0,3);
		gridPane.add(dp, 1, 3);
		gridPane.add(new Label("M.R.P."),0,4);
		gridPane.add(mrp, 1, 4);
		gridPane.add(new Label("Hotel Price"),0,5);
		gridPane.add(hp, 1, 5);
		gridPane.add(add, 1, 6);
		gridPane.add(msg,1,8);
		return gridPane;
	}
}
