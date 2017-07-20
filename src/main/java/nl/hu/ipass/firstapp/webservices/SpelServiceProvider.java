package nl.hu.ipass.firstapp.webservices;

import nl.hu.ipass.firstapp.webservices.SpelService;;

public class SpelServiceProvider {
private static SpelService spelService = new SpelService();
	
	public static SpelService getSpelservice(){
		return spelService;
	}

}