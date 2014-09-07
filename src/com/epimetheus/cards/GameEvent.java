package com.epimetheus.cards;

import javafx.scene.layout.Pane;

public interface GameEvent {

	public Pane present();
	public Hand getChoices();
}
