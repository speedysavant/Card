package com.epimetheus.cards.test;

import javafx.scene.CacheHint;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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