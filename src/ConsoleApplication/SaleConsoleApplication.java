/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleApplication;

import Business.ProductBusiness;
import Utilities.ConsoleUI;
import Entities.Sale;
import Business.SaleBusiness;
import Entities.Product;
import Entities.SaleItem;
import Utilities.OperationPackage;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class SaleConsoleApplication {

    private static final SaleBusiness _business = new SaleBusiness();
    private static final ProductBusiness _productBusiness = new ProductBusiness();

    private static void ShowMenu() {
        System.out.println("-- Vendas ------------------------");
        System.out.println("1 - Inserir");
        System.out.println("2 - Consultar");
        System.out.println("3 - Relatório");
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
                    Insert();
                    break;
                }
                case 2: {
                    Search();
                    break;
                }

                case 3: {
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

            ShowMenu();

        } while (opMenu != 0);
    }

    private static void Insert() throws Exception {
        Utilities.ConsoleUI.PrintWhiteSpace();
        Sale entiti = new Sale();
        entiti.Customer.CPF = ConsoleUI.scanString("CPF do Cliente: ");
        InsertDeleteItems(entiti);
        OperationPackage result = _business.Insert(entiti);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }

    private static void InsertDeleteItems(Sale entiti) throws Exception {

        entiti.Items = new ArrayList<>();

        ShowMenuItems();

        int opMenu = 0;

        do {
            opMenu = Utilities.ConsoleUI.RequestOptionMenu();

            switch (opMenu) {
                case 1: {
                    String code = ConsoleUI.scanString("Código do Produto: ");

                    OperationPackage searchProductByCode = _productBusiness.Get(code);

                    if (searchProductByCode.ValidOperation) {
                        Product product = (Product) searchProductByCode.Data;

                        if (product == null) {
                            PrintProductNotFound();
                        } else {
                            SaleItem item = new SaleItem();
                            item.Product = product;
                            item.Quantity = ConsoleUI.scanInt("Quantidade: ");
                            entiti.Items.add(item);
                            ConsoleUI.PrintMessage("Produto inserido com sucesso!");
                        }

                    } else {
                        Utilities.ConsoleUI.FeedBackCRUD(searchProductByCode);
                    }

                    break;
                }
                case 2: {
                    boolean removeSuccess = false;
                    String code = ConsoleUI.scanString("Código do Produto: ");
                    entiti.Items.stream().filter((item) -> (item.Product.Code.equals(code))).forEachOrdered((item) -> {
                        entiti.Items.remove(item);
                    });
                    if (removeSuccess) {
                        ConsoleUI.PrintMessage("Produto removido com sucesso!");
                    } else {
                        PrintProductNotFound();
                    }
                    break;
                }

                case 3: {
                    entiti.Items.forEach((item) -> {
                        Utilities.ConsoleUI.PrintMessage(item.toString());
                    });
                    break;
                }

                case 0: {
                    break;
                }

                default: {
                    Utilities.ConsoleUI.PrintMessage("Opção inválida!");
                }
            }

            ShowMenuItems();

        } while (opMenu != 0);
    }

    private static void ShowMenuItems() {
        Utilities.ConsoleUI.PrintLine();
        System.out.println("1 - Inserir Produto");
        System.out.println("2 - Remover Produto");
        System.out.println("3 - Listar Produtps");
        System.out.println("0 - Concluir Venda");
        Utilities.ConsoleUI.PrintLine();
        Utilities.ConsoleUI.PrintWhiteSpace();
    }

    private static void PrintProductNotFound() {
        ConsoleUI.PrintWhiteSpace();
        ConsoleUI.PrintMessageError("Produto não encontrado!");
        ConsoleUI.PrintWhiteSpace();
        ConsoleUI.PrintWhiteSpace();
    }
    
    private static void Search() throws Exception {
        
        Utilities.ConsoleUI.PrintWhiteSpace();
        String code = ConsoleUI.scanString("Código da venda: ");
        
        OperationPackage getByCode = _business.Get(code);
        
        if (getByCode.ValidOperation) {
            Product entiti = (Product) getByCode.Data;
            Utilities.ConsoleUI.PrintMessage(entiti.toString());
        }
        else {
            Utilities.ConsoleUI.FeedBackCRUD(getByCode);
        }
    }

    private static void Report() throws Exception {

        OperationPackage getAll = _business.GetAll();

        if (getAll.ValidOperation) {

            ArrayList<Sale> list = (ArrayList<Sale>) getAll.Data;

            if (list.isEmpty()) {
                Utilities.ConsoleUI.PrintWhiteSpace();
                Utilities.ConsoleUI.PrintMessage("Nenhuma venda cadastrada!");
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
