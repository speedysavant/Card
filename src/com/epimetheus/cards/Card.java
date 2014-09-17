package com.epimetheus.cards;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Card<T> extends AnchorPane {

	protected String name = "";
	protected CardResolution resolution;
	
	private DoubleProperty xroot;
	private DoubleProperty yroot;
	private DoubleProperty rotroot;
	
	public static int defaultwidth = 250;
	public static int defaultheight = 375;
	
	private Image image;
	private ImageView imv;
	private DropShadow ds;
	private Label label = new Label();
	
	public Card(String name){
		super();
		this.setCache(true);
		this.setCacheHint(CacheHint.SPEED);
		setName(name);
		this.getStyleClass().add("card-basic");
		this.setMaxHeight(defaultheight);
		this.setMinHeight(defaultheight);
		this.setMinWidth(defaultwidth);
		this.setMaxWidth(defaultwidth);
		
		xroot = new SimpleDoubleProperty();
		yroot = new SimpleDoubleProperty();
		rotroot = new SimpleDoubleProperty();
		
		image = new Image("resources/blankcardframe.png");
		imv = new ImageView(image);
		imv.fitWidthProperty().bind(this.widthProperty());
		imv.fitHeightProperty().bind(this.heightProperty());
		
		ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.BLACK);
        this.setEffect(ds);
        
		this.getChildren().addAll(label,imv);
	}
	
	public Card(String name, CardResolution resolution){
		this(name);
		this.resolution = resolution;
	}

	// Runs when the card is selected as the desired choice from the hand.
	public void select(Deck deck){
		FadeTransition ft = new FadeTransition(Duration.millis(500), this);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		
		ScaleTransition scale = new ScaleTransition(Duration.millis(500), this);
		scale.setToX(3);
		scale.setToY(3);
		
		ParallelTransition trans = new ParallelTransition();
		trans.setOnFinished(event->deck.resolveDeck(this));
		trans.getChildren().addAll(ft, scale);
		trans.play();
	}
	
	public double getXroot() {
		return xroot.get();
	}

	public void setXroot(double xroot) {
		this.xroot.set(xroot);
	}

	public double getYroot() {
		return yroot.get();
	}

	public void setYroot(double yroot) {
		this.yroot.set(yroot);
	}

	public double getRotroot() {
		return rotroot.get();
	}

	public void setRotroot(double rotroot) {
		this.rotroot.set(rotroot);
	}

	public CardResolution getResolution() {
		return resolution;
	}
	
	public Object resolve(){
		return resolution.resolve();
	}

	public void setResolution(CardResolution resolution) {
		this.resolution = resolution;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		label.setText(name);
		label.getStyleClass().add("card-label-basic");
		label.setWrapText(true);
		AnchorPane.setTopAnchor(label, 50.0);
		AnchorPane.setLeftAnchor(label, 35.0);
		AnchorPane.setRightAnchor(label, 35.0);
		
		label.toFront();
	}
	
}
