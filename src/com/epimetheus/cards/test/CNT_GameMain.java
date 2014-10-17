package com.epimetheus.cards.test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class CNT_GameMain implements ControlledScreen {

	@FXML protected AnchorPane root;
	@FXML protected MenuBar menubar;
	@FXML protected Menu menu;
	Stage stage;

	protected ScreensController controller;
	protected TokenManager tm = TokenManager.getManager();
	
	public CNT_GameMain(){
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		this.controller=screenPage;
	}

	public void refresh(){
		presentEvent(new GameEvent());
	}
	
	protected void presentEvent(GameEvent event){
		AnchorPane eventroot;
		
		try {
			FXMLLoader myLoader = new
					FXMLLoader(getClass().getResource("FXPane_Event.fxml"));
			eventroot = (AnchorPane) myLoader.load();
			ControlledScreen eventController =
					((ControlledScreen) myLoader.getController());
			if (eventController instanceof CNTPane_Event){
				((CNTPane_Event)eventController).setEvent(event);
			}
			
			eventroot.setTranslateX(root.prefWidthProperty().get());
			eventroot.setTranslateY(root.prefHeightProperty().get()*0.2);
			eventroot.prefWidthProperty().bind(root.prefWidthProperty().multiply(0.4));
			eventroot.prefHeightProperty().bind(root.prefHeightProperty().multiply(0.8));
			
			if (!root.getChildren().contains(eventroot))
				root.getChildren().add(eventroot);
			
			TranslateTransition trans = new TranslateTransition(
					new Duration(1250), eventroot);
			trans.setToX(root.prefWidthProperty().get()/2 );
				//	+ (table.prefWidthProperty().get())/4);
			trans.setToY(root.prefHeightProperty().get()/2 
					- (eventroot.prefHeightProperty().get()/2));
			trans.setCycleCount(1);
			trans.setAutoReverse(false);
			trans.play();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
