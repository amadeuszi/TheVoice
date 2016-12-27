
public class Pair2 {
	private String artist;
	private int howManyWords;
	
	public Pair2(String artist, int howManyWords) {
		this.artist = artist;
		this.howManyWords = howManyWords;
	}
	
	@Override
	public String toString() {
		return artist.replaceAll("_", " ") + " " + howManyWords;
	}

	public String getArtist() {
		return this.artist;
	}
	
	public int getHowManyWords() {
		return this.howManyWords;
	}
}
