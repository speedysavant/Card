package com.epimetheus.cards.test;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;

public class Theatre extends Application {
	
	protected static Theatre theatre;
	protected static GameManager gm;
	
	protected GraphMap graphs;
	protected Group root;
	protected IntegerProperty height, width;
	protected int minheight = 768;
	protected int minwidth = 1200;
	protected Stage stage;
	
	public Theatre(){
		super();
		theatre = this;
		gm = new GameManager();
		graphs = new GraphMap();
		root = new Group();
		height = new SimpleIntegerProperty(900);
		width  = new SimpleIntegerProperty(1600);
	}
	
	public static Theatre getTheatre(){
		return theatre;
	}
	public static GameManager getGameManager(){
		return gm;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			// Configure the Stage
			this.stage=stage;
			stage.setResizable(true);
			height.bind(stage.heightProperty());
			width.bind(stage.widthProperty());
			stage.setMinHeight(minheight);
			stage.setMinWidth(minwidth);
			Scene scene = new Scene(root, height.get(), width.get());
			
			// Build the Hashmap of all Scenegraphs here
			graphs.put("menu", new Graph_MainMenu());
			graphs.put("menu-save", new Graph_Menu_Save());
			
			// Present the Screen to the viewer in a new application window
			
			graphs.enstage(root, "menu");
			
			scene.getStylesheets().add(getClass()
					.getResource("application.css").toExternalForm());

			// Set the stage and start the show!
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enstage(String name){
		graphs.enstage(root, name);
	}
	public Stage getStage(){
		return stage;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}


/*
 * Old ScreensController Main Launcher. We're taking it from the top now.
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			// The ScreensController pre-loads all Screens that we will find
			// in the app. This code will be migrated into the Screens enum
			ScreensController mainContainer = new ScreensController(primaryStage);
			mainContainer.loadScreen(Screens.MAIN);
			mainContainer.loadScreen(Screens.MENU);
			
			// Tell the Screen Controller which Screen we want to display
			mainContainer.setScreen(Screens.MENU);
			
			// Present the Screen to the viewer in a new application window
			Group root = new Group();
			root.getChildren().addAll(mainContainer);
			Scene scene = new Scene(root, 1600, 1000);
			scene.getStylesheets().add(getClass()
					.getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
*/
