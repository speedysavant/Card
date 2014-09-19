package com.epimetheus.cards.test;

public class GameManager {
	protected TokenManager tm;
	protected Theatre theatre;
	
	public GameManager(){
		tm = TokenManager.getManager();
		theatre = Theatre.getTheatre();
	}
	
	protected boolean newGame(){
		
		return true;
	}
	
	protected boolean saveGame(String filename){
		
		return true;
	}
	protected boolean loadGame(String filename){
		
		return true;
	}
}

interface Saveable {
	public String save();
	public boolean load();
}