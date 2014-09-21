package com.epimetheus.cards.test;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ZDEPR_Graph_Menu_Save extends ZDEPR_Graph_Abstract {

	protected Text title;
	
	public ZDEPR_Graph_Menu_Save(){
		super();
		
		// Build Main Container //
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setPadding(new Insets(20,20,20,20));
		vbox.setSpacing(20);
		AnchorPane.setTopAnchor(vbox, 50.0);
		AnchorPane.setBottomAnchor(vbox, 50.0);
		AnchorPane.setLeftAnchor(vbox, 50.0);
		AnchorPane.setRightAnchor(vbox, 50.0);
		
		// Build Background //
		backimage = new Image("resources/temp-menu.jpg");
		imv = new ImageView(backimage);
		imv.setCache(true);
		imv.setCacheHint(CacheHint.SPEED);
		imv.fitHeightProperty().bind(this.prefHeightProperty());
		imv.fitWidthProperty().bind(this.prefWidthProperty());
		
		// Build Title //
		title = new Text("Save Game");
		title.setCache(true);
		title.setCacheHint(CacheHint.SPEED);
		title.setId("menu-title");
		DropShadow titleglow = new DropShadow();
		titleglow.setRadius(10.0);
		titleglow.setColor(Color.WHITE);
		InnerShadow is = new InnerShadow();
		is.setColor(Color.BLACK);
		is.setRadius(5.0);
		titleglow.setInput(is);
		title.setEffect(titleglow);
		
		// Build List //
		String fileroot = new File("").getAbsolutePath();
		File dir = new File(fileroot+"\\src\\resources\\");
		File[] list = dir.listFiles();
		ListView<File> lv = new ListView<File>();
		ObservableList<File> files = FXCollections.observableArrayList(list);
		lv.setItems(files);
		lv.setMinHeight(400);
		lv.setMinWidth(750);
		
		// Build Button HBox //
		HBox hbox = new HBox();
		hbox.setSpacing(50);
		hbox.setAlignment(Pos.CENTER_LEFT);
		// This isn't laying out as I'd like. Boo-urns.
		
		MenuChoiceText load = new MenuChoiceText("Load");
		load.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				FXMLLoader myLoader = new
						FXMLLoader(getClass().getResource("FX_Main.fxml"));
				try {
					Parent loadScreen = (Parent) myLoader.load();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		MenuChoiceText cancel = new MenuChoiceText("Cancel");
		cancel.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
// 				theatre.enstage("menu");
			}
		});
		hbox.getChildren().addAll(load, cancel);
		
		// Add Controls to VBox //
		vbox.getChildren().addAll(title, lv, hbox);
		
		// Add them to the scene graph //
		getChildren().addAll(vbox, imv);
		imv.toBack();
	}
	
	public void refresh(){
	}
}
