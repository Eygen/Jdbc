package Jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Dao<T extends BaseEntity> {
    public abstract List<T> findAll();
    public abstract T findById(int id);
    public abstract boolean delete(int id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract T update(T entity);

    /*public void close(Statement statement) {
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
    }*/
}
