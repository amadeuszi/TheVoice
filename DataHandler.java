import java.util.List;
import java.util.Set;

public abstract class DataHandler {
	
	public abstract List<ArtistWrapper> allTheArtists();
	public abstract Set<String> filter();
	public abstract boolean top5();
	public abstract boolean count();
	
}
