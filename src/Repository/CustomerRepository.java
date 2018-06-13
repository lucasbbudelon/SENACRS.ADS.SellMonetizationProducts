/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Account;
import Entities.Customer;
import Entities.Sale;
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
public class CustomerRepository implements IRepository<Customer> {

    public CustomerRepository() {
    }

    /*
     * Write record to database
     * @author Lucas B Budelon
     * @param  model Customer - Record that will be recorded
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage Insert(Customer model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {

                Connection connection = (Connection) resultOperation.Data;

                String sql = "INSERT INTO Customer(Name,CPF,Email,AccountId) "
                        + "VALUES (?,?,?,?)";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, model.Name);
                comand.setString(2, model.CPF);
                comand.setString(3, model.Email);
                comand.setInt(4, model.Account.Id);

                int executeResult = comand.executeUpdate();

                connection.close();

                resultOperation = new OperationPackage(
                        "Cliente inserido com sucesso!",
                        executeResult > 0,
                        model);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível inserir o cliente!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    /*
     * Updates Record in database
     * @author Lucas B Budelon
     * @param  model Customer - Record that will be updated
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage Update(Customer model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "UPDATE Customer "
                        + "SET Name=?,CPF=?,Email=? "
                        + "WHERE Id=?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, model.Name);
                comand.setString(2, model.CPF);
                comand.setString(3, model.Email);
                comand.setInt(4, model.Id);

                int executeResult = comand.executeUpdate();

                connection.close();

                resultOperation = new OperationPackage(
                        "Atualização efetuada com sucesso!",
                        executeResult > 0,
                        model);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível atualizar cliente!",
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

                String sql = "DELETE FROM Customer "
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
                        "Não foi possível excluir cliente!",
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

                String sql = "SELECT * FROM Customer WHERE Id = ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setInt(1, id);

                ResultSet executeResult = comand.executeQuery();

                AccountRepository accountRepository = new AccountRepository();

                if (executeResult.next()) {

                    OperationPackage getAccount = accountRepository
                            .SearchByPK(executeResult.getInt("AccountId"));

                    if (getAccount.ValidOperation) {
                        Customer entiti = new Customer();

                        entiti.Id = executeResult.getInt("Id");
                        entiti.CPF = executeResult.getString("CPF");
                        entiti.Name = executeResult.getString("Name");
                        entiti.Email = executeResult.getString("Email");
                        entiti.Account = (Account) getAccount.Data;

                        connection.close();

                        resultOperation = new OperationPackage(
                                "Registro encontrado",
                                true,
                                entiti);
                    } else {
                        resultOperation = getAccount;
                    }

                } else {

                    connection.close();

                    resultOperation = new OperationPackage(
                            "Nenhum registro foi encontrado!",
                            false);
                }
            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível buscar cliente pelo Id!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    /*
     * Search By Alternative Key
     * @author Lucas B Budelon
     * @param  CPF String - CPF used to search
     * @return If it does, it returns the record, otherwise returns null
     */
    @Override
    public OperationPackage SearchByAK(String CPF) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Customer WHERE CPF LIKE ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, "%" + CPF + "%");

                ResultSet executeResult = comand.executeQuery();

                AccountRepository accountRepository = new AccountRepository();

                if (executeResult.next()) {

                    OperationPackage getAccount = accountRepository
                            .SearchByPK(executeResult.getInt("AccountId"));

                    if (getAccount.ValidOperation) {
                        Customer entiti = new Customer();

                        entiti.Id = executeResult.getInt("Id");
                        entiti.CPF = executeResult.getString("CPF");
                        entiti.Name = executeResult.getString("Name");
                        entiti.Email = executeResult.getString("Email");
                        entiti.Account = (Account) getAccount.Data;

                        connection.close();

                        resultOperation = new OperationPackage(
                                "Registro encontrado",
                                true,
                                entiti);
                    } else {
                        resultOperation = getAccount;
                    }

                } else {

                    connection.close();

                    resultOperation = new OperationPackage(
                            "Nenhum registro foi encontrado!",
                            false);
                }
            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível buscar produto cliente pelo CPF!",
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

                String sql = "SELECT * FROM Customer";

                PreparedStatement comand = connection.prepareStatement(sql);

                ResultSet executeResult = comand.executeQuery();

                ArrayList<Customer> list = new ArrayList<>();

                AccountRepository accountRepository = new AccountRepository();

                while (executeResult.next()) {

                    OperationPackage getAccount = accountRepository
                            .SearchByPK(executeResult.getInt("AccountId"));

                    if (getAccount.ValidOperation) {
                        Customer entiti = new Customer();

                        entiti.Id = executeResult.getInt("Id");
                        entiti.CPF = executeResult.getString("CPF");
                        entiti.Name = executeResult.getString("Name");
                        entiti.Email = executeResult.getString("Email");
                        entiti.Account = (Account) getAccount.Data;

                        list.add(entiti);
                    } else {
                        return getAccount;
                    }
                }

                connection.close();

                resultOperation = new OperationPackage(
                        "Registros encontrados",
                        true,
                        list);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível buscar clientes!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }

    @Override
    public OperationPackage ValidateDuplicateData(Customer model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Customer "
                        + "WHERE CPF LIKE ?"
                        + "OR Email LIKE ?"
                        + "OR Name LIKE ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, "%" + model.CPF + "%");
                comand.setString(2, "%" + model.Email + "%");
                comand.setString(3, "%" + model.Name + "%");

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
     * Customer sales reports
     * @author Lucas B Budelon
     * @param  CPF String - CPF used to search
     * @return Package with information regarding operation
     */
    public OperationPackage ReportSalesByCustomer(String CPF) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT S.* "
                        + "FROM Customer C INNER JOIN Sale S ON C.Id = S.CustomerId "
                        + "WHERE C.CPF = ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, CPF);

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
                            return getCustomer;
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
                        "Não foi possível buscar vendas do cliente!",
                        false,
                        ex);
            }
        }

        return resultOperation;
    }
}
