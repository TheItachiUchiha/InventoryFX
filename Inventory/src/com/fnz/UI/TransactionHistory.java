package com.fnz.UI;

import java.awt.Checkbox;
import java.util.Date;

import com.fnz.UI.IncomingStock.EditingCell;
import com.fnz.VO.CategoryVO;
import com.fnz.VO.ItemVO;
import com.fnz.VO.StockVO;
import com.fnz.Validation.Validation;
import com.fnz.common.CommonConstants;
import com.fnz.dao.UtiliesDAO;
import com.fnz.security.ModalDialog;
import com.fnz.service.IncomingStockService;
import com.fnz.service.OutgoingStockService;
import com.fnz.service.TransactionHistoryService;
import com.mytdev.javafx.scene.control.AutoCompleteTextField;
import com.sai.javafx.calendar.FXCalendar;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
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
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.application.Application;
import javafx.beans.value.*;
import javafx.concurrent.Worker;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;


public class TransactionHistory 
{
	IncomingStockService incomingStockService;
	OutgoingStockService outgoingStockService;
	Animation animation;
	Validation validation;
	ObservableList<StockVO> data;
	TransactionHistoryService transactionHistoryService;
	Button BDelete = new Button("Delete");
	ComboBox<String> cStockTypes=null;
	TableView<StockVO> table = null;
	HBox hTableResult=null;
	ToggleGroup group;
	FXCalendar sCalendar;
	FXCalendar eCalendar;
	AutoCompleteTextField<String> textInvoice;
	
	public TransactionHistory()
	{
		incomingStockService = new IncomingStockService();
		outgoingStockService = new OutgoingStockService();
		validation = new Validation();
		transactionHistoryService = new TransactionHistoryService();
	}
	
	public BorderPane viewHistory(Stage stage)
	{
		final BorderPane borderPane = new BorderPane();
		borderPane.setId("borderxx");
		borderPane.setCenter(viewHistoryStack(stage));
		return borderPane;
	}
	
