package com.fnz.UI;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.w3c.dom.ls.LSInput;

import com.fnz.VO.CategoryTypeVO;
import com.fnz.VO.CategoryVO;
import com.fnz.VO.ItemTypeVO;
import com.fnz.VO.ItemVO;
import com.fnz.dao.UtiliesDAO;
import com.fnz.service.StockDetailsService;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Screen;
import javafx.util.Callback;

public class StockDetails 
{
	StockDetailsService stockDetailsService;
	Animation animation;
	public StackPane stack;
	public StockDetails()
	{
		stockDetailsService = new StockDetailsService();
		animation = new Animation();
	}
	
	
	public BorderPane viewStockDrinkList(final ObservableList<CategoryVO> listCategory)
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
 				borderPane.setCenter(viewStock(listCategory.get(0).getCategotyId(),listCategory.get(0).getCategoryName()));
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
 				borderPane.setCenter(viewStock(listCategory.get(1).getCategotyId(),listCategory.get(1).getCategoryName()));
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
 				borderPane.setCenter(viewStock(listCategory.get(2).getCategotyId(),listCategory.get(2).getCategoryName()));
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
 				borderPane.setCenter(viewStock(listCategory.get(3).getCategotyId(),listCategory.get(3).getCategoryName()));
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
 				borderPane.setCenter(viewStock(listCategory.get(4).getCategotyId(),listCategory.get(5).getCategoryName()));
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
 				borderPane.setCenter(viewStock(listCategory.get(5).getCategotyId(),listCategory.get(5).getCategoryName()));
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
 			//	borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(listCategory.get(6).getCategotyId(),listCategory.get(6).getCategoryName()));
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
 			//	borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(listCategory.get(7).getCategotyId(),listCategory.get(7).getCategoryName()));
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
 			//	borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(listCategory.get(8).getCategotyId(),listCategory.get(8).getCategoryName()));
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
 			//	borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(listCategory.get(9).getCategotyId(),listCategory.get(9).getCategoryName()));
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
 			//	borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(listCategory.get(10).getCategotyId(),listCategory.get(10).getCategoryName()));
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
 			//	borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(listCategory.get(11).getCategotyId(),listCategory.get(11).getCategoryName()));
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
 			//	borderPane.setStyle("-fx-background-image: url('othertype2.jpg');");
 				borderPane.setCenter(viewStock(listCategory.get(12).getCategotyId(),listCategory.get(12).getCategoryName()));
 				animation.animateLeft(bt13,0,4);
 			}
 		});
       // typesOfDrink.getChildren().addAll(tbWine,tbVodka,tbBeer,tbWisky,tbRum,tbScotch,tbOther);
    	borderPane.setLeft(typesOfDrink);
    	borderPane.setRight(typesOfDrink2);
    	bt2.fire();
    	return borderPane;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public StackPane viewStock(String categoryId, String categoryName)
	{
		stack = new StackPane();
		
		GridPane grid = new GridPane();
	
        grid.setVgap(8);
        grid.setPadding(new Insets(30));
		final ObservableList<ItemVO> dataTable;
		final ObservableList<ItemVO> dataTable1;
		final ObservableList<ItemVO> dataTable2;
		ObservableList<CategoryTypeVO> typeList;
		
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
			typeList = UtiliesDAO.getUtiliesDAO().fetchTypes(categoryId);
			
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
		 	
		 	System.out.println(Screen.getPrimary().getVisualBounds().getWidth());
		 	System.out.println(Screen.getPrimary().getVisualBounds().getHeight());
		 	final TableView<ItemVO> table1 = new TableView<ItemVO>();
		 	table1.setEditable(false);
			table1.setMaxSize(roundRect.getWidth()*0.41, roundRect.getHeight()*0.519);//400,300
		 	table1.setMinSize(roundRect.getWidth()*0.35, roundRect.getHeight()*0.519);//400,300
		 	
		 	table1.getSelectionModel().setCellSelectionEnabled(false);
		 	
		 	table1.setStyle("-fx-background-color: transparent;");
		 	
		 	
		 	
		 	TableColumn<ItemVO,String> itemName = new TableColumn<ItemVO,String> ("Item");
		 	itemName.setMaxWidth(roundRect.getWidth()*0.3);
		 	itemName.setMinWidth(roundRect.getWidth()*0.3);//200
		 	itemName.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, String>("itemName"));
		 	
		 	TableColumn<ItemVO, Integer>  quantity = new TableColumn<ItemVO, Integer> ("Quantity");
		 	quantity.setMinWidth(roundRect.getWidth()*0.107);//200
		 
		 	/*quantity.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, Integer>("quantity"));*/
		 	
		 	
		 	
		 	for (final CategoryTypeVO type : typeList)
		 	{
		 		  TableColumn<ItemVO, Integer> col = new TableColumn<ItemVO, Integer>(type.getTypeName());
		 		  col.setMinWidth(roundRect.getWidth()*0.107);//100
		 		  col.setResizable(false);
		 		  
		 		  col.setCellValueFactory(new Callback<CellDataFeatures<ItemVO,Integer>, ObservableValue<Integer>>() {
		 		    @Override
		 		    public ObservableValue<Integer> call(CellDataFeatures<ItemVO,Integer> item) 
		 		    {
		 		    	ItemVO itemVO = item.getValue();
		 		    	if (itemVO == null) 
		 		    	{
		 		    	  return null ;
		 		    	// or perhaps
		 		    	// return new ReadOnlyObjectWrapper<Integer>(null);
		 		    	} 
		 		    	else
		 		    	{
			 		    	ObservableMap<String,ItemTypeVO> itemTypesMap = FXCollections.observableHashMap();
			 		    	itemTypesMap = item.getValue().getListType();
			 		    
			 		    	return new ReadOnlyObjectWrapper<Integer>(itemTypesMap.get(type.getTypeId()).getQuantity());
			 		    	
		 		    	}

		 		    }

		 		  });
		 		  col.setCellFactory(new Callback<TableColumn<ItemVO,Integer>, TableCell<ItemVO,Integer>>() {
					
					@Override
					public TableCell<ItemVO, Integer> call(TableColumn<ItemVO, Integer> paramP) {
						// TODO Auto-generated method stub
						return new TableCell<ItemVO, Integer>(){
							@Override
							public void updateItem(Integer item, boolean empty)
							{
								super.updateItem(item, empty);
								if(!isEmpty())
								{
									setText(item.toString());
									if(item<=5)
									{
										setTextFill(Color.MAROON);
									}
								}
									
							}
						};

					}
				});
		 		  quantity.getColumns().add(col);
		 		}

		 	
		 	
		 	
		 	
		 	table1.setItems(dataTable1);
		 	final TableColumn[] columns1 = {itemName, quantity};
		 	table1.getColumns().addAll(columns1);
		 	table1.getColumns().addListener(new ListChangeListener() {
		        public boolean suspended;

		        @Override
		        public void onChanged(Change change) {
		            change.next();
		            if (change.wasReplaced() && !suspended) {
		                this.suspended = true;
		                table1.getColumns().setAll(columns1);
		                this.suspended = false;
		            }
		        }
		    });
		 	
		 	final TableView<ItemVO> table2 = new TableView<ItemVO>();
		 	table2.setEditable(false);
		 	
		 	table2.setMaxSize(roundRect.getWidth()*0.41, roundRect.getHeight()*0.519);
		 	table2.setMinSize(roundRect.getWidth()*0.35, roundRect.getHeight()*0.519);//400,300
		 	table2.setStyle("-fx-background-color: transparent;");
		 	
		 	TableColumn<ItemVO,String> itemName2 = new TableColumn<ItemVO,String> ("Item");
		 	itemName2.setMinWidth(roundRect.getWidth()*0.3);//200
		 	
		 	itemName2.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, String>("itemName"));
		 	
		 	TableColumn<ItemVO, Integer>  quantity2 = new TableColumn<ItemVO, Integer> ("Quantity");
		 	quantity2.setMinWidth(roundRect.getWidth()/9.345);//200
		 	/*quantity.setCellValueFactory(
		 			new PropertyValueFactory<ItemVO, Integer>("quantity"));*/
		 	
		 	
		 	
		 	for (final CategoryTypeVO type : typeList)
		 	{
		 		  TableColumn<ItemVO, Integer> col2 = new TableColumn<ItemVO, Integer>(type.getTypeName());
		 		  col2.setMinWidth(roundRect.getWidth()*0.107);//100
		 		  col2.setResizable(false);
		 		  
		 		  col2.setCellValueFactory(new Callback<CellDataFeatures<ItemVO,Integer>, ObservableValue<Integer>>() {
		 		    @Override
		 		    public ObservableValue<Integer> call(CellDataFeatures<ItemVO,Integer> item) 
		 		    {
		 		    	ItemVO itemVO = item.getValue();
		 		    	if (itemVO == null) 
		 		    	{
		 		    	  return null ;
		 		    	// or perhaps
		 		    	// return new ReadOnlyObjectWrapper<Integer>(null);
		 		    	} 
		 		    	else
		 		    	{
			 		    	ObservableMap<String,ItemTypeVO> itemTypesMap = FXCollections.observableHashMap();
			 		    	itemTypesMap = item.getValue().getListType();
			 		    
			 		    	return new ReadOnlyObjectWrapper<Integer>(itemTypesMap.get(type.getTypeId()).getQuantity());
			 		    	
		 		    	}

		 		    }

		 		  });
		 		 col2.setCellFactory(new Callback<TableColumn<ItemVO,Integer>, TableCell<ItemVO,Integer>>() {
						
						@Override
						public TableCell<ItemVO, Integer> call(TableColumn<ItemVO, Integer> paramP) {
							// TODO Auto-generated method stub
							return new TableCell<ItemVO, Integer>(){
								@Override
								public void updateItem(Integer item, boolean empty)
								{
									super.updateItem(item, empty);
									if(item!=null)
									{
										setText(item.toString());
										if(item<=5)
										{
											setTextFill(Color.MAROON);
										}
									}
										
								}
							};

						}
					});
		 		  quantity2.getColumns().add(col2);
		 		}
		 	
		 	
		 	table2.setItems(dataTable2);
		 	final TableColumn[] columns2 = {itemName2, quantity2};
		 	table2.getColumns().addAll(columns2);
		 	table2.getColumns().addListener(new ListChangeListener() {
		        public boolean suspended;

		        @Override
		        public void onChanged(Change change) {
		            change.next();
		            if (change.wasReplaced() && !suspended) {
		                this.suspended = true;
		                table2.getColumns().setAll(columns2);
		                this.suspended = false;
		            }
		        }
		    });
		 	
			
			grid.add(table1,0,12);
			grid.add(table2,1,12);
			grid.setAlignment(Pos.TOP_CENTER);
			StackPane.setMargin(grid, new Insets(35,0,0,0));
			
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
	
	
}
