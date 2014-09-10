package com.epimetheus.cards;

public class ConsoleTestResolution implements CardResolution {

	String message = "Test Message";
	
	public ConsoleTestResolution(String s){
		this.message=s;
	}
	
	@Override
	public Object resolve() {
		return message;
	}

}
