package com.epimetheus.cards.test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameEvent {
	protected String title;
	protected String text;
	protected ObservableList<Choice> choices;
	
	public GameEvent(){
		super();
		title = "Test Event";
		text = "This world is a bright stone set on black velvet. \n\n "
				+ "Our ancestors found it worthy, and chose to make it their "
				+ "home amongst the stars. \n\n They stretched out their hand"
				+ "s and crushed mountains of ice across the plains to form t"
				+ "he oceans. \n\n They struck their hammers across the land "
				+ "and raised the mountains. \n\n They breathed out the sweet,"
				+ " clean air, and covered the land in a green blanket of lif"
				+ "e.";
		choices = FXCollections.observableArrayList();
		choices.addAll(new Choice("First Choice"), new Choice("Second Choice"), new Choice("Third Choice"));
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ObservableList<Choice> getChoices() {
		return choices;
	}
	public void setChoices(ObservableList<Choice> choices) {
		this.choices = choices;
	}
	
	
}
