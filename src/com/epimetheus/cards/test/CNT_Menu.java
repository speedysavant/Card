package com.epimetheus.cards.test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class CNT_Menu implements ControlledScreen {

	@FXML protected AnchorPane menu;
	protected Image backimage;
	protected ImageView imv;
	protected VBox vBox;
	protected Text title;
	protected DropShadow titleglow;
	
	protected ScreensController controller;

	public CNT_Menu(){
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Build Title //
		title = new Text("Epimetheus");
		title.setCache(true);
		title.setCacheHint(CacheHint.SPEED);
		title.setId("menu-title");
		titleglow = new DropShadow();
		titleglow.setRadius(10.0);
		titleglow.setColor(Color.WHITE);
		InnerShadow is = new InnerShadow();
		is.setColor(Color.BLACK);
		is.setRadius(5.0);
		titleglow.setInput(is);
		title.setEffect(titleglow);
		AnchorPane.setTopAnchor(title, 50d);
		AnchorPane.setLeftAnchor(title, 200d);
		
		// Build Buttons //
		vBox = new VBox();
		vBox.setCache(true);
		vBox.setCacheHint(CacheHint.SPEED);
		vBox.setSpacing(20);
		MenuChoiceText newGame = new MenuChoiceText("Start");
		newGame.setOnMouseClicked(event->{
			Theatre.setupWorld(null);
			controller.setScreen(Screens.GAME_MAIN);
		});
		MenuChoiceText loadGame = new MenuChoiceText("Load");
		loadGame.setOnMouseClicked(event->{controller.setScreen(Screens.MENU);});
		
		MenuChoiceText options = new MenuChoiceText("Options");
		options.setOnMouseClicked(event->{controller.setScreen(Screens.OPTIONS);});
		
		vBox.getChildren().addAll(newGame, loadGame, options);
		AnchorPane.setTopAnchor(vBox, 300d);
		AnchorPane.setRightAnchor(vBox, 500d);
		
		// Build Backgrounds //
		backimage = new Image("resources/temp-menu.jpg");
		imv = new ImageView(backimage);
		imv.setCache(true);
		imv.setCacheHint(CacheHint.SPEED);
		// resize stuff here?
		
		// Add'em'all to the page. //
		menu.getChildren().addAll(title, vBox, imv);
		imv.toBack();
	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		this.controller=screenPage;
	}

	
	public void refresh(){
		
	}
}
