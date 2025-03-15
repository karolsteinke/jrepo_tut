package sk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.sqlite.SQLiteDataSource;

//CRUD Service for 'Car' class
public class CarService {
    private SQLiteDataSource dataSource;

    //constructors
    public CarService(String databaseUrl) {
        this.dataSource = new SQLiteDataSource();
        dataSource.setUrl(databaseUrl);
    }

    //reset database to an empty table
    public void reset() {
        try (Connection con = dataSource.getConnection()) {
            try (Statement stat = con.createStatement()) {
                stat.execute("DROP TABLE IF EXISTS cars;");
                stat.execute("CREATE TABLE IF NOT EXISTS cars(id INTEGER PRIMARY KEY, model TEXT NOT NULL, manufacturer TEXT NOT NULL, price INTEGER NOT NULL);");
            }
            catch (SQLException e) {e.printStackTrace();}
        }
        catch (SQLException e) {e.printStackTrace();}
    }
    
    //"C" = create (add a new entry to database)
    public void create(String model, String manufacturer, double price) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement prep = con.prepareStatement("INSERT INTO cars (model, manufacturer, price) VALUES (?,?,?);")) {
                prep.setString(1, model);
                prep.setString(2, manufacturer);
                prep.setDouble(3, price);
                prep.execute();
            }
            catch (SQLException e) {e.printStackTrace();}
        }
        catch (SQLException e) {e.printStackTrace();}
    }
    //"R" = read
    public Car read(int id) {
        return null;
    }
    //"U" = update
    public Car update(int id, Car car) {
        return null;
    }
    //"D" = delete
    public void delete(int id) {

    }
    //"L" = list
    public List<Car> list() {
        return null;
    }

}
