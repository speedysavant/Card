package com.epimetheus.cards.test;

import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

abstract class Graph_Abstract extends AnchorPane implements GamePane {
	
	protected Theatre theatre;
	protected Image backimage;
	protected ImageView imv;
	
	public Graph_Abstract(){
		super();
		// Set Properties //
		setCache(true);
		setCacheHint(CacheHint.SPEED);
		setStyle("-fx-background-color: radial-gradient(radius 100%, gray, black)");
	}
	
	
	
	public void setTheatre(Theatre theatre){
		this.theatre = theatre;
	}
}