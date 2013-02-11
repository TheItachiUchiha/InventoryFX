package com.fnz.UI;

import java.util.Date;

import com.fnz.UI.IncomingStock.EditingCell;
import com.fnz.VO.ItemVO;
import com.fnz.VO.StockVO;
import com.fnz.service.IncomingStockService;
import com.fnz.service.OutgoingStockService;
import com.sai.javafx.calendar.FXCalendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Screen;
import javafx.util.Callback;

public class TransactionHistory 
{
	IncomingStockService incomingStockService;
	OutgoingStockService outgoingStockService;
	
	public TransactionHistory()
	{
		incomingStockService = new IncomingStockService();
		outgoingStockService = new OutgoingStockService();
	}
	public StackPane viewHistoryStack() 
	{
		StackPane stack = new StackPane();
		
		 Rectangle roundRect = RectangleBuilder.create()
				    .x(50)
				    .y(50)
				    .width(Screen.getPrimary().getVisualBounds().getWidth()-180)
				    .height(Screen.getPrimary().getVisualBounds().getHeight()-150)
				    .arcWidth(30)
				    .arcHeight(30)
				    .build();
					
					roundRect.setFill(Color.DARKGRAY);
					roundRect.setOpacity(0.2);
					roundRect.setStroke(Color.TRANSPARENT);
					
					StackPane.setAlignment(roundRect, Pos.TOP_CENTER);
					
					stack.getChildren().addAll(roundRect);
		return stack;
	}
	
	public BorderPane viewHistory()
	{
		final BorderPane borderPane = new BorderPane();
		   borderPane.setId("borderxx");
		   
		   
		  
					
					
					
		VBox vBox = new VBox(30);
		
		ObservableList<String> stockType = FXCollections.observableArrayList();
		stockType.addAll("Purchase","Sales");
		Label lStockType = new Label("Select Stock type");
		final ComboBox<String> cStockTypes = new ComboBox<>(stockType);
		
		HBox upperPart = new HBox(10);
		upperPart.setAlignment(Pos.CENTER);
		upperPart.setPadding(new Insets(50, 20, 0, 20));
		
		upperPart.getChildren().addAll(lStockType,cStockTypes);
		
		
		HBox lowerPart = new HBox(10);
		lowerPart.setAlignment(Pos.CENTER);
		lowerPart.setPadding(new Insets(0, 20, 0, 20));
		//upperPart.set
		
		final Label sDate = new Label("Start Date");
		final Label eDate = new Label("End Date");
		
		final FXCalendar sCalendar = new FXCalendar();
		final FXCalendar eCalendar = new FXCalendar();
		
		Button search = new Button("Search");
		
		
		search.setOnAction(new EventHandler<ActionEvent>() {
		 			
		 			@Override
		 			public void handle(ActionEvent e) 
		 			{
		 				try 
		 				{
							if(cStockTypes.getValue().equalsIgnoreCase("Purchase"))
							{
								borderPane.setBottom(fetchIncomingHistoryTable(sCalendar.getTextField().getText(), eCalendar.getTextField().getText()));
							}
							else if(cStockTypes.getValue().equalsIgnoreCase("Sales"));
							{
								borderPane.setBottom(fetchOutgoingHistoryTable(sCalendar.getTextField().getText(), eCalendar.getTextField().getText()));
							}
						} 
		 				catch (Exception e1) 
		 				{
							e1.printStackTrace();
						}
		 				
		 			}
		 		});
		
	
		
		lowerPart.getChildren().addAll(sDate,sCalendar,eDate,eCalendar,search);
		
		
		vBox.getChildren().addAll(upperPart,lowerPart);
		borderPane.setTop(vBox);
		
		return borderPane;
	}
	
	public HBox fetchIncomingHistoryTable(String initialDate, String finalDate) throws Exception
	{
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		ObservableList<StockVO> data = FXCollections.observableArrayList();
		
		data = incomingStockService.fetchIncomingStockDetails(initialDate, finalDate);
		
		
		TableView<StockVO> table = new TableView<StockVO>();
	 	table.setEditable(false);
	 	//table1.setMaxSize(400, 300);
	 	table.setStyle("-fx-background-color: transparent;");
	 	
	 	TableColumn date = new TableColumn("Date");
	 	date.setMinWidth(100);
	 	date.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("date"));
	 	
	 	TableColumn invoiceId = new TableColumn("Invoice Id");
	 	invoiceId.setMinWidth(100);
	 	invoiceId.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("invoiceId"));
	 	
	 	TableColumn itemName = new TableColumn("Item Name");
	 	itemName.setMinWidth(100);
	 	itemName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("itemName"));
	 	
	 	TableColumn typeName = new TableColumn("Type");
	 	typeName.setMinWidth(100);
	 	typeName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("typeName"));
	 	
	 	TableColumn quantity = new TableColumn("Quantity");
	 	quantity.setMinWidth(100);
	 	quantity.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("quantity"));
	 	
	 	table.setItems(data);
	 	
		table.getColumns().addAll(date, invoiceId, itemName, typeName, quantity);
		hBox.getChildren().addAll(table);
		return hBox;
	}
	
	public HBox fetchOutgoingHistoryTable(String initialDate, String finalDate) throws Exception
	{
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		ObservableList<StockVO> data = FXCollections.observableArrayList();
		
		data = outgoingStockService.fetchOutgoingStockDetails(initialDate, finalDate);
		
		
		TableView<StockVO> table = new TableView<StockVO>();
	 	table.setEditable(false);
	 	//table1.setMaxSize(400, 300);
	 	table.setStyle("-fx-background-color: transparent;");
	 	
	 	TableColumn date = new TableColumn("Date");
	 	date.setMinWidth(100);
	 	date.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("date"));
	 	
	 	
	 	TableColumn itemName = new TableColumn("Item Name");
	 	itemName.setMinWidth(100);
	 	itemName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("itemName"));
	 	
	 	TableColumn typeName = new TableColumn("Type");
	 	typeName.setMinWidth(100);
	 	typeName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("typeName"));
	 	
	 	TableColumn quantity = new TableColumn("Quantity");
	 	quantity.setMinWidth(100);
	 	quantity.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("quantity"));
	 	
	 	table.setItems(data);
	 	
		table.getColumns().addAll(date, itemName, typeName, quantity);
		hBox.getChildren().addAll(table);
		return hBox;
	}
}
