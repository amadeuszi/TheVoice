
public class Pair {
	private String artist;
	private String[] words;
	private int[] counter;
	
	public Pair(String artist, String[] words, int[] counter) {
		this.artist = artist;
		this.words = words;
		this.counter = counter;
	}
	
	@Override
	public String toString() {
		String result = artist.replaceAll("_", " ");
		result += "\n";
		result += "[";
		for (int i = 0; i < words.length; i++) {
			result += words[i] + "=" + counter[i];
			if (i < words.length - 1) {
				result += ", ";
			}
		}
		result += "]";
		return result;
	}
	
	public String[] getWords() {
		String[] hider = new String[words.length];
		
		for (int i = 0; i < words.length; i++) {
			hider[i] = new String(words[i]);
		}
		return hider;
	}
	public int[] getCounter() {
		return this.counter;
	}
	public String getArtist() {
		return this.artist;
	}
}
