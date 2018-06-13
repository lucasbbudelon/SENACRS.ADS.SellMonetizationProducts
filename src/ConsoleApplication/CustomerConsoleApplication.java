/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleApplication;

import Utilities.ConsoleUI;
import Entities.Customer;
import Business.CustomerBusiness;
import Entities.Sale;
import Utilities.OperationPackage;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class CustomerConsoleApplication {

    private static final CustomerBusiness _business = new CustomerBusiness();

    public static void main(String[] args) throws Exception {

        ConsoleUI.ShowMenuCRUD("CLIENTE");

        int opMenu = 0;

        do {
            opMenu = Utilities.ConsoleUI.RequestOptionMenu();

            switch (opMenu) {
                case 1: {
                    Insert();
                    break;
                }
                case 2: {
                    Update();
                    break;
                }
                case 3: {
                    Delete();
                    break;
                }
                case 4: {
                    List();
                    break;
                }

                case 5: {
                    Report();
                    break;
                }

                case 0: {
                    Main.main(args);
                }

                default: {
                    Utilities.ConsoleUI.PrintMessage("Opção inválida!");
                }
            }

            Utilities.ConsoleUI.ShowMenuCRUD("CLIENTE");

        } while (opMenu != 0);
    }

    private static void Insert() throws Exception {
        Utilities.ConsoleUI.RequestDataInsert();
        Customer entiti = new Customer();
        entiti.CPF = ConsoleUI.scanString("CPF: ");
        entiti.Name = ConsoleUI.scanString("Nome: ");
        entiti.Email = ConsoleUI.scanString("Email: ");
        OperationPackage result = _business.Insert(entiti);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }

    private static void Update() throws Exception {
        String CPF = ConsoleUI.scanString("CPF: ");
        OperationPackage searchByCPF = _business.Get(CPF);

        if (searchByCPF.ValidOperation) {
            Customer newEntiti = new Customer();
            newEntiti.Id = ((Customer) searchByCPF.Data).Id;
            Utilities.ConsoleUI.RequestDataUpdate();
            newEntiti.CPF = ConsoleUI.scanString("CPF: ");
            newEntiti.Name = ConsoleUI.scanString("Nome: ");
            newEntiti.Email = ConsoleUI.scanString("Email: ");
            OperationPackage result = _business.Update(newEntiti);
            Utilities.ConsoleUI.FeedBackCRUD(result);
        } else {
            Utilities.ConsoleUI.FeedBackCRUD(searchByCPF);
        }
    }

    private static void Delete() throws Exception {
        String CPF = ConsoleUI.scanString("CPF: ");
        OperationPackage result = _business.Delete(CPF);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }

    private static void List() throws Exception {

        OperationPackage getAll = _business.GetAll();

        if (getAll.ValidOperation) {

            ArrayList<Customer> list = (ArrayList<Customer>) getAll.Data;

            if (list.isEmpty()) {
                Utilities.ConsoleUI.PrintWhiteSpace();
                Utilities.ConsoleUI.PrintMessage("Nenhum cliente cadastrado!");
                Utilities.ConsoleUI.PrintWhiteSpace();

            } else {

                Utilities.ConsoleUI.PrintWhiteSpace();
                Utilities.ConsoleUI.PrintLine();
                Utilities.ConsoleUI.PrintWhiteSpace();
                list.forEach((entiti) -> {
                    Utilities.ConsoleUI.PrintMessage(entiti.toString());
                });
                Utilities.ConsoleUI.PrintWhiteSpace();
            }
        } else {
            Utilities.ConsoleUI.FeedBackCRUD(getAll);
        }
    }

    private static void Report() throws Exception {
        
        String CPF = ConsoleUI.scanString("CPF: ");

        OperationPackage getAll = _business.ReportSalesByCustomer(CPF);

        if (getAll.ValidOperation) {

            ArrayList<Sale> list = (ArrayList<Sale>) getAll.Data;

            if (list.isEmpty()) {
                Utilities.ConsoleUI.PrintWhiteSpace();
                Utilities.ConsoleUI.PrintMessage("Nenhum cliente cadastrado!");
                Utilities.ConsoleUI.PrintWhiteSpace();

            } else {

                Utilities.ConsoleUI.PrintMessage("Compras realizada pelo cliente");
                Utilities.ConsoleUI.PrintWhiteSpace();
                Utilities.ConsoleUI.PrintLine();
                Utilities.ConsoleUI.PrintWhiteSpace();
                list.forEach((entiti) -> {
                    Utilities.ConsoleUI.PrintMessage(entiti.toString());
                });
                Utilities.ConsoleUI.PrintWhiteSpace();
            }
        } else {
            Utilities.ConsoleUI.FeedBackCRUD(getAll);
        }
    }
}
