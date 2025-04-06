package Logik;

import java.util.ArrayList;

public class BlackJackSpiel {
	
	private Hand hand;
	private Kartenstapel ks = new Kartenstapel();
	private boolean ende = false;
	private ArrayList<Mensch> spieler = new ArrayList<>();

	public BlackJackSpiel() {
	}
	
	public void erstelleSpieler(String name) {
		Spieler s = new Spieler(name);
		spieler.add(s);
		s.setHand1(getNeueHand());
	}
	
	public Hand getNeueHand() {
		hand = new Hand(ks);
		return hand;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public boolean isEnde() {
		return ende;
	}
	
	public boolean pruefeEnde() {
		return hand.getPunkte() >= 21;
	}
	
	public String ausgabeErgebnisse() {
		return "";
	}
	
	public boolean pruefeBlackJack() {
		boolean bj = false;
		for(Mensch s: spieler) {
			
			if (s.getHand1().isBlackJack()) bj = true;
		}
		return bj;
	}
	
	public void fuegeKarteHinzu() {
		hand.addKarte();
	}
	
	public int ausgabePunkteKarte() {
		return hand.getPunkte();
	}
	
	public void verteilePunkteNeu() {
		ArrayList<Karte> ks = hand.getKarten();
		for(Karte k: ks) {
			if(k.getPunkte() == 11) {
				if(hand.getPunkte() >= 21) {
					k.setPunkte(1);
				}
			}
		}
	}

	public Kartenstapel getKs() {
		return ks;
	}

	public ArrayList<Mensch> getSpieler() {
		return spieler;
	}
}
