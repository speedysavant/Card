package com.epimetheus.cards;

import com.epimetheus.cards.test.GameToken;
import com.epimetheus.cards.test.TokenManager;

/**
 * <p>A Card Resolution that flatly changes the given value to the given
 * Attribute of the given Game Token. Use with care, this isn't percentage-
 * based, so can make attributes do strange things. Returns a String.</p>
 * 
 * <p>Usage: No spaces in tokenname or attrname. Value can have spaces.</p>
 * 
 * <p>aset tokenname.attrname=modifier</p>
 * <p>For example:</p>
 * <p>aset playercity.motto="These Walls Have Eyes."</p>
 * @author Colin
 *
 */
public class AttributeSetResolution implements CardResolution {

	protected String tokenname;
	protected String attrname;
	protected String mod;
	
	public AttributeSetResolution(String input){
		input = input.trim();
		String[] args = input.split("\\.",2);
		if (args.length != 2) 
			throw new RuntimeException("Attr Mod Resolution malformed: "+ input);
		tokenname = args[0];
		args[1] = args[1].trim();
		args = args[1].split("=",2);
		if (args.length != 2) 
			throw new RuntimeException("Attr Mod Resolution malformed: "+ input);
		attrname = args[0].trim();
		try {
			mod = args[1];
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Object resolve() {
		TokenManager tm = TokenManager.getManager();
		GameToken token = tm.get(tokenname);
		token.setAtt(attrname, mod);
		return true;
	}

}
