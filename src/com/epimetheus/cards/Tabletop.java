package com.epimetheus.cards;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class Tabletop extends AnchorPane {
	protected GameEvent currentEvent;
	protected Hand hand;
	protected Card<String> selected;
	protected Pane presentation;
	protected Deck deck;
	
	private double bodyHeight;
	private double handHeight; 
	
	public Tabletop(Deck deck){
		super();
		setEvent(deck.getEvent());
		
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
		
	}
	
	public void setEvent(GameEvent t){
		this.getChildren().clear();
		this.currentEvent=t;
		this.hand=currentEvent.getChoices();
		setHand(currentEvent.getChoices());
		present(currentEvent.present());
		this.getChildren().addAll(presentation, hand);
	}
	
	public void setHand(Hand hand){
		this.hand = hand;
		AnchorPane.setBottomAnchor(hand, 5.0);
		AnchorPane.setLeftAnchor(hand, 5.0);
		AnchorPane.setRightAnchor(hand, 5.0);
		// getChildren().add(hand);
	}
	
	public void present(Pane p){
		presentation = p;
		AnchorPane.setTopAnchor(presentation, 5.0);
		AnchorPane.setLeftAnchor(presentation, 5.0);
		AnchorPane.setRightAnchor(presentation, 5.0);
	}
}
