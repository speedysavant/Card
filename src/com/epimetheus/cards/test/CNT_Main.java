package com.epimetheus.cards.test;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.epimetheus.cards.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CNT_Main implements ControlledScreen {

	@FXML protected AnchorPane cardtable;
	@FXML protected AnchorPane left;
	@FXML protected AnchorPane world;
	@FXML protected ImageView brassbutton;
	@FXML protected TextArea messageArea;
	protected Image image;
	protected ImageView imv;
	
	public CNT_Main(){
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		image = new Image("resources/fjord.jpg");
		imv = new ImageView(image);
		imv.setFitHeight(1000);
		imv.setFitWidth(1600);
		world.getChildren().add(imv);
		imv.toBack();
		buildLeft();
		
	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		
	}

	public void buildLeft(){
		
	}
	
	public void addMessage(String message){
		messageArea.appendText(message + "\n");
	}
	private void addCards(){
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
