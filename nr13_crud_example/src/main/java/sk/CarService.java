package sk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    //"C" = create (add a new entry to a database)
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
        System.out.println("Item \"" + model + ", " + manufacturer + ", " + price + "\" added.");
    }
    
    //"R" = read
    public void read(int id) {
        System.out.print("Search result for id \"" + id + "\": ");
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement prep = con.prepareStatement("SELECT * FROM cars WHERE id = ?;")) {
                prep.setInt(1, id);
                ResultSet rs = prep.executeQuery();
                //check if result was found
                if (!rs.isBeforeFirst()) System.out.println("NO DATA!");
                else System.out.println(rs.getString("model")+", "+rs.getString("manufacturer")+", "+rs.getDouble("price"));
            }
            catch (SQLException e) {e.printStackTrace();}
        }
        catch (SQLException e) {e.printStackTrace();}
    }
    
    //"U" = update
    public void update(int id, String model, String manufacturer, double price) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement prep = con.prepareStatement("UPDATE cars SET model = ?, manufacturer = ?, price = ? WHERE id = ?;")) {
                prep.setString(1, model);
                prep.setString(2, manufacturer);
                prep.setDouble(3, price);
                prep.setInt(4, id);
                prep.execute();
            }
            catch (SQLException e) {e.printStackTrace();}
        }
        catch (SQLException e) {e.printStackTrace();}
        System.out.println("Item with id \"" + id + "\" has been updated.");
    }
    
    //"D" = delete
    public void delete(int id) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement prep = con.prepareStatement("DELETE FROM cars WHERE id = ?;")) {
                prep.setInt(1, id);
                prep.execute();
            }
            catch (SQLException e) {e.printStackTrace();}
        }
        catch (SQLException e) {e.printStackTrace();}
        System.out.println("Item with id \"" + id + "\" has been deleted.");
    }
    
    //"L" = list
    public void list() {
        System.out.println("*** Cars list: ***");
        try (Connection con = dataSource.getConnection()) {
            try (Statement stat = con.createStatement()) {
                ResultSet rs = stat.executeQuery("SELECT * FROM cars");
                while (rs.next()) {
                    System.out.println(rs.getInt("id")+". "+rs.getString("model")+", "+rs.getString("manufacturer")+", "+rs.getDouble("price"));
                }
            }
            catch (SQLException e) {e.printStackTrace();}
        }
        catch (SQLException e) {e.printStackTrace();}
    }
}
