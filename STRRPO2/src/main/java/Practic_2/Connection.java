package Practic_2;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
    private java.sql.Connection conn = null;

    public Connection() {
        try {
            String url = "jdbc:sqlite:src/main/resources/dates.db";
            conn = DriverManager.getConnection(url);

            System.out.println("SQLite была подключена.");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getData() {
        String query = "SELECT * FROM employees";
        String data = "";

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                data += rs.getString("name") + " " + rs.getString("surname") + " " + rs.getString("lastname") + ". Номер телефона: " + rs.getString("phone") + ". Город: " + rs.getString("address") + "-";
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }
}
