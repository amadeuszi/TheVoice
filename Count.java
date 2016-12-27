import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Count {
	
	//it counts howMany distinct words are there in files
	//files should belong to one artist, but it isn't compulsory
	private static int countOne(File[] files, Set<String> filter) {
		
		Set<String> words = new HashSet<>(2000);
		
		for (int i = 0; i < files.length; i++) {
			
			String[] wordsHelper = Operations.createArrayOfStringsFromFile(files[i]);
			
			for (String s : wordsHelper) {
				if (!words.contains(s) && !filter.contains(s) && !s.equals("\n") && !s.equals("")) {
					words.add(s);
				}
			}
		}
		return words.size();
	}
	
	//it counts distinct  words for all of the artists 
	public static List<Pair2> countAll(DataHandler data) {
		List<Pair2> result = new ArrayList<Pair2>();
		
		List<ArtistWrapper> artistList = data.allTheArtists();
		Set<String> filter = data.filter();
		
		for (ArtistWrapper art: artistList) {
			String artist = art.getArtist();
			File[] files = art.getFiles();
			int howMany = countOne(files, filter);
			
			result.add(new Pair2(artist, howMany));
		}
		
		return result;
	}
}


