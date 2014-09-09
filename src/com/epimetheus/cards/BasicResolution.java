package com.epimetheus.cards;

public class BasicResolution implements CardResolution {

	protected String i;
	
	public BasicResolution(String i){
		this.i=i;
	}
	
	@Override
	public Object getResolution() {
		return i;
	}

}
