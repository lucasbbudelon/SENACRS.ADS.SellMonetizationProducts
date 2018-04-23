/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Product;
import Utilities.CRUDPackage;
import Repository.ProductRepository;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class ProductBusiness {

    private final ProductRepository _repository;

    public ProductBusiness() {
        _repository = new ProductRepository();
    }

    public ArrayList<Product> GetAll() {
        return _repository.SearchAll();
    }

    public Product Get(int id) {
        return _repository.SearchByID(id);
    }

    public Product Get(String code) {
        return _repository.SearchByCode(code);
    }

    public CRUDPackage Insert(Product model) {

        Product searchByCode = _repository.SearchByCode(model.Code);

        if (searchByCode == null) {
            return _repository.Insert(model);
        } else {
            return new CRUDPackage("Não foi possível cadastrar pois o código " + model.Code + " já está vinculado a outra produto", false);
        }
    }

    public CRUDPackage Update(Product model) {

        Product searchByCode = _repository.SearchByCode(model.Code);

        if (searchByCode == null) {
            return _repository.Update(model);
        } else {
            return new CRUDPackage("Não foi possível atualizar pois o código " + model.Code + " já está vinculado a outra produto", false);
        }
    }

    public CRUDPackage Delete(int id) {

        Product searchById = _repository.SearchByID(id);

        if (searchById == null) {
            return new CRUDPackage("Não foi possível excluir pois não foi encontrato produto correspondente ao Id " + id, false);
        } else {
            return _repository.Delete(id);
        }
    }

    public CRUDPackage Delete(String code) {

        Product searchByCode = _repository.SearchByCode(code);

        if (searchByCode == null) {
            return new CRUDPackage("Não foi possível excluir pois não foi encontrato produto correspondente ao código " + code, false);

        } else {
            return _repository.Delete(searchByCode.Id);
        }
    }
}
