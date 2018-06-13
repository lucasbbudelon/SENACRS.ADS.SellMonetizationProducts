/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleApplication;

import Business.AccountBusiness;
import Business.CustomerBusiness;
import Entities.Customer;
import Utilities.OperationPackage;

/**
 *
 * @author lucas.budelon
 */
public class MonetizationOperationsAccountConsoleApplication {

    private static final AccountBusiness _accountBusiness = new AccountBusiness();
    private static final CustomerBusiness _customerBusiness = new CustomerBusiness();

    private static void ShowMenu() {
        System.out.println("-- MOVIMENTAR CONTA ------------------------");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Transferência");
        System.out.println("4 - Saldo");
        System.out.println("0 - Voltar");

        Utilities.ConsoleUI.PrintLine();
        Utilities.ConsoleUI.PrintWhiteSpace();
    }

    public static void main(String[] args) throws Exception {

        ShowMenu();

        int opMenu = 0;

        do {
            opMenu = Utilities.ConsoleUI.RequestOptionMenu();

            switch (opMenu) {
                case 1: {
                    Deposit();
                    break;
                }
                case 2: {
                    Withdrawal();
                    break;
                }
                case 3: {
                    Transfer();
                    break;
                }
                case 4: {
                    Balance();
                    break;
                }

                case 0: {
                    Main.main(args);
                }

                default: {
                    Utilities.ConsoleUI.PrintMessage("Opção inválida!");
                }
            }

            ShowMenu();

        } while (opMenu != 0);
    }

    private static void Deposit() throws Exception {
        String CPF = Utilities.ConsoleUI.scanString("Informe o CPF do cliente cujo conta receberá o depósito: ");
        double value = Utilities.ConsoleUI.scanDouble("Informe o valor: ");
        OperationPackage result = _accountBusiness.Deposit(CPF, value);
        Utilities.ConsoleUI.FeedBackCRUD(result);
        PrintBalance(CPF);
    }

    private static void Withdrawal() throws Exception {
        String CPF = Utilities.ConsoleUI.scanString("Informe o CPF do cliente cujo conta receberá o saque: ");
        double value = Utilities.ConsoleUI.scanDouble("Informe o valor: ");
        OperationPackage result = _accountBusiness.Withdrawal(CPF, value);
        Utilities.ConsoleUI.FeedBackCRUD(result);
        PrintBalance(CPF);
    }

    private static void Transfer() throws Exception {
        String CPFOrigen = Utilities.ConsoleUI.scanString("Informe o CPF do cliente cujo conta será retirado o valor: ");
        String CPFDestiny = Utilities.ConsoleUI.scanString("Informe o CPF do cliente cujo conta receberá o valor: ");
        double value = Utilities.ConsoleUI.scanDouble("Informe o valor: ");
        OperationPackage result = _accountBusiness.Transfer(CPFOrigen, CPFDestiny, value);
        Utilities.ConsoleUI.FeedBackCRUD(result);
        PrintBalance(CPFOrigen);
        PrintBalance(CPFDestiny);
    }

    private static void Balance() {
        String CPF = Utilities.ConsoleUI.scanString("Informe o CPF do cliente para consultar saldo: ");
        PrintBalance(CPF); 
    }
    
    private static void PrintBalance(String CPF) {

        OperationPackage searchByCPF = _customerBusiness.Get(CPF);
        
        if (searchByCPF.ValidOperation) {
            Utilities.ConsoleUI.PrintMessage(((Customer)searchByCPF.Data).GetAccountBalance());
            Utilities.ConsoleUI.PrintWhiteSpace();
        }
    }
}
