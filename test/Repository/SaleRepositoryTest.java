/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Sale;
import java.time.LocalDateTime;
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
public class SaleRepositoryTest {

    private static SaleRepository _repository;
    private static Sale _entitiMock;

    public SaleRepositoryTest() {
        _repository = new SaleRepository();
        _entitiMock = new Sale();
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

        _entitiMock.Code = "CODE";
        _entitiMock.CPFCustomer = "123456789";
        _entitiMock.DateTime = LocalDateTime.now();

        _repository.Insert(_entitiMock);
        
        Sale newEntiti = _repository.SearchByCode("CODE");
        
        assertNotNull(newEntiti);
    }  
    
    @Test
    public void Should_SearchAll() {

        ArrayList<Sale> list = _repository.SearchAll();
        
        assertNotNull(list);
    }
}
