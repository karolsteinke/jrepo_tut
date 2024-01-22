public class Person {
    
    public enum Sex {
        MALE, FEMALE
    }
    
    String name;
    int age;
    Sex gender;
    String emailAddress;

    public Person(String name, int age, Sex gender, String emailAddress) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        return this.age;
    }

    public Sex getGender() {
        return this.gender;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void printPerson() {
        System.out.println("Person " + name + " is " + age + "years old");
    }
}
