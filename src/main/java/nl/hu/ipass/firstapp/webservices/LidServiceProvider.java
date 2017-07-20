package nl.hu.ipass.firstapp.webservices;

import nl.hu.ipass.firstapp.webservices.LidService;;

public class LidServiceProvider {
private static LidService lidService = new LidService();
	
	public static LidService getLidservice(){
		return lidService;
	}

}
