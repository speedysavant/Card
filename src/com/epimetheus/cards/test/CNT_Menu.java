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
		vBox.getChildren().add(buildMenuButton("New Game", Screens.MAIN));
		vBox.getChildren().add(buildMenuButton("Save Game", Screens.MENU));
		vBox.getChildren().add(buildMenuButton("Load Game", Screens.MENU));
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
	
	private Text buildMenuButton(String text, Screens target){
		
		// Design the Label //
		Text menuchoice = new Text(text);
		menuchoice.setCache(true);
		menuchoice.setCacheHint(CacheHint.SPEED);
		DropShadow ds = new DropShadow();
		ds.setRadius(10.0);
		ds.setColor(Color.WHITE);
		InnerShadow is = new InnerShadow();
		is.setColor(Color.BLACK);
		is.setRadius(5.0);
		ds.setInput(is);
		menuchoice.setEffect(ds);
		menuchoice.setPickOnBounds(true);
		menuchoice.setId("menu-choice-label");
		
		// Add the Mouse Action Properties //
		menuchoice.setOnMouseEntered(event->startHover(menuchoice));
		menuchoice.setOnMouseExited(event->stopHover(menuchoice));
		menuchoice.setOnMouseClicked(event->launchTarget(menuchoice, target));
		
		return menuchoice;
	}
	
	private void startHover(Text text){
		try {
			DropShadow ds = (DropShadow) text.getEffect();
			ds.setRadius(20.0);
		} catch (NullPointerException e) {
			
		}
		
	}
	
	private void stopHover(Text text){
		try {
			DropShadow ds = (DropShadow) text.getEffect();
			ds.setRadius(10.0);
		} catch (NullPointerException e) {
			
		}
	}
	
	private void launchTarget(Text text, Screens target){
		try {
			DropShadow ds = (DropShadow) text.getEffect();
			ds.setRadius(30.0);
		} catch (NullPointerException e) {
			
		}

		controller.setScreen(target);
	}
}
