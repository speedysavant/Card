package com.epimetheus.cards;

import java.util.ArrayList;

public class ComplexResolution implements CardResolution {

	ArrayList<CardResolution> resolutions = new ArrayList<>();
	
	public ComplexResolution(String choice){
		String[] args = choice.trim().split("&");
		for(String arg : args){
			addResolution(ResolutionManager.build(arg.trim()));
		}
	}
	
	public void addResolution(CardResolution r){
		resolutions.add(r);
	}
	
	@Override
	public Object resolve() {
		return resolutions;
	}

}
