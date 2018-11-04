import java.util.Hashtable;
class WebSearch {
	PageRank pagerank;
	Hashtable<String, Bag<Integer>> ht;
	WebSearch(PageRank rankobj, String filename) {
		pagerank = rankobj;
		In newfile = new In(filename);
		ht = new Hashtable<>();
		while (newfile.hasNextLine()) {
			String line = newfile.readLine();
			String[] tokens = line.split(":");
			for (String word : tokens[1].split(" ")) {
				if (ht.containsKey(word)) {
					Bag bag = ht.get(word);
					bag.add(Integer.parseInt(tokens[0]));
					ht.put(word, bag);
				} else {
					ht.put(word, new Bag<Integer>());
					Bag bag = ht.get(word);
					bag.add(Integer.parseInt(tokens[0]));
					ht.put(word, bag);
				}
			}
		}
	}
	int iAmFeelingLucky(String input) {
		if(!ht.containsKey(input)) {
			return -1;
		}
		Bag<Integer> bag = ht.get(input);
		Double pr = -1.0;
		int id = -1;
		for (Integer everyid : bag) {
			if (pagerank.getPR(everyid) > pr) {
				pr = pagerank.getPR(everyid);
				id = everyid;
			}
		}
		return id;
	}
	void printkeys() {
		for (String eachkey : ht.keySet()) {
			System.out.println(eachkey);
		}
	}

}