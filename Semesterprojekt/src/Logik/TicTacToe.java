package Logik;

import java.awt.Color;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JButton;

import Anzeige.GUI;

public class TicTacToe {

	private GUI gui;

	private TreeMap<Integer, Character> belegteFelder = new TreeMap<>();
	private int[] gewinnerIndexe = new int[3];

	private int counter, ausgewaehltesFeld, modus;
	private char aktuellerSpieler;
	private boolean aiIstDran;

	public TicTacToe() throws Exception {

		ermittleEingabeAnfang();

	}

	public void ermittleEingabeAnfang() throws Exception {

		resetGame();

		Scanner s = new Scanner(System.in);
		System.out.println(
				"\nWollen Sie mit einer GUI('gui' eingeben) oder mit der Konsole('konsole' eingeben) spielen?\nZum beenden einfach 'ende' eingeben.");
		System.out.print(">>");
		String befehl = s.nextLine();

		if (befehl.equalsIgnoreCase("gui")) {

			ermittleModus();

			if (modus == 1 || modus == 2) {

				if (modus == 2)
					System.out.println("Sie sind X die AI ist O.");
				System.out.println("Um ein Feld für ein Zug auszuwählen muss dieses Feld angeklickt werden...");

				gui = new GUI(this);

			}

		} else if (befehl.equalsIgnoreCase("konsole")) {

			ermittleModus();

			System.out.println(
					"Die Felder sind von 0 bis 8 durchnummerriert.\nUm ein Feld für ein Zug auszuwählen muss die Zahl des Feldes eingegeben werden...");
			gibAktuellenStandAus();

			for (int i = 0; i < 9; i++) {

				erfasseEingabe();
				gibAktuellenStandAus();

				if (i >= 4 && werHatGewonnen(belegteFelder) != null)
					break;

				wechselAktuellerSpieler();

			}

			if (werHatGewonnen(belegteFelder) == 'D')
				System.out.println("Unentschieden!");
			else
				System.out.println(aktuellerSpieler + " hat gewonnen!");

			ermittleEingabeAnfang();

		} else if (befehl.equalsIgnoreCase("ende")) {
			System.out.println("Spiel wird beendet.");
			System.exit(0);
		} else {
			System.out.println("Eingabe ungültig!\nVersuchen Sie es erneut.");
			ermittleEingabeAnfang();
		}
	}

