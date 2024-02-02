import java.util.*;

public class App {

    
    // ******************** STATIC MAIN ********************
    public static void main(String[] args) {
        String[] someArgs = {"a", "b", "bc", "c", "ab", "d", "g", "f", "e"};
        
        //view array as a list - it's a view, not a copy
        List<String> list = Arrays.asList(someArgs);
        Collections.shuffle(list);

        //Exercise a) print out with for statement
        for (String a : list) System.out.print(a + " ");
        System.out.println();

        //Exercise b) print out with stream
        list.stream().forEach(a -> System.out.print(a + " "));
        System.out.println();
    }
}
