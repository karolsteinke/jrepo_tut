package sk;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        
        //create a sqlite data source
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:/home/karols/Dokumenty/jrepo_tut/nr12_jdbc_example/src/main/resources/mydb.db");

        //create a connection, create a statement
        try (Connection con = dataSource.getConnection()) {
            try (Statement stat = con.createStatement()) {
                stat.execute("DROP TABLE IF EXISTS HOUSES");
                stat.executeUpdate("CREATE TABLE IF NOT EXISTS HOUSES(id INTEGER PRIMARY KEY, name TEXT NOT NULL, words TEXT NOT NULL)");
                int i = stat.executeUpdate("INSERT INTO HOUSES VALUES (1, 'Kingdom_1', 'Hello!'), (2, 'Kingdom_2', 'Hi'), (3, 'Kingdom_3', 'Hiho!')");
                int u = stat.executeUpdate("UPDATE HOUSES SET words = 'Hi!' WHERE id=2");
                System.out.println("inserted: " + i + ", updated: " + u);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}