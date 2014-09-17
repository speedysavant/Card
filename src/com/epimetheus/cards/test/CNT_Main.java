package com.epimetheus.cards.test;

import java.net.URL;
import java.util.ResourceBundle;

import com.epimetheus.cards.*;

import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CNT_Main implements ControlledScreen {

	@FXML protected AnchorPane cardtable;
	@FXML protected AnchorPane left;
	@FXML protected AnchorPane world;
	@FXML protected ImageView brassbutton;
	protected Image image;
	protected ImageView imv;
	
	protected ScreensController controller;
	protected TokenManager tm;
	
	public CNT_Main(){
		super();
		tm = TokenManager.getManager();
		World world = new World();
		world.setName("Heofon");
		tm.store("World", world);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		image = new Image("resources/fjord.jpg");
		imv = new ImageView(image);
		imv.setCache(true);
		imv.setCacheHint(CacheHint.SPEED);
		imv.setFitHeight(1000);
		imv.setFitWidth(1600);
		world.getChildren().add(imv);
		imv.toBack();
		buildLeft();
		
	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		this.controller=screenPage;
	}

	public void buildLeft(){
		
	}
	
	private void addCards(){
		// Here's where my problem is - this is too slow, and is blocking.
		// Move this off of the UI thread! At least move to a loading screen!
		Deck deck = new BasicDeckBuilder().getDeck();
		Tabletop table = new Tabletop(deck);
		cardtable.getChildren().addAll(table);
		AnchorPane.setTopAnchor(table, 30.0);
		AnchorPane.setBottomAnchor(table, 30.0);
		AnchorPane.setLeftAnchor(table, 30.0);
		AnchorPane.setRightAnchor(table, 30.0);
	}
	
	public void refresh(){
		addCards();
	}
}
