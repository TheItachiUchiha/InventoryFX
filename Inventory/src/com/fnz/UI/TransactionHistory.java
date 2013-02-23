package com.fnz.UI;

import java.util.Date;

import com.fnz.UI.IncomingStock.EditingCell;
import com.fnz.VO.ItemVO;
import com.fnz.VO.StockVO;
import com.fnz.common.CommonConstants;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Callback;

public class TransactionHistory 
{
	IncomingStockService incomingStockService;
	OutgoingStockService outgoingStockService;
	Animation animation;
	public TransactionHistory()
	{
		incomingStockService = new IncomingStockService();
		outgoingStockService = new OutgoingStockService();
	}
	
	
	public StackPane viewHistoryStack() 
	{
		StackPane stack = new StackPane();
		final BorderPane borderPane = new BorderPane();
		final HBox hTableResult=new HBox();
		hTableResult.setMaxHeight(300);
		
		GridPane gMain = new GridPane();
		//gMain.setAlignment(Pos.CENTER);
		gMain.setHgap(10);
		gMain.setVgap(15);
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
					//VBox vBox = new VBox(30);
					
					final Text text5 = new Text(25, 175, "Search in History");  
				      text5.setFill(Color.DARKORANGE);  
				      text5.setFont(Font.font ("Edwardian Script ITC", 50));
				      final Light.Distant light = new Light.Distant();  
				      light.setAzimuth(-135.0);  
				      final Lighting lighting = new Lighting();  
				      lighting.setLight(light);  
				      lighting.setSurfaceScale(9.0);  
				      text5.setEffect(lighting);  
				      
					ObservableList<String> stockType = FXCollections.observableArrayList();
					stockType.addAll("Purchase","Sales");
					Label lStockType = new Label("Select Stock type");
					lStockType.setTextFill(Color.DARKGOLDENROD);
					final ComboBox<String> cStockTypes = new ComboBox<>(stockType);
					cStockTypes.setValue(stockType.get(CommonConstants.ZERO));
					gMain.add(lStockType, 0, 0);
					
					/*HBox upperPart = new HBox(10);
					upperPart.setAlignment(Pos.CENTER);
					upperPart.setPadding(new Insets(50, 20, 0, 20));*/
					
					//upperPart.getChildren().addAll(lStockType,cStockTypes);
					gMain.add(cStockTypes, 1, 0);
					
					/*HBox lowerPart = new HBox(10);
					lowerPart.setAlignment(Pos.CENTER);
					lowerPart.setPadding(new Insets(0, 20, 0, 20));*/
					//upperPart.set
					
					final Label sDate = new Label("Start Date");
					sDate.setTextFill(Color.DARKGOLDENROD);
					gMain.add(sDate, 0, 1);
					
					final Label eDate = new Label("End Date");
					eDate.setTextFill(Color.DARKGOLDENROD);
					gMain.add(eDate, 3, 1);
					
					final FXCalendar sCalendar = new FXCalendar();
					gMain.add(sCalendar, 1, 1);
					
					final FXCalendar eCalendar = new FXCalendar();
					gMain.add(eCalendar, 4, 1);
					
					
					Button search = new Button("Search");
					HBox hSearch=new HBox();
					hSearch.setAlignment(Pos.CENTER);
					hSearch.setMaxHeight(10);
					hSearch.getChildren().add(search);
					gMain.add(hSearch, 0, 2, 6, 2);
					
					search.setOnAction(new EventHandler<ActionEvent>() {
					 			
					 			@Override
					 			public void handle(ActionEvent e) 
					 			{
					 				try 
					 				{
										if(cStockTypes.getValue().equalsIgnoreCase("Purchase"))
										{
											hTableResult.getChildren().clear();
											hTableResult.getChildren().add(fetchIncomingHistoryTable(sCalendar.getTextField().getText(), eCalendar.getTextField().getText()));
										}
										else if(cStockTypes.getValue().equalsIgnoreCase("Sales"));
										{
											hTableResult.getChildren().clear();
											hTableResult.getChildren().add(fetchOutgoingHistoryTable(sCalendar.getTextField().getText(), eCalendar.getTextField().getText()));
										}
									} 
					 				catch (Exception e1) 
					 				{
										e1.printStackTrace();
									}
					 				
					 			}
					 		});
					
				
					
					/*lowerPart.getChildren().addAll(sDate,sCalendar,eDate,eCalendar,search);*/
					
					
					/*vBox.getChildren().addAll(upperPart,lowerPart);
					borderPane.setTop(vBox);*/
					StackPane.setMargin(roundRect, new Insets(15,10,8,8));
					StackPane.setAlignment(roundRect, Pos.TOP_CENTER);
					
					StackPane.setMargin(gMain, new Insets(120,0,0,Screen.getPrimary().getVisualBounds().getWidth()/3));
					StackPane.setAlignment(gMain, Pos.CENTER);
					
					StackPane.setMargin(text5, new Insets(50,8,8,8));
					StackPane.setAlignment(text5, Pos.TOP_CENTER);
					
					StackPane.setMargin(hTableResult, new Insets(85,0,0,Screen.getPrimary().getVisualBounds().getWidth()/3.75));
					StackPane.setAlignment(hTableResult, Pos.CENTER);
					
					stack.getChildren().addAll(text5,roundRect,gMain,hTableResult);
					
					
					
		return stack;
	}
	
