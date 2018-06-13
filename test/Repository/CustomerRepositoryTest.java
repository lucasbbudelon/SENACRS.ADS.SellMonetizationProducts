/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Customer;
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
public class CustomerRepositoryTest {

    private static CustomerRepository _repository;
    private static Customer _entitiMock;

    public CustomerRepositoryTest() {
        _repository = new CustomerRepository();
        _entitiMock = new Customer();
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

        _entitiMock.CPF = "123456789";
        _entitiMock.Email = "EMAIL";
        _entitiMock.Name = "NAME";
        _entitiMock.AccountId = 1;

        _repository.Insert(_entitiMock);

        Customer newEntiti = _repository.SearchByCPF("123456789");

        assertNotNull(newEntiti);
    }

    @Test
    public void Should_Update() {

        _entitiMock.CPF = "987654321";
        _entitiMock.Email = "NEW_EMAIL";
        _entitiMock.Name = "NEW NAME";
        _entitiMock.AccountId = 2;

        _repository.Update(_entitiMock);

        Customer updateEntiti = _repository.SearchByCPF("987654321");

        assertNotNull(updateEntiti);
    }

    @Test
    public void Should_Delete() {

        _repository.Delete(_entitiMock.Id);

        Customer deleteEntiti = _repository.SearchByID(_entitiMock.Id);

        assertNull(deleteEntiti);
    }

    @Test
    public void Should_SearchAll() {

        ArrayList<Customer> list = _repository.SearchAll();

        assertNotNull(list);
    }
}
