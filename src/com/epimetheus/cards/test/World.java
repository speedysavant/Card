package com.epimetheus.cards.test;

public class World extends GameToken {
	
	@Override public void setAtt(String key, String value){
		super.setAtt(key, value);
		System.out.println("com.epimetheus.World.World() : Current Attributes: ");
		System.out.println(atts.values() + "\n\n");
	}

	@Override
	public String save() {
		return "World";
	}

	@Override
	public boolean load() {
		return false;
	}
}