	private void ermittleModus() {

		Scanner s = new Scanner(System.in);

		System.out.println(
				"Geben Sie die jeweilige Zahl für den Spielmodus ein: (Player vs. Player -> 1), (Player vs. AI -> 2)");
		System.out.print(">>");

		try {
			modus = s.nextInt();

			if (modus < 1 || modus > 3) {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println("Es wurde keine gültige Eingabe getätigt!");
			ermittleModus();
		}

	}

	private void fuelleTreeMap() {
		for (int i = 0; i < 9; i++) {
			belegteFelder.put(i, (char) (48 + i));
		}
	}

	private void gibAktuellenStandAus() {

		System.out.println("\n-------------");
		for (int i = 0; i < 9; i += 3) {
			System.out.printf("|%2s |%2s |%2s |", belegteFelder.get(i), belegteFelder.get(i + 1),
					belegteFelder.get(i + 2));
			System.out.println("\n-------------");
		}
		System.out.println();
	}

	private void erfasseEingabe() throws Exception {

		Scanner s = new Scanner(System.in);

		System.out.print("\nSpieler " + aktuellerSpieler + " ist am Zug!\nGeben Sie eine Feldnummer ein:\n>>");

		try {

			if (modus == 1 || modus == 2 && aktuellerSpieler == 'X')

				ausgewaehltesFeld = s.nextInt();

			if (modus == 2 && aiIstDran || modus == 3) {

				ausgewaehltesFeld = makeTurn();

			}

			System.out.println("Die Eingabe: " + ausgewaehltesFeld);
			if (pruefeBelegtesFeld(ausgewaehltesFeld))
				throw new Exception();

		} catch (Exception e) {
			System.out.println("Es wurde keine passende Eingabe getätigt,\nversuchen Sie es erneut...");
			if (modus == 1 || modus == 2 && aktuellerSpieler == 'X')
				erfasseEingabe();
		}
	}

	private int makeTurn() {
		int bestMove = -1;
		int bestScore = Integer.MIN_VALUE; // Maximierung für AI (O)

		for (int i = 0; i < 9; i++) {
			if (belegteFelder.get(i) != 'X' && belegteFelder.get(i) != 'O') {
				belegteFelder.put(i, 'O'); // Temporär setzen
				int moveScore = minimax(new TreeMap<>(belegteFelder), false);
				belegteFelder.put(i, (char) ('0' + i)); // Zug zurücksetzen

				if (moveScore > bestScore) {
					bestScore = moveScore;
					bestMove = i;
				}
			}
		}
		return bestMove;
	}

	private int minimax(TreeMap<Integer, Character> board, boolean isMaximizing) {

		Character winner = werHatGewonnen(board);

		if (winner != null) {
			if (winner == 'X')
				return -1;
			if (winner == 'O')
				return 1;
			if (winner == 'D')
				return 0;
		}

		if (isMaximizing) {
			int bestScore = Integer.MIN_VALUE;
			for (int i = 0; i < 9; i++) {
				if (board.get(i) != 'X' && board.get(i) != 'O') {
					board.put(i, 'O');
					int score = minimax(board, false);
					board.put(i, (char) ('0' + i)); // Zug zurücksetzen
					bestScore = Math.max(score, bestScore);
				}
			}
			return bestScore;

		} else {
			int bestScore = Integer.MAX_VALUE;
			for (int i = 0; i < 9; i++) {
				if (board.get(i) != 'X' && board.get(i) != 'O') {
					board.put(i, 'X');
					int score = minimax(board, true);
					board.put(i, (char) ('0' + i)); // Zug zurücksetzen
					bestScore = Math.min(score, bestScore);
				}
			}
			return bestScore;
		}
	}

	private boolean pruefeBelegtesFeld(int index) {

		// System.out.println("Zu pruefender Index: " + index+"| Inhalt:
		// "+belegteFelder.get(index));
		if (belegteFelder.get(index) == 'X' || belegteFelder.get(index) == 'O') {
			System.out.println("Feld " + index + " schon belegt, machen Sie eine erneute Eingabe...");
			return true;
		} else {
			belegteFelder.put(index, aktuellerSpieler);
		}

		System.out.println("Feld " + index + " wurde mit " + aktuellerSpieler + " belegt");
		return false;

	}

	private Character werHatGewonnen(TreeMap<Integer, Character> board) {

		int[][] gewinnKombinationen = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // Reihen
				{ 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // Spalten
				{ 0, 4, 8 }, { 2, 4, 6 } // Diagonalen
		};

		for (int[] kombi : gewinnKombinationen) {
			if (board.get(kombi[0]) == board.get(kombi[1]) && board.get(kombi[1]) == board.get(kombi[2])
					&& (board.get(kombi[0]) == 'X' || board.get(kombi[0]) == 'O')) {
				gewinnerIndexe = kombi;
				return board.get(kombi[0]); // Gibt 'X' oder 'O' zurück
			}
		}

		// Prüfen, ob noch leere Felder existieren
		for (int i = 0; i < 9; i++) {
			if (board.get(i) != 'X' && board.get(i) != 'O') {
				return null; // Es gibt noch leere Felder → Spiel geht weiter
			}
		}

		return 'D';
	}

	public void actionListenerInhalt(JButton b) {

		int index = gui.getButtons().indexOf(b);

		if (werHatGewonnen(belegteFelder) == null && !pruefeBelegtesFeld(index)) {

			b.setText("" + aktuellerSpieler);

			if (counter >= 4 && werHatGewonnen(belegteFelder) != null) {

				if (werHatGewonnen(belegteFelder) != 'D') {
					for (int i = 0; i < 3; i++) {
						gui.getButtons().get(gewinnerIndexe[i]).setForeground(Color.RED);
						gui.getButtons().get(gewinnerIndexe[i]).setText("" + aktuellerSpieler);
					}
				}

				return;

			}

			counter++;
			wechselAktuellerSpieler();

			if (modus == 2 && aiIstDran) {
				actionListenerInhalt(gui.getButtons().get(makeTurn()));
			}
		}
	}

	private void wechselAktuellerSpieler() {

		if (aktuellerSpieler == 'X') {
			aktuellerSpieler = 'O';
			if (modus == 2)
				aiIstDran = true;
		} else {
			aktuellerSpieler = 'X';
			if (modus == 2)
				aiIstDran = false;
		}
	}

	public void resetGame() {

		counter = 0;
		aktuellerSpieler = 'X';
		aiIstDran = false;

		fuelleTreeMap();

	}

	public int getModus() {
		return modus;
	}

	public void setModus(int modus) {
		this.modus = modus;
	}

	public boolean isAiIstDran() {
		return aiIstDran;
	}
}
