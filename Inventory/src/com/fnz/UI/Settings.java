package com.fnz.UI;

import com.fnz.service.UtiliesService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Settings 
{
	UtiliesService utiliesService;
	public Settings()
	{
		utiliesService = new UtiliesService();
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
		final Label lmsg = new Label();
		add.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				try 
 				{
					utiliesService.addItem(itemName.getText(), mapCategories.get(cbcategory.getValue()));
					lmsg.setText("Item added successfully");
				} catch (Exception e1) 
				{
					lmsg.setText("Some Error Occured !!");
					e1.printStackTrace();
				}
 			}
 		});
		
		
		
		
		settings.setAlignment(Pos.CENTER);
		
		settings.add(lAddItem, 1, 1);
		settings.add(itemName, 2, 1);
		settings.add(lCategoryName, 1, 2);
		settings.add(cbcategory,2,2);
		settings.add(add,2,3);
		settings.add(lmsg,2,4);
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
		
		settings.setAlignment(Pos.CENTER);
		
		settings.add(lAddItem, 1, 1);
		settings.add(cbItem, 2, 1);
		settings.add(delete,2,3);
		settings.add(lmsg, 2, 4);
		return settings;
	}
}
