package com.fnz.UI;

import com.fnz.VO.ItemVO;
import com.fnz.service.StockDetailsService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;

public class StockDetails 
{
	StockDetailsService stockDetailsService;
	public StockDetails()
	{
		stockDetailsService = new StockDetailsService();
	}
	@SuppressWarnings("unchecked")
	public StackPane wineStock(String categoryId)
	{
		StackPane stack = new StackPane();
		GridPane grid = new GridPane();
	
        grid.setVgap(8);
        grid.setPadding(new Insets(30));
		ObservableList<ItemVO> dataTable;
		
		Rectangle roundRect = RectangleBuilder.create()
	    .x(50)
	    .y(50)
	    .width(1190)
	    .height(550)
	    .arcWidth(30)
	    .arcHeight(30)
	    .build();
		
		roundRect.setFill(Color.DARKORANGE);
		roundRect.setOpacity(0.3);
		roundRect.setStroke(Color.TRANSPARENT);
		
		try
		{
			dataTable = FXCollections.observableArrayList();
			dataTable = stockDetailsService.viewStock(categoryId);
			
			final Label label = new Label("Wine Stock");
			label.setAlignment(Pos.CENTER);
		 	grid.add(label,0,0);
		 	
		 	TableView<ItemVO> table1 = new TableView<ItemVO>();
		 	table1.setEditable(false);
		 	table1.setMaxSize(250, 250);
		 	
		 	TableView<ItemVO> table2 = new TableView<ItemVO>();
		 	table2.setEditable(false);
		 	table2.setMaxSize(250, 250);
		 	
		 	TableColumn<ItemVO,String> itemName = new TableColumn<ItemVO,String> ("Item");
		 	itemName.setMinWidth(125);
		 	itemName.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, String>("itemName"));
		 	
		 	TableColumn<ItemVO,Integer>  quantity = new TableColumn<ItemVO,Integer> ("Quantity");
		 	quantity.setMinWidth(125);
		 	quantity.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, Integer>("quantity"));
		 	
		 	
		 	table1.setItems(dataTable);
		 	table1.getColumns().addAll(itemName, quantity);
		 	

		 	table2.setItems(dataTable);
		 	table2.getColumns().addAll(itemName, quantity);
		 	
			
			grid.add(table1,0,1);
			grid.add(table2,1,1);
			grid.setAlignment(Pos.CENTER);
			
			stack.getChildren().addAll(roundRect, grid);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return stack;
	
	}
}
