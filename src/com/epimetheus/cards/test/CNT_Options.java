package com.epimetheus.cards.test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;


public class CNT_Options implements ControlledScreen {

	@FXML protected AnchorPane root;
	protected AnchorPane options;

	protected ScreensController controller;
	CNTMenu_Options optController;

	public CNT_Options(){
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		this.controller=screenPage;
	}

	public void refresh(){
		presentMenu();
	}
	
	private void presentMenu(){
		if (options==null)
		options = (AnchorPane) controller.getNode(Screens.MENU_OPTIONS);
		if (optController==null)
		optController = (CNTMenu_Options) controller.getController(Screens.MENU_OPTIONS);
		
		options.setTranslateX(root.prefWidthProperty().get());
		options.setTranslateY(root.prefHeightProperty().get()/2 
				- (options.prefHeightProperty().get()/2));
		if (!root.getChildren().contains(options))
		root.getChildren().add(options);
		
		TranslateTransition trans = new TranslateTransition(
				new Duration(1250), options);
		trans.setToX(root.prefWidthProperty().get()/2
				- (options.prefWidthProperty().get()/2));
		trans.setToY(root.prefHeightProperty().get()/2 
				- (options.prefHeightProperty().get()/2));
		trans.setCycleCount(1);
		trans.setAutoReverse(false);
		trans.play();
		optController.refresh();
	}
}
