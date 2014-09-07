package com.epimetheus.cards;

public class BasicResolution implements CardResolution {

	private int i;
	
	public BasicResolution(int i){
		this.i=i;
	}
	
	@Override
	public Object getResolution() {
		return i;
	}

}
