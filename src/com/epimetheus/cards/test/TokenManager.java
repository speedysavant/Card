package com.epimetheus.cards.test;

import java.util.HashMap;

public class TokenManager implements Saveable{
	protected HashMap<String, GameToken> tokens = new HashMap<>();
	protected static TokenManager manager=null;
	
	private TokenManager(){
		manager=this;
	}
	
	public static TokenManager getManager(){
		if (manager==null){
			return new TokenManager();
		}
		return manager;
	}
	
	public void store(String key, GameToken token){
		tokens.put(key.toLowerCase(), token);
	}
	
	public GameToken get(String key){
		
		return tokens.get(key.toLowerCase());
	}

	@Override
	public String save() {
		return "TokenManager";
	}

	@Override
	public boolean load() {
		// TODO Auto-generated method stub
		return false;
	}
}
