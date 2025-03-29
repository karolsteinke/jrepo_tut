package sk;

public class CrudExampleMain {
    public static void main(String[] args) {
        System.out.println("****** CRUD Operations with SQLite for cars catalogue. ******\n");
        
        //Car Service initialization
        CarService carService = new CarService("jdbc:sqlite:/home/karols/Dokumenty/jrepo_tut/nr13_crud_example/src/main/resources/mydb.db");

        //CRUD examples
        carService.reset();
        carService.create("Civic", "Honda", 9999.0);
        carService.create("Yaris", "Toyota", 7777.0);
        carService.create("3008", "Peugeot", 184850.0);
        carService.update(3, "3008", "Peugeot", 1000.0);
        carService.read(3);
        carService.read(0);
        carService.delete(1);
        carService.list();
    }
}