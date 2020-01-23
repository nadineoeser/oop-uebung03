package ueb03;

class Duplikate {
	/**
	 * Gibt ein Set mit den Wörtern zurück, welche mindestens zwei mal im Text vorkommen.
	 * Alle Satzzeichen im Text sollen ignoriert werden.
	 * @param text Eingabetext, kann Satzzeichen enthalten welche ignoriert werden.
	 * @return Set mit den Wörtern, welche mind. zwei mal vorkommen.
	 */
	static Set findeDuplikate(String text) {
		// nur Zeichen und Leerzeichen zulassen
		StringBuilder sb = new StringBuilder();
		for (char c : text.toCharArray()) {
			if (Character.isAlphabetic(c) || c == ' ')
				sb.append(c);
		}
		text = sb.toString();

		Set<String> s1 = new SetImpl<>();
		Set<String> s2 = new SetImpl<>();

		for (String w : text.split(" ")) {
			// kennt das erste Set das Wort bereits, so tritt es zum 2. mal auf!
			if (s1.contains(w))
				s2.add(w);

			// jedes Wort in das erste Set einfuegen
			s1.add(w);
		}

		return s2;
	}
}
