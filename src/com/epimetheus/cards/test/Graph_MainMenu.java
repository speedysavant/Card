package com.epimetheus.cards.test;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

class Graph_MainMenu extends Graph_Abstract {

	protected VBox vBox;
	protected Text title;
	protected DropShadow titleglow;
	
	public Graph_MainMenu(){
		super();
		
		// Build Background //
		backimage = new Image("resources/temp-menu.jpg");
		imv = new ImageView(backimage);
		imv.setCache(true);
		imv.setCacheHint(CacheHint.SPEED);
		imv.fitHeightProperty().bind(this.prefHeightProperty());
		imv.fitWidthProperty().bind(this.prefWidthProperty());
		
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
		
		// Build Buttons and Button Functions //
		vBox = new VBox();
		vBox.setCache(true);
		vBox.setCacheHint(CacheHint.SPEED);
		vBox.setSpacing(20);
		MenuChoiceText newGame = new MenuChoiceText("New Game");
		newGame.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				System.out.println("New Game!");
			}
		});
		MenuChoiceText saveGame = new MenuChoiceText("Save Game");
		saveGame.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				theatre.enstage("menu-save");
			}
		});
		MenuChoiceText loadGame = new MenuChoiceText("Load Game");
		loadGame.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				final FileChooser fc = new FileChooser();
				File file = fc.showOpenDialog(theatre.getStage());
				System.out.println(file);	
			}
		});
		vBox.getChildren().addAll(newGame, saveGame, loadGame);
		
		AnchorPane.setTopAnchor(vBox, 300d);
		AnchorPane.setRightAnchor(vBox, 100d);
		
		// Add'em'all to the page. //
		getChildren().addAll(title, vBox, imv);
		imv.toBack();
	}

	public void refresh(){
	}
}

class MenuChoiceText extends Text {
	protected DropShadow ds;
	
	public MenuChoiceText(String text){
		super(text);
		
		// Custom Config //
		setCache(true);
		setCacheHint(CacheHint.SPEED);
		ds = new DropShadow();
		ds.setRadius(10.0);
		ds.setColor(Color.WHITE);
		InnerShadow is = new InnerShadow();
		is.setColor(Color.BLACK);
		is.setRadius(5.0);
		ds.setInput(is);
		setEffect(ds);
		setPickOnBounds(true);
		setId("menu-choice-label");
	
		// Add the Mouse Action Properties //
		this.setOnMouseEntered(event->ds.setRadius(20.0));
		this.setOnMouseExited(event->ds.setRadius(5.0));
		this.setOnMouseClicked(event->ds.setRadius(30.0));
	}
}
