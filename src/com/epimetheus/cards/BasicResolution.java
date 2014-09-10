package com.epimetheus.cards;

public class BasicResolution implements CardResolution {

	protected String i;
	
	public BasicResolution(String i){
		this.i=i;
	}
	
	@Override
	public Object resolve() {
		System.out.println("Testing for Resolution: " + i);
		return i;
	}

}
