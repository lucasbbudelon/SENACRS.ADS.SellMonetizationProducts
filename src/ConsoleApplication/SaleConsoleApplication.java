/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleApplication;

import Utilities.ConsoleUI;
import Entities.Product;
import Business.ProductBusiness;
import Utilities.CRUDPackage;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class ProductConsoleApplication {

    private static final ProductBusiness _business = new ProductBusiness();

    public static void main(String[] args) throws Exception {

        ConsoleUI.ShowMenuCRUD("Produto");

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

            Utilities.ConsoleUI.ShowMenuCRUD("Produto");

        } while (opMenu != 5);
    }

    private static void Insert() throws Exception {
        Utilities.ConsoleUI.RequestDataInsert();
        Product entiti = new Product();
        entiti.Code = ConsoleUI.scanString("Código: ");
        entiti.Name = ConsoleUI.scanString("Nome: ");
        entiti.Price = ConsoleUI.scanDouble("Preço: ");
        CRUDPackage result = _business.Insert(entiti);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }

    private static void Update() throws Exception {
        String code = ConsoleUI.scanString("Código do Produto: ");
        Product oldEntiti = _business.Get(code);

        if (oldEntiti == null) {
            Utilities.ConsoleUI.PrintWhiteSpace();
            Utilities.ConsoleUI.PrintMessageError("Não foi possível alterar pois não foi encontrato produto correspondente ao código " + code);
            Utilities.ConsoleUI.PrintWhiteSpace();
            Utilities.ConsoleUI.PrintWhiteSpace();
        } else {
            Product newEntiti = new Product();
            newEntiti.Id = oldEntiti.Id;
            Utilities.ConsoleUI.RequestDataUpdate();
            newEntiti.Code = ConsoleUI.scanString("Código: ");
            newEntiti.Name = ConsoleUI.scanString("Nome: ");
            newEntiti.Price = ConsoleUI.scanDouble("Preço: ");
            CRUDPackage result = _business.Update(newEntiti);
            Utilities.ConsoleUI.FeedBackCRUD(result);
        }
    }

    private static void Delete() throws Exception {
        String code = ConsoleUI.scanString("Código do Produto: ");
        CRUDPackage result = _business.Delete(code);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }

    private static void List() {
        ArrayList<Product> list = _business.GetAll();

        if (list.isEmpty()) {
            Utilities.ConsoleUI.PrintWhiteSpace();
            Utilities.ConsoleUI.PrintMessage("Nenhuma produto cadastrada!");
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
