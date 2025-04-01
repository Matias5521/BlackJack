package Logik;

public class Karte {

	private String farbe, karte;
	private int punkte;

	Karte(String farbe, String karte) {
		this.farbe = farbe;
		this.karte = karte;
		weisePunkteZu();
	}

	public String toString() {
		return karte + " in " + farbe + ".";
	}

	private void weisePunkteZu() {

		String[] karte = this.karte.split("-");

		if (karte[1].equals("Bube") || karte[1].equals("Dame") || karte[1].equals("König")) {
			punkte = 10;
		} else if (karte[1].equals("Ass")) {
			punkte = 11; 
		} else {
			punkte = Integer.parseInt(karte[1]);
		}
	}

	public int getPunkte() {
		return punkte;
	}
	
	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}
}
