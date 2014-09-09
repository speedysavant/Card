package com.epimetheus.cards;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

public class BasicEvent implements GameEvent {

	protected String name="Test Event";
	protected String text="Click a Card to inspect it, click again to select it.";
	
	protected Hand hand;
	protected Deck deck;
	protected Pane pane;
	
	public BasicEvent(Deck deck){
		this.deck=deck;
		hand = new Hand(deck);
		pane = new VBox();
	}
	
	public BasicEvent(Deck deck, String name, String text){
		this(deck);
		this.name = name;
		this.text = text;
	}
	
	@Override
	public Pane present() {
		pane = new VBox();
		((VBox)pane).setAlignment(Pos.TOP_CENTER);
		pane.setId("event-pane");
		
		Label title = new Label(name);
		title.setId("event-display-title");
		title.setWrapText(true);
		title.setPadding(new Insets(0,15,0,15));
		
		Separator sep1 = new Separator();
		sep1.setPadding(new Insets(15,0,15,0));
		
		Label body = new Label(text);
		body.setId("event-display-body");
		body.setWrapText(true);
		body.setPadding(new Insets(0,15,0,15));
		
		pane.widthProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				sep1.setMaxWidth(2*arg2.doubleValue()/3);
			}
		});
		
		pane.getChildren().addAll(title, sep1, body);
		return pane;
	}

	public void addChoice(String text, String choice){
		Card<String> c = new Card<>(new BasicResolution(choice));
		c.setName(name);
		hand.add(c);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Hand getChoices(){
		return hand;
	}
	
}
