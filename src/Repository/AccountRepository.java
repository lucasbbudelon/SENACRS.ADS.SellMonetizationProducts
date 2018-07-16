/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Account;
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
public class AccountRepository implements IRepository<Account> {

    public AccountRepository() {
    }

    /*
     * Write record to database
     * @author Lucas B Budelon
     * @param  model Account - Record that will be recorded
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage Insert(Account model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {

                Connection connection = (Connection) resultOperation.Data;

                String sql = "INSERT INTO Account(Number,Balance) "
                        + "VALUES (?,?)";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, model.getNumber());
                comand.setDouble(2, model.getBalance());

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
     * @param  model Account - Record that will be updated
     * @return Package with information regarding operation
     */
    @Override
    public OperationPackage Update(Account model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "UPDATE Account "
                        + "SET Number=?,Balance=? "
                        + "WHERE Id=?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, model.getNumber());
                comand.setDouble(2, model.getBalance());
                comand.setInt(3, model.getId());

                int executeResult = comand.executeUpdate();

                connection.close();

                resultOperation = new OperationPackage(
                        "Atualização efetuada com sucesso!",
                        executeResult > 0,
                        model);

            } catch (SQLException ex) {
                resultOperation = new OperationPackage(
                        "Não foi possível atualizar conta!",
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

                String sql = "DELETE FROM Account "
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
                        "Não foi possível excluir conta!",
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

                String sql = "SELECT * FROM Account WHERE Id = ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setInt(1, id);

                ResultSet executeResult = comand.executeQuery();

                if (executeResult.next()) {

                    Account entiti = new Account();

                    entiti.setId(executeResult.getInt("Id"));
                    entiti.setNumber(executeResult.getString("Number"));
                    entiti.setBalance(executeResult.getDouble("Balance"));

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
     * Search By Number
     * @author Lucas B Budelon
     * @param  number String - code used to search
     * @return If it does, it returns the record, otherwise returns null
     */
    @Override
    public OperationPackage SearchByAK(String number) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Account WHERE Number LIKE ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setString(1, "%" + number + "%");

                ResultSet executeResult = comand.executeQuery();

                if (executeResult.next()) {

                    Account entiti = new Account();

                    entiti.setId(executeResult.getInt("Id"));
                    entiti.setNumber(executeResult.getString("Number"));
                    entiti.setBalance(executeResult.getDouble("Balance"));

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

                String sql = "SELECT * FROM Account";

                PreparedStatement comand = connection.prepareStatement(sql);

                ResultSet executeResult = comand.executeQuery();

                ArrayList<Account> list = new ArrayList<>();

                while (executeResult.next()) {

                    Account entiti = new Account();

                    entiti.setId(executeResult.getInt("Id"));
                    entiti.setNumber(executeResult.getString("Number"));
                    entiti.setBalance(executeResult.getDouble("Balance"));

                    list.add(entiti);

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
    public OperationPackage ValidateDuplicateData(Account model) {
        IDatabaseConnection databaseConnection = new MySQLDatabaseConnections();
        OperationPackage resultOperation = databaseConnection.GetConnection();

        if (resultOperation.Success) {

            try {
                Connection connection = (Connection) resultOperation.Data;

                String sql = "SELECT * FROM Account "
                        + "WHERE Id <> ? AND Number = ?";

                PreparedStatement comand = connection.prepareStatement(sql);

                comand.setInt(1, model.getId());
                comand.setString(1, model.getNumber());

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
