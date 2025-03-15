package sk;

public class CrudExampleMain {
    public static void main(String[] args) {
        System.out.println("*** CRUD Operations with SQLite for cars catalogue. ***");
        
        //Car Service initialization
        CarService carService = new CarService("jdbc:sqlite:/home/karols/Dokumenty/jrepo_tut/nr13_crud_example/src/main/resources/mydb.db");

        //CRUD examples
        carService.reset();
        carService.create("Civic", "Honda", 9999.0);
    }
}