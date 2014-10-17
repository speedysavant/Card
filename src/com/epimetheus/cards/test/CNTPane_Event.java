package com.epimetheus.cards.test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;


public class CNTPane_Event implements ControlledScreen {

	@FXML protected AnchorPane root;
	@FXML protected Rectangle back;
	@FXML protected Label title;
	@FXML protected ListView<Choice> choicelist;
	@FXML protected ScrollPane textscroll;
	@FXML protected AnchorPane textpane;
	@FXML protected Label text;
	
	// Game Events //
	protected GameEvent event;
	
	protected ScreensController controller;

	public CNTPane_Event() {
		
	}
	
	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		root.setId("event-pane");
		text.setId("event-pane");
		title.setId("event-pane-title");
		
		title.setText("NULL TITLE");
		text.setText("NULL.");
		
		textscroll.setFitToHeight(true);
		textscroll.setFitToWidth(true);
		back.heightProperty().bind(root.heightProperty());
		back.widthProperty().bind(root.widthProperty());
	}
	
	@Override
	public void setScreenParent(ScreensController screenPage) {
		this.controller=screenPage;
	}

	public void setEvent(GameEvent event){
		this.event = event;
		title.setText(event.getTitle());
		text.setText(event.getText());
		choicelist.setItems(event.getChoices());
	}
	
	public void refresh(){
		
	}
}