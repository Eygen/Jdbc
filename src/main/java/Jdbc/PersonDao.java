package Jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class PersonDao implements Dao<Person> {
    final String url = "jdbc:h2:tcp://localhost/test";
    final String login = "sa";
    final String password = "sa";

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM PERSON");
            while (result.next()) {
                Person person = new Person();
                person.setId(result.getInt("ID"));
                person.setFirstName(result.getString("FIRSTNAME"));
                person.setLastName(result.getString("LASTNAME"));
                person.setAge(result.getInt("AGE"));
                persons.add(person);
            }
            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Request or table failed " + e);
        }
        return persons;
    }

    @Override
    public Person findById(int id) {
        Person person = new Person();
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            person.setId(result.getInt("ID"));
            person.setFirstName(result.getString("FIRSTNAME"));
            person.setLastName(result.getString("LASTNAME"));
            person.setAge(result.getInt("AGE"));
            result.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Person person) {
        return false;
    }

    @Override
    public boolean create(Person person) {
        boolean res = false;
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PERSON VALUES (DEFAULT, ?, ?, ?)");
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getAge());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                res = true;
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Person update(Person person) {
        return null;
    }
}
