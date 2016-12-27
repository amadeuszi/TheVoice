import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DataHandlerDirectory extends DataHandler {
	private Parsed parsed;
	
	public DataHandlerDirectory(String[] args) {
		this.parsed = parseArgs(args);
	}
	
	@Override
	public boolean top5() {
		return parsed.isTop5();
	}
	
	@Override
	public boolean count() {
		return parsed.isCount();
	}
	
	
	@Override
	public List<ArtistWrapper> allTheArtists() {
		List<ArtistWrapper> result = new ArrayList<>();
		String[] artistArray = parsed.getArtists();
		String source = parsed.getSource();
		
		for (String artist: artistArray) {
			File[] files = arrayOfFiles(source, artist);
			result.add(new ArtistWrapper(artist, files));
		}
		return result;
	}

	@Override
	public Set<String> filter() {
		return parsed.getFilter(); 
	}
	
	//parses args from main and puts it in Class Parsed which is comfortable
	private static Parsed parseArgs(String[] args) {
		String source = null;
		boolean top5 = false;
		boolean count = false;
		String[] filters = null;
		String[] artists = null;
		int howManyArtists = 0;
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("java") || args[i].equals("TheVoice")) {
				continue;
			}
			
			if (args[i].startsWith("--source=")) {
				source = args[i].replaceFirst("--source=", "");
			}
			
			if (args[i].startsWith("--processors")) {
				if (args[i].contains("top5")) {
					top5 = true;
				}
				if (args[i].contains("count")) {
					count = true;
				}
			}
			
			if (args[i].startsWith("--filters=")) {
				String filterHelper = args[i].replaceFirst("--filters=", "");
				filters = filterHelper.split(":");
			}
		}
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("java") || args[i].equals("TheVoice")) {
				continue;
			}
			
			if (!args[i].startsWith("--source") 
				&& !args[i].startsWith("--processors") 
				&& !args[i].startsWith("--filters")) {
				howManyArtists++;
			}
		}
		
		artists = new String[howManyArtists];
		int index = 0;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("java") || args[i].equals("TheVoice")) {
				continue;
			}
			
			if (!args[i].startsWith("--source") 
				&& !args[i].startsWith("--processors") 
				&& !args[i].startsWith("--filters")) {
				artists[index] = args[i];
				index++;
			}
		}
		return new Parsed(source, filters, artists, top5, count);
	}
	
	//it takes the source folder and artist name and makes an array 
	//of files for this artist
	private static File[] arrayOfFiles(String source, String artist) {
		String directoryName = source;
		if (!directoryName.endsWith("\\")) {
			directoryName += "\\";
		}
		directoryName += artist.replaceAll("_", " ");
		
		File directory = new File(directoryName);
		
		return directory.listFiles();
	}
	
	/* ****** Tests ****** */
	
	public static void parseTests() throws Exception {
		/* *********** Parse Tests ************ */
		/* **First** */
		String[] input = new String[] {"java", "TheVoice",
							"--source-type=file",
							"--source=C:\\ddd\\miszczostwo", 
							"--processors=top5",
							"Napoleon", "Mozart", "Szopen"};
		
		Parsed output = parseArgs(input);
		
		String[] artists = output.getArtists();
		String source = output.getSource();
		boolean isCount = output.isCount();
		boolean isTop5 = output.isTop5();
		
		String[] artistsCheck = new String[] {"Napoleon", "Mozart", "Szopen"};
		String sourceCheck = new String("C:\\ddd\\miszczostwo");
		boolean isCountCheck = false;
		boolean isTop5Check = true;
		
		assert (Arrays.deepEquals(artists, artistsCheck));
		assert source.equals(sourceCheck);
		assert isCount == isCountCheck;
		assert isTop5 == isTop5Check;
		
		/* ** Second ** */
		input = new String[] {"java", "TheVoice",
				"--source-type=file",
				"--source=C:\\gol\\", 
				"--processors=top5,count", 
				"Napoleon", "Mozart", "Szopen"};

		output = parseArgs(input);
		
		artists = output.getArtists();
		source = output.getSource();
		isCount = output.isCount();
		isTop5 = output.isTop5();
		
		artistsCheck = new String[] {"Napoleon", "Mozart", "Szopen"};
		sourceCheck = new String("C:\\gol\\");
		isCountCheck = true;
		isTop5Check = true;
		
		assert (Arrays.deepEquals(artists, artistsCheck));
		assert source.equals(sourceCheck);
		assert isCount == isCountCheck;
		assert isTop5 == isTop5Check;
		/* *********** End of Parse Tests ********** */
	}
	
	public static void arrayOfFilesTests() throws Exception {
	/* *********** arrayOfFiles Tests ******** */
	new File("testFiles\\Madonna").mkdirs();
	PrintWriter writer1 = new PrintWriter("testFiles\\Madonna\\file1", "UTF-8");
	PrintWriter writer2 = new PrintWriter("testFiles\\Madonna\\file2", "UTF-8");
	PrintWriter writer3 = new PrintWriter("testFiles\\Madonna\\file3", "UTF-8");
	writer1.close();
	writer2.close();
	writer3.close();
	
	
	File[] files = arrayOfFiles("testFiles", "Madonna");
	
	String[] filesNames = new String[3];
	
	for (int i = 0; i < files.length; i++) {
		filesNames[i] = files[i].getPath();
	}
	
	String[] filesCheck = 
			new String[] {"testFiles\\Madonna\\file1", "testFiles\\Madonna\\file2", "testFiles\\Madonna\\file3" };
	
	
	assert Arrays.deepEquals(filesNames, filesCheck);
	/* **** End of arrayOfFiles Tests **** */
	}
}
