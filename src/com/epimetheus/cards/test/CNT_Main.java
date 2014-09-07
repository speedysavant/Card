package com.epimetheus.cards.test;

import java.net.URL;
import java.util.ResourceBundle;

import com.epimetheus.cards.*;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class CNT_Main implements ControlledScreen {

	@FXML protected AnchorPane cardtable;
	@FXML protected AnchorPane world;
	protected Image image;
	protected ImageView imv;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		image = new Image("resources/fjord.jpg");
		imv = new ImageView(image);
		imv.setFitHeight(1000);
		imv.setFitWidth(1600);
		//imv.fitWidthProperty().bind(world.widthProperty());
		//imv.fitHeightProperty().bind(world.heightProperty());
		world.getChildren().add(imv);
		imv.toBack();
		addCards();
	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		// TODO Auto-generated method stub
		
	}

	private void addCards(){
		
		// Build the Deck
		Deck deck = new Deck();
		deck.addEvent(new TestEvent(deck, "Event 0", "Event 1 Description"));
		deck.addEvent(new TestEvent(deck, "Event 1", "Event 2 Description"));
		deck.addEvent(new TestEvent(deck, "Event 2", "Event 3 Description"));
		deck.addEvent(new TestEvent(deck, "Event 3", "Event 4 Description"));
		deck.addEvent(new TestEvent(deck, "Event 4", "Event 5 Description"));

		// Setup a Table, add it to the scenegraph
		Tabletop table = new Tabletop(deck);
		deck.setTable(table);
		cardtable.getChildren().addAll(table);
		AnchorPane.setTopAnchor(table, 5.0);
		AnchorPane.setBottomAnchor(table, 5.0);
		AnchorPane.setLeftAnchor(table, 5.0);
		AnchorPane.setRightAnchor(table, 5.0);
	}
	
}
