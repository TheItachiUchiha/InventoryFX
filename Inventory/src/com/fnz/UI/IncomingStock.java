package com.fnz.UI;

import javafx.application.Platform;
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
import javafx.scene.control.cell.PropertyValueFactory;
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





import com.fnz.VO.CategoryVO;
import com.fnz.VO.ItemVO;
import com.fnz.service.IncomingStockService;
import com.fnz.service.StockDetailsService;

public class IncomingStock 
{
	IncomingStockService incomingStockService;
	StockDetailsService stockDetailsService;
	Animation animation;
	StackPane stack;
	
	public IncomingStock() 
	{
		stockDetailsService= new StockDetailsService();
		incomingStockService = new IncomingStockService();
		animation = new Animation();
	}
	public BorderPane addStockDrinkList(ObservableList<CategoryVO> categoryList)
	{
		final BorderPane borderPane = new BorderPane();
		
		 borderPane.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth());
	        borderPane.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight());
	        borderPane.setPadding(new Insets(15,0,0,20));
	        borderPane.setId("borderxx");
		GridPane typesOfDrink = new GridPane();
		
		typesOfDrink.setVgap(8);
    	typesOfDrink.setPadding(new Insets(30,0,0,0));

    	ToggleGroup groupDrink=new ToggleGroup();
    	
    	final ToggleButton tbWine= new ToggleButton("Wine");
    	tbWine.setToggleGroup(groupDrink);
    	tbWine.setId("drinkName");
    	tbWine.setMaxSize(250,250);
    	typesOfDrink.add(tbWine,0,0);
    	
    	tbWine.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('wine.jpeg');");
 				/*borderPane.setCenter(drinks.viewWineStock());
 				chkRect()*/
 				borderPane.setCenter(addStock(mapCategoryIdName.get("Wine"),"Wine"));
 				animation.animate(tbWine,0,0);
 			}
 		});
    	
    	final ToggleButton tbVodka= new ToggleButton("Vodka");
    	tbVodka.setToggleGroup(groupDrink);
    	tbVodka.setId("drinkName");
    	tbVodka.setMaxSize(250,250);
    	typesOfDrink.add(tbVodka,0,1);
    	tbVodka.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('vodka.jpg');");
 				//borderPane.setCenter(drinks.viewVodkaStock());
 				borderPane.setCenter(addStock(mapCategoryIdName.get("Vodka"),"Vodka"));
 				animation.animate(tbVodka,0,1);
 			}
 		});
    	
    	final ToggleButton tbBeer= new ToggleButton("Beer");
    	tbBeer.setToggleGroup(groupDrink);
    	tbBeer.setId("drinkName");
    	tbBeer.setMaxSize(250,250);
    	typesOfDrink.add(tbBeer,0,2);
    	tbBeer.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('beer2.jpg');");
 				borderPane.setCenter(addStock(mapCategoryIdName.get("Beer"),"Beer"));
 				animation.animate(tbBeer,0,2);
 			}
 		});

    	final ToggleButton tbWisky= new ToggleButton("Whisky");
    	tbWisky.setToggleGroup(groupDrink);
    	tbWisky.setId("drinkName");
    	tbWisky.setMaxSize(250,250);
    	typesOfDrink.add(tbWisky,0,3);
    	tbWisky.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('whisky.jpg');");
 				borderPane.setCenter(addStock(mapCategoryIdName.get("Whisky"),"Whisky"));
 				animation.animate(tbWisky,0,3);
 			}
 		});
    	
    	final ToggleButton tbRum= new ToggleButton("Rum");
    	tbRum.setToggleGroup(groupDrink);
    	tbRum.setId("drinkName");
    	tbRum.setMaxSize(250,250);
    	typesOfDrink.add(tbRum,0,4);
    	tbRum.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('rum2.jpg');");
 				borderPane.setCenter(addStock(mapCategoryIdName.get("Rum"),"Rum"));
 				animation.animate(tbRum,0,4);
 			}
 		});
    	
    	final ToggleButton tbScotch= new ToggleButton("Scotch");
    	tbScotch.setToggleGroup(groupDrink);
    	tbScotch.setId("drinkName");
    	tbScotch.setMaxSize(250,250);
    	typesOfDrink.add(tbScotch,0,5);
    	tbScotch.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('Scotch.jpg');");
 				borderPane.setCenter(addStock(mapCategoryIdName.get("Scotch"),"Scotch"));
 				animation.animate(tbScotch,0,5);
 			}
 		});
    	final ToggleButton tbOther= new ToggleButton("Other");
    	tbOther.setToggleGroup(groupDrink);
    	tbOther.setId("drinkName");
    	tbOther.setMaxSize(250,250);
    	typesOfDrink.add(tbOther,0,6);
    	tbOther.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(addStock(mapCategoryIdName.get("Others"),"Others"));
 				animation.animate(tbOther,0,6);
 			}
 		});
       // typesOfDrink.getChildren().addAll(tbWine,tbVodka,tbBeer,tbWisky,tbRum,tbScotch,tbOther);
    	borderPane.setLeft(typesOfDrink);
    	return borderPane;
	}	
	
	public StackPane addStock(String categoryId, String categoryName)
	{
		stack = new StackPane();
		
		GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setPadding(new Insets(30));
		
        final ObservableList<ItemVO> dataTable;
		
		Rectangle roundRect = RectangleBuilder.create()
	    .x(50)
	    .y(50)
	    .width(Screen.getPrimary().getVisualBounds().getWidth()-160)
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
			dataTable.addAll(stockDetailsService.viewStock(categoryId));
			
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
		 	
		 	
		 	final TableView<ItemVO> table1 = new TableView<ItemVO>();
		 	table1.setEditable(true);
		 	table1.setMaxSize(400, 300);
		 	table1.setStyle("-fx-background-color: transparent;");
		 	
		 	TableView<ItemVO> table2 = new TableView<ItemVO>();
		 	table2.setEditable(true);
		 	table2.setMaxSize(400, 300);
		 	table2.setStyle("-fx-background-color: transparent;");
		 	
		 	final Callback<TableColumn, TableCell> cellFactory =
		              new Callback<TableColumn, TableCell>() {
		                  public TableCell call(TableColumn p) {
		                      return new EditingCell();
		                  }
		              };
		              
		 	TableColumn<ItemVO,String> itemName = new TableColumn<ItemVO,String> ("Item");
		 	itemName.setMinWidth(200);
		 	itemName.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, String>("itemName"));
		 	
		 	final TableColumn  quantity = new TableColumn ("Quantity");
		 	quantity.setMinWidth(200);
		 	quantity.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, Integer>("quantity"));
		 	quantity.setEditable(true);
		 	
		 	quantity.setCellFactory(cellFactory);
		 	quantity.setOnEditCommit(
		              new EventHandler<TableColumn.CellEditEvent<ItemVO, Integer>>() {
		                  public void handle(TableColumn.CellEditEvent<ItemVO, Integer> t) {
		                      ((ItemVO)t.getTableView().getItems().get(
		                              t.getTablePosition().getRow())).setQuantity(t.getNewValue());
		                  }
		              });
		 	table1.setItems(dataTable);
		 	table1.getColumns().addAll(itemName, quantity);
		 	

		 	table2.setItems(dataTable);
		 	table2.getColumns().addAll(itemName, quantity);
		 	
			
			
			
			Button add = new Button("Add To Stock");
			
			add.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) 
				{
					
					//cellFactory.
				}
			});
			
			
			grid.add(table1,0,12);
			grid.add(table2,1,12);
			grid.add(add,1,15);
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
	          textField.selectAll();
	          Platform.runLater(new Runnable() {
	              @Override
	              public void run() {
	                  textField.requestFocus();
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
	          textField.setText(getString());
	          textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
	          
	          textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

	        	    @Override
	        	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
	        	        if (!arg2) {
	        	            commitEdit(Integer.parseInt(textField.getText()));
	        	        }
	        	    }
	        	});
	          
	          textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
	              @Override public void handle(KeyEvent t) {
	                  if (t.getCode() == KeyCode.ENTER) {
	                      commitEdit(Integer.parseInt(textField.getText()));
	                  } else if (t.getCode() == KeyCode.ESCAPE) {
	                      cancelEdit();
	                  }
	              }
	          });
	      }

	     
	      private String getString() {
	          return getItem() == null ? "" : getItem().toString();
	      }
	  }
	 
	
}
