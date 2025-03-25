import java.util.*;

public class BlackJackSpiel {
	
	private Hand hand;
	private Kartenstapel ks = new Kartenstapel();
	private boolean ende = false;

	public BlackJackSpiel(Kartenstapel ks) {
		
		this.ks = ks;
		
	}
	
	public Hand getNeueHand() {
		hand = new Hand(ks);
		return hand;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public boolean isEnde() {
		return ende;
	}
	
}
