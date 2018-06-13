/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Account;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucas.budelon
 */
public class AccountRepositoryTest {

    private static AccountRepository _repository;
    private static Account _entitiMock;

    public AccountRepositoryTest() {
        _repository = new AccountRepository();
        _entitiMock = new Account();
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void Should_Insert() {

        _entitiMock.Number = "NUMBER";
        _entitiMock.Balance = 50;

        _repository.Insert(_entitiMock);
        
        Account newEntiti = _repository.SearchByNumber("NUMBER");
        
        assertNotNull(newEntiti);
    }

    @Test
    public void Should_Update() {

        _entitiMock.Number = "NEW_NUMBER";
        _entitiMock.Balance = 40;

        _repository.Update(_entitiMock);
        
        Account updateEntiti = _repository.SearchByNumber("NEW_NUMBER");
        
        assertNotNull(updateEntiti);
    }
    
    @Test
    public void Should_Delete() {

        _repository.Delete(_entitiMock.Id);
        
        Account deleteEntiti = _repository.SearchByID(_entitiMock.Id);
        
        assertNull(deleteEntiti);
    }
    
    @Test
    public void Should_SearchAll() {

        ArrayList<Account> list = _repository.SearchAll();
        
        assertNotNull(list);
    }
}
