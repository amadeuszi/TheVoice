import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//the class is only used for normal data handler, 
//but isn't good for other data handlers
public class Parsed {
	private String source; //source folder
	private String[] filters; //paths to files containing filters
	private Set<String> filter; //words that are bound to be filtered
	private String[] artists; //artists names
	private boolean top5; //determines whether I have to make top5
	private boolean count; //determines whether I have to make count

	public Parsed(String source, String[] filters, String[] artists, boolean top5, boolean count) {
		super();
		this.source = source;
		this.filters = filters;
		this.artists = artists;
		this.top5 = top5;
		this.count = count;
		
		makeFilterSet();
	}
	
	//it processes filter paths and constructs Set of Strings
	//that are bound to be filtered
	private void makeFilterSet() {
		filter = new HashSet<String>();
		
		if (filters == null) {
			return;
		}
		
		for (String f : filters) {
			String[] array = Operations.createArrayOfStringsFromFile(new File(f));
			filter.addAll(Arrays.asList(array));
		}
	}
	
	public String getSource() {
		return source;
	}
	public Set<String> getFilter() {
		return this.filter;
	}
	public String[] getFilters() {
		return this.filters;
	}
	public String[] getArtists() {
		return artists;
	}
	public boolean isTop5() {
		return top5;
	}
	public boolean isCount() {
		return count;
	}
}
