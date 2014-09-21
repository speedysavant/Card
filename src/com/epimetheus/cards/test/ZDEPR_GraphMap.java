package com.epimetheus.cards.test;

import java.util.HashMap;

import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class ZDEPR_GraphMap extends HashMap<String, Pane> {
	private static final long serialVersionUID = 6556717795537146887L;
	private Theatre theatre;
	
	public ZDEPR_GraphMap(){
		super();
//		theatre = Theatre.getTheatre();
	}
	
	@Override public Pane put(String key, Pane value){
		Pane pane = super.put(key, value);
		value.setCache(true);
		value.setCacheHint(CacheHint.SPEED);
		value.setId(key);
		if (value instanceof GamePane){
			((GamePane) value).setTheatre(theatre);
			((GamePane) value).refresh();
		}
		return pane;
	}
	
	public void enstage(Group root, String name){
		
		// Swap the graphs on the stage //
		destage(root);
		try {
			Pane newPane = get(name);
			root.getChildren().add(newPane);
			
			// now bind it to the stage and size it properly //
			Scene scene = root.getScene();
			scene.widthProperty().addListener(listener->{
				newPane.setPrefWidth(scene.widthProperty().get());
			});
			scene.heightProperty().addListener(listener->{
				newPane.setPrefHeight(scene.heightProperty().get());
			});
			newPane.setPrefHeight(scene.heightProperty().get());
			newPane.setPrefWidth(scene.widthProperty().get());
			
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		
	}
	
	private void destage(Group root){
		// Remove the current graph
		// ObservableList<javafx.scene.Node> old = root.getChildren();
		root.getChildren().clear();
		
		// Now unbind the cleared graph
		// To do this we'll need to have a handle on the lambda originally 
		// assigned - a lot of work! Avoid if you can. Come back here if
		// you need to optimize.
	}
}