package Verarbeitung;

import java.util.Scanner;
import java.util.TreeMap;

import GUI.GUI;

public class TicTacToe {

	private GUI gui;
	private TreeMap<Integer, Character> belegteFelder = new TreeMap<>();
	private char aktuellerSpieler = 'X';
	private int ausgewaehltesFeld = 0;

	public TicTacToe() {

		Scanner s = new Scanner(System.in);
		System.out.println("Wollen Sie mit einer GUI('gui' eingeben) oder mit der Konsole('konsole' eingeben) spielen?");
		String befehl = s.nextLine();
		
		fuelleTreeMap();

		if (befehl.equalsIgnoreCase("gui")) {

			System.out.println("Spieler 1 ist X, Sieler 2 ist O.\nUm ein Feld für ein Zug auszuwählen muss dieses Feld angeklickt werden...");

			gui = new GUI(this);

		    
			

		} else if (befehl.equalsIgnoreCase("konsole")) {
			System.out.println("Spieler 1 ist X, Sieler 2 ist O.\nDie Felder sind von 1 bis 9 durchnummerriert.\nUm ein Feld für ein Zug auszuwählen muss die Zahl des Feldes eingegeben werden...");

			for (int i = 1; i <= 9; i++) {

				erfasseEingabe();
				gibAktuellenStandAus();
				if (i >= 5)
					pruefeSieg();

				if (aktuellerSpieler == 'X')aktuellerSpieler = 'O';
				else aktuellerSpieler = 'X';

			}

			System.out.println("Ergebnis des Spiels ist ein Untentschieden!");
		} else {
			System.out.println("Eingabe ungültig!");
		}
	}

	// Fuellt die TreeMap mit Standart Werten
	private void fuelleTreeMap() {
		for (int i = 1; i <= 9; i++) {
			belegteFelder.put(i, (char) (48 + i));
		}
	}

	private void gibAktuellenStandAus() {

		System.out.println("\n-------------");
		int counter = 0;
		while (counter < 9) {
			System.out.printf("|%2s |%2s |%2s |", belegteFelder.get(counter += 1), belegteFelder.get(counter += 1),
					belegteFelder.get(counter += 1));
			System.out.println("\n-------------");
		}
	}

	private void erfasseEingabe() {

		Scanner s = new Scanner(System.in);

		System.out.println("Spieler " + aktuellerSpieler + " ist am Zug!");

		System.out.println("Geben Sie eine Feldnummer ein:\n>>");

		try {
			ausgewaehltesFeld = s.nextInt();
			System.out.println("Die Eingabe: " + ausgewaehltesFeld);
		} catch (Exception E) {
			System.out.println("Es wurde keine passende Eingabe getätigt,\nversuchen Sie es erneut...");
			erfasseEingabe();
		}

		if (belegteFelder.get(ausgewaehltesFeld) == 'X' || belegteFelder.get(ausgewaehltesFeld) == 'O') {
			System.out.println("Feld " + ausgewaehltesFeld + " schon belegt, machen Sie eine erneute Eingabe...");
			erfasseEingabe();
		} else {
			belegteFelder.put(ausgewaehltesFeld, aktuellerSpieler);
		}

	}

	private void pruefeSieg() {

		if (belegteFelder.get(1) == aktuellerSpieler && belegteFelder.get(2) == aktuellerSpieler
				&& belegteFelder.get(3) == aktuellerSpieler) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler
					+ "' in der ersten Waagerechte und hat somit gewonnen!");
			System.exit(0);
		} else if (belegteFelder.get(4) == aktuellerSpieler && belegteFelder.get(5) == aktuellerSpieler
				&& belegteFelder.get(6) == aktuellerSpieler) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler
					+ "' in der zweiten Waagerechte und hat somit gewonnen!");
			System.exit(0);
		} else if (belegteFelder.get(7) == aktuellerSpieler && belegteFelder.get(8) == aktuellerSpieler
				&& belegteFelder.get(9) == aktuellerSpieler) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler
					+ "' in der dritten Waagerechte und hat somit gewonnen!");
			System.exit(0);
		} else if (belegteFelder.get(1) == aktuellerSpieler && belegteFelder.get(4) == aktuellerSpieler
				&& belegteFelder.get(7) == aktuellerSpieler) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler
					+ "' in der ersten Senkrechte und hat somit gewonnen!");
			System.exit(0);
		} else if (belegteFelder.get(2) == aktuellerSpieler && belegteFelder.get(5) == aktuellerSpieler
				&& belegteFelder.get(8) == aktuellerSpieler) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler
					+ "' in der zweiten Senkrechte und hat somit gewonnen!");
			System.exit(0);
		} else if (belegteFelder.get(3) == aktuellerSpieler && belegteFelder.get(6) == aktuellerSpieler
				&& belegteFelder.get(9) == aktuellerSpieler) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler
					+ "' in der dritten Senkrechte und hat somit gewonnen!");
			System.exit(0);
		} else if (belegteFelder.get(1) == aktuellerSpieler && belegteFelder.get(5) == aktuellerSpieler
				&& belegteFelder.get(9) == aktuellerSpieler) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler
					+ "' in der ersten Diagonale und hat somit gewonnen!");
			System.exit(0);
		} else if (belegteFelder.get(3) == aktuellerSpieler && belegteFelder.get(5) == aktuellerSpieler
				&& belegteFelder.get(7) == aktuellerSpieler) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler
					+ "' in der zweiten Diagonale und hat somit gewonnen!");
			System.exit(0);
		}
	}

	public void setAktuellerSpieler(char aktuellerSpieler) {
		this.aktuellerSpieler = aktuellerSpieler;
	}

	public char getAktuellerSpieler() {
		return aktuellerSpieler;
	}

	public TreeMap<Integer, Character> getBelegteFelder() {
		return belegteFelder;
	}
	
	
}
