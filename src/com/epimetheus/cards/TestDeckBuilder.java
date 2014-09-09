package com.epimetheus.cards;

import java.util.HashMap;

public class TestDeckBuilder implements DeckBuilder {

	HashMap<String, Deck> decks = new HashMap<>();
	
	public TestDeckBuilder(){
		Deck deck = new Deck();
		
		
		deck.addEvent(new TestEvent(deck, "Event 0", "Event 0 Description"));
		deck.addEvent(new TestEvent(deck, "Event 1", "Event 1 Description"));
		deck.addEvent(new TestEvent(deck, "Event 2", "Event 2 Description"));
		deck.addEvent(new TestEvent(deck, "Event 3", "Event 3 Description"));
		deck.addEvent(new TestEvent(deck, "Event 4", "Event 4 Description"));
		decks.put("Test", deck);
	}
	
	@Override
	public Deck getDeck() {
		return decks.get("Test");
	}

	@Override
	public Deck getDeck(Object obj) {
		return decks.get(obj.toString());
	}

}
