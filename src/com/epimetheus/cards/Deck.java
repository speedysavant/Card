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
	
	public void resolve(Card<?> c){
		c.select(this);
	}
	
	protected void resolveDeck(Card<?> c){
		FadeTransition ft = new FadeTransition(Duration.millis(500), myTable);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.setOnFinished(event-> launchNext(c));
		ft.play();
	}
	
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
