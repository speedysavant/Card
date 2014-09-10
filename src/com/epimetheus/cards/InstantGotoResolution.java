package com.epimetheus.cards;

public class InstantGotoResolution implements CardResolution {

	protected String go;
	
	public InstantGotoResolution(String go){
		this.go=go;
	}
	
	@Override
	public Object resolve() {
		return go;
	}

}
