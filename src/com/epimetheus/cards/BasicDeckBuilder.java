package com.epimetheus.cards;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BasicDeckBuilder implements DeckBuilder {

	HashMap<String, Deck> decks = new HashMap<>();
	
	ResourceBundle rb;
	
	public BasicDeckBuilder(){
		Deck deck = new Deck();
		rb = ResourceBundle.getBundle("resources.quests.story-intro");
		String eventTag = "event";
		int i = 1;
		
		while (true){
			try {
				String tempId = rb.getString(eventTag + i + ".id");
				String tempName = rb.getString(eventTag + i + ".name");
				String tempText=rb.getString(eventTag + i + ".text");
				BasicEvent event = new BasicEvent(tempId, deck);
				int j = 1;
				while (true){
					try {
						String choiceText = rb.getString(eventTag + i + ".choice" + j + ".text");
						String choiceResult = rb.getString(eventTag + i + ".choice" + j + ".result");
						event.addChoice(choiceText, choiceResult);
						j++;
					} catch (MissingResourceException e) {
						break;
					}
				}
				event.setName(tempName);
				event.setText(tempText);
				deck.addEvent(tempId,event);
				i++;
			} catch (MissingResourceException e) {
				break;
			}
		}
				
		decks.put("Intro", deck);
	}
	
	@Override
	public Deck getDeck() {
		return decks.get("Intro");
	}

	@Override
	public Deck getDeck(Object obj) {
		return decks.get(obj.toString());
	}

}
