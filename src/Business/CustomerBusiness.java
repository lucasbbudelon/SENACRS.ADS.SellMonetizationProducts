/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Customer;
import Utilities.OperationPackage;
import Repository.CustomerRepository;

/**
 *
 * @author lucas.budelon
 */
public class CustomerBusiness implements IBusiness<Customer> {

    private final CustomerRepository _repository;

    public CustomerBusiness() {
        _repository = new CustomerRepository();
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
    public OperationPackage Insert(Customer model) {

        OperationPackage validateDuplicateData = _repository.ValidateDuplicateData(model);

        if (validateDuplicateData.Success) {

            OperationPackage result;

            AccountBusiness accountBusiness = new AccountBusiness();

            String codeAccount = Utilities.Tools.GenerateRandomString(4);

            result = accountBusiness.Insert(new Account(codeAccount));

            if (result.ValidOperation) {

                result = accountBusiness.Get(codeAccount);

                if (result.ValidOperation) {

                    model.setAccount((Account) result.Data);
                    result = _repository.Insert(model);

                }
            }

            return result;

        } else {
            return validateDuplicateData;
        }

    }

    @Override
    public OperationPackage Update(Customer model) {

        OperationPackage validateDuplicateData = _repository.ValidateDuplicateData(model);

        if (validateDuplicateData.Success) {
            return _repository.Update(model);
        } else {
            return validateDuplicateData;
        }
    }

    @Override
    public OperationPackage Delete(int id) {
        return new OperationPackage("Não é possivel excluir por Id", false);
    }

    @Override
    public OperationPackage Delete(String CPF) {

        OperationPackage result = _repository.SearchByAK(CPF);

        if (result.ValidOperation) {

            Customer searchByCode = (Customer) result.Data;

            AccountBusiness AccountBusiness = new AccountBusiness();

            result = AccountBusiness.Delete(searchByCode.getAccount().Id);

            if (result.ValidOperation) {
                result = _repository.Delete(searchByCode.getId());
            }
        }

        return result;
    }

    public OperationPackage ReportSalesByCustomer(String CPF) {
        return _repository.ReportSalesByCustomer(CPF);
    }
}
