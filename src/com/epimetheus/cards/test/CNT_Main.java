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
		TestEvent event = new TestEvent();
		Tabletop<TestEvent> table = new Tabletop<TestEvent>(event);
		cardtable.getChildren().addAll(table);
		AnchorPane.setTopAnchor(table, 5.0);
		AnchorPane.setBottomAnchor(table, 5.0);
		AnchorPane.setLeftAnchor(table, 5.0);
		AnchorPane.setRightAnchor(table, 5.0);
	}
	
}
