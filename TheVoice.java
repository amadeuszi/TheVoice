import java.util.List;

public class TheVoice {
	public static void main(String[] args) throws Exception {
		DataHandler data = new DataHandlerDirectory(args);
		System.out.println("***");
		if (data.count()) {
			List<Pair2> list = Count.countAll(data);
			list.sort(new Pair2Comparator());
			System.out.println("count:");
			for(Pair2 pair: list) {
				System.out.println(pair);
			}
		}
		System.out.println("***");
		if (data.top5()) {
			List<Pair> list = Top5.top5All(data);
			System.out.println("top5:");
			for (Pair pair : list) {
					System.out.println(pair);
				}
			}
		System.out.println("***");
	}	
}

