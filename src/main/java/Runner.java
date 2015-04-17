import Jdbc.Person;
import Jdbc.PersonDao;

import java.sql.*;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/test", "sa", "sa");
        Statement statement = connection.createStatement();
        //statement.execute("INSERT INTO PERSON VALUES (DEFAULT ,'Ivan','Ivanov',25)");
        ResultSet result = statement.executeQuery("SELECT * FROM PERSON WHERE ID=2");
        while (result.next()) {
            System.out.println(result.getString(1) + result.getString(2) + result.getString(3) + result.getString(4));
        }
        result.close();
        statement.close();
        connection.close();*/

        PersonDao personDao = new PersonDao();
        Person pers = new Person();
        pers.setFirstName("Mike");
        pers.setLastName("Black");
        pers.setAge(21);
        boolean resInsert = personDao.create(pers);
        if (resInsert) {
            System.out.println("Person created");
        }
        List<Person> persons = personDao.findAll();
        for (Person person : persons) {
            System.out.println(person);
        }
        Person person = personDao.findById(2);
        System.out.println(person);
    }

}
