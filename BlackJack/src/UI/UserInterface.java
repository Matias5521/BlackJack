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
		
		Scanner s = new Scanner(System.in);
		
		ermittleSpieler(s);

		if (bjs.pruefeBlackJack()) {
			System.out.println("Es wurde ein Blackjack erreicht:\n" + bjs.ausgabeErgebnisse());
			System.exit(0);
		}
		
		ausgabeAktuellerStand();

		
	

		while (!bjs.isEnde()) {
			
			
			
			for(int i = 0;i<anzSpieler;i++) {
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
					System.err.println("Falsche Eingabe, versuchen Sie es erneut!");
				} catch (RuntimeException re) {
					System.err.println("Es sind bereits alle Karten vergeben oder der aktuelle Spieler kann keine Karte mehr ziehen!");
				}
			}

			
		}
	}
	
	private void ermittleSpieler(Scanner s) {
		
		while(true) {
			
			System.out.println("Wollen Sie einen weiteren Spieler hinzufügen?(y/n)");
			String input = s.nextLine();
			
			if(input.equalsIgnoreCase("y")) {
				
				System.out.println("Geben Sie einen Namen ein:");
				String name = s.nextLine();
				
				bjs.erstelleSpieler(name);

			}else {
				System.out.println("Hinzufügen von Spielern wurde beendet.");
				break;
			}
		}
	}

	private void ausgabeAktuellerStand() {
		System.out.println("Ihr aktueller Stand:\n" + bjs.getHand().toString());
	}

}
