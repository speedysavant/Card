package com.epimetheus.cards;

import java.util.HashMap;

import javafx.animation.FadeTransition;
import javafx.scene.CacheHint;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Implements the Quest functionality
 * @author Colin
 *
 */
public class Deck extends Pane {
	protected HashMap<String, GameEvent> events =
			new HashMap<>();
	String current = "";
	protected Tabletop myTable;
	
	public Deck(){
		super();
		this.setCache(true);
		this.setCacheHint(CacheHint.SPEED);
	}
	
	public void setTable(Tabletop table){
		this.myTable = table;
	}
	
	public void addEvent(String name, GameEvent e){
		events.put(name, e);
		if (current.compareTo("")==0) current = name;
	}
	
	public GameEvent getEvent(){
		return events.get(current);
	}
	
	public GameEvent getEvent(String key){
		return events.get(key);
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
			
			// resolve the deck
			// current = (String)c.getResolution();
			// myTable.setEvent(events.get(current));
			ResolutionManager.resolve(c.getResolution(), myTable);
			
			// move the card back to the proper configuration
			c.setOpacity(1.0);
			c.setScaleX(1.0);
			c.setScaleY(1.0);
		} catch (NullPointerException e){
			throw new RuntimeException("Unable to load Event " + c.getResolution());
		}
	}
	protected void setCurrent(String current){
		this.current = current;
	}
	protected String getCurrent(){
		return current;
	}
}
