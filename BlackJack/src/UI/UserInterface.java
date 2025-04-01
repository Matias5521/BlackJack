package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import Logik.BlackJackSpiel;

public class UserInterface {
	
	private BlackJackSpiel bjs;

	public UserInterface(BlackJackSpiel bjs) {

		if(bjs != null) {
			this.bjs = bjs;
			spiel();
		}else{
			System.out.println("Spiel konnte nicht gestartet werden.");
		}	
	}
	
	private void spiel() {
		
		System.out.println("Das Spiel wurde gestartet.\nEs kann nur ein Spieler spielen und dass auch nur einmal\nUm erneut zu spielen muss das Programm neu gestartet werden.");

		bjs.getNeueHand();
		
		if (bjs.pruefeEnde()) {
			bjs.verteilePunkteNeu(); //hier wird eventuell ein Ass Wert neu belegt
			System.out.println(
					"Es wurden 21 oder mehr Punkte erreicht, der Versuch ist beendet!\nDas Ergebnis ist: "
							+ bjs.ausgabePunkteKarte());
			System.exit(0);
		}
		
		if (bjs.pruefeBlackJack()) {
			System.out.println("Es wurde ein Blackjack erreicht:\n" + bjs.ausgabeHand());
			System.exit(0);
		}

		Scanner s = new Scanner(System.in);

		while (!bjs.isEnde()) {

			ausgabeAktuellerStand();

			System.out.println("Wollen Sie eine weitere Karte ziehen? (j/n)");

			try {
				String input = s.nextLine();

				if (input.equalsIgnoreCase("j")) {
					bjs.fuegeKarteHinzu();
					ausgabeAktuellerStand();
				} else if (input.equalsIgnoreCase("n")) {
					System.out.println("Das Ergebnis des Versuchs ist: " + bjs.ausgabePunkteKarte());
					break;
				} else {
					throw new InputMismatchException();
				}

				if (bjs.pruefeEnde()) {
					bjs.verteilePunkteNeu(); //hier wird eventuell ein Ass Wert neu belegt
					System.out.println(
							"Es wurden 21 oder mehr Punkte erreicht, der Versuch ist beendet!\nDas Ergebnis ist: "
									+ bjs.ausgabePunkteKarte());
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Falsche Eingabe, versuchen Sie es erneut!");
			}
		}
	}

	private void ausgabeAktuellerStand() {
		System.out.println("Ihr aktueller Stand:\n" + bjs.getHand().toString());
	}

}
