/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Account;
import Repository.AccountRepository;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class AccountService {

    private final AccountRepository _repository;

    public AccountService() {
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
    
    public boolean Save(Account model) {

        Account exist = _repository.SearchByID(model.Id);

        if (exist == null) {
            _repository.Insert(model);
        } else {
            _repository.Update(exist);
        }

        return true;
    }

    public void Delete(int id) {
        _repository.Delete(id);
    }

    public void Delete(String number) {
        Account entiti = _repository.SearchByNumber(number);
        _repository.Delete(entiti.Id);
    }
}
