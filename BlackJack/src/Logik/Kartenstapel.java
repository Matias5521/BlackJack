package Logik;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Kartenstapel {

	private ArrayList<Karte> karten = new ArrayList<>();

	Kartenstapel() {

		befuelleKartenstapel();

		System.out.println("Der Kartenstapel ist bereit.");

	}
	
	private void befuelleKartenstapel() {
		
		String[] symbole = { "Kreuz", "Pik", "Herz", "Karo" };
		String[] werte = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Bube", "Dame", "König", "Ass" };

		for (String symbol : symbole) {
			String farbe;

			if (symbol.equals("Kreuz") || symbol.equals("Pik")) {
				farbe = "Schwarz";
			} else {
				farbe = "Rot";
			}

			for (String wert : werte) {
				karten.add(new Karte(farbe, symbol + "-" + wert));
			}
		}
		
	}

	public Karte getKarte() throws RuntimeException {

		if (karten.size() > 0) {

			Random r = new Random();
			int randIndex = r.nextInt(0, 52);
			Karte randKarte = karten.get(randIndex); 
			karten.remove(randKarte);
			return randKarte;

		} else {
			throw new RuntimeException();
		}

	}

	public int getZahlUebrigerKarten() {
		int counter = 0;
		for (Karte k : karten) {
			if (k != null)
				counter++;
		}
		return counter;
	}
}
