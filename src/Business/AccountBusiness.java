/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Customer;
import Entities.Sale;
import Utilities.OperationPackage;
import Repository.AccountRepository;
import Repository.CustomerRepository;

/**
 *
 * @author lucas.budelon
 */
public class AccountBusiness implements IBusiness<Account> {

    private final AccountRepository _repository;
    private final CustomerRepository _customerRepository;

    public AccountBusiness() {
        _repository = new AccountRepository();
        _customerRepository = new CustomerRepository();
    }

    @Override
    public OperationPackage GetAll() {
        return _repository.SearchAll();
    }

    @Override
    public OperationPackage Get(int id) {
        return _repository.SearchByPK(id);
    }

    @Override
    public OperationPackage Get(String number) {
        return _repository.SearchByAK(number);
    }

    @Override
    public OperationPackage Insert(Account model) {
        
        OperationPackage validateDuplicateData = _repository.ValidateDuplicateData(model);

        if (validateDuplicateData.Success) {
            return _repository.Insert(model);
        } else {
            return validateDuplicateData;
        }
    }

    @Override
    public OperationPackage Update(Account model) {
        
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
        return new OperationPackage("Não é possivel excluir pelo Código", false);
    }

    public OperationPackage Deposit(String CPF, double value) {

        OperationPackage packageReturn = _customerRepository.SearchByAK(CPF);

        if (packageReturn.ValidOperation) {

            Customer customer = (Customer) packageReturn.Data;
            customer.getAccount().Balance += value;

            OperationPackage update = _repository.Update(customer.getAccount());

            if (update.HasError || !update.Success) {
                packageReturn = update;
            } else {
                packageReturn.Message = "Depósito realizado com sucesso!";
                packageReturn.Success = true;
            }
        }

        return packageReturn;
    }

    public OperationPackage Withdrawal(String CPF, double value) {

        OperationPackage packageReturn = _customerRepository.SearchByAK(CPF);

        if (packageReturn.ValidOperation) {

            Customer customer = (Customer) packageReturn.Data;

            if (value > customer.getAccount().Balance) {
                packageReturn.Message = "Não foi possível efetuar o débito pois não há saldo suficiente na conta";
                packageReturn.Success = false;
            } else {
                customer.getAccount().Balance -= value;
                OperationPackage update = _repository.Update(customer.getAccount());

                if (update.HasError || !update.Success) {
                    packageReturn = update;
                } else {
                    packageReturn.Message = "Saque realizado com sucesso!";
                    packageReturn.Success = true;
                }
            }
        }

        return packageReturn;

    }

    public OperationPackage Transfer(String CPFOrigin, String CPFDestiny, double value) {

        OperationPackage packageReturn = new OperationPackage();
        OperationPackage withdrawalOrigin = Withdrawal(CPFOrigin, value);
        OperationPackage depositDestiny = Deposit(CPFDestiny, value);

        if (withdrawalOrigin.HasError || !withdrawalOrigin.Success) {
            return withdrawalOrigin;
        } else if (depositDestiny.HasError || !depositDestiny.Success) {
            return depositDestiny;
        } else {
            packageReturn.Message = "Transferência realizada com sucesso!";
            packageReturn.Success = true;
            return packageReturn;
        }
    }

//    public OperationPackage DebitSale(Sale sale) {
//        return (Withdrawal(sale.Customer.CPF, sale.GetTotal()));
//    }
}
