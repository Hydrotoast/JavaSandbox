import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

class CamelCasing {
	private static final int CONVERT = 32;

	private static String parseWord(String word) {
		if (word.isEmpty())
			return "";

		char firstChar = word.charAt(0);
		if ((firstChar + "").matches("[a-z]"))
			firstChar = (char) (firstChar - CONVERT);
		return (firstChar + word.substring(1));
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Insufficient arguments\n");
			return;
		}

		Scanner s = null;

		try {
			s = new Scanner(new FileReader(args[0]));
			String buffer = "";
			String[] words;

			while (s.hasNext()) {
				words = s.nextLine().split("\\s+");

				if (words.length > 2) {
					for (String word : words)
						buffer += parseWord(word);

					buffer += System.getProperty("line.separator");
				}
			}

			buffer = (char) ((buffer.charAt(0) + "").matches("[A-Z]") ? (char) (buffer
					.charAt(0)) + CONVERT
					: (char) (buffer.charAt(0)))
					+ buffer.substring(1);
			System.out.println(buffer);
		} catch (FileNotFoundException ex) {
			System.err.println("Put a damn file\n");
		} finally {
			if (s != null)
				s.close();
		}
	}
}