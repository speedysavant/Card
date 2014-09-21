package com.epimetheus.cards.test;

enum Screens {
	MAIN("FX_Main.fxml"),
	MENU("FX_Menu.fxml"),
	OPTIONS("FX_Options.fxml"),
	MENU_OPTIONS("FXMenu_Options.fxml")
	;
	String filename;
	private Screens(String filename){
		this.filename = filename;
	}
	String getFxml(){
		return filename;
	}
}