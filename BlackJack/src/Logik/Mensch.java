package Logik;

public abstract class Mensch {
	
	private Hand hand1, hand2;
	private int einsatz;
	private String name;
	
	Mensch(){
		
	}
	
	public String toString() {
		return "Person "+name+" mit dem Einsatz von "+einsatz+" hat folgende Karten: "+hand1.toString();
	}
	
	public void addKarte() {
		hand1.addKarte();
	}
	
	public boolean pruefeBust() {
		return false;
	}
	
	public boolean pruefeBlackJack() {
		return false;
	}
	
	public abstract void naechsterZug(String move);

	public Hand getHand1() {
		return hand1;
	}

	public void setHand1(Hand hand1) {
		this.hand1 = hand1;
	}

	public Hand getHand2() {
		return hand2;
	}

	public void setHand2(Hand hand2) {
		this.hand2 = hand2;
	}

	public int getEinsatz() {
		return einsatz;
	}

	public void setEinsatz(int einsatz) {
		this.einsatz += einsatz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
