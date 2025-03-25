import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		BlackJackSpiel bjs = new BlackJackSpiel(new Kartenstapel());
		
		System.out.println("Das Spiel wurde gestartet.\nEs kann nur ein Spieler spielen und dass auch nur einmal\n Um erneut zu spielen muss das Programm neu gestartet werden.");
		
		bjs.getNeueHand();
		
		Scanner s = new Scanner(System.in);
		
		while(!bjs.isEnde()) {
			
			if (bjs.getHand().isBlackJack()) {
				System.out.println("Es wurde ein Blackjack erreicht:\n"+bjs.getHand().toString());
				System.exit(0);
			}
			
			System.out.println("Wollen Sie eine weitere Karte ziehen? (j/n)");
			
			try {
				String input = s.nextLine();
				
				if(input.equalsIgnoreCase("j")) {
					bjs.getHand().addKarte();
				}else if(input.equalsIgnoreCase("n")) {
					System.out.println("Das Ergebnis des Versuchs ist: "+bjs.getHand().getPunkte());
					break;
				}else{
					throw new InputMismatchException();
				}
				
				System.out.println("Ihr aktueller Stand:\n"+bjs.getHand().toString());
				
				if(bjs.getHand().getPunkte() >= 21) {
					System.out.println("Es wurden 21 oder mehr Punkte erreicht, der Versuch ist beendet!"); 
					break;
				}
					
			}catch(InputMismatchException e){
				System.out.println("Falsche Eingabe, versuchen Sie es erneut!");
			}catch(RuntimeException e) {
				System.out.println("Der nächste Spieler ist dran oder es sind keine Karten mehr übrig.");
				break;
			}finally {
				System.out.println("Ihre aktuellen Punkte: "+bjs.getHand().getPunkte());
				
			}
		}

	}

}
