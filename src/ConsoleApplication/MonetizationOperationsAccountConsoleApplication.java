/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleApplication;

import Utilities.ConsoleUI;
import Entities.Account;
import Business.AccountBusiness;
import Utilities.CRUDPackage;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class AccountConsoleApplication {

    private static final AccountBusiness _business = new AccountBusiness();

    public static void main(String[] args) throws Exception {

        ConsoleUI.ShowMenuCRUD("Conta");

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

            Utilities.ConsoleUI.ShowMenuCRUD("Conta");

        } while (opMenu != 5);
    }

    private static void Insert() throws Exception {
        Utilities.ConsoleUI.RequestDataInsert();
        Account entiti = new Account();
        entiti.Number = ConsoleUI.scanString("Número: ");
        CRUDPackage result = _business.Insert(entiti);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }

    private static void Update() throws Exception {
        String number = ConsoleUI.scanString("Número da Conta: ");
        Account oldEntiti = _business.Get(number);

        if (oldEntiti == null) {
            Utilities.ConsoleUI.PrintWhiteSpace();
            Utilities.ConsoleUI.PrintMessageError("Não foi possível alterar pois não foi encontrato conta correspondente ao número " + number);
            Utilities.ConsoleUI.PrintWhiteSpace();
            Utilities.ConsoleUI.PrintWhiteSpace();
        } else {
            Account newEntiti = new Account();
            newEntiti.Id = oldEntiti.Id;
            newEntiti.Balance = oldEntiti.Balance;
            Utilities.ConsoleUI.RequestDataUpdate();
            newEntiti.Number = ConsoleUI.scanString("Número: ");
            CRUDPackage result = _business.Update(newEntiti);
            Utilities.ConsoleUI.FeedBackCRUD(result);
        }
    }

    private static void Delete() throws Exception {
        String number = ConsoleUI.scanString("Número da Conta: ");
        CRUDPackage result = _business.Delete(number);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }

    private static void List() {
        ArrayList<Account> list = _business.GetAll();

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
    }
}
