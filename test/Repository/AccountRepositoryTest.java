/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Account;
import Repository.AccountRepository;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucas.budelon
 */
public class AccountRepositoryTest {
    
    private final AccountRepository _repository;
    
    public AccountRepositoryTest() {
        _repository = new AccountRepository();
    }
    
    @Test
    public void Should_Insert_One_Account(){
        Account newEntiti = new Account("123abc", 75);
        _repository.Insert(newEntiti);
        Account getNewEntiti = _repository.SearchByNumber("123abc");
        assertNotNull(getNewEntiti);
    }
}
