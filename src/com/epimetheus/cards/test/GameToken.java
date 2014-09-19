package com.epimetheus.cards.test;

import java.util.HashMap;

public abstract class GameToken implements Saveable {
	protected String name = "UNDEFINED GAME TOKEN";
	protected HashMap<String, String> atts = new HashMap<>();
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public void setAtt(String key, String value){
		atts.put(key, value);
	}
	public String getAtt(String key){
		return atts.get(key);
	}
}
