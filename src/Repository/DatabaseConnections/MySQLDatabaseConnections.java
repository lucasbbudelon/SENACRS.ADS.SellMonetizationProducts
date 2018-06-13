package Repository.DatabaseConnections;

import Utilities.OperationPackage;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lucas.budelon
 */
public class MySQLDatabaseConnections implements IDatabaseConnection {

    @Override
    public OperationPackage GetConnection() {

        java.sql.Connection connection;
        Utilities.OperationPackage operationPackage;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3307/senacrs.ads.sellmonetizationproducts";
            String user = "root";
            String password = "usbw";

            connection = DriverManager.getConnection(url, user, password);

            operationPackage = new OperationPackage("Conexão conexão com o banco de dados aberta com sucesso!", true, connection);

        } catch (ClassNotFoundException | SQLException ex) {
            operationPackage = new OperationPackage("Não foi possível abrir abrir a conexão com o banco de dados!", ex);
        }

        return operationPackage;
    }
}
