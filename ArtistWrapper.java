import java.io.File;

public class ArtistWrapper {
	private String artist;
	private File[] files;
	
	public ArtistWrapper(String artist, File[] files) {
		this.artist = artist;
		this.files = files;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public File[] getFiles() {
		File[] hider = new File[files.length];
		for (int i = 0; i < files.length; i++) {
			hider[i] = new File(files[i].getPath());
		}
		return hider; 
	}
}
