package com.epimetheus.cards.test;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreensController extends StackPane {
	@SuppressWarnings("rawtypes")
	private final class FadeBetweenScreens implements EventHandler {
		private final DoubleProperty opacity;
		private final String name;

		private FadeBetweenScreens(DoubleProperty opacity, String name) {
			this.opacity = opacity;
			this.name = name;
		}

		@Override
		public void handle(Event t) {
			// remove current screen
			getChildren().remove(0);
			// add new screen
			getChildren().add(0,screens.get(name));
			Timeline fadeIn = new Timeline(
					new KeyFrame(Duration.ZERO,
							new KeyValue(opacity, 0.0)),
					new KeyFrame(new Duration(800),
							new KeyValue(opacity, 1.0)));
			fadeIn.play();
		}
	}
	private HashMap<String, Node> screens = new HashMap<>();
	

	public void addScreen(Screens name, Node screen){
		screens.put(name.toString(), screen);
	}
	public boolean loadScreen(Screens screen){
		try {
			FXMLLoader myLoader = new
					FXMLLoader(getClass().getResource(screen.getFxml()));
			Parent loadScreen = (Parent) myLoader.load();
			ControlledScreen myScreenController =
					((ControlledScreen) myLoader.getController());
			myScreenController.setScreenParent(this);
			addScreen(screen, loadScreen);
			return true;
		} catch (Exception e) {
			System.out.println("Load error with: " + screen.getFxml());
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setScreen(Screens name){
		if(screens.get(name.toString()) != null){
			final DoubleProperty opacity = opacityProperty();
			
			//If there is more than one screen
			if (!getChildren().isEmpty()){
				@SuppressWarnings("unchecked")
				Timeline fade = new Timeline(
						new KeyFrame(Duration.ZERO, 
								new KeyValue(opacity, 1.0)),
						new KeyFrame(new Duration(1000),
								new FadeBetweenScreens(opacity, name.toString()), new KeyValue(opacity, 0.0)));
				fade.play();
			} else {
				// Nothing else displayed, so just show
				setOpacity(0.0);
				getChildren().add(screens.get(name.toString()));
				Timeline fadeIn = new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
						new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
				fadeIn.play();
			}
			return true;
			
		} else {
			throw new RuntimeException("Flagrant System Error : Screen "
					+ name +" hasn't been loaded into Screen Controller");
		}
	}
	
	public boolean unloadScreen(String name){
		return !(screens.remove(name) == null);
	}
}