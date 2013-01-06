package com.fnz.UI;

import com.fnz.service.UtiliesService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	public GridPane addCategory()
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
	
	public GridPane addItem() throws Exception
	{
		GridPane settings = new GridPane();
		//list Creation
		ObservableList<String> listOfCategories = FXCollections.observableArrayList();
		listOfCategories = utiliesService.fetchCategory();
 		Label lAddItem = new Label("Name Of The Item");
		final TextField itemName = new TextField();
		Button add = new Button("Add Item");
		Label lCategoryName = new Label("Select Category");
		ChoiceBox<String> cbcategory = new  ChoiceBox<String>(listOfCategories);
		
		final Label lmsg = new Label();
		add.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				try 
 				{
					utiliesService.addCategory(itemName.getText());
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
		
		
		
		return settings;
		
	}
}
