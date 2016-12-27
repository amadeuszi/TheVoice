import java.util.Comparator;

public class Pair2Comparator implements Comparator<Pair2> {
	@Override
	public int compare(Pair2 x, Pair2 y) {
		return -((Integer)x.getHowManyWords()).compareTo(y.getHowManyWords());
	}
}
