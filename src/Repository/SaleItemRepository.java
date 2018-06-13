/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author lucas.budelon
 */
public class SaleItemRepository {

    public SaleItemRepository() {
    }

    /*
     * Write Sale Items to database
     * @author Lucas B Budelon
     * @param  saleId int - Sales Identifier
     * @param  model SaleItems - Record that will be recorded
     * @return Package with information regarding operation
     */
    public OperationPackage Insert(int saleId, SaleItem model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {

                Connection connection = (Connection) resultOperation.Data;

                String sql = "INSERT INTO sale_items(ProductId,Quantity,SaleId) "
                        + "VALUES (?,?,?)";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setInt(1, model.Product.Id);
                comand.setInt(2, model.Quantity);
                comand.setInt(3, saleId);

                int executeResult = comand.executeUpdate();

                connection.close();

                resultOperation = new OperationPackage(
                        "Item da Venda inserido com sucesso!",
                        executeResult > 0,
                        model);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível inserir o Item da Venda!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }


    /*
     * Returns sale items
     * @author Lucas B Budelon
     * @param  saleId int - Sales Identifier
     * @return ArrayList
     */
    public OperationPackage SearchBySale(int saleId) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM sale_items WHERE SaleId = ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setInt(1, saleId);

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
                        return getProduct;
                    }
                }

                connection.close();

                if (resultOperation.ValidOperation) {
                    resultOperation = new OperationPackage(
                            "Registros encontrados",
                            true,
                            list);
                }

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível buscar contas!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }
}
