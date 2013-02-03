package com.fnz.UI;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;



import com.fnz.VO.CategoryTypeVO;
import com.fnz.VO.CategoryVO;
import com.fnz.VO.ItemTypeVO;
import com.fnz.VO.ItemVO;
import com.fnz.dao.UtiliesDAO;
import com.fnz.service.OutgoingStockService;
import com.fnz.service.StockDetailsService;
import com.sai.javafx.calendar.FXCalendar;

public class OutgoingStock 
{
	OutgoingStockService outgoingStockService;
	StockDetailsService stockDetailsService;
	Animation animation;
	StackPane stack;
	
	public OutgoingStock() 
	{
		stockDetailsService= new StockDetailsService();
		outgoingStockService = new OutgoingStockService();
		animation = new Animation();
	}
	public BorderPane delStockDrinkList(final ObservableList<CategoryVO> listCategory)
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

   	
   	
   	final ToggleButton bt1= new ToggleButton(listCategory.get(0).getCategoryName());
   	bt1.setToggleGroup(groupDrink);
   	bt1.setId("drinkName");
   	bt1.setMaxSize(250,250);
   	typesOfDrink2.add(bt1,0,0); //premium whisky
   	
   	bt1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('wine.jpeg');");
				/*borderPane.setCenter(drinks.viewWineStock());
				chkRect()*/
				borderPane.setCenter(addStock(listCategory.get(0).getCategotyId(),listCategory.get(0).getCategoryName()));
				animation.animateLeft(bt1,0,0);
			}
		});
   	
   	final ToggleButton bt2= new ToggleButton(listCategory.get(1).getCategoryName());
   	bt2.setToggleGroup(groupDrink);
   	bt2.setId("drinkName");
   	bt2.setMaxSize(250,250);
   	typesOfDrink.add(bt2,0,0); //regular whisky
   	bt2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('vodka.jpg');");
				//borderPane.setCenter(drinks.viewVodkaStock());
				borderPane.setCenter(addStock(listCategory.get(1).getCategotyId(),listCategory.get(1).getCategoryName()));
				animation.animateRight(bt2,0,0);
			}
		});
   	
   	final ToggleButton bt3= new ToggleButton(listCategory.get(2).getCategoryName());
   	bt3.setToggleGroup(groupDrink);
   	bt3.setId("drinkName");
   	bt3.setMaxSize(250,250);
   	typesOfDrink2.add(bt3,0,1);//Premium Vodka
   	bt3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('beer2.jpg');");
				borderPane.setCenter(addStock(listCategory.get(2).getCategotyId(),listCategory.get(2).getCategoryName()));
				animation.animateLeft(bt3,0,1);
			}
		});

   	final ToggleButton bt4= new ToggleButton(listCategory.get(3).getCategoryName());
   	bt4.setToggleGroup(groupDrink);
   	bt4.setId("drinkName");
   	bt4.setMaxSize(250,250);
   	typesOfDrink.add(bt4,0,1);//Regular Vodka
   	bt4.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('whisky.jpg');");
				borderPane.setCenter(addStock(listCategory.get(3).getCategotyId(),listCategory.get(3).getCategoryName()));
				animation.animateRight(bt4,0,1);
			}
		});
   	
   	final ToggleButton bt5= new ToggleButton(listCategory.get(4).getCategoryName());
   	bt5.setToggleGroup(groupDrink);
   	bt5.setId("drinkName");
   	bt5.setMaxSize(250,250);
   	typesOfDrink.add(bt5,0,4);//Brandy
   	bt5.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('rum2.jpg');");
				borderPane.setCenter(addStock(listCategory.get(4).getCategotyId(),listCategory.get(5).getCategoryName()));
				animation.animateRight(bt5,0,4);
			}
		});
   	
   	final ToggleButton bt6= new ToggleButton(listCategory.get(5).getCategoryName());
   	bt6.setToggleGroup(groupDrink);
   	bt6.setId("drinkName");
   	bt6.setMaxSize(250,250);
   	typesOfDrink.add(bt6,0,5);//Gin
   	bt6.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('Scotch.jpg');");
				borderPane.setCenter(addStock(listCategory.get(5).getCategotyId(),listCategory.get(5).getCategoryName()));
				animation.animateRight(bt6,0,5);
			}
		});
   	
   	final ToggleButton bt7= new ToggleButton(listCategory.get(6).getCategoryName().replace('&','\n' ));
   	bt7.setToggleGroup(groupDrink);
   	bt7.setId("drinkName");
   	bt7.setMaxSize(250,250);
   	typesOfDrink2.add(bt7,0,5);//White Rum and Premixes
   	bt7.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
				borderPane.setCenter(addStock(listCategory.get(6).getCategotyId(),listCategory.get(6).getCategoryName()));
				animation.animateLeft(bt7,0,5);
			}
		});
   	
   	final ToggleButton bt8= new ToggleButton(listCategory.get(7).getCategoryName());
   	bt8.setToggleGroup(groupDrink);
   	bt8.setId("drinkName");
   	bt8.setMaxSize(250,250);
   	typesOfDrink.add(bt8,0,3);//Regular Rum
   	bt8.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
				borderPane.setCenter(addStock(listCategory.get(7).getCategotyId(),listCategory.get(7).getCategoryName()));
				animation.animateRight(bt8,0,3);
			}
		});
   	
   	final ToggleButton bt9= new ToggleButton(listCategory.get(8).getCategoryName());
   	bt9.setToggleGroup(groupDrink);
   	bt9.setId("drinkName");
   	bt9.setMaxSize(250,250);
   	typesOfDrink.add(bt9,0,6);//Beer
   	bt9.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
				borderPane.setCenter(addStock(listCategory.get(8).getCategotyId(),listCategory.get(8).getCategoryName()));
				animation.animateRight(bt9,0,6);
			}
		});
   	
   	final ToggleButton bt10= new ToggleButton(listCategory.get(9).getCategoryName());
   	bt10.setToggleGroup(groupDrink);
   	bt10.setId("drinkName");
   	bt10.setMaxSize(250,250);
   	typesOfDrink2.add(bt10,0,3);//Wine
   	bt10.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
				borderPane.setCenter(addStock(listCategory.get(9).getCategotyId(),listCategory.get(9).getCategoryName()));
				animation.animateLeft(bt10,0,3);
			}
		});
   	
   	final ToggleButton bt11= new ToggleButton(listCategory.get(10).getCategoryName());
   	bt11.setToggleGroup(groupDrink);
   	bt11.setId("drinkName");
   	bt11.setMaxSize(250,250);
   	typesOfDrink2.add(bt11,0,2);//Premium Scotch
   	bt11.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
				borderPane.setCenter(addStock(listCategory.get(10).getCategotyId(),listCategory.get(10).getCategoryName()));
				animation.animateLeft(bt11,0,2);
			}
		});
   	
   	final ToggleButton bt12= new ToggleButton(listCategory.get(11).getCategoryName());
   	bt12.setToggleGroup(groupDrink);
   	bt12.setId("drinkName");
   	bt12.setMaxSize(250,250);
   	typesOfDrink.add(bt12,0,2); //regular Scotch
   	bt12.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
				borderPane.setCenter(addStock(listCategory.get(11).getCategotyId(),listCategory.get(11).getCategoryName()));
				animation.animateRight(bt12,0,2);
			}
		});
   	
   	final ToggleButton bt13= new ToggleButton(listCategory.get(12).getCategoryName());
   	bt13.setToggleGroup(groupDrink);
   	bt13.setId("drinkName");
   	bt13.setMaxSize(250,250);
   	typesOfDrink2.add(bt13,0,4);//Beverages
   	bt13.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) 
			{
				//borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
				borderPane.setCenter(addStock(listCategory.get(12).getCategotyId(),listCategory.get(12).getCategoryName()));
				animation.animateLeft(bt13,0,4);
			}
		});
      // typesOfDrink.getChildren().addAll(tbWine,tbVodka,tbBeer,tbWisky,tbRum,tbScotch,tbOther);
   	borderPane.setLeft(typesOfDrink);
   	borderPane.setRight(typesOfDrink2);
   	bt2.fire();
   	return borderPane;
	}	
	
	public StackPane addStock(String categoryId, String categoryName)
	{
		stack = new StackPane();
		
		GridPane grid = new GridPane();
	
        grid.setVgap(8);
        grid.setPadding(new Insets(30));
		final ObservableList<ItemVO> dataTable;
		final ObservableList<ItemVO> dataTable1;
		final ObservableList<ItemVO> dataTable2;
		final ObservableList<CategoryTypeVO> typeList;
		
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
			typeList =  FXCollections.observableArrayList();
			typeList.addAll(UtiliesDAO.getUtiliesDAO().fetchTypes(categoryId));
			
			dataTable = FXCollections.observableArrayList();
			dataTable.addAll(stockDetailsService.viewStock(categoryId));
			
			dataTable1 = FXCollections.observableArrayList();
			dataTable2 = FXCollections.observableArrayList();
			
			for(int i=0;i<dataTable.size();i++)
			{
				dataTable1.add(dataTable.get(i++));
				if(i<=dataTable.size()-1)
				{
					dataTable2.add(dataTable.get(i));
				}
			}
			
			
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
		 	
			HBox invoiceBox = new HBox();
			invoiceBox.setPadding(new Insets(0.5, 0, 0.5, 50));
			Label l1= new Label("Invoice Id : ");
			l1.setTextFill(Color.GRAY);
			invoiceBox.getChildren().add(l1);
			
			
			final TextField invoiceField = new TextField();
			invoiceBox.getChildren().add(invoiceField);
			
			HBox dateBox = new HBox();
			dateBox.setPadding(new Insets(0.5, 0, 0.5, 50));
			Label l2=new Label("Date : ");
			l2.setTextFill(Color.GRAY);
			dateBox.getChildren().add(l2);
			
			final FXCalendar date = new FXCalendar();
			dateBox.getChildren().add(date);
			
			
			
			
		 	TableView<ItemVO> table1 = new TableView<ItemVO>();
		 	table1.setEditable(true);
		 	table1.setMaxSize(400, 300);
		 	table1.setStyle("-fx-background-color: transparent;");
		 	
		 	TableView<ItemVO> table2 = new TableView<ItemVO>();
		 	table2.setEditable(true);
		 	
		 	table2.setMaxSize(400, 300);
		 	table2.setStyle("-fx-background-color: transparent;");
		 	
			/*final Callback<TableColumn<ItemVO, Integer>, TableCell<ItemVO, Integer>> cellFactory = new Callback<TableColumn<ItemVO, Integer>, TableCell<ItemVO, Integer>>() {
				public TableCell call(TableColumn p) {
					return new EditingCell();
				}
			};*/
		 	
		 	
		 	TableColumn<ItemVO,String> itemName = new TableColumn<ItemVO,String> ("Item");
		 	itemName.setMinWidth(200);
		 	itemName.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, String>("itemName"));
		 	
		 	TableColumn<ItemVO, Integer>  quantity = new TableColumn<ItemVO, Integer> ("Quantity");
		 	quantity.setMinWidth(200);
		 	quantity.setEditable(true);
		 	
		 	
		 	for (final CategoryTypeVO type : typeList)
		 	{
		 		 TableColumn<ItemVO, Integer> col = new TableColumn<ItemVO, Integer>(type.getTypeName());
		 		 col.setMinWidth(100);
		 		 col.setEditable(true);
		 		
		 		final Callback<TableColumn<ItemVO, Integer>, TableCell<ItemVO, Integer>> cellFactory = new Callback<TableColumn<ItemVO, Integer>, TableCell<ItemVO, Integer>>() {
					public TableCell<ItemVO, Integer> call(TableColumn<ItemVO, Integer> p) {
						return new EditingCell();
					}
				};

				col.setCellValueFactory(new Callback<CellDataFeatures<ItemVO, Integer>, ObservableValue<Integer>>() {
	                @Override
	                public ObservableValue<Integer> call(CellDataFeatures<ItemVO, Integer> cellData) {
	                  ItemVO item = cellData.getValue();
	                  if (item == null) {
	                    return null;
	                  } else {
	                	  ObservableMap<String,ItemTypeVO> itemTypesMap = FXCollections.observableHashMap();
			 		    	itemTypesMap = item.getListType();
			 		    
			 		    	return new ReadOnlyObjectWrapper<Integer>(0);
			 		    
	                  }
	                }
	              });
		 		 col.setCellFactory(cellFactory);
		 		
		 		 col.setOnEditCommit(
		 				new EventHandler<TableColumn.CellEditEvent<ItemVO, Integer>>() {
		 				public void handle(TableColumn.CellEditEvent<ItemVO, Integer> t) {
		 				((ItemVO)t.getTableView().getItems().get(
		 				t.getTablePosition().getRow())).getListType().get(type.getTypeId()).setQuantity(t.getNewValue());
		 				}
		 				});
		 		  quantity.getColumns().add(col);
		 		}
		 		
		 		
		 	
		 	table1.setItems(dataTable1);
		 	table1.getColumns().addAll(itemName, quantity);
		 	

		 	
		 	TableColumn<ItemVO,String> itemName2 = new TableColumn<ItemVO,String> ("Item");
		 	itemName2.setMinWidth(200);
		 	itemName2.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, String>("itemName"));
		 	
		 	TableColumn<ItemVO, Integer>  quantity2 = new TableColumn<ItemVO, Integer> ("Quantity");
		 	quantity.setMinWidth(200);
		 	quantity.setEditable(true);
		 	
		 	
		 	for (final CategoryTypeVO type : typeList)
		 	{
		 		 TableColumn<ItemVO, Integer> col2 = new TableColumn<ItemVO, Integer>(type.getTypeName());
		 		 col2.setMinWidth(100);
		 		 col2.setEditable(true);
		 		
		 		final Callback<TableColumn<ItemVO, Integer>, TableCell<ItemVO, Integer>> cellFactory = new Callback<TableColumn<ItemVO, Integer>, TableCell<ItemVO, Integer>>() {
					public TableCell<ItemVO, Integer> call(TableColumn<ItemVO, Integer> p) {
						return new EditingCell();
					}
				};

				col2.setCellValueFactory(new Callback<CellDataFeatures<ItemVO, Integer>, ObservableValue<Integer>>() {
	                @Override
	                public ObservableValue<Integer> call(CellDataFeatures<ItemVO, Integer> cellData) {
	                  ItemVO item = cellData.getValue();
	                  if (item == null) {
	                    return null;
	                  } else {
	                	  ObservableMap<String,ItemTypeVO> itemTypesMap = FXCollections.observableHashMap();
			 		    	itemTypesMap = item.getListType();
			 		    
			 		    	return new ReadOnlyObjectWrapper<Integer>(0);
			 		    
	                  }
	                }
	              });
		 		 col2.setCellFactory(cellFactory);
		 		
		 		 col2.setOnEditCommit(
		 				new EventHandler<TableColumn.CellEditEvent<ItemVO, Integer>>() {
		 				public void handle(TableColumn.CellEditEvent<ItemVO, Integer> t) {
		 				((ItemVO)t.getTableView().getItems().get(
		 				t.getTablePosition().getRow())).getListType().get(type.getTypeId()).setQuantity(t.getNewValue());
		 				}
		 				});
		 		  quantity2.getColumns().add(col2);
		 		}
		 	table2.setItems(dataTable2);
		 	table2.getColumns().addAll(itemName2, quantity2);
		 	
			Button button = new Button("Update Stock");
			button.setOnAction(new EventHandler<ActionEvent>() {
	 			
	 			@Override
	 			public void handle(ActionEvent e) 
	 			{
	 				try 
	 				{
	 					dataTable.clear();
	 					dataTable.addAll(dataTable1);
	 					dataTable.addAll(dataTable2);
	 					outgoingStockService.deleteOutgoingStock(invoiceField.getText(),date.getTextField().getText(), dataTable);
					} 
	 				catch (Exception e1) 
					{
						e1.printStackTrace();
					}
	 			}
	 		});
			
			
			
			
			
			grid.add(invoiceBox,0,11);
			grid.add(dateBox, 1, 11);
			grid.add(table1,0,12);
			grid.add(table2,1,12);
			grid.add(button,1,14);
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
	
	 class EditingCell extends TableCell<ItemVO, Integer> {
		 
	      private TextField textField;
	     
	      public EditingCell() {}
	     
	      @Override
	      public void startEdit() {
	          super.startEdit();
	         
	          if (textField == null) {
	              createTextField();
	          }
	         
	          setGraphic(textField);
	          setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	          //textField.selectAll();
	          Platform.runLater(new Runnable() {
		            @Override
		            public void run() {
		                textField.requestFocus();
		                textField.selectAll();
		            }
		        });
	      }
	     
	      @Override
	      public void cancelEdit() {
	          super.cancelEdit();
	         
	          setText(String.valueOf(getItem()));
	          setContentDisplay(ContentDisplay.TEXT_ONLY);
	      }
	 
	      @Override
	      public void updateItem(Integer item, boolean empty) {
	          super.updateItem(item, empty);
	         
	          if (empty) {
	              setText(null);
	              setGraphic(null);
	          } else {
	              if (isEditing()) {
	                  if (textField != null) {
	                      textField.setText(getString());
	                  }
	                  setGraphic(textField);
	                  setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	              } else {
	                  setText(getString());
	                  setContentDisplay(ContentDisplay.TEXT_ONLY);
	              }
	          }
	      }
	
	      private void createTextField() {
	          textField = new TextField();
	          //textField.setText(getString());
	          textField.setText("0");
	          textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
	          
	          textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

	        	    @Override
	        	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
	        	        if (!arg2) {
	        	            commitEdit(Integer.parseInt(textField.getText()));
	        	        }
	        	    }
	        	});
	          
	          textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
		            @Override
		            public void handle(KeyEvent t) {
		                if (t.getCode() == KeyCode.ENTER) {
		                    commitEdit(Integer.parseInt(textField.getText()));
		                } else if (t.getCode() == KeyCode.ESCAPE) {
		                    cancelEdit();
		                } else if (t.getCode() == KeyCode.TAB) {
		                    commitEdit(Integer.parseInt(textField.getText()));
		                    TableColumn nextColumn = getNextColumn(!t.isShiftDown());
		                    if (nextColumn != null) {
		                        getTableView().edit(getTableRow().getIndex(), nextColumn);
		                    }
		                }
		            }
		        });
	          textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                if (!newValue && textField != null) {
		                    commitEdit(Integer.parseInt(textField.getText()));
		                }
		            }
		        });
	      }
	     
	      private String getString() {
	          return getItem() == null ? "" : getItem().toString();
	      }
	      
	      private TableColumn<ItemVO, ?> getNextColumn(boolean forward) {
		        List<TableColumn<ItemVO, ?>> columns = new ArrayList<>();
		        for (TableColumn<ItemVO, ?> column : getTableView().getColumns()) {
		            columns.addAll(getLeaves(column));
		        }
		        //There is no other column that supports editing.
		        if (columns.size() < 2) {
		            return null;
		        }
		        int currentIndex = columns.indexOf(getTableColumn());
		        int nextIndex = currentIndex;
		        if (forward) {
		            nextIndex++;
		            if (nextIndex > columns.size() - 1) {
		                nextIndex = 0;
		            }
		        } else {
		            nextIndex--;
		            if (nextIndex < 0) {
		                nextIndex = columns.size() - 1;
		            }
		        }
		        return columns.get(nextIndex);
		    }
	      
	      	private List<TableColumn<ItemVO, ?>> getLeaves(TableColumn<ItemVO, ?> root) {
		        List<TableColumn<ItemVO, ?>> columns = new ArrayList<>();
		        if (root.getColumns().isEmpty()) {
		            //We only want the leaves that are editable.
		            if (root.isEditable()) {
		                columns.add(root);
		            }
		            return columns;
		        } else {
		            for (TableColumn<ItemVO, ?> column : root.getColumns()) {
		                columns.addAll(getLeaves(column));
		            }
		            return columns;
		        }
	      	}
	  }
		
}
