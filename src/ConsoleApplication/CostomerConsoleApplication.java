/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleApplication;

import Utilities.ConsoleUI;
import Entities.Customer;
import Business.CustomerBusiness;
import Utilities.CRUDPackage;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class CostomerConsoleApplication {

    private static final CustomerBusiness _business = new CustomerBusiness();

    public static void main(String[] args) throws Exception {

        ConsoleUI.ShowMenuCRUD("Cliente");

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
                    Main.main(args);
                }

                default: {
                    Utilities.ConsoleUI.PrintMessage("Opção inválida!");
                }
            }

            Utilities.ConsoleUI.ShowMenuCRUD("Cliente");

        } while (opMenu != 5);
    }

    private static void Insert() throws Exception {
        Utilities.ConsoleUI.RequestDataInsert();
        Customer entiti = new Customer();
        entiti.CPF = ConsoleUI.scanString("CPF: ");
        entiti.Name = ConsoleUI.scanString("Nome: ");
        entiti.Email = ConsoleUI.scanString("Email: ");
        CRUDPackage result = _business.Insert(entiti);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }

    private static void Update() throws Exception {
        String CPF = ConsoleUI.scanString("CPF: ");
        Customer oldEntiti = _business.Get(CPF);

        if (oldEntiti == null) {
            Utilities.ConsoleUI.PrintWhiteSpace();
            Utilities.ConsoleUI.PrintMessageError("Não foi possível alterar pois não foi encontrato cliente correspondente ao CPF " + CPF);
            Utilities.ConsoleUI.PrintWhiteSpace();
            Utilities.ConsoleUI.PrintWhiteSpace();
        } else {
            Customer newEntiti = new Customer();
            newEntiti.Id = oldEntiti.Id;
            Utilities.ConsoleUI.RequestDataUpdate();
            newEntiti.CPF = ConsoleUI.scanString("CPF: ");
            newEntiti.Name = ConsoleUI.scanString("Nome: ");
            newEntiti.Email = ConsoleUI.scanString("Email: ");
            CRUDPackage result = _business.Update(newEntiti);
            Utilities.ConsoleUI.FeedBackCRUD(result);
        }
    }

    private static void Delete() throws Exception {
        String CPF = ConsoleUI.scanString("CPF: ");
        CRUDPackage result = _business.Delete(CPF);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }

    private static void List() {
        ArrayList<Customer> list = _business.GetAll();

        if (list.isEmpty()) {
            Utilities.ConsoleUI.PrintWhiteSpace();
            Utilities.ConsoleUI.PrintMessage("Nenhuma cliente cadastrada!");
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
    }
}
