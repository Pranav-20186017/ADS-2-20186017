import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {
		//unused.
	}
	/**
	 * main method.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		String[] words = loadWords();
		Scanner sc = new Scanner(System.in);
		TST<Integer> tst = new TST<Integer>();
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				String str = words[i].substring(j);
                if (!tst.contains(str)) {
                    tst.put(str, i);
                }
			}
		}
		String tokens = sc.nextLine();
        for (String s : tst.keysWithPrefix(tokens)) {
            System.out.println(s);
        }
	}
	/**
	 * Loads words.
	 *
	 * @return     { description_of_the_return_value }
	 */
	public static String[] loadWords() {
		In in = new In("/Files/dictionary-algs4.txt");
		String[] words = in.readAllStrings();
		return words;
	}
}