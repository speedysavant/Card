package com.epimetheus.cards.test;

import java.util.HashMap;

public class TokenManager {
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
		tokens.put(key, token);
	}
	
	public GameToken get(String key){
		return tokens.get(key);
	}
}
