/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Utilities.CRUDPackage;
import Repository.AccountRepository;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class AccountBusiness {

    private final AccountRepository _repository;

    public AccountBusiness() {
        _repository = new AccountRepository();
    }

    public ArrayList<Account> GetAll() {
        return _repository.SearchAll();
    }

    public Account Get(int id) {
        return _repository.SearchByID(id);
    }

    public Account Get(String number) {
        return _repository.SearchByNumber(number);
    }

    public CRUDPackage Insert(Account model) {

        Account searchByNumber = _repository.SearchByNumber(model.Number);

        if (searchByNumber == null) {
            return _repository.Insert(model);
        } else {
            return new CRUDPackage("Não foi possível cadastrar pois o número " + model.Number + " já está vinculado a outra conta", false);
        }
    }

    public CRUDPackage Update(Account model) {

        Account searchByNumber = _repository.SearchByNumber(model.Number);

        if (searchByNumber == null) {
            return _repository.Update(model);
        } else {
            return new CRUDPackage("Não foi possível atualizar pois o número " + model.Number + " já está vinculado a outra conta", false);
        }
    }

    public CRUDPackage Delete(int id) {

        Account searchById = _repository.SearchByID(id);

        if (searchById == null) {
            return new CRUDPackage("Não foi possível excluir pois não foi encontrato conta correspondente ao Id " + id, false);
        } else {
            return _repository.Delete(id);
        }
    }

    public CRUDPackage Delete(String number) {

        Account searchByNumber = _repository.SearchByNumber(number);

        if (searchByNumber == null) {
            return new CRUDPackage("Não foi possível excluir pois não foi encontrato conta correspondente ao número " + number, false);

        } else {
            return _repository.Delete(searchByNumber.Id);
        }
    }

    public CRUDPackage Deposit(int id, double value) {

        CRUDPackage packageReturn = new CRUDPackage();

        Account account = _repository.SearchByID(id);

        if (account == null) {
            packageReturn.Message = "Não foi possível efetuar o depósito pois não foi encontrato conta correspondente ao Id " + id;
            packageReturn.Success = false;
        } else {
            account.Balance += value;
            CRUDPackage update = _repository.Update(account);

            if (update.HasError || !update.Success) {
                packageReturn = update;
            } else {
                packageReturn.Message = "Depósito realizado com sucesso!";
                packageReturn.Success = true;
            }
        }

        return packageReturn;
    }

    public CRUDPackage Withdrawal(int id, double value) {

        CRUDPackage packageReturn = new CRUDPackage();

        Account account = _repository.SearchByID(id);

        if (account == null) {
            packageReturn.Message = "Não foi possível efetuar o depósito pois não foi encontrato conta correspondente ao Id " + id;
            packageReturn.Success = false;
        } else if (value > account.Balance) {
            packageReturn.Message = "Não foi possível efetuar o saque pois não há saldo suficiente na conta";
            packageReturn.Success = false;
        } else {
            account.Balance -= value;
            CRUDPackage update = _repository.Update(account);

            if (update.HasError || !update.Success) {
                packageReturn = update;
            } else {
                packageReturn.Message = "Saque realizado com sucesso!";
                packageReturn.Success = true;
            }
        }

        return packageReturn;
    }

    public CRUDPackage Transfer(int idOrigin, int idDestiny, double value) {

        CRUDPackage packageReturn = new CRUDPackage();
        CRUDPackage withdrawalOrigin = Withdrawal(idOrigin, value);
        CRUDPackage depositDestiny = Deposit(idDestiny, value);

        if (withdrawalOrigin.HasError || !withdrawalOrigin.Success) {
            return withdrawalOrigin;
        } else if (depositDestiny.HasError || !depositDestiny.Success) {
            return depositDestiny;
        } else {
            packageReturn.Message = "Transferência realizada com sucesso!";
            packageReturn.Success = true;
            return packageReturn;
        }
    }
}
