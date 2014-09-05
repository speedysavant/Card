package com.epimetheus.cards;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class Tabletop<T extends GameDataComponent> extends AnchorPane {
	protected Hand hand;
	protected Card<String> selected;
	protected Pane presentation;
	
	private double bodyHeight;
	private double handHeight; 
	
	protected T t;
	
	public Tabletop(T t){
		super();
		this.hand=t.getChoices();
		setHand(t.getChoices());
		present(t.present());
		
		this.heightProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				bodyHeight = 3*arg2.doubleValue()/5;
				handHeight = 2*arg2.doubleValue()/5;
				presentation.setPrefHeight(bodyHeight);
				hand.setPrefHeight(handHeight);
			}
		});
		
		this.getChildren().addAll(presentation,hand);
	}
	
	public void setHand(Hand hand){
		this.hand = hand;
		AnchorPane.setBottomAnchor(hand, 5.0);
		AnchorPane.setLeftAnchor(hand, 5.0);
		AnchorPane.setRightAnchor(hand, 5.0);
		// getChildren().add(hand);
	}
	
	private void present(Pane p){
		presentation = p;
		AnchorPane.setTopAnchor(presentation, 5.0);
		AnchorPane.setLeftAnchor(presentation, 5.0);
		AnchorPane.setRightAnchor(presentation, 5.0);
	}
}
