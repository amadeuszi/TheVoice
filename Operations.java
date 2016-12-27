import java.io.File;
import java.nio.file.Files;

public class Operations {
	//it reads the whole file and returns all of the words in this file
	public static String[] createArrayOfStringsFromFile(File file) {
		byte bytes[];
		String text;
		try {
			bytes = Files.readAllBytes(file.toPath());
			text = new String(bytes, "UTF-8");
		}
		catch(Exception e) {
			System.out.println("Blad przy wczytywaniu pliku: " + file.getPath());
			System.out.println("Wyjatek: " + e.toString());
			return new String[] {""};
		}
		
		String[] wordsHelper = text.split("[\\s,.:;?!()-]+");
		for (int i = 0; i < wordsHelper.length; i++) {
			wordsHelper[i] = wordsHelper[i].toLowerCase();
		}
		return wordsHelper;
	}
}
