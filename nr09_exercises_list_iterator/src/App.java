import java.util.*;

public class App {
    static void trimElements(List<String> list) {
        //ListIterator allows to repace an existing elements
        for (ListIterator<String> lit = list.listIterator(); lit.hasNext(); ) {
            lit.set(lit.next().trim());
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList(" red ", " white ", " blue ");
        
        trimElements(list);
        System.out.println(list);
    }
}