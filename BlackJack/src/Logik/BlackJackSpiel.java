package Logik;

import java.util.ArrayList;

public class BlackJackSpiel {
	
	private Kartenstapel ks = new Kartenstapel();
	private boolean ende = false;
	private ArrayList<Mensch> spieler = new ArrayList<>();

	public BlackJackSpiel() {
	}
	
	public Spieler erstelleSpieler(String name) {
		Spieler s = new Spieler(name);
		spieler.add(s);
		s.setHand1(getNeueHand(s));
		return s;
	}
	
	public Hand getNeueHand(Spieler s) {
		Hand h = new Hand(ks);
		s.setHand1(h);
		return h;
	}
	
	public boolean isEnde() {
		return ende;
	}
	
	public boolean pruefeEnde(Mensch m) {
		return m.getHand1().getPunkte() >= 21;
	}
	
	public String ausgabeErgebnisse() {
		String s = "Die Karten aller Spieler:\n";
		for(Mensch s1: spieler) {
			s += s1.toString();
		}
		
		return s;
	}
	
	public boolean pruefeBlackJack() {
		boolean bj = false;
		for(Mensch s: spieler) {
			if (s.getHand1().isBlackJack()) bj = true;
		}
		return bj;
	}
	
	public void fuegeKarteHinzu(Mensch m) {
		m.addKarte();
	}
	
	public int ausgabePunkteKarte(Mensch m) {
		return m.getHand1().getPunkte();
	}
	
	public void verteilePunkteNeu(Mensch m) {
		ArrayList<Karte> ks = m.getHand1().getKarten();
		for(Karte k: ks) {
			if(k.getPunkte() == 11) {
				if(m.getHand1().getPunkte() >= 21) {
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
	
	public int getAnzSpieler() {
		return spieler.size();
	}
}
