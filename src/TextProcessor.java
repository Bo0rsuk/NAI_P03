
public class TextProcessor {

	public static double[] processText(String text) {
		text = text.toLowerCase();
		text = text.replaceAll("\\s+", "");
		int[] counts = new int[26];
		int totalLetters = 0;

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 'a' && c <= 'z') {
				counts[c - 'a']++;
				totalLetters++;
			}
		}

		if (totalLetters == 0) {
			return new double[26];
		}

		double[] processedText = new double[26];

		for (int i = 0; i < 26; i++) {
			processedText[i] = (double) counts[i] / totalLetters;
		}
		return processedText;
	}
}
