/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Customer;
import Entities.Sale;
import Entities.SaleItem;
import Repository.DatabaseConnections.IDatabaseConnection;
import Repository.DatabaseConnections.MySQLDatabaseConnections;
import Utilities.OperationPackage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class SaleRepository implements IRepository<Sale> {

    public SaleRepository() {
    }

    /*
     * Write record to database
     * @author Lucas B Budelon
     * @param  model Sale - Record that will be recorded
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage Insert(Sale model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {

                Connection connection = (Connection) resultOperation.Data;

                String sql = "INSERT INTO Sale(Code,DateTime,CustomerId) "
                        + "VALUES (?,?,?)";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, model.Code);
                comand.setDate(2, java.sql.Date.valueOf(model.Date));
                comand.setInt(3, model.Customer.getId());

                int executeResult = comand.executeUpdate();

                connection.close();

                resultOperation = new OperationPackage(
                        "Venda inserida com sucesso!",
                        executeResult > 0,
                        model);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível inserir a venda!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    /*
     * Updates Record in database
     * @author Lucas B Budelon
     * @param  model Sale - Record that will be updated
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage Update(Sale model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                com.mysql.jdbc.Connection connection = (com.mysql.jdbc.Connection) resultOperation.Data;

                String sql = "UPDATE Sale "
                        + "SET Code=?,DateTime=?,CustomerId=? "
                        + "WHERE Id=?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, model.Code);
                comand.setDate(2, java.sql.Date.valueOf(model.Date));
                comand.setInt(3, model.Customer.getId());
                comand.setInt(4, model.Id);

                int executeResult = comand.executeUpdate();

                connection.close();

                resultOperation = new OperationPackage(
                        "Atualização efetuada com sucesso!",
                        executeResult > 0,
                        model);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível atualizar venda!",
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
                com.mysql.jdbc.Connection connection = (com.mysql.jdbc.Connection) resultOperation.Data;

                String sql = "DELETE FROM Sale "
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
                        "Não foi possível excluir venda!",
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
     * @return If it does, it returns the record, otherwise returns null
     */
    @Override
    public OperationPackage SearchByPK(int id) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Sale WHERE Id = ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setInt(1, id);

                ResultSet executeResult = comand.executeQuery();

                if (executeResult.next()) {

                    Sale entiti = new Sale();

                    entiti.Id = executeResult.getInt("Id");
                    entiti.Code = executeResult.getString("Code");
                    entiti.Date = (executeResult.getDate("DateTime")).toLocalDate();

                    CustomerRepository customerRepository = new CustomerRepository();
                    OperationPackage getCustomer = customerRepository
                            .SearchByPK(executeResult.getInt("CustomerId"));
                    if (getCustomer.ValidOperation) {
                        entiti.Customer = (Customer) getCustomer.Data;
                    } else {
                        resultOperation = getCustomer;
                    }

                    SaleItemRepository saleItemRepository = new SaleItemRepository();
                    OperationPackage getSaleItem = saleItemRepository
                            .SearchBySale(executeResult.getInt("Id"));
                    if (getSaleItem.ValidOperation) {
                        entiti.Items = (ArrayList<SaleItem>) getSaleItem.Data;
                    } else {
                        resultOperation = getCustomer;
                    }

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
                        "Não foi possível buscar conta pelo Id!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    /*
     * Search By Code
     * @author Lucas B Budelon
     * @param  code String - code used to search
     * @return If it does, it returns the record, otherwise returns null
     */
    @Override
    public OperationPackage SearchByAK(String code) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Sale WHERE code=?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, code);

                ResultSet executeResult = comand.executeQuery();

                if (executeResult.next()) {

                    Sale entiti = new Sale();

                    entiti.Id = executeResult.getInt("Id");
                    entiti.Code = executeResult.getString("Code");
                    entiti.Date = (executeResult.getDate("DateTime")).toLocalDate();

                    CustomerRepository customerRepository = new CustomerRepository();
                    OperationPackage getCustomer = customerRepository
                            .SearchByPK(executeResult.getInt("CustomerId"));
                    if (getCustomer.ValidOperation) {
                        entiti.Customer = (Customer) getCustomer.Data;
                    } else {
                        resultOperation = getCustomer;
                    }

                    SaleItemRepository saleItemRepository = new SaleItemRepository();
                    OperationPackage getSaleItem = saleItemRepository
                            .SearchBySale(executeResult.getInt("Id"));
                    if (getSaleItem.ValidOperation) {
                        entiti.Items = (ArrayList<SaleItem>) getSaleItem.Data;
                    } else {
                        resultOperation = getCustomer;
                    }

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
                        "Não foi possível buscar conta pelo código!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }


    /*
     * Returns all records
     * @author Lucas B Budelon
     * @return ArrayList
     */
    @Override
    public OperationPackage SearchAll() {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Sale";

                PreparedStatement comand = connection.prepareStatement(sql);

                ResultSet executeResult = comand.executeQuery();

                ArrayList<Sale> list = new ArrayList<>();

                while (executeResult.next()) {

                    Sale entiti = new Sale();

                    entiti.Id = executeResult.getInt("Id");
                    entiti.Code = executeResult.getString("Code");
                    entiti.Date = (executeResult.getDate("DateTime")).toLocalDate();

                    CustomerRepository customerRepository = new CustomerRepository();

                    OperationPackage getCustomer = customerRepository
                            .SearchByPK(executeResult.getInt("CustomerId"));

                    if (getCustomer.ValidOperation) {

                        entiti.Customer = (Customer) getCustomer.Data;

                        SaleItemRepository saleItemRepository = new SaleItemRepository();

                        OperationPackage getSaleItem = saleItemRepository
                                .SearchBySale(executeResult.getInt("Id"));

                        if (getSaleItem.ValidOperation) {
                            entiti.Items = (ArrayList<SaleItem>) getSaleItem.Data;
                        } else {
                            return getSaleItem;
                        }

                        list.add(entiti);

                    } else {
                        return getCustomer;
                    }
                }

                connection.close();

                resultOperation = new OperationPackage(
                        "Registros encontrados",
                        true,
                        list);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível buscar contas!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    @Override
    public OperationPackage ValidateDuplicateData(Sale model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Sale "
                        + "WHERE Code LIKE ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, "%" + model.Code + "%");

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
}
