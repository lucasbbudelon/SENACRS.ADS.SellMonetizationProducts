/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleApplication;

import Utilities.ConsoleUI;
import Entities.Account;
import Business.AccountBusiness;
import Utilities.OperationPackage;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class AccountConsoleApplication {

    private static final AccountBusiness _business = new AccountBusiness();

    public static void main(String[] args) throws Exception {

        ConsoleUI.ShowMenuCRUD("CONTA");

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
                
                case 0: {
                    Main.main(args);
                }

                default: {
                    Utilities.ConsoleUI.PrintMessage("Opção inválida!");
                }
            }

            Utilities.ConsoleUI.ShowMenuCRUD("CONTA");

        } while (opMenu != 0);
    }

    private static void Insert() throws Exception {
        Utilities.ConsoleUI.RequestDataInsert();
        Account entiti = new Account();
        entiti.setNumber(ConsoleUI.scanString("Número: "));
        OperationPackage result = _business.Insert(entiti);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }

    private static void Update() throws Exception {
        String number = ConsoleUI.scanString("Número da Conta: ");
        OperationPackage searchByNumber = _business.Get(number);
        
        if (searchByNumber.ValidOperation) {
            Account newEntiti = new Account();
            newEntiti.setId(((Account) searchByNumber.Data).getId());
            newEntiti.setBalance(((Account) searchByNumber.Data).getBalance());
            Utilities.ConsoleUI.RequestDataUpdate();
            newEntiti.setNumber(ConsoleUI.scanString("Número: "));
            OperationPackage result = _business.Update(newEntiti);
            Utilities.ConsoleUI.FeedBackCRUD(result);
        } else {
            Utilities.ConsoleUI.FeedBackCRUD(searchByNumber);
        }
    }

    private static void Delete() throws Exception {
        String number = ConsoleUI.scanString("Número da Conta: ");
        OperationPackage result = _business.Delete(number);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }

 private static void List() throws Exception {

        OperationPackage getAll = _business.GetAll();

        if (getAll.ValidOperation) {

            ArrayList<Account> list = (ArrayList<Account>) getAll.Data;

            if (list.isEmpty()) {
                Utilities.ConsoleUI.PrintWhiteSpace();
                Utilities.ConsoleUI.PrintMessage("Nenhuma conta cadastrada!");
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
}
