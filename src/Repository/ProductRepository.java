/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Product;
import Utilities.CRUDPackage;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class ProductRepository {

    private static ArrayList<Product> RepositoryInMemory;
    private static int SequentialId;

    public ProductRepository() {
        if (RepositoryInMemory == null) {
            RepositoryInMemory = new ArrayList<>();
            SequentialId = 0;
        }
    }

    public CRUDPackage Insert(Product model) {
        try {
            SequentialId++;
            model.Id = SequentialId;
            RepositoryInMemory.add(model);
            return new CRUDPackage("Cadastro efetuado com sucesso!", true);
        } catch (Exception e) {
            SequentialId--;
            return new CRUDPackage("Não foi possível salvar produto!", e);
        }
    }

    public CRUDPackage Update(Product model) {
        try {
            Product old = SearchByID(model.Id);
            RepositoryInMemory.remove(old);
            RepositoryInMemory.add(model);
            return new CRUDPackage("Atualização efetuada com sucesso!", true);
        } catch (Exception e) {
            return new CRUDPackage("Não foi possível atualizar produto!", e);
        }
    }

    public CRUDPackage Delete(int id) {
        try {
            Product model = SearchByID(id);
            RepositoryInMemory.remove(model);
             return new CRUDPackage("Exclusão efetuada com sucesso!", true);
        } catch (Exception e) {
            return new CRUDPackage("Não foi excluir produto!", e);
        }
    }

    public Product SearchByID(int id) {
        try {
            for (Product model : RepositoryInMemory) {
                if (model.Id == id) {
                    return model;
                }
            }
            return null;
        } catch (Exception e) {
            throw new InternalError("Não foi possível buscar produto pelo Id", e);
        }
    }

    public Product SearchByCode(String code) {
        try {
            for (Product model : RepositoryInMemory) {
                if (model.Code.equals(code)) {
                    return model;
                }
            }
            return null;
        } catch (Exception e) {
            throw new InternalError("Não foi possível buscar produto pelo código", e);
        }
    }

    public ArrayList<Product> SearchAll() {
        try {
            return RepositoryInMemory;
        } catch (Exception e) {
            throw new InternalError("Não foi possível buscar produtos", e);
        }
    }
}
