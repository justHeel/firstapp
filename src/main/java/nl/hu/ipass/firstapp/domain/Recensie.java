package nl.hu.ipass.firstapp.domain;

public class Recensie {

	private int recensieNummer;
	private int recensieCijfer;
	private String comment;
	private Spel spel;
	private Lid lid;
	
	public Recensie (int recN, int recC, String cmt, Spel sp, Lid ld){
		this.recensieNummer = recN;
		this.recensieCijfer = recC;
		this.comment =  cmt;
		this.spel = sp;
		this.lid = ld;
	}
	
	public Recensie (int recC, String cmt, Spel sp, Lid ld){
		this.recensieCijfer = recC;
		this.comment = cmt;
		this.spel = sp;
		this.lid = ld;
	}
	public int getRecensieNummer() {
		return recensieNummer;
	}

	public void setRecensieNummer(int recensieNummer) {
		this.recensieNummer = recensieNummer;
	}

	public int getRecensieCijfer() {
		return recensieCijfer;
	}

	public void setRecensieCijfer(int recensieCijfer) {
		this.recensieCijfer = recensieCijfer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Spel getSpel() {
		return spel;
	}

	public void setSpel(Spel spel) {
		this.spel = spel;
	}

	public Lid getLid() {
		return lid;
	}

	public void setLid(Lid lid) {
		this.lid = lid;
	}
	
}
