package com.epimetheus.cards.test;

import javafx.fxml.Initializable;

public interface ControlledScreen extends Initializable {
	public void setScreenParent(ScreensController screenPage);
}