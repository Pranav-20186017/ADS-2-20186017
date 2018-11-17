import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
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
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            String dir = "/Files/t9.csv";
            BinarySearchST<String, Integer> hash = loadDictionary(dir);
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }
    /**
     * method to read the csv file.
     *
     * @param      file  The file
     *
     * @return     { array of strings}.
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }
    /**
     * Loads a dictionary.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static BinarySearchST<String, Integer> loadDictionary(final String file) {
        BinarySearchST<String, Integer>  st =
        new BinarySearchST<String, Integer>();
        // your code goes here
        String[] t9d = toReadFile(file);
        for (int i = 0; i < t9d.length; i++) {
            String token = t9d[i].toLowerCase();
            if (st.contains(token)) {
                st.put(token, st.get(token) + 1);
            } else {
                st.put(token, 1);
            }
        }
        return st;
    }

}
/**
 * Class for t 9.
 */
class T9 {
    /**
     * object for terenary search tries class.
     */
    private TST<Integer> terstr;
    /**
     * Constructs the object.
     *
     * @param      st    { parameter_description }
     */
    public T9(final BinarySearchST<String, Integer> st) {
        // your code goes here
        terstr = new TST<>();
        for (String each : st.keys()) {
            terstr.put(each, st.get(each));
        } 
    }
    // return all possibilities(words), find top k with highest frequency.
    public Iterable<String> getSuggestions(Iterable<String> words, int k) {
        // your code goes here
        MaxPQ<Integer> pq = new MaxPQ<>();
        ArrayList<String> list = new ArrayList<>();
        for(String itr : words) {
            pq.insert(terstr.get(itr));
        }
        for(int i =0; i < k; i++) {
            int temp = pq.delMax();
            for(String word : words) {
                if(temp == terstr.get(word)) {
                    list.add(word);
                }
            }
        }
        Collections.sort(list);
        return list;
    }
    // get all the prefixes that match with given prefix.
    public Iterable<String> getAllWords(String prefix) {
        // your code goes here
        return terstr.keysWithPrefix(prefix);
    }
    public Iterable<String> potentialWords(String t9Signature) {
        // your code goes here
        ArrayList<String> list = new ArrayList<>();
        for(String itr: terstr.keys()) {
            String[] token = itr.split("");
            String number = "";
            for(String ch : token){
                if(ch.equals("a")
                    || ch.equals("b") || ch.equals("c")) {
                    number = number + "2";
                }
                if(ch.equals("d")
                    || ch.equals("e") || ch.equals("f")) {
                    number = number + "3";
                }
                if(ch.equals("g")
                    || ch.equals("h") || ch.equals("i")) {
                    number = number + "4";
                }
                if(ch.equals("j")
                    || ch.equals("k") || ch.equals("l")) {
                    number = number + "5";
                }
                if(ch.equals("m")
                    || ch.equals("n") || ch.equals("o")) {
                    number = number + "6";
                }
                if(ch.equals("p")
                    || ch.equals("q") || ch.equals("r") || ch.equals("s")) {
                    number = number + "7";
                }
                if(ch.equals("t")
                    || ch.equals("u") || ch.equals("v")) {
                    number = number + "8";
                }
                if(ch.equals("w")
                    || ch.equals("x") || ch.equals("y") || ch.equals("z")) {
                    number = number + "9";
                }
            }
            if(number.equals(t9Signature)) {
                list.add(itr);
            }
        } 
        return list;
    }
    // final output
    // Don't modify this method.
    public Iterable<String> t9(String t9Signature, int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}