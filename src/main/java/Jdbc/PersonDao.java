package Jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao extends AbstractDao {

    @Override
    public List<Person> findAll() {
        Statement statement = null;
        Connection connection = null;
        List<Person> persons = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/test", "sa", "sa");
            statement = connection.createStatement();
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
        } catch (SQLException e) {
            System.err.println("Request or table failed " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return persons;
    }

    @Override
    public Person findById(int id) {
        Person person = new Person();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/test", "sa", "sa");
            preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            person.setId(result.getInt("ID"));
            person.setFirstName(result.getString("FIRSTNAME"));
            person.setLastName(result.getString("LASTNAME"));
            person.setAge(result.getInt("AGE"));
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
            close(connection);
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean res = false;
        try {
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/test", "sa", "sa");
            preparedStatement = connection.prepareStatement("INSERT INTO PERSON VALUES (DEFAULT, ?, ?, ?)");
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getAge());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                res = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return res;
    }

    @Override
    public Person update(Person person) {
        return null;
    }
}
