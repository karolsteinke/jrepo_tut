import java.util.ArrayList;
import java.util.function.Predicate;

public class RosterTest {
    
    //Approach 6: Use Standard Functional Interfaces with Lambda Expressions
    //'tester' is type of functional interface, so it's possible to use lambda expr. later, in method invocation
    //also: we use one of standard interfaces Preface<T> from java.util.fucntion package
    public static void printPersonsWithPredicate(ArrayList<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        ArrayList<Person> roster = new ArrayList<>();
        Person a = new Person("a",10,Person.Sex.MALE,"a@mail.com");
        Person b = new Person("b",20,Person.Sex.MALE,"b@mail.com");
        Person c = new Person("c",30,Person.Sex.MALE,"c@mail.com");
        roster.add(a);
        roster.add(b);
        roster.add(c);

        //Approach 6: Use Standard Functional Interfaces with Lambda Expressions
        System.out.println("Persons who are aligable for Selective Service (with Predicate parameter):");
        printPersonsWithPredicate(
            roster, 
            p -> p.getGender() == Person.Sex.MALE 
                && p.getAge() >= 18 
                && p.getAge() <= 25
        );
        System.out.println();

        //Approach 9: Use Bulk Data Opeartions That Accept Lambda Expressions
        System.out.println("Persons who are eligible for Selective Service (with bulk data operations):");
        roster
            .stream()
            .filter(
                p -> p.getGender() == Person.Sex.MALE
                    && p.getAge() >= 18
                    && p.getAge() <= 25)
            .map(p -> p.getEmailAddress())
            .forEach(email -> System.out.println(email));
    }
}
