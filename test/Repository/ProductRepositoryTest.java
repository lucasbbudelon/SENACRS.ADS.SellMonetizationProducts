/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Product;
import Utilities.OperationPackage;
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
public class ProductRepositoryTest {

    private static ProductRepository _repository;
    private static Product _entitiMock;

    public ProductRepositoryTest() {
        _repository = new ProductRepository();
        _entitiMock = new Product();
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
        _entitiMock.Name = "NAME";
        _entitiMock.Price = 9.9;

        OperationPackage resultOperation = _repository.Insert(_entitiMock);
        OperationPackage searchOperation = _repository.SearchByAK(_entitiMock.Code);
        _entitiMock = (Product) searchOperation.Data;

        assertTrue(resultOperation.ValidOperation);
        assertTrue(searchOperation.ValidOperation);
    }

    @Test
    public void Should_Update() {

        _entitiMock.Code = "NEW_CODE";
        _entitiMock.Name = "NEW NAME";
        _entitiMock.Price = 4.9;

        OperationPackage resultOperation = _repository.Update(_entitiMock);
        OperationPackage searchOperation = _repository.SearchByAK(_entitiMock.Code);

        assertTrue(resultOperation.ValidOperation);
        assertTrue(searchOperation.ValidOperation);
    }

    @Test
    public void Should_Delete() {

        OperationPackage resultOperation = _repository.Delete(_entitiMock.Id);
        OperationPackage searchOperation = _repository.SearchByPK(_entitiMock.Id);

        assertFalse(searchOperation.Success);
        assertFalse(searchOperation.HasError);
    }

    @Test
    public void Should_SearchAll() {

        OperationPackage resultOperation = _repository.SearchAll();

        assertTrue(resultOperation.ValidOperation);
        assertNotNull(resultOperation.Data);
    }
}
