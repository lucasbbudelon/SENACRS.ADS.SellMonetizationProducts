/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Customer;
import Utilities.CRUDPackage;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class CustomerRepository {

    private static ArrayList<Customer> RepositoryInMemory;
    private static int SequentialId;

    public CustomerRepository() {
        if (RepositoryInMemory == null) {
            RepositoryInMemory = new ArrayList<>();
            SequentialId = 0;
        }
    }

    public CRUDPackage Insert(Customer model) {
        try {
            SequentialId++;
            model.Id = SequentialId;
            RepositoryInMemory.add(model);
            return new CRUDPackage("Cadastro efetuado com sucesso!", true);
        } catch (Exception e) {
            SequentialId--;
            return new CRUDPackage("Não foi possível salvar cliente!", e);
        }
    }

    public CRUDPackage Update(Customer model) {
        try {
            Customer old = SearchByID(model.Id);
            RepositoryInMemory.remove(old);
            RepositoryInMemory.add(model);
            return new CRUDPackage("Atualização efetuada com sucesso!", true);
        } catch (Exception e) {
            return new CRUDPackage("Não foi possível atualizar cliente!", e);
        }
    }

    public CRUDPackage Delete(int id) {
        try {
            Customer model = SearchByID(id);
            RepositoryInMemory.remove(model);
             return new CRUDPackage("Exclusão efetuada com sucesso!", true);
        } catch (Exception e) {
            return new CRUDPackage("Não foi excluir cliente!", e);
        }
    }

    public Customer SearchByID(int id) {
        try {
            for (Customer model : RepositoryInMemory) {
                if (model.Id == id) {
                    return model;
                }
            }
            return null;
        } catch (Exception e) {
            throw new InternalError("Não foi possível buscar cliente pelo Id", e);
        }
    }

    public Customer SearchByCPF(String CPF) {
        try {
            for (Customer model : RepositoryInMemory) {
                if (model.CPF.equals(CPF)) {
                    return model;
                }
            }
            return null;
        } catch (Exception e) {
            throw new InternalError("Não foi possível buscar cliente pelo CPF", e);
        }
    }

    public ArrayList<Customer> SearchAll() {
        try {
            return RepositoryInMemory;
        } catch (Exception e) {
            throw new InternalError("Não foi possível buscar clientes", e);
        }
    }
}
