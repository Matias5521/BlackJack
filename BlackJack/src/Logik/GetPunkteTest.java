package Logik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetPunkteTest {

	@Test
	void testPunkte() {
		Karte k = new Karte("Schwarz", "Pik-4");
		assertEquals(4,k.getPunkte());
		
	}
}
