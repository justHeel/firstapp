package nl.hu.ipass.firstapp.webservices;

import nl.hu.ipass.firstapp.webservices.RecensieService;;

public class RecensieServiceProvider {
private static RecensieService recensieService = new RecensieService();
	
	public static RecensieService getRecensieservice(){
		return recensieService;
	}

}
