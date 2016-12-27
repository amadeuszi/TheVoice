import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class Tests {
	
	/* Copied from stackoverflow, but it is so simple that I will not change it */
	private static void delete(File f) throws IOException {
		  if (f.isDirectory()) {
		    for (File c : f.listFiles())
		      delete(c);
		  }
		  if (!f.delete())
		    throw new FileNotFoundException("Failed to delete file: " + f);
		}
	/* End of Stackoverflow */
	
	public static void moduleTests() throws Exception {
		/* ****** Parse Tests ******* */
		DataHandlerDirectory.parseTests();
		/* *** End of Parse Tests *** */
		
		/* ****** ArrayOfFiles Tests ****** */
		DataHandlerDirectory.arrayOfFilesTests();
		/* *** End of ArrayOfFilesTests *** */
		
		PrintWriter writer1 = new PrintWriter("testFiles\\Madonna\\file1", "UTF-8");
		
		/* ******** createArrayOfStringsFromFile Test ******** */
		writer1.println("Zastanowmy sie, jak aktualizowac te tablice.");
		writer1.println("Zauwazmy, ze liczby w niej zawarte tworza ciag niemalejacy.");
		writer1.println("sie jak sie sie jak tworza sie");
		writer1.close();
		File testingFile = new File("testFiles\\Madonna\\file1");
		
		String[] words = Operations.createArrayOfStringsFromFile(testingFile);
		
		String[] wordsCheck = new String[] {
				"zastanowmy",  "sie", "jak", "aktualizowac", "te", "tablice",
				"zauwazmy", "ze", "liczby", "w", "niej", "zawarte", "tworza", 
				"ciag", "niemalejacy",
				"sie", "jak", "sie", "sie", "jak", "tworza", "sie"
		};
		
		assert Arrays.deepEquals(words, wordsCheck);
		/* **** End of createArrayOfStringsFromFile Tests **** */
		
		/* ******* Count Tests ******* */
		new File("testFiles\\filters").mkdir();
		PrintWriter writer4 = new PrintWriter("testFiles\\filters\\filter", "UTF-8");
		writer4.println("amek jak ,tworza:,");
		writer4.close();
		
		String[] input2 = new String[] {"--source=testFiles", 
				"Madonna", "--processors=count", "--filters=testFiles\\filters\\filter"};
		DataHandler data = new DataHandlerDirectory(input2);
		List<Pair2> pairs = Count.countAll(data);
		
		assert 13 == pairs.get(0).getHowManyWords();
		/* *** End of Count Tests **** */
		
		/* ****** Top5 Tests ******* */
		List<Pair> pairs2 = Top5.top5All(data);
		assert pairs2.get(0).getWords()[0].equals("sie");
		assert pairs2.get(0).getWords()[1].equals("aktualizowac");
		/* *** End of Top5 Tests *** */
		
		System.out.println("Wszystko ok!!!!");
		
		delete(new  File("testFiles"));
	}
}
