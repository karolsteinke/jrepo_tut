import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        String[] arr = {"I", "came", "I", "saw", "I", "left"};
        
        Set<String> uniques = new HashSet<>();
        Set<String> dups = new HashSet<>();

        for (String a : arr) {
            //method add() return true if it adds successfully
            if (!uniques.add(a)) dups.add(a);
        }

        //Destructive set-difference
        uniques.removeAll(dups);
        
        System.out.println("Unique words: " + uniques);
        System.out.println("Duplicate words" + dups);
    }
}