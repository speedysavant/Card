package com.epimetheus.cards;

import java.util.ArrayList;

public class ResolutionManager {
	public static CardResolution build(String choice){
		if (choice.contains("&")){
			return new ComplexResolution(choice);
		}
		String[] args = choice.split(" ");
		switch (args[0]){
		case "goto" : return new InstantGotoResolution(args[1]);
		
		case "test"	: 	String message = "";
						for (int i = 1; i < args.length; i++) {
							message=message+args[i] + " ";
						}
						return new ConsoleTestResolution(message.trim());
						
		default		: throw new RuntimeException("Badly formed Resolution: " + choice);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void resolve(CardResolution r, Tabletop table){
		Deck deck = table.getDeck();
		switch (r.getClass().getSimpleName()){
		case "ComplexResolution"		:	ArrayList<CardResolution> list = (ArrayList<CardResolution>)r.resolve();
											for (CardResolution res : list){
												resolve(res, table);
											}
											break;
											
		case "InstantGotoResolution"	: 	deck.setCurrent((String)r.resolve());
											table.setEvent(deck.getEvent(deck.getCurrent()));
											break;
											
		case "ConsoleTestResolution"	:	System.out.println((String)r.resolve());
											
											break;
											
		default							: 	System.err.println("No resolution script for " + r);
		}
	}
}
