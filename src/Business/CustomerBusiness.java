/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Customer;
import Utilities.CRUDPackage;
import Repository.CustomerRepository;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class CustomerBusiness {

    private final CustomerRepository _repository;

    public CustomerBusiness() {
        _repository = new CustomerRepository();
    }

    public ArrayList<Customer> GetAll() {
        return _repository.SearchAll();
    }

    public Customer Get(int id) {
        return _repository.SearchByID(id);
    }

    public Customer Get(String number) {
        return _repository.SearchByCPF(number);
    }

    public CRUDPackage Insert(Customer model) {

        Customer searchByCPF = _repository.SearchByCPF(model.CPF);

        if (searchByCPF == null) {
            GenerateAccount(model);
            return _repository.Insert(model);
        } else {
            return new CRUDPackage("Não foi possível cadastrar pois o CPF " + model.CPF + " já está vinculado a outro cliente", false);
        }
    }

    public CRUDPackage Update(Customer model) {

        Customer searchByCPF = _repository.SearchByCPF(model.CPF);

        if (searchByCPF == null) {
            return _repository.Update(model);
        } else {
            return new CRUDPackage("Não foi possível atualizar pois o CPF " + model.CPF + " já está vinculado a outro cliente", false);
        }
    }

    public CRUDPackage Delete(int id) {
        
         Customer searchById = _repository.SearchByID(id);

        if (searchById == null) {
            return new CRUDPackage("Não foi possível excluir pois não foi encontrato cliente correspondente ao Id " + id, false);
        } else {
            return _repository.Delete(id);
        }
    }

    public CRUDPackage Delete(String number) {
        
        Customer searchByCPF = _repository.SearchByCPF(number);
        
        if (searchByCPF == null) {
            return new CRUDPackage("Não foi possível excluir pois não foi encontrato cliente correspondente ao CPF " + number, false);

        } else {
            return _repository.Delete(searchByCPF.Id);
        }
    }
    
    private void GenerateAccount(Customer customer){
        
        Account account = new Account(Utilities.Tools.GenerateRandomString(4));
        
        AccountBusiness accountBusiness = new AccountBusiness();
        CRUDPackage result = accountBusiness.Insert(account);
        
        if (!result.Success && !result.HasError ){
            GenerateAccount(customer);
        }else{
            customer.Account = account;
            customer.AccountId = account.Id;
        } 
    }
}
