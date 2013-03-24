package com.fnz.security;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;

import com.fnz.Validation.*;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class ModalDialog {

	GridPane gridSecurity;
	Validation validate;
	TextField tSecurityNumber;
	EncryptMacWithKey eMac;
	BufferedReader br = null;
	BufferedReader brTemp = null;
	
	
	  public void ModalSecurity(final Stage stg,String title, String message) {
		 
		  
		  File fileKeylastentered=new File("keyentered.txt");
			if(fileKeylastentered.exists())
			{
				eMac= new EncryptMacWithKey();
  				  eMac.MakeKeyFile();
  				  
  				try {
					//br = new BufferedReader(new FileReader("key.txt"));
					brTemp = new BufferedReader(new FileReader("keyentered.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sCurrentLine;
				String sCurrentLine2;
				try {
					while ((sCurrentLine = brTemp.readLine()) != null) {
						//System.out.println(sCurrentLine);
						while ((sCurrentLine2 = br.readLine()) != null) {
							//System.out.println(sCurrentLine);
							if (sCurrentLine2.contains(sCurrentLine)){
								
								 return;
							}
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  				  
			}
		  
		  
	        final  Stage stage = new Stage(StageStyle.UTILITY);           
	            //Initialize the Stage with type of modal
	       
	            stage.initModality(Modality.APPLICATION_MODAL);
	            stage.setResizable(false);
	            //Set the owner of the Stage 
	            stage.initOwner(stg);
	            stage.setTitle(title);
	            //stage.initStyle(StageStyle.UNDECORATED);
	          
	           
	             Group root = new Group();
	             Scene scene = new Scene(root);
	             scene.getStylesheets().add(
	                     this.getClass().getClassLoader().getResource("com/fnz/styles/Tab.css").toString());
	            
	             
	           
			
	            
	          
	          
	            
	           
	            gridSecurity = new GridPane();
	            gridSecurity.setVgap(10);
	            gridSecurity.add(new Label("Please enter product key : "), 1, 1);
	          
	          //  final TextField tSecurityNumber = new TextField();
	            
	            tSecurityNumber = new TextField();
	            gridSecurity.add(tSecurityNumber,2,1);
	            HBox hbutton= new HBox();
	            hbutton.setSpacing(10);
	            hbutton.setAlignment(Pos.CENTER);
	           
	            
	            Button btOk = new Button("OK");
	            Button btCancel = new Button("Cancel");
	            btOk.setId("buttonall");
	            btCancel.setId("buttonall");
	            btOk.setPrefSize(70, 30);
	            btCancel.setPrefSize(70, 30);
	            hbutton.getChildren().addAll(btOk,btCancel);
	           
	            gridSecurity.add(hbutton,1,3,2,3);
	            
	            final HBox hLabelMsg = new HBox();
	            
	             final Label lMsg= new Label("");
	             
	             hLabelMsg.setAlignment(Pos.CENTER);
	             hLabelMsg.getChildren().add(lMsg);
	             gridSecurity.add(hLabelMsg,1,5,2,5);
	             
	             final Text text = new Text(message);
	             text.setFont(Font.font("Courier New", 18));
	           //  text.setEffect(dropShadow);
	             text.setX(600); 
	             
	          
	             
	             BorderPane borderPane = new BorderPane();
	             borderPane.setPadding(new Insets(20,20,20,20));
	             
	             final HBox btnHBox = new HBox(15);
	             btnHBox.setPadding(new Insets(20,5,20,5));
	             btnHBox.setAlignment(Pos.CENTER);
	             
	             
	             
	             btOk.setOnAction(new EventHandler<ActionEvent>() {
	      			
	      			@Override
	      			public void handle(ActionEvent e) 
	      			{
	      				
	      				System.out.println("ok clicked");
	      				
	      				if (tSecurityNumber.getText().isEmpty()){
	      					System.out.println("can't be empty");
	      					tSecurityNumber.requestFocus();
	      				}
	      					else{
	      						
	      						
	      					
	      						try {
	      							eMac= new EncryptMacWithKey();
	      		  				  eMac.MakeKeyFile();
	      		      				  
	      							String sCurrentLine;
	      							
									br = new BufferedReader(new FileReader("key.txt"));
									while ((sCurrentLine = br.readLine()) != null) {
										//System.out.println(sCurrentLine);
										System.out.println(tSecurityNumber.getText());
										if (sCurrentLine.contains(tSecurityNumber.getText())){
											//System.out.println("fine");
											ModalDialog mtemp= new ModalDialog();
				      						mtemp.ModalRegister(stage, "Kryptcode", "Registered Successfully");
				      						
				      						
				      						FileWriter fstream = new FileWriter("keyentered.txt");
				      					  BufferedWriter out = new BufferedWriter(fstream);
				      					  out.write(tSecurityNumber.getText());
				      					 out.close();
				    						
										}
										else{
										//	System.out.println("chor");
											lMsg.setText("Wrong product key entered");
											lMsg.setTextFill(Color.MAROON);
											tSecurityNumber.requestFocus();
										}
										
									}
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
	      						
	      						
	      					}
	      				
	      				//stage.close();
	      				
	      			}
	      			
	      		});
	             
	             btCancel.setOnAction(new EventHandler<ActionEvent>() {
		      			
		      			@Override
		      			public void handle(ActionEvent e) 
		      			{
		      				
		      				System.out.println("cancel clicked");
		      				stage.close();
		      				stg.close();
		      			}
		      		});
	             stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						
						
						@Override
						public void handle(WindowEvent paramT) {
							// TODO Auto-generated method stub
							
							stg.close();
						}
					});  
	           
	                
	     		//borderPane.setTop(topAnchorPane);
	          //borderPane.setTop(gridSecurity);
	     		borderPane.setCenter(gridSecurity);
	     		borderPane.setBottom(btnHBox);
	     		
	            root.getChildren().add(borderPane);   
	            stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth()/4);
	    	    stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight()/4.5);
	            stage.setScene(scene);        
	            stage.show();
	  }
	     
	           
	  public void ModalRegister(final Stage stg,String title, String message) {
		  stg.close();
	        final  Stage stage = new Stage(StageStyle.UTILITY);       
		  stage.initModality(Modality.APPLICATION_MODAL);
          stage.setResizable(false);
          //Set the owner of the Stage 
          stage.initOwner(stg);
          stage.setTitle(title);
          //stage.initStyle(StageStyle.UNDECORATED);
        
         
           Group root = new Group();
           Scene scene = new Scene(root);
           scene.getStylesheets().add(
                   this.getClass().getClassLoader().getResource("com/fnz/styles/Tab.css").toString());
           BorderPane borderPane = new BorderPane();
           borderPane.setPadding(new Insets(20,0,20,20));
           HBox hMsg = new HBox();
           HBox hButtons = new HBox();
           hMsg.setAlignment(Pos.CENTER);
           GridPane gPane = new GridPane();
           gPane.setVgap(10);
           gPane.add(new Label(message), 1, 1);
           hButtons.setAlignment(Pos.CENTER);
           
           
           Button btConfirm = new Button("OK");
           
           gPane.add(btConfirm, 3, 3);
           btConfirm.setPrefSize(70, 30);
           btConfirm.setId("buttonall");
           
          
           hButtons.getChildren().addAll(gPane);
          hButtons.setAlignment(Pos.CENTER);
         
          hButtons.setSpacing(10);
           
          
          // hButtons.setStyle("-fx-background-color: #336699;");
           borderPane.setTop(hButtons);
           root.getChildren().add(borderPane);   
           stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth()/4);
   	    	stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight()/5);
           stage.setScene(scene);        
           stage.show();
           stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				
				@Override
				public void handle(WindowEvent paramT) {
					// TODO Auto-generated method stub
					
					stage.close();
					stg.close();
				}
			}); 
           btConfirm.setOnAction(new EventHandler<ActionEvent>() {
     			
     			@Override
     			public void handle(ActionEvent e) 
     			{
     				
     				System.out.println("confirm clicked");
     				
     				stage.close();
     				stg.close();
     			}
     		});
	  }
	  
	  
	 
 public void DeleteKeyFile(){
	 File file = new File("key.txt");
	 if (file.exists()) {
		 file.delete();
	 }
 }
	
}
	  
	  
