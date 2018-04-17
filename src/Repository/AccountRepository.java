/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Account;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class AccountRepository {

    private static ArrayList<Account> SingletonRepository;

    public AccountRepository() {
        if (SingletonRepository == null) {
            SingletonRepository = new ArrayList<>();
        }
    }

    public void Insert(Account model) {
        SingletonRepository.add(model);
    }

    public void Update(Account model) {
        Account old = SearchByID(model.Id);
        SingletonRepository.remove(old);
        SingletonRepository.add(model);
    }

    public void Delete(int id) {
        Account model = SearchByID(id);
        SingletonRepository.remove(model);
    }

    public Account SearchByID(int id) {
        for (Account model : SingletonRepository) {
            if (model.Id == id) {
                return model;
            }
        }
        return null;
    }
    
    public Account SearchByNumber(String number) {
        for (Account model : SingletonRepository) {
            if (model.Number == null ? number == null : model.Number.equals(number)) {
                return model;
            }
        }
        return null;
    }
    
    public ArrayList<Account> SearchAll() {
        return SingletonRepository;
    }
}
