package com.epimetheus.cards.test;

import java.net.URL;
import java.util.ResourceBundle;

import com.epimetheus.cards.*;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class CNT_Main implements ControlledScreen {

	@FXML protected AnchorPane cardtable;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addCards();
	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		// TODO Auto-generated method stub
		
	}

	private void addCards(){
		Hand hand = new Hand();
		hand.setPrefWidth(cardtable.widthProperty().get());
		hand.setPrefHeight(cardtable.heightProperty().get());
		cardtable.getChildren().add(hand);
		AnchorPane.setTopAnchor(hand, 5.0);
		AnchorPane.setBottomAnchor(hand, 5.0);
		AnchorPane.setLeftAnchor(hand, 5.0);
		AnchorPane.setRightAnchor(hand, 5.0);
	}
	
}
