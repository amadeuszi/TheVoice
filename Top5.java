import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Top5 {
	
	private static Pair top5One(String artist, File[] files, Set<String> filter) {
		Map<String, Integer> dictionary = new Hashtable<String, Integer>(1000);
		
		for (File f : files) {
			String[] text = Operations.createArrayOfStringsFromFile(f);
			
			for (String s : text) {
				if (s.equals("") || s.equals("\n") || filter.contains(s)) {
					continue;
				}
				
				if (dictionary.containsKey(s)) {
					int value = dictionary.get(s);
					dictionary.put(s, value + 1);
				}
				else {
					dictionary.put(s, 1);
				}
			}
		}
		
		int n = 5;
		String[] words = new String[n];
		int[] counter = new int[n];
		
		for (int i = 0; i < n; i++) {
			String theBestString = findStringWithTheGreatestAmountOfOccurances(dictionary);
			int howMany = dictionary.get(theBestString);
			words[i] = theBestString;
			counter[i] = howMany;
			dictionary.remove(theBestString);
		}
		
		return new Pair(artist, words, counter);
	}
	
	public static List<Pair> top5All(DataHandler data) {
		List<Pair> result = new ArrayList<>();
		
		List<ArtistWrapper> artistList = data.allTheArtists();
		Set<String> filter = data.filter();
		
		for (ArtistWrapper art : artistList) {
			String artist = art.getArtist();
			File[] files = art.getFiles();
			result.add(top5One(artist, files, filter));
		}
		
		return result;
	}
	
	public static String findStringWithTheGreatestAmountOfOccurances(Map<String, Integer> dictionary) {
		int max = 0;
		String result = null;
		for (String key : dictionary.keySet()) {
			int tmp = dictionary.get(key);
			if (tmp >= max) {
				if (tmp == max && key.compareTo(result) >= 0) {
					continue;
				}
				max = tmp;
				result = key;
			}
		}
		return result;
	}
	
}
