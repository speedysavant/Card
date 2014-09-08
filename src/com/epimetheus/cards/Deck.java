package com.epimetheus.cards;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Implements the Quest functionality
 * @author Colin
 *
 */
public class Deck extends Pane {
	protected ObservableList<GameEvent> events =
			FXCollections.observableArrayList();
	int current = 0;
	protected Tabletop myTable;
	
	public Deck(){
		super();
	}
	
	public void setTable(Tabletop table){
		this.myTable = table;
	}
	
	public void addEvent(GameEvent e){
		events.add(e);
	}
	
	public GameEvent getEvent(){
		return events.get(current);
	}
	
	// resolve the result of a selected card. called after the user selects a card.
	public void resolve(Card<?> c){
		c.select(this);
	}
	
	// Called by the selected card at the end of the cards' highlight animation.
	// This allows the animation to play to completion.
	protected void resolveDeck(Card<?> c){
		FadeTransition ft = new FadeTransition(Duration.millis(500), myTable);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.setOnFinished(event-> launchNext(c));
		ft.play();
	}
	
	// Called at the termination of the resolveDeck animation. This launches
	// the next Event. This will only be called for immediate event follow-ups
	// so i'll have to come up with a more sophisticated determiner based on
	// the CardResolution.
	private void launchNext(Card<?> c){
		try {
			FadeTransition ft = new FadeTransition(Duration.millis(500), myTable);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();
			current = (Integer)c.getResolution();
			myTable.setEvent(events.get(current));
		} catch (NullPointerException e){
			throw new RuntimeException("Unable to load Event " + c.getResolution());
		}
	}
}
