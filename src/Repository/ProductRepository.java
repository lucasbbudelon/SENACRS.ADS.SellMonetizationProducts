package Repository;

import Entities.Product;
import Entities.SaleItem;
import Repository.DatabaseConnections.IDatabaseConnection;
import Repository.DatabaseConnections.MySQLDatabaseConnections;
import Utilities.OperationPackage;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Product data persistence layer
 *
 * @author lucas.budelon
 */
public class ProductRepository implements IRepository<Product> {

    public ProductRepository() {
    }

    /*
     * Write record to database
     * @author Lucas B Budelon
     * @param  model Product - Record that will be recorded
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage Insert(Product model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {

                Connection connection = (Connection) resultOperation.Data;

                String sql = "INSERT INTO Product(Code,Name,Price) "
                        + "VALUES (?,?,?)";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, model.Code);
                comand.setString(2, model.Name);
                comand.setDouble(3, model.Price);

                int executeResult = comand.executeUpdate();

                connection.close();

                resultOperation = new OperationPackage(
                        "Produto inserido com sucesso!",
                        executeResult > 0,
                        model);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível inserir o produto!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    /*
     * Updates Record in database
     * @author Lucas B Budelon
     * @param  model Product - Record that will be updated
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage Update(Product model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "UPDATE Product "
                        + "SET Code=?,Name=?,Price=? "
                        + "WHERE Id=?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, model.Code);
                comand.setString(2, model.Name);
                comand.setDouble(3, model.Price);
                comand.setInt(4, model.Id);

                int executeResult = comand.executeUpdate();

                connection.close();

                resultOperation = new OperationPackage(
                        "Atualização efetuada com sucesso!",
                        executeResult > 0,
                        model);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível atualizar produto!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    /*
     * I deleted the database record
     * @author Lucas B Budelon
     * @param  id int - Identifier of the record to be deleted
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage Delete(int id) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "DELETE FROM Product "
                        + "WHERE Id = ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setInt(1, id);

                int executeResult = comand.executeUpdate();

                connection.close();

                resultOperation = new OperationPackage(
                        "Exclusão efetuada com sucesso!",
                        executeResult > 0);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível excluir produto!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    /*
     * Search By Id
     * @author Lucas B Budelon
     * @param  Id int - Id used to search
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage SearchByPK(int id) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Product WHERE Id = ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setInt(1, id);

                ResultSet executeResult = comand.executeQuery();

                if (executeResult.next()) {

                    Product entiti = new Product();

                    entiti.Id = executeResult.getInt("Id");
                    entiti.Code = executeResult.getString("Code");
                    entiti.Name = executeResult.getString("Name");
                    entiti.Price = executeResult.getDouble("Price");

                    connection.close();

                    resultOperation = new OperationPackage(
                            "Registro encontrado",
                            true,
                            entiti);
                } else {

                    connection.close();

                    resultOperation = new OperationPackage(
                            "Nenhum registro foi encontrado!",
                            false);
                }
            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível buscar produto pelo Id!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    /*
     * Search By Alternative Key
     * @author Lucas B Budelon
     * @param  code String - code used to search
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage SearchByAK(String code) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Product WHERE Code=?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, code);

                ResultSet executeResult = comand.executeQuery();

                if (executeResult.next()) {

                    Product entiti = new Product();

                    entiti.Id = executeResult.getInt("Id");
                    entiti.Code = executeResult.getString("Code");
                    entiti.Name = executeResult.getString("Name");
                    entiti.Price = executeResult.getDouble("Price");

                    connection.close();

                    resultOperation = new OperationPackage(
                            "Registro encontrado",
                            true,
                            entiti);
                } else {

                    connection.close();

                    resultOperation = new OperationPackage(
                            "Nenhum registro foi encontrado!",
                            false);
                }
            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível buscar produto pelo código!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    /*
     * Returns all records
     * @author Lucas B Budelon
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage SearchAll() {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Product";

                PreparedStatement comand = connection.prepareStatement(sql);

                ResultSet executeResult = comand.executeQuery();

                ArrayList<Product> list = new ArrayList<>();

                while (executeResult.next()) {

                    Product entiti = new Product();

                    entiti.Id = executeResult.getInt("Id");
                    entiti.Code = executeResult.getString("Code");
                    entiti.Name = executeResult.getString("Name");
                    entiti.Price = executeResult.getDouble("Price");

                    list.add(entiti);
                }

                connection.close();

                resultOperation = new OperationPackage(
                        "Registros encontrados",
                        true,
                        list);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível buscar produtos!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    @Override
    public OperationPackage ValidateDuplicateData(Product model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Product "
                        + "WHERE Code LIKE ?"
                        + "OR Name LIKE ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, "%" + model.Code + "%");
                comand.setString(2, "%" + model.Name + "%");

                ResultSet executeResult = comand.executeQuery();

                if (executeResult.next()) {

                    connection.close();
                    resultOperation = new OperationPackage(
                            "Dados Inválidos!",
                            false);

                } else {

                    connection.close();
                    resultOperation = new OperationPackage(
                            "Dados válidos!",
                            true);
                }
            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível validas os dados!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    /*
     * Returns products sold
     * @author Lucas B Budelon
     * @return Package with information regarding operation
     */
    public OperationPackage ReportProductsSold() {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT SI.ProductId, COUNT(SI.Quantity) AS Quantity "
                        + "FROM Product P "
                        + "INNER JOIN sale_items SI ON P.Id = SI.ProductId "
                        + "ORDER BY Quantity ASC";

                PreparedStatement comand = connection.prepareStatement(sql);

                ResultSet executeResult = comand.executeQuery();

                ArrayList<SaleItem> list = new ArrayList<>();

                ProductRepository productRepository = new ProductRepository();

                while (executeResult.next()) {

                    OperationPackage getProduct = productRepository
                            .SearchByPK(executeResult.getInt("ProductId"));

                    if (getProduct.ValidOperation) {

                        SaleItem entiti = new SaleItem();

                        entiti.Quantity = executeResult.getInt("Quantity");
                        entiti.Product = (Product) getProduct.Data;

                        list.add(entiti);
                    } else {
                        resultOperation = getProduct;
                    }
                }

                connection.close();

                resultOperation = new OperationPackage(
                        "Registros encontrados",
                        true,
                        list);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível buscar produtos!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }
}
