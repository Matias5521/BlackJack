package Logik;

import java.util.Scanner;
import java.util.TreeMap;

import Anzeige.GUI;

public class TicTacToe {
	
	public TreeMap<Integer, Character> belegteFelder = new TreeMap<>();
	
	private int counter;
	private GUI gui;
	private char aktuellerSpieler;
	private int ausgewaehltesFeld;
	private int[] gewinnerIndexe = new int[3];
	
	public TicTacToe() throws Exception {
		
		ermittleEingabeAnfang();

	}
	
	public void ermittleEingabeAnfang() throws Exception {
		
		//setzt praktisch werte zurück
		fuelleTreeMap();
		aktuellerSpieler = 'X';
		counter = 0;
		
		Scanner s = new Scanner(System.in);
		System.out.println("\nWollen Sie mit einer GUI('gui' eingeben) oder mit der Konsole('konsole' eingeben) spielen?");
		String befehl = s.nextLine();
		
		if (befehl.equalsIgnoreCase("gui")) {

			System.out.println("Um ein Feld für ein Zug auszuwählen muss dieses Feld angeklickt werden...");

			gui = new GUI(this);

		} else if (befehl.equalsIgnoreCase("konsole")) {
			
			gibAktuellenStandAus();
			
			System.out.println("Die Felder sind von 0 bis 8 durchnummerriert.\nUm ein Feld für ein Zug auszuwählen muss die Zahl des Feldes eingegeben werden...");

			for (int i = 0; i < 9; i++) {

				erfasseEingabe();
				gibAktuellenStandAus();
				
				if (i >= 4 && pruefeSieg()) break;

				if (aktuellerSpieler == 'X')aktuellerSpieler = 'O';
				else aktuellerSpieler = 'X';

			}

			ermittleEingabeAnfang();
			
		}else if(befehl.equalsIgnoreCase("ende")) {
			System.out.println("Spiel wird beendet.");
			System.exit(0);
		}else {
			System.out.println("Eingabe ungültig!\nVersuchen Sie es erneut.");
			ermittleEingabeAnfang();
		}
	}

	public void fuelleTreeMap() {
		for (int i = 0; i < 9; i++) {
			belegteFelder.put(i, (char) (48 + i));
		}
	}

	private void gibAktuellenStandAus() {

		System.out.println("\n-------------");
		for (int i = 0; i < 9; i+=3) {
			System.out.printf("|%2s |%2s |%2s |", belegteFelder.get(i), belegteFelder.get(i+1), belegteFelder.get(i+2));
			System.out.println("\n-------------");
		}
		System.out.println();
	}

	private void erfasseEingabe() throws Exception {

		Scanner s = new Scanner(System.in);

		System.out.print("\nSpieler " + aktuellerSpieler + " ist am Zug!\nGeben Sie eine Feldnummer ein:\n>>");

		try {
			ausgewaehltesFeld = s.nextInt();
			System.out.println("Die Eingabe: " + ausgewaehltesFeld);
			if(pruefeBelegtesFeld(ausgewaehltesFeld)) throw new Exception();
		} catch (Exception e) {
			System.out.println("Es wurde keine passende Eingabe getätigt,\nversuchen Sie es erneut...");
			//e.printStackTrace();
			erfasseEingabe();
		}
	}
	
	public synchronized boolean pruefeBelegtesFeld(int index) {
		
		System.out.println("Zu pruefender Index: "+index);
		if (belegteFelder.get(index) == 'X' || belegteFelder.get(index) == 'O') {
			System.out.println("Feld " + ausgewaehltesFeld + " schon belegt, machen Sie eine erneute Eingabe...");
			return true;
		}
		
		belegteFelder.put(ausgewaehltesFeld, aktuellerSpieler);
		return false;
		
	}

	public boolean pruefeSieg() {

		if ((belegteFelder.get(0) == aktuellerSpieler) && (belegteFelder.get(1) == aktuellerSpieler) && (belegteFelder.get(2) == aktuellerSpieler)) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler+ "' in der ersten Waagerechte und hat somit gewonnen!");
			gewinnerIndexe[0] = 0;
			gewinnerIndexe[1] = 1;
			gewinnerIndexe[2] = 2;
			return true;
		} else if ((belegteFelder.get(3) == aktuellerSpieler) && (belegteFelder.get(4) == aktuellerSpieler) && (belegteFelder.get(5) == aktuellerSpieler)) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler+ "' in der zweiten Waagerechte und hat somit gewonnen!");
			gewinnerIndexe[0] = 3;
			gewinnerIndexe[1] = 4;
			gewinnerIndexe[2] = 5;
			return true;
		} else if ((belegteFelder.get(6) == aktuellerSpieler) && (belegteFelder.get(7) == aktuellerSpieler) && (belegteFelder.get(8) == aktuellerSpieler)) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler+ "' in der dritten Waagerechte und hat somit gewonnen!");
			gewinnerIndexe[0] = 6;
			gewinnerIndexe[1] = 7;
			gewinnerIndexe[2] = 8;
			return true;
		} else if ((belegteFelder.get(0) == aktuellerSpieler) && (belegteFelder.get(3) == aktuellerSpieler) && (belegteFelder.get(6) == aktuellerSpieler)) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler+ "' in der ersten Senkrechte und hat somit gewonnen!");
			gewinnerIndexe[0] = 0;
			gewinnerIndexe[1] = 3;
			gewinnerIndexe[2] = 6;
			return true;
		} else if ((belegteFelder.get(1) == aktuellerSpieler) && (belegteFelder.get(4) == aktuellerSpieler) && (belegteFelder.get(7) == aktuellerSpieler)) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler+ "' in der zweiten Senkrechte und hat somit gewonnen!");
			gewinnerIndexe[0] = 1;
			gewinnerIndexe[1] = 4;
			gewinnerIndexe[2] = 7;
			return true;
		} else if ((belegteFelder.get(2) == aktuellerSpieler) && (belegteFelder.get(5) == aktuellerSpieler) && (belegteFelder.get(8) == aktuellerSpieler)) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler+ "' in der dritten Senkrechte und hat somit gewonnen!");
			gewinnerIndexe[0] = 2;
			gewinnerIndexe[1] = 5;
			gewinnerIndexe[2] = 8;
			return true;
		} else if ((belegteFelder.get(0) == aktuellerSpieler) && (belegteFelder.get(4) == aktuellerSpieler) && (belegteFelder.get(8) == aktuellerSpieler)) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler+ "' in der ersten Diagonale und hat somit gewonnen!");
			gewinnerIndexe[0] = 0;
			gewinnerIndexe[1] = 4;
			gewinnerIndexe[2] = 8;
			return true;
		} else if ((belegteFelder.get(2) == aktuellerSpieler) && (belegteFelder.get(4) == aktuellerSpieler) && (belegteFelder.get(6) == aktuellerSpieler)) {
			System.out.println("Spieler " + aktuellerSpieler + " hat drei '" + aktuellerSpieler+ "' in der zweiten Diagonale und hat somit gewonnen!");
			gewinnerIndexe[0] = 2;
			gewinnerIndexe[1] = 4;
			gewinnerIndexe[2] = 6;
			return true;
		}
		return false;
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

	public int[] getGewinnerIndexe() {
		return gewinnerIndexe;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
