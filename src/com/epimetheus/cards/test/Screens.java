package com.epimetheus.cards.test;

enum Screens {
	MAIN("FX_Main.fxml"),
	MENU("FX_Menu.fxml")
	;
	String filename;
	private Screens(String filename){
		this.filename = filename;
	}
	String getFxml(){
		return filename;
	}
}