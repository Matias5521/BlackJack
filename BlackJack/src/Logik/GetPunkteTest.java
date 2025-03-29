package Logik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetPunkteTest {

	@Test
	void test() {
		assertEquals(4, new Karte("Schwarz", "Pik-4").getPunkte());
	}

}
