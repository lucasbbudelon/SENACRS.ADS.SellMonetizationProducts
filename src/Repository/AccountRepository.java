/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entities.Account;
import Utilities.CRUDPackage;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class AccountRepository {

    private static ArrayList<Account> RepositoryInMemory;
    private static int SequentialId;

    public AccountRepository() {
        if (RepositoryInMemory == null) {
            RepositoryInMemory = new ArrayList<>();
            SequentialId = 0;
        }
    }

    public CRUDPackage Insert(Account model) {
        try {
            SequentialId++;
            model.Id = SequentialId;
            RepositoryInMemory.add(model);
            return new CRUDPackage("Cadastro efetuado com sucesso!", true);
        } catch (Exception e) {
            SequentialId--;
            return new CRUDPackage("Não foi possível salvar conta!", e);
        }
    }

    public CRUDPackage Update(Account model) {
        try {
            Account old = SearchByID(model.Id);
            RepositoryInMemory.remove(old);
            RepositoryInMemory.add(model);
            return new CRUDPackage("Atualização efetuada com sucesso!", true);
        } catch (Exception e) {
            return new CRUDPackage("Não foi possível atualizar conta!", e);
        }
    }

    public CRUDPackage Delete(int id) {
        try {
            Account model = SearchByID(id);
            RepositoryInMemory.remove(model);
             return new CRUDPackage("Exclusão efetuada com sucesso!", true);
        } catch (Exception e) {
            return new CRUDPackage("Não foi excluir conta!", e);
        }
    }

    public Account SearchByID(int id) {
        try {
            for (Account model : RepositoryInMemory) {
                if (model.Id == id) {
                    return model;
                }
            }
            return null;
        } catch (Exception e) {
            throw new InternalError("Não foi possível buscar conta pelo Id", e);
        }
    }

    public Account SearchByNumber(String number) {
        try {
            for (Account model : RepositoryInMemory) {
                if (model.Number.equals(number)) {
                    return model;
                }
            }
            return null;
        } catch (Exception e) {
            throw new InternalError("Não foi possível buscar conta pelo número", e);
        }
    }

    public ArrayList<Account> SearchAll() {
        try {
            return RepositoryInMemory;
        } catch (Exception e) {
            throw new InternalError("Não foi possível buscar contas", e);
        }
    }
}
