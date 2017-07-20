package nl.hu.ipass.firstapp.domain;

public class Lid {
	private int lidnummer;
	private String gebruikersnaam;
	private String wachtwoord; 
	private String email;
	private String rol;


	public Lid(int lidn, String gebr, String ww, String email, String rol){
		this.lidnummer = lidn;
		this.gebruikersnaam = gebr;
		this.wachtwoord = ww;
		this.email = email;
		this.rol = rol;

	}

	public Lid(String gebr, String ww, String email){
		this.gebruikersnaam =  gebr;
		this.wachtwoord = ww;
		this.email =  email;
	}

	public int getLidnummer() {
		return lidnummer;
	}

	public void setLidnummer(int lidnummer) {
		this.lidnummer = lidnummer;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}


	public String getWachtwoord() {
		return wachtwoord;
	}


	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRol(){
		return rol;
	}
	
	public void setRol(String rol){
		this.rol = rol;
	}
	
	}



