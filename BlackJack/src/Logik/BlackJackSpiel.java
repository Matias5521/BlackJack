package Logik;

import java.util.ArrayList;

public class BlackJackSpiel {
	
	private Hand hand;
	private Kartenstapel ks = new Kartenstapel();
	private boolean ende = false;

	public BlackJackSpiel() {
	}
	
	//warum als return type Hand ???
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
	
	public String ausgabeHand() {
		return hand.toString();
	}
	
	public boolean pruefeBlackJack() {
		return hand.isBlackJack();
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
}
