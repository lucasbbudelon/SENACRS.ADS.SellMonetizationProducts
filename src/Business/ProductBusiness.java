/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Product;
import Utilities.OperationPackage;
import Repository.ProductRepository;

/**
 *
 * @author lucas.budelon
 */
public class ProductBusiness implements IBusiness<Product>{

    private final ProductRepository _repository;

    public ProductBusiness() {
        _repository = new ProductRepository();
    }

    /*
     * Returns all records
     * @author Lucas B Budelon
     * @return ArrayList
     */
    @Override
    public OperationPackage GetAll() {
        return _repository.SearchAll();
    }

    @Override
    public OperationPackage Get(int id) {
        return _repository.SearchByPK(id);
    }

    @Override
    public OperationPackage Get(String code) {
        return _repository.SearchByAK(code);
    }

    @Override
    public OperationPackage Insert(Product model) {
        
        OperationPackage validateDuplicateData = _repository.ValidateDuplicateData(model);

        if (validateDuplicateData.Success) {
            return _repository.Insert(model);
        } else {
            return validateDuplicateData;
        }
        
    }

    @Override
    public OperationPackage Update(Product model) {
        
        OperationPackage validateDuplicateData = _repository.ValidateDuplicateData(model);

        if (validateDuplicateData.Success) {
            return _repository.Update(model);
        } else {
            return validateDuplicateData;
        }
    }

    @Override
    public OperationPackage Delete(int id) {
        return _repository.Delete(id);
    }

    @Override
    public OperationPackage Delete(String code) {

        OperationPackage resultSearchByCode = _repository.SearchByAK(code);

        if (!resultSearchByCode.HasError && resultSearchByCode.Success) {

            Product searchByCode = (Product) resultSearchByCode.Data;
            return _repository.Delete(searchByCode.Id);

        } else {
            return resultSearchByCode;
        }
    }
    
    public OperationPackage ReportProductsSold() {
        return _repository.ReportProductsSold();
    }
}
