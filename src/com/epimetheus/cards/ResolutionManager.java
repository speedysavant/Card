package com.epimetheus.cards;

import java.util.ArrayList;

/**
 * 
 * @author Colin
 *
 */
public class ResolutionManager {
	
	/* Notes for Development:
	 * 
	 * We can replace this ugly switch statement set with a HashMap of build() and resolve()
	 * method calls. On build or resolution, the resman will check its hashmap for the appropriate
	 * procedure. if it doesn't find one, the resman will ask the CardResolution to supply it with
	 * the appropriate procedure - builders will have to be static. resolution procedures will have
	 * to be reflective, likely, since we won't know exactly what sort of objects will be needed
	 * to do a given task.alternatively, CardResolution.resolve() can be changed to accept an
	 * array of Objects instead, and it can internally parse that array into the objects it needs.
	 */
	
	public static CardResolution build(String choice){
		if (choice.contains("&")){
			return new ComplexResolution(choice);
		}
		String[] args = choice.split(" ");
		String message = "";
		for (int i = 1; i < args.length; i++) {
			message=message+args[i] + " ";
		}
		message=message.trim();
		switch (args[0]){
		case "goto" : return new InstantGotoResolution(args[1]);
		
		case "test"	: 	return new ConsoleTestResolution(message);
		case "aset"	:	return new AttributeSetResolution(message);
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
											
		case "AttributeSetResolution"	:	r.resolve();
											break;
		default							: 	System.err.println("No Card Resolution for " 
												+ r.getClass().getSimpleName() + " in ResolutionManager. Invoking"
														+ " default resolution.");
											r.resolve();
		}
	}
}
