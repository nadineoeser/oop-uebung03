package ueb03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromTest {
	@Test
	void testPalindrom() {
		String[] xs = {
				"a",
				"aa",
				"Ana",
				"Anna",
				"Regallager",
				"Ein Neger mit Gazelle zagt im Regen nie",
		};

		for (String s : xs) {
			System.err.println(s);
			assertTrue(Palindrom.istPalindrom(s));
		}

		String[] zs = {
				"hansdampf",
				"peter pan",
				"quatsch mit sosse",
				"ab"
		};

		for (String s : zs) {
			System.err.println(s);
			assertFalse(Palindrom.istPalindrom(s));
		}
	}
}