	public StackPane viewHistoryStack(final Stage stage) 
	{
		StackPane stack = new StackPane();
		hTableResult = new HBox();
		hTableResult.setMaxHeight(300);
		
		final Text lMsg=new Text();
		lMsg.setFill(Color.RED);  
		lMsg.setFont(Font.font ("Arial", 12));
		
		final GridPane gMain = new GridPane();
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
					
					final ObservableList<String> listOfInvoice = FXCollections.observableArrayList();
					
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
					cStockTypes = new ComboBox<>(stockType);
					cStockTypes.setId("test");
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
					
					group = new ToggleGroup();
					
					
					final RadioButton searchByInvoice = new RadioButton("Invoice");
					searchByInvoice.setTextFill(Color.DARKGOLDENROD);
					searchByInvoice.setToggleGroup(group);
					searchByInvoice.setUserData("invoice");
					
					final RadioButton searchByDate = new RadioButton("Date");
					searchByDate.setTextFill(Color.DARKGOLDENROD);
					searchByDate.setToggleGroup(group); 
					searchByDate.setUserData("date");
					
					
					/*final ToggleGroup groupSales = new ToggleGroup();
					final RadioButton searchByDateSales = new RadioButton("Date");
					searchByDateSales.setTextFill(Color.DARKGOLDENROD); 
					searchByDateSales.setUserData("date");
					searchByDateSales.setSelected(true);
					searchByDateSales.setToggleGroup(groupSales);*/
					
					final HBox hSearchBy = new HBox();
					hSearchBy.setAlignment(Pos.TOP_LEFT);
					hSearchBy.setMaxHeight(10);
					hSearchBy.setSpacing(10);
					
					
					
					final Label searchBy = new Label("Search By");
					searchBy.setTextFill(Color.DARKGOLDENROD);
					gMain.add(searchBy, 0, 1);
					
					gMain.add(hSearchBy, 1,1);
					
					//gMain.add(searchByDate, 4,1);
					final Text star1=new Text("*");
					star1.setFill(Color.RED);  
				     star1.setFont(Font.font ("calibri", 15));
					
					final Label sDate = new Label("Start Date");
					sDate.setTextFill(Color.DARKGOLDENROD);
					
					final Text star2=new Text("*");
					star2.setFill(Color.RED);  
				     star2.setFont(Font.font ("calibri", 15));
					final Label eDate = new Label("End Date");
					eDate.setTextFill(Color.DARKGOLDENROD);
					
					
					sCalendar = new FXCalendar();
					
					
					eCalendar = new FXCalendar();
					
					
					HBox sHBox = new HBox();
					sHBox.getChildren().addAll(sDate,star1);
					
					HBox eHBox = new HBox();
					eHBox.getChildren().addAll(eDate,star2);
					
					final HBox hDate=new HBox();
					hDate.setAlignment(Pos.TOP_LEFT);
					hDate.setMaxHeight(25);
					hDate.setSpacing(25);
				//	hDate.setPadding(new Insets(1, 0, 0, 0));
					hDate.getChildren().addAll(sHBox,sCalendar,eHBox,eCalendar);
					
					Button search = new Button("Search");
					search.setId("buttonall");
					final HBox hSearch=new HBox();
					hSearch.setAlignment(Pos.CENTER);
					hSearch.setMaxHeight(10);
					hSearch.setPadding(new Insets(15, 0, 0, 0));
					hSearch.getChildren().add(search);
					final Text star3=new Text("*");
					star3.setFill(Color.RED);  
				     star3.setFont(Font.font ("calibri", 15));
				     HBox hTextInvoice = new HBox();
					final Label invoiceId = new Label("Invoice Id");
					invoiceId.setTextFill(Color.DARKGOLDENROD);
					hTextInvoice.getChildren().addAll(invoiceId,star3);
					textInvoice = new AutoCompleteTextField<String>();		
					textInvoice.setItems(listOfInvoice);
					
					final HBox hInvoice=new HBox();
					hInvoice.setAlignment(Pos.TOP_LEFT);
					hInvoice.setMaxHeight(25);
					hInvoice.setSpacing(25);
					hInvoice.setPadding(new Insets(1, 0, 0, 0));
					hInvoice.getChildren().addAll(hTextInvoice,textInvoice);
					
					if(cStockTypes.getValue().equalsIgnoreCase("purchase"))
					{
						listOfInvoice.clear();
						try {
							listOfInvoice.addAll(UtiliesDAO.getUtiliesDAO().fetchInvoiceId(cStockTypes.getValue()));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						gMain.getChildren().removeAll(hDate,hInvoice,hSearch);
						searchByInvoice.setSelected(false);
						searchByDate.setSelected(true);
						hSearchBy.getChildren().removeAll(searchByInvoice,searchByDate);
						hSearchBy.getChildren().addAll(searchByInvoice,searchByDate);
					}
					
					if(group.getSelectedToggle().getUserData().toString().equals("date"))
			        {
			        	gMain.getChildren().removeAll(hDate,hInvoice,hSearch);
			        	gMain.add(hDate, 0,2,4,2);
			        	gMain.add(hSearch, 0, 3, 6, 3);
			        }
				
					
					/*if(groupSales.getSelectedToggle().getUserData().toString().equals("date"))
			        {
			        	gMain.getChildren().removeAll(hDate,hInvoice,hSearch);
			        	gMain.add(hDate, 0,2,4,2);
			        	gMain.add(hSearch, 0, 3, 6, 3);
			        }*/
					
					cStockTypes.valueProperty().addListener(new ChangeListener<String>() {

						@Override
						public void changed(ObservableValue<? extends String> observable,
								String oldValue, String newValue) 
						{
							try
							{
								BDelete.setVisible(false);
								sCalendar.getTextField().clear();
								eCalendar.getTextField().clear();
								hTableResult.getChildren().clear();
								gMain.getChildren().removeAll(hDate,hInvoice,hSearch);
								searchByInvoice.setSelected(false);
								searchByDate.setSelected(true);
								if(newValue.equalsIgnoreCase("purchase"))
								{
									listOfInvoice.clear();
									listOfInvoice.addAll(UtiliesDAO.getUtiliesDAO().fetchInvoiceId(cStockTypes.getValue()));
									hSearchBy.getChildren().removeAll(searchByInvoice,searchByDate);
									hSearchBy.getChildren().addAll(searchByInvoice,searchByDate);
									if(group.getSelectedToggle().getUserData().toString().equals("date"))
							        {
							        	gMain.getChildren().removeAll(hDate,hInvoice,hSearch);
							        	gMain.add(hDate, 0,2,4,2);
							        	gMain.add(hSearch, 0, 3, 6, 3);
							        }
								}
								else if(newValue.equalsIgnoreCase("sales"))
								{
									hSearchBy.getChildren().removeAll(searchByInvoice,searchByDate);
									hSearchBy.getChildren().addAll(searchByDate);
									if(group.getSelectedToggle().getUserData().toString().equals("date"))
							        {
							        	gMain.getChildren().removeAll(hDate,hInvoice,hSearch);
							        	gMain.add(hDate, 0,2,4,2);
							        	gMain.add(hSearch, 0, 3, 6, 3);
							        }
								}
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
						
					});
					
					
					
					if(cStockTypes.getValue().equalsIgnoreCase("purchase"))
					{
						
						group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
							@Override
						    public void changed(ObservableValue<? extends Toggle> ov,
						        Toggle old_toggle, Toggle new_toggle) {
						         
								if(new_toggle!=null)
								{
							        if(new_toggle.getUserData().toString().equals("date"))
							        {
							        	gMain.getChildren().removeAll(hDate,hInvoice,hSearch);
							        	gMain.add(hDate, 0,2,4,2);
							        	gMain.add(hSearch, 0, 3, 6, 3);
							        	star2.setVisible(true);
							        }
							        else if(new_toggle.getUserData().toString().equals("invoice"))
							        {
							        	gMain.getChildren().removeAll(hDate,hInvoice,hSearch);
							        	gMain.add(hInvoice, 0, 2,4,2);
							        	gMain.add(hSearch, 0, 3, 6, 3);
							        	star2.setVisible(false);
							        }
							    }
							}
						});
					}
					
					
					
					
					search.setOnAction(new EventHandler<ActionEvent>() {
					 			
					 			@Override
					 			public void handle(ActionEvent e) 
					 			{
					 				try 
					 				{
					 					lMsg.setText("");
										if(cStockTypes.getValue().equalsIgnoreCase("Purchase"))
										{
											hTableResult.getChildren().clear();
											if(group.getSelectedToggle().getUserData().equals("date"))
											{
											
												if (!validation.isEmpty(sCalendar.getTextField()) && !validation.isEmpty(eCalendar.getTextField()))
												{
								 					if (!validation.isInvalidDate(sCalendar.getTextField()) && !validation.isInvalidDate(eCalendar.getTextField()) )
								 					{
								 						if (validation.compareDates(sCalendar.getTextField().getText(), eCalendar.getTextField().getText())){
								 							
								 						
								 						hTableResult.getChildren().addAll(fetchIncomingHistoryTable(sCalendar.getTextField().getText(), eCalendar.getTextField().getText()));
								 						
								 						}
								 						else{
								 							lMsg.setText(CommonConstants.DATE_COMPARE);
								 							BDelete.setVisible(false);
								 						}
								 					}
								 					else
								 					{
								 						lMsg.setText(CommonConstants.WRONG_DATE);
								 						BDelete.setVisible(false);
								 					}
												}
												else
												{
													lMsg.setText(CommonConstants.EMPTY_MSG);
													BDelete.setVisible(false);
												}
											}
											else if(group.getSelectedToggle().getUserData().equals("invoice"))
											{
												hTableResult.getChildren().clear();
												if (!validation.isEmpty(textInvoice))
												{
													hTableResult.getChildren().addAll(fetchIncomingHistoryTable(textInvoice.getText()));
												}
												else
												{
													lMsg.setText(CommonConstants.EMPTY_MSG);
												}
												
											}
										}
										else if(cStockTypes.getValue().equalsIgnoreCase("Sales"))
										{
											hTableResult.getChildren().clear();
											if(group.getSelectedToggle().getUserData().equals("date"))
											{
												if (!validation.isEmpty(sCalendar.getTextField()) && !validation.isEmpty(eCalendar.getTextField()))
												{
								 					if (!validation.isInvalidDate(sCalendar.getTextField()) && !validation.isInvalidDate(eCalendar.getTextField()) )
								 					{
								 						
								 						hTableResult.getChildren().addAll(fetchOutgoingHistoryTable(sCalendar.getTextField().getText(), eCalendar.getTextField().getText()));
								 					}
								 					else
								 					{
								 						BDelete.setVisible(false);
								 						lMsg.setText(CommonConstants.WRONG_DATE);
								 					}
												}
												else
												{
													BDelete.setVisible(false);
													lMsg.setText(CommonConstants.EMPTY_MSG);
												}
												
											}
											//Not required For current Client
											/*else if(group.getSelectedToggle().getUserData().equals("invoice"))
											{
												hTableResult.getChildren().addAll(fetchOutgoingHistoryTable(textInvoice.getText()));
											}*/
											
										}
										
					 				}
									
					 				catch (Exception e1) 
					 				{
										e1.printStackTrace();
									}
					 				
					 				
					 			}
					 		});
					
					
					
					
					
					BDelete.setVisible(false);
                    BDelete.setId("buttonall");
                    
                   
                    	BDelete.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent arg0) {
							
							try {
								
								TransactionDeleteModalWindow(stage);
										
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
                    
                    
                    StackPane.setMargin(BDelete, new Insets(240,Screen.getPrimary().getVisualBounds().getWidth()/2.1 ,20,0));
                    StackPane.setAlignment(BDelete, Pos.BASELINE_RIGHT);
					
					
					Text man_text=new Text(CommonConstants.STAR_MSG);
					man_text.setFill(Color.DARKKHAKI);  
					man_text.setFont(Font.font ("Arial", 12));
					
					
					StackPane.setMargin(man_text, new Insets(240,266,20,0));
					StackPane.setAlignment(man_text, Pos.BASELINE_RIGHT);
					
					StackPane.setMargin(lMsg, new Insets(253,0,0,0));
					StackPane.setAlignment(lMsg, Pos.BASELINE_CENTER);
					
					StackPane.setMargin(roundRect, new Insets(15,10,8,8));
					StackPane.setAlignment(roundRect, Pos.TOP_CENTER);
					
					StackPane.setMargin(gMain, new Insets(120,0,0,Screen.getPrimary().getVisualBounds().getWidth()/3));
					StackPane.setAlignment(gMain, Pos.CENTER);
					
					StackPane.setMargin(text5, new Insets(50,8,8,8));
					StackPane.setAlignment(text5, Pos.TOP_CENTER);
					
					if(cStockTypes.getValue().equalsIgnoreCase("Purchase"))
					{
						StackPane.setMargin(hTableResult, new Insets(165,0,0,Screen.getPrimary().getVisualBounds().getWidth()/4.5));
						StackPane.setAlignment(hTableResult, Pos.CENTER);
						
					}
					else
					{
						StackPane.setMargin(hTableResult, new Insets(85,0,0,Screen.getPrimary().getVisualBounds().getWidth()/3.75));
						StackPane.setAlignment(hTableResult, Pos.CENTER);
						
					}
					
					StackPane.setMargin(star2, new Insets(190,20,8,95));
					StackPane.setAlignment(star2, Pos.TOP_CENTER);
					stack.getChildren().addAll(text5,roundRect,gMain,hTableResult,man_text,lMsg,BDelete);
					
		return stack;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public HBox fetchIncomingHistoryTable(String initialDate, String finalDate) throws Exception
	{
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		data = FXCollections.observableArrayList();
		
		data = incomingStockService.fetchIncomingStockDetails(initialDate, finalDate);
		BDelete.setVisible(true);
		
		table = new TableView<StockVO>();
	 	table.setEditable(true);
	 	table.setMinSize(737, 280);
	 	table.setMaxSize(737, 280);
	 	table.setStyle("-fx-background-color: transparent;");
	 	table.setItems(data);
	 	
	 	TableColumn<StockVO,String> date = new TableColumn<StockVO,String>("Date");
	 	date.setMinWidth(150);
	 	date.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("date"));
	 	
	 	TableColumn<StockVO,String> invoiceId = new TableColumn<StockVO,String>("Invoice Id");
	 	invoiceId.setMinWidth(140);
	 	invoiceId.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("invoiceId"));
	 	
	 	TableColumn<StockVO,String> itemName = new TableColumn<StockVO,String>("Item Name");
	 	itemName.setMinWidth(150);
	 	itemName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("itemName"));
	 	
	 	TableColumn<StockVO,String> typeName = new TableColumn<StockVO,String>("Type");
	 	typeName.setMinWidth(130);
	 	typeName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("typeName"));
	 	
