package com.epimetheus.cards;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

public class TestEvent implements GameDataComponent {

	protected String name="Test Event";
	protected String text="Click a Card to inspect it, click again to select it. "
			+ "Right click to return it to the hand.\n\nTitles and Descriptions "
			+ "are the meat of the Event and, "
			+ "by extension, the primary way that the world is shown"
			+ " to the player. It's important that the Descriptions are "
			+ "well written and portray the character of the City and "
			+ "world well, without being overdone. Major Quests and "
			+ "Events can be voice acted if possible, and if a voice "
			+ "actor of sufficient talent is found - look for "
			+ "Bastion-style and quality in voice acting. Any voice-acted"
			+ " Quest should feature animations or at the very least "
			+ "static pictures or 3D scenes. Ideally any important Event "
			+ "will have pictures to go with them. Music will also be "
			+ "required for the more important Quests.";
	
	protected Hand hand;
	protected Pane pane;
	
	public TestEvent(){
		hand = new Hand();
		pane = new VBox();
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

	public Hand getChoices(){
		hand = new Hand();
		for(int i = 0; i < 5; i++){
			hand.add(new Card<String>());
		}
		return hand;
	}
	
}
