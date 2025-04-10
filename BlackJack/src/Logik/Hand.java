package Logik;

import java.util.ArrayList;

public class Hand {

	private ArrayList<Karte> karten = new ArrayList<>();
	private Kartenstapel ks;

	Hand(Kartenstapel ks) {

		if (ks != null)
			this.ks = ks;
		for (int i = 0; i < 2; i++) {
			addKarte();
			
			//Karte k = new Karte("Rot", "Karo-10");
			//karten.add(k);
			
		}

	}

	public String toString() {
		String r = "\n";
		for (Karte k : karten) {
			r += k.toString() + "\n";
		}
		return r;
	}

	public void addKarte() throws RuntimeException {
		Karte k;
		if (karten.size() < 10) {

			k = ks.getKarte();
			karten.add(k);

		} else
			throw new RuntimeException();

	}

	public int getPunkte() {
		int punkte = 0;
		for (Karte k : karten) {
			punkte += k.getPunkte();
		}
		return punkte;
	}

	public boolean isBlackJack() {
		return karten.size() == 2 && getPunkte() == 21;
	}

	public ArrayList<Karte> getKarten() {
		return karten;
	}

}
