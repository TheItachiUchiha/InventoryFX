package com.fnz.UI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animation 
{
	
	private Node listBtn;
	private int listBtnX;
	private int listBtnY;
	 public void unanimate(Node obj,int x, int y) 
	    {
	    	//System.out.println(obj);
	    	if (listBtn==null){
	    		
	    	}
	    	else{
	    	
	    	final Timeline timeline = new Timeline();
	    	timeline.getKeyFrames().addAll(
	                new KeyFrame(Duration.ZERO, // set start position at 0
	                new KeyValue(listBtn.translateXProperty(), listBtnX),
	                new KeyValue(listBtn.translateYProperty(), listBtnY)),
	                new KeyFrame(new Duration(150), // set end position at 10s
	                new KeyValue(listBtn.translateXProperty(),listBtnX),
	                new KeyValue(listBtn.translateYProperty(), listBtnY)));
	    	
				timeline.play();
	    	}
	    	listBtn=obj;
	    	listBtnX=x;
	    	listBtnY=y;
	    }
	    
	    public void animateSettings(Node obj,int x, int y) 
	    {
	    	
	    	if (listBtn!=obj){
	    	
	    	final Timeline timeline = new Timeline();
	    	timeline.getKeyFrames().addAll(
	                new KeyFrame(Duration.ZERO, // set start position at 0
	                new KeyValue(obj.translateXProperty(), x),
	                new KeyValue(obj.translateYProperty(), y)),
	                new KeyFrame(new Duration(100), // set end position at 10s
	                new KeyValue(obj.translateXProperty(),x+25),
	                new KeyValue(obj.translateYProperty(), y)));
	    	
				timeline.play();
	    	}
	    }
	    public void animate(Node obj,int x, int y) 
	    {
	    	if (listBtn!=obj){
	    		unanimate(obj,x,y);
	    	
	    	
	    	
	    	final Timeline timeline = new Timeline();
	    	timeline.getKeyFrames().addAll(
	                new KeyFrame(Duration.ZERO, // set start position at 0
	                new KeyValue(obj.translateXProperty(), x),
	                new KeyValue(obj.translateYProperty(), y)),
	                new KeyFrame(new Duration(100), // set end position at 10s
	                new KeyValue(obj.translateXProperty(),x+25),
	                new KeyValue(obj.translateYProperty(), y)));
	    	
				timeline.play();
	    	}
	    	/*else{
	    		unanimate(obj,x,y);
	    	}*/
	    }
}
