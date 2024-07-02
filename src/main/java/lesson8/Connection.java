package lesson8;

import org.postgresql.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    public static java.sql.Connection connect() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/my_ticket_service_db","postgres","1");
    }
}
