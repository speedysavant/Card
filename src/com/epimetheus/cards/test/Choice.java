package com.epimetheus.cards.test;

public class Choice {
	String text;
	
	public Choice(){
		text = "NULL CHOICE";
	}
	public Choice(String text){
		this.text=text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override public String toString(){
		return text;
	}
}