	public BorderPane viewHistory()
	{
		final BorderPane borderPane = new BorderPane();
		//borderPane.setPadding(new Insets(15,0,0,0));
		 borderPane.setId("borderxx");
		// GridPane buttonGrid = new GridPane();
			
		// buttonGrid.setVgap(20);
		 //buttonGrid.setPadding(new Insets(130,0,0,0));
			
		   borderPane.setCenter(viewHistoryStack());
		   
		//   final ToggleButton bt1= new ToggleButton("History Search");
		   
		  // 	bt1.setId("drinkName");
		 //  	bt1.setMaxSize(250,250);
		 //  	buttonGrid.add(bt1,0,0); //search
		 //  	
		  // 	bt1.setOnAction(new EventHandler<ActionEvent>() {
					
		//			@Override
			//		public void handle(ActionEvent e) 
			//		{
						//borderPane.setStyle("-fx-background-image: url('wine.jpeg');");
			//			 borderPane.setCenter(viewHistoryStack());
				//		 animation.animateRight(bt1,0,0);
			//		}
			//	});
			//		
					
					
		
		  // 	borderPane.setLeft(buttonGrid);
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
	 	table.setMinSize(450, 500);
	 	table.setStyle("-fx-background-color: transparent;");
	 	
	 	TableColumn date = new TableColumn("Date");
	 	date.setMinWidth(150);
	 	date.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("date"));
	 	
	 	TableColumn invoiceId = new TableColumn("Invoice Id");
	 	invoiceId.setMinWidth(150);
	 	invoiceId.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("invoiceId"));
	 	
	 	TableColumn itemName = new TableColumn("Item Name");
	 	itemName.setMinWidth(150);
	 	itemName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("itemName"));
	 	
	 	TableColumn typeName = new TableColumn("Type");
	 	typeName.setMinWidth(150);
	 	typeName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("typeName"));
	 	
	 	TableColumn quantity = new TableColumn("Quantity");
	 	quantity.setMinWidth(150);
	 	quantity.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("quantity"));
	 	
	 	table.setItems(data);
	 	
		table.getColumns().addAll(date, invoiceId, itemName, typeName, quantity);
		//hBox.getChildren().addAll(table);
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
	 	table.setMinSize(600, 300);
	 	table.setStyle("-fx-background-color: transparent;");
	 	
	 	TableColumn date = new TableColumn("Date");
	 	date.setMinWidth(150);
	 	date.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("date"));
	 	
	 	
	 	TableColumn itemName = new TableColumn("Item Name");
	 	itemName.setMinWidth(150);
	 	itemName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("itemName"));
	 	
	 	TableColumn typeName = new TableColumn("Type");
	 	typeName.setMinWidth(150);
	 	typeName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("typeName"));
	 	
	 	TableColumn quantity = new TableColumn("Quantity");
	 	quantity.setMinWidth(150);
	 	quantity.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("quantity"));
	 	
	 	table.setItems(data);
	 	
		table.getColumns().addAll(date, itemName, typeName, quantity);
		hBox.getChildren().addAll(table);
		return hBox;
	}
}
