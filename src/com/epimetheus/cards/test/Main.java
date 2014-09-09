package com.epimetheus.cards.test;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			// The ScreensController pre-loads all Screens that we will find
			// in the app. This code will be migrated into the Screens enum
			ScreensController mainContainer = new ScreensController(primaryStage);
			mainContainer.loadScreen(Screens.MAIN);
			
			// Tell the Screen Controller which Screen we want to display
			mainContainer.setScreen(Screens.MAIN);
			
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

