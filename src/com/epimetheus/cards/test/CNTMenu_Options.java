package com.epimetheus.cards.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


public class CNTMenu_Options implements ControlledScreen {

	@FXML protected AnchorPane root;
	@FXML protected HBox linkbox;
	protected ArrayList<HBox> boxes;
	
	protected ScreensController controller;

	public CNTMenu_Options(){
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MenuChoiceText ok = new MenuChoiceText("OK");
		ok.setOnMouseClicked(event->{controller.setScreen(Screens.MENU);});
		MenuChoiceText cancel = new MenuChoiceText("Cancel");
		cancel.setOnMouseClicked(event->{controller.setScreen(Screens.MENU);});
		linkbox.getChildren().addAll(ok, cancel);
	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		this.controller=screenPage;
	}

	
	public void refresh(){
		
	}
}