	 	TableColumn<StockVO,Integer> quantity = new TableColumn<StockVO,Integer>("Quantity");
	 	quantity.setMinWidth(137);
	 	quantity.setMaxWidth(137);
	 	quantity.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, Integer>("quantity"));
	 	
	 	TableColumn<StockVO,Boolean> checkColumn = new TableColumn<StockVO,Boolean>("");
	 	checkColumn.setMinWidth(28);
	 	checkColumn.setMaxWidth(28);
	 	checkColumn.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, Boolean>("check"));
	 	
	 	
	 	
	 	
		final Callback<TableColumn<StockVO, Boolean>, TableCell<StockVO, Boolean>> cellFactory = CheckBoxTableCell.forTableColumn(checkColumn);
	 	
	 	
	 	
	 	checkColumn.setCellFactory(new Callback<TableColumn<StockVO, Boolean>, TableCell<StockVO, Boolean>>() {
	 	  @Override
	 	  public TableCell<StockVO, Boolean> call(TableColumn<StockVO, Boolean> column) {
	 	    TableCell<StockVO, Boolean> cell = cellFactory.call(column);
	 	    cell.setAlignment(Pos.CENTER);
	 	    return cell ;
	 	  }
	 	});
	 
	 	
	 	 	
		table.getColumns().addAll(checkColumn, date, invoiceId, itemName, typeName, quantity);
		hBox.getChildren().addAll(table);
		return hBox;
	}
	
	public HBox fetchIncomingHistoryTable(String invoiceIdText) throws Exception
	{
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		data = FXCollections.observableArrayList();
		
		data = incomingStockService.fetchIncomingStockDetails(invoiceIdText);
		BDelete.setVisible(true);
		
		table = new TableView<StockVO>();
		table.setItems(data);
		
		
	 	table.setEditable(true);
	 	table.setMinSize(737, 280);
	 	table.setMaxSize(737, 280);
	 	table.setStyle("-fx-background-color: transparent;");
	 	
	 	TableColumn<StockVO, String> date = new TableColumn<StockVO, String>("Date");
	 	date.setMinWidth(147);
	 	date.setMaxWidth(147);
	 	date.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("date"));
	 	
	 	TableColumn<StockVO, String> invoiceId = new TableColumn<StockVO, String>("Invoice Id");
	 	invoiceId.setMinWidth(150);
	 	invoiceId.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("invoiceId"));
	 	
	 	TableColumn<StockVO, String> itemName = new TableColumn<StockVO, String>("Item Name");
	 	itemName.setMinWidth(150);
	 	itemName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("itemName"));
	 	
	 	TableColumn<StockVO, String> typeName = new TableColumn<StockVO, String>("Type");
	 	typeName.setMinWidth(128);
	 	typeName.setMaxWidth(128);
	 	typeName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("typeName"));
	 	
	 	TableColumn<StockVO,Integer> quantity = new TableColumn<StockVO, Integer>("Quantity");
	 	quantity.setMinWidth(130);
	 	quantity.setMaxWidth(130);
	 	quantity.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, Integer>("quantity"));
	 	
	 	TableColumn<StockVO,Boolean> checkColumn = new TableColumn<StockVO,Boolean>("");
	 	checkColumn.setMinWidth(28);
	 	checkColumn.setMaxWidth(28);
	 	checkColumn.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, Boolean>("check"));
	 	
	 	final Callback<TableColumn<StockVO, Boolean>, TableCell<StockVO, Boolean>> cellFactory = CheckBoxTableCell.forTableColumn(checkColumn);
	 	
	 	
	 	checkColumn.setCellFactory(new Callback<TableColumn<StockVO, Boolean>, TableCell<StockVO, Boolean>>() {
	 	  @Override
	 	  public TableCell<StockVO, Boolean> call(TableColumn<StockVO, Boolean> column) {
	 	    TableCell<StockVO, Boolean> cell = cellFactory.call(column);
	 	    cell.setAlignment(Pos.CENTER);
	 	    return cell ;
	 	  }
	 	});
	 
	 	
		table.getColumns().addAll(checkColumn, date, invoiceId, itemName, typeName, quantity);
		hBox.getChildren().addAll(table);
		return hBox;
	}

	
	public HBox fetchOutgoingHistoryTable(String initialDate, String finalDate) throws Exception
	{
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(0, 0, 0, 34));
		data = FXCollections.observableArrayList();
		
		data = outgoingStockService.fetchOutgoingStockDetails(initialDate, finalDate);
		BDelete.setVisible(true);
		
		table = new TableView<StockVO>();
		table.setItems(data);
		
	 	table.setEditable(true);
	 	table.setMinSize(627, 280);
	 	table.setMaxSize(627, 280);
	 	table.setStyle("-fx-background-color: transparent;");
	 	
	 	TableColumn<StockVO, String> date = new TableColumn<StockVO, String>("Date");
	 	date.setMinWidth(150);
	 	date.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("date"));
	 	
	 	
	 	TableColumn<StockVO, String> itemName = new TableColumn<StockVO, String>("Item Name");
	 	itemName.setMinWidth(150);
	 	itemName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("itemName"));
	 	
	 	TableColumn<StockVO, String> typeName = new TableColumn<StockVO, String>("Type");
	 	typeName.setMinWidth(150);
	 	typeName.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, String>("typeName"));
	 	
	 	TableColumn<StockVO, Integer> quantity = new TableColumn<StockVO, Integer>("Quantity");
	 	quantity.setMinWidth(140);
	 	quantity.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, Integer>("quantity"));
	 	
	 	TableColumn<StockVO,Boolean> checkColumn = new TableColumn<StockVO,Boolean>("");
	 	checkColumn.setMinWidth(10);
	 	checkColumn.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, Boolean>("check"));
	 	final Callback<TableColumn<StockVO, Boolean>, TableCell<StockVO, Boolean>> cellFactory = CheckBoxTableCell.forTableColumn(checkColumn);
	 	
	 	
	 	
	 	checkColumn.setCellFactory(new Callback<TableColumn<StockVO, Boolean>, TableCell<StockVO, Boolean>>() {
	 	  @Override
	 	  public TableCell<StockVO, Boolean> call(TableColumn<StockVO, Boolean> column) {
	 	    TableCell<StockVO, Boolean> cell = cellFactory.call(column);
	 	    cell.setAlignment(Pos.CENTER);
	 	    return cell ;
	 	  }
	 	});
	 	
		table.getColumns().addAll(checkColumn, date, itemName, typeName, quantity);
		hBox.getChildren().addAll(table);
		return hBox;
	}
	
	public HBox fetchOutgoingHistoryTable(String invoiceIdText) throws Exception
	{
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		data = FXCollections.observableArrayList();
		
		data = outgoingStockService.fetchOutgoingStockDetails(invoiceIdText);
		BDelete.setVisible(true);
		
		table = new TableView<StockVO>();
		table.setItems(data);
		
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
	 	
	 	TableColumn<StockVO,Boolean> checkColumn = new TableColumn<StockVO,Boolean>("Select");
	 	checkColumn.setMinWidth(60);
	 	checkColumn.setCellValueFactory(
	 	new PropertyValueFactory<StockVO, Boolean>("check"));
	 	final Callback<TableColumn<StockVO, Boolean>, TableCell<StockVO, Boolean>> cellFactory = CheckBoxTableCell.forTableColumn(checkColumn);
	 	
	 	
	 	
	 	checkColumn.setCellFactory(new Callback<TableColumn<StockVO, Boolean>, TableCell<StockVO, Boolean>>() {
	 	  @Override
	 	  public TableCell<StockVO, Boolean> call(TableColumn<StockVO, Boolean> column) {
	 	    TableCell<StockVO, Boolean> cell = cellFactory.call(column);
	 	    cell.setAlignment(Pos.CENTER);
	 	    return cell ;
	 	  }
	 	});
	 	
		table.getColumns().addAll(checkColumn, date, itemName, typeName, quantity);
		hBox.getChildren().addAll(table);
		return hBox;
	}
	public void TransactionDeleteModalWindow(final Stage classStage) throws Exception{
		final TextField temp = new TextField();
		// initialize the confirmation dialog
		final Stage dialog = new Stage(StageStyle.TRANSPARENT);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(classStage);
		dialog.setScene(new Scene(HBoxBuilder
				.create()
				.styleClass("modal-dialog")
				.children(
						LabelBuilder.create().text("Are you sure to delete the entry/entries?")
								.build(),
						ButtonBuilder.create().text("Yes").defaultButton(true)
								.onAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent actionEvent) {
										// take action and close the dialog.
										if(cStockTypes.getValue().equalsIgnoreCase("Purchase"))
										{
											hTableResult.getChildren().clear();
											if(group.getSelectedToggle().getUserData().equals("date"))
											{
													try {
														transactionHistoryService.deletePurchaseFromDate(table.getItems());
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													hTableResult.getChildren().clear();
													try {
														hTableResult.getChildren().add(fetchIncomingHistoryTable(sCalendar.getTextField().getText(), eCalendar.getTextField().getText()));
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
											}
											else if(group.getSelectedToggle().getUserData().equals("invoice"))
											{
													try {
														transactionHistoryService.deletePurchaseFromDate(table.getItems());
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													hTableResult.getChildren().clear();
													try {
														hTableResult.getChildren().addAll(fetchIncomingHistoryTable(textInvoice.getText()));
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
											}
										}
										else if(cStockTypes.getValue().equalsIgnoreCase("Sales"))
										{
											if(group.getSelectedToggle().getUserData().equals("date"))
											{
													try {
														transactionHistoryService.deleteSalesFromDate(table.getItems());
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													hTableResult.getChildren().clear();
									 				try {
														hTableResult.getChildren().addAll(fetchOutgoingHistoryTable(sCalendar.getTextField().getText(), eCalendar.getTextField().getText()));
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												
											}
										}	
										classStage.getScene().getRoot()
												.setEffect(null);
										dialog.close();
									}
								}).build(),
						ButtonBuilder.create().text("No").cancelButton(true)
								.onAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent actionEvent) {
										// abort action and close the dialog.
										
										classStage.getScene().getRoot()
												.setEffect(null);
										dialog.close();
									}
								}).build()).build(), Color.TRANSPARENT));
		dialog.getScene()
				.getStylesheets()
				.add(getClass().getResource("../styles/modal-dialog.css")
						.toExternalForm());
		
		// allow the dialog to be dragged around.
		final Node root = dialog.getScene().getRoot();
		final Delta dragDelta = new Delta();
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				// record a delta distance for the drag and drop operation.
				dragDelta.x = dialog.getX() - mouseEvent.getScreenX();
				dragDelta.y = dialog.getY() - mouseEvent.getScreenY();
			}
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				dialog.setX(mouseEvent.getScreenX() + dragDelta.x);
				dialog.setY(mouseEvent.getScreenY() + dragDelta.y);
			}
		});

		

				classStage.getScene().getRoot().setEffect(new BoxBlur());
				dialog.show();
	}
	
	class Delta { double x, y; }

}

