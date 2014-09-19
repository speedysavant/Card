package com.epimetheus.cards.test;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Theatre extends Application {
	
	protected static Theatre theatre;
	
	protected GraphMap graphs;
	protected Group root;
	protected IntegerProperty height, width;
	protected int minheight = 768;
	protected int minwidth = 1200;
	
	public Theatre(){
		super();
		theatre = this;
		graphs = new GraphMap();
		root = new Group();
		height = new SimpleIntegerProperty(900);
		width  = new SimpleIntegerProperty(1600);
	}
	
	public static Theatre getTheatre(){
		return theatre;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			// Configure the Stage
			stage.setResizable(true);
			height.bind(stage.heightProperty());
			width.bind(stage.widthProperty());
			stage.setMinHeight(minheight);
			stage.setMinWidth(minwidth);
			
			// Build the Hashmap of all Scenegraphs here
			graphs.put("menu", new Graph_MainMenu());
			
			// Present the Screen to the viewer in a new application window
			Scene scene = new Scene(root, height.get(), width.get());
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
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}


interface GamePane {
	public void setTheatre(Theatre theatre);
}

abstract class Graph_Abstract extends AnchorPane implements GamePane {
	
	protected Theatre theatre;
	protected Image backimage;
	protected ImageView imv;
	
	public Graph_Abstract(){
		super();
		// Set Properties //
		setCache(true);
		setCacheHint(CacheHint.SPEED);
		setStyle("-fx-background-color: radial-gradient(radius 100%, gray, black)");
	}
	
	
	
	public void setTheatre(Theatre theatre){
		this.theatre = theatre;
	}
}

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
		
		// Build Buttons //
		vBox = new VBox();
		vBox.setCache(true);
		vBox.setCacheHint(CacheHint.SPEED);
		vBox.setSpacing(20);
		vBox.getChildren().add(buildMenuButton("New Game"));
		vBox.getChildren().add(buildMenuButton("Save Game"));
		vBox.getChildren().add(buildMenuButton("Load Game"));
		AnchorPane.setTopAnchor(vBox, 300d);
		AnchorPane.setRightAnchor(vBox, 100d);
		
		// Add'em'all to the page. //
		getChildren().addAll(title, vBox);
		imv.toBack();
	}
	
	private Text buildMenuButton(String text){
		
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
		menuchoice.setOnMouseClicked(event->launchTarget(menuchoice));
		
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
	
	private void launchTarget(Text text){
		try {
			DropShadow ds = (DropShadow) text.getEffect();
			ds.setRadius(30.0);
			theatre.enstage("menu");
		} catch (NullPointerException e) {
			
		}
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
