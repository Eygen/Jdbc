package Jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao {
    public abstract List<Person> findAll();
    public abstract Person findById(int id);
    public abstract boolean delete(int id);
    public abstract boolean delete(Person person);
    public abstract boolean create(Person person);
    public abstract Person update(Person person);

    public void close(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println("Can't close statement " + e);
        }
    }

    public void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't close connection " + e);
        }
    }
}
