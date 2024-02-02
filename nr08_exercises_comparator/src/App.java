import java.util.*;

public class App {
    static final Comparator<String> IGNORE_CASE_ORDER = new Comparator<String>() {
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }
    };
    
    // ******************** STATIC MAIN ********************
    public static void main(String[] args) {
        String[] someArgs = {"a", "b", "bc", "c", "ab", "ab", "g", "a", "e"};

        //Comparator<String> comp = (first, second) -> first.compareTo(second);
        SortedSet<String> s = new TreeSet<String>(IGNORE_CASE_ORDER);
        
        for (String a : someArgs) s.add(a);

        System.out.println(s.size() + " distinct words: " + s);
    }
}
