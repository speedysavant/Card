package com.epimetheus.cards.test;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScreensController extends StackPane {
	
	private Stage stage;

	private HashMap<String, Node> screens = new HashMap<>();
	private HashMap<String, ControlledScreen> controllers = new HashMap<>();
	
	public ScreensController(Stage stage){
		super();
		this.setCache(true);
		this.setCacheHint(CacheHint.SPEED);
		this.stage=stage;
	}
	
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
			Node node = screens.get(name);
			getChildren().add(0,node);
			Timeline fadeIn = new Timeline(
					new KeyFrame(Duration.ZERO,
							new KeyValue(opacity, 0.0)),
					new KeyFrame(new Duration(500),
							new KeyValue(opacity, 1.0)));
			fadeIn.play();
		}
	}
	

	public void addScreen(Screens name, Node screen){
		if (screen instanceof AnchorPane){
			stage.resizableProperty().addListener(new ChangeListener<Boolean>(){

				@Override
				public void changed(ObservableValue<? extends Boolean> arg0,
						Boolean arg1, Boolean arg2) {
					System.out.println("val = " + arg0 + " old = " + arg1 + " new = " + arg2);
				}
				
			});
			
		} else {
			
		}
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
			controllers.put(screen.getFxml(), myScreenController);
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
						new KeyFrame(new Duration(500),
								new FadeBetweenScreens(opacity, name.toString()), new KeyValue(opacity, 0.0)));
				fade.play();
			} else {
				// Nothing else displayed, so just show
				setOpacity(0.0);
				getChildren().add(screens.get(name.toString()));
				Timeline fadeIn = new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
						new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0)));
				fadeIn.play();
			}
			controllers.get(name.getFxml()).refresh();
			
			Pane pane = (Pane)screens.get(name.toString());
			pane.prefWidthProperty().bind(stage.widthProperty());
			pane.prefHeightProperty().bind(stage.heightProperty());
			
			return true;
			
		} else {
			throw new RuntimeException("Flagrant System Error : Screen "
					+ name +" hasn't been loaded into Screen Controller");
		}
	}
	
	public boolean unloadScreen(String name){
		return !(screens.remove(name) == null);
	}
	
	public ControlledScreen getController(Screens screen){
		return controllers.get(screen.getFxml());
	}
	public Node getNode(Screens screen){
		return screens.get(screen.toString());
	}
}