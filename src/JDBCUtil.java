import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private String url = "jdbc:mysql://localhost/minefield?useSSL=false";
    private String user = "root";
    private String password = "";
    private Connection connection = null;

    public Connection getConnection() {

        try {
            if(connection == null)
                connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
