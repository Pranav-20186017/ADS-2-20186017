import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Brute {
    // return offset of first match or N if no match
    public static int search2(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            if (txt.charAt(i) == pat.charAt(j)) j++;
            else {
                i -= j;
                j = 0;
            }
        }
        if (j == m) return i - m;    // found
        else        return n;        // not found
    }
    /** 
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws IOException{
    	long startTime = System.currentTimeMillis();
        String pat = "It is a far, far better thing that I do, than I have ever done";
        String txt = new String(Files.readAllBytes(Paths.get("tale.txt")));
        int offset2a = search2(pat, txt);
        // from brute force search method 2a
        if (offset2a != txt.length()) {
        	System.out.println("Pattern Found");
        for (int i = offset2a; i < offset2a + pat.length(); i++) {
        	System.out.print(txt.charAt(i));
        }
       } else {
       	System.out.println("Pattern Not Found");
       }
       long endTime = System.currentTimeMillis();
       System.out.println("\nIt took " + (endTime - startTime) + " milliseconds");
       System.out.println(endTime - startTime);
    }
}