package com.epimetheus.cards;

import javafx.animation.FadeTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Card<T> extends AnchorPane {

	protected T data;
	
	private DoubleProperty xroot;
	private DoubleProperty yroot;
	private DoubleProperty rotroot;
	
	public static int defaultwidth = 200;
	public static int defaultheight = 300;
	
	private Image image;
	private ImageView imv;
	private DropShadow ds;
	
	public Card(){
		super();
		this.getStyleClass().add("testcard");
		this.setMaxHeight(defaultheight);
		this.setMinHeight(defaultheight);
		this.setMinWidth(defaultwidth);
		this.setMaxWidth(defaultwidth);
		
		xroot = new SimpleDoubleProperty();
		yroot = new SimpleDoubleProperty();
		rotroot = new SimpleDoubleProperty();
		
		image = new Image("resources/Jennar.jpg");
		imv = new ImageView(image);
		imv.fitWidthProperty().bind(this.widthProperty());
		imv.fitHeightProperty().bind(this.heightProperty());
		
		ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.BLACK);
		
        this.setEffect(ds);
        
		this.getChildren().addAll(imv);
	}
	
	public Card(T data){
		this();
		this.data = data;
	}

	public void select(){
		FadeTransition ft = new FadeTransition(Duration.millis(500), this);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
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
	
	public T getData(){
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
}
