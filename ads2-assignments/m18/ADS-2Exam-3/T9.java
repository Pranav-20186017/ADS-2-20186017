import java.util.Collections;
import java.util.ArrayList;
class T9 {
    TST<Integer> terstr;
    public T9(BinarySearchST<String, Integer> st) {
        // your code goes here
        terstr = new TST<>();
        for(String each : st.keys()) {
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