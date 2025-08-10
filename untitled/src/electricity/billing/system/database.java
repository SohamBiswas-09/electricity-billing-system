package electricity.billing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class database {
    private Connection connection;
    private Statement statement;

    public database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bill_system", "root", "Shubham1234");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }
}
