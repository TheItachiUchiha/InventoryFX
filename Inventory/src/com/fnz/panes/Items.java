package com.fnz.panes;






import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


public class Items {

	
	
	
	@SuppressWarnings("unchecked")
	
	
	
	public GridPane viewBeerStock()
	{
 		
 				
		GridPane grid = new GridPane();
		grid.setHgap(10);
     /*   grid.setVgap(8);*/
       grid.setPadding(new Insets(10));
        
 	
        try
        {
        	
    
		 	final Label label = new Label("Beer varities");
		 	label.setFont(new Font("Arial", 20));
		 	grid.add(label,50,0);
		 	
		 
		 /*
		  * 
		  * 
		  * Table code to get Beer details here
		  * 
		  * 
		  */
		
		 	
		 	
		 	grid.setAlignment(Pos.TOP_LEFT);
		 	
		 //	grid.add(table,1,3);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	 	
	 	return grid;
	}
	
	
	public GridPane viewWineStock()
	{
 		
 				
		GridPane grid = new GridPane();
		grid.setHgap(10);
     /*   grid.setVgap(8);*/
       grid.setPadding(new Insets(10));
        
 	
        try
        {
        	
    
		 	final Label label = new Label("Wine varities");
		 	label.setFont(new Font("Arial", 20));
		 	grid.add(label,50,0);
		 	
		 
		 /*
		  * 
		  * 
		  * Table code to get wine details here
		  * 
		  * 
		  */
		
		 	
		 	
		 	grid.setAlignment(Pos.TOP_LEFT);
		 	
		 //	grid.add(table,1,3);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	 	
	 	return grid;
	}
	
	
	public GridPane viewRumStock()
	{
 		
 				
		GridPane grid = new GridPane();
		grid.setHgap(10);
     /*   grid.setVgap(8);*/
       grid.setPadding(new Insets(10));
        
 	
        try
        {
        	
    
		 	final Label label = new Label("Rum varities");
		 	label.setFont(new Font("Arial", 20));
		 	grid.add(label,50,0);
		 	
		 
		 /*
		  * 
		  * 
		  * Table code to get rum details here
		  * 
		  * 
		  */
		
		 	
		 	
		 	grid.setAlignment(Pos.TOP_LEFT);
		 	
		 //	grid.add(table,1,3);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	 	
	 	return grid;
	}
	
	public GridPane viewScotchStock()
	{
 		
 				
		GridPane grid = new GridPane();
		grid.setHgap(10);
     /*   grid.setVgap(8);*/
       grid.setPadding(new Insets(10));
        
 	
        try
        {
        	
    
		 	final Label label = new Label("Scotch varities");
		 	label.setFont(new Font("Arial", 20));
		 	grid.add(label,50,0);
		 	
		 
		 /*
		  * 
		  * 
		  * Table code to get scotch details here
		  * 
		  * 
		  */
		
		 	
		 	
		 	grid.setAlignment(Pos.TOP_LEFT);
		 	
		 //	grid.add(table,1,3);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	 	
	 	return grid;
	}
	public GridPane viewOtherStock()
	{
 		
 				
		GridPane grid = new GridPane();
		grid.setHgap(10);
     /*   grid.setVgap(8);*/
       grid.setPadding(new Insets(10));
        
 	
        try
        {
        	
    
		 	final Label label = new Label("Other varities");
		 	label.setFont(new Font("Arial", 20));
		 	grid.add(label,50,0);
		 	
		 
		 /*
		  * 
		  * 
		  * Table code to get other details here
		  * 
		  * 
		  */
		
		 	
		 	
		 	grid.setAlignment(Pos.TOP_LEFT);
		 	
		 //	grid.add(table,1,3);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	 	
	 	return grid;
	}
	
	public GridPane viewVodkaStock()
	{
 		
 				
		GridPane grid = new GridPane();
		grid.setHgap(10);
     /*   grid.setVgap(8);*/
       grid.setPadding(new Insets(10));
        
 	
        try
        {
        	
    
		 	final Label label = new Label("Vodka varities");
		 	label.setFont(new Font("Arial", 20));
		 	grid.add(label,50,0);
		 	
		 
		 /*
		  * 
		  * 
		  * Table code to get vodka details here
		  * 
		  * 
		  */
		
		 	
		 	
		 	grid.setAlignment(Pos.TOP_LEFT);
		 	
		 //	grid.add(table,1,3);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	 	
	 	return grid;
	}
	
	public GridPane viewWhiskyStock()
	{
 		
 				
		GridPane grid = new GridPane();
		grid.setHgap(10);
     /*   grid.setVgap(8);*/
       grid.setPadding(new Insets(10));
        
 	
        try
        {
        	
    
		 	final Label label = new Label("Whisky varities");
		 	label.setFont(new Font("Arial", 20));
		 	grid.add(label,50,0);
		 	
		 
		 /*
		  * 
		  * 
		  * Table code to get whisky details here
		  * 
		  * 
		  */
		
		 	
		 	
		 	grid.setAlignment(Pos.TOP_LEFT);
		 	
		 //	grid.add(table,1,3);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	 	
	 	return grid;
	}
	
	
}
