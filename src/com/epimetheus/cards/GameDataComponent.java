package com.epimetheus.cards;

import javafx.scene.layout.Pane;

public interface GameDataComponent {

	public Pane present();
	public Hand getChoices();
}
