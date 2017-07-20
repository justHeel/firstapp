package nl.hu.ipass.firstapp.domain;

import java.util.Date;

public class Spel {
	
	private int spelNummer;
	private String naam;
	private String beschrijving;
	private String spelGenre;
	private String trailer;
	private String publisher;
	private Date releaseDatum;
	
	public Spel(int spn, String nm, String bs, String sgen, String trl, String pbs, Date rel){
		this.spelNummer = spn;
		this.naam = nm;
		this.beschrijving = bs;
		this.spelGenre =  sgen;
		this.trailer = trl;
		this.publisher = pbs;
		this.releaseDatum = rel;
	}
	
	public Spel(int spn, String nm, String bs, String sgen, String trl, String pbs){
		this.spelNummer = spn;
		this.naam = nm;
		this.beschrijving = bs;
		this.spelGenre =  sgen;
		this.trailer = trl;
		this.publisher = pbs;
	}
	
	public Spel (String nm, String bs, String sgen, String trl, String pbs, Date rel){
		this.naam = nm;
		this.beschrijving = bs;
		this.spelGenre =  sgen;
		this.trailer = trl;
		this.publisher = pbs;
		this.releaseDatum = rel;
	}
	
	public Spel (String nm, String bs, String sgen, String trl, String pbs){
		this.naam = nm;
		this.beschrijving = bs;
		this.spelGenre =  sgen;
		this.trailer = trl;
		this.publisher = pbs;
	}
	
	public int getSpelNummer() {
		return spelNummer;
	}

	public void setSpelNummer(int spelNummer) {
		this.spelNummer = spelNummer;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public String getSpelGenre() {
		return spelGenre;
	}

	public void setSpelGenre(String spelGenre) {
		this.spelGenre = spelGenre;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getReleaseDatum() {
		return releaseDatum;
	}

	public void setReleaseDatum(Date releaseDatum) {
		this.releaseDatum = releaseDatum;
	}

}
