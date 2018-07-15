/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleApplication;

import Utilities.ConsoleUI;
import Entities.Product;
import Business.ProductBusiness;
import Entities.SaleItem;
import Utilities.OperationPackage;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class ProductConsoleApplication {
    
    private static final ProductBusiness _business = new ProductBusiness();
    
    public static void main(String[] args) throws Exception {
        
        ConsoleUI.ShowMenuCRUD("PRODUTO");
        
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
            
            Utilities.ConsoleUI.ShowMenuCRUD("PRODUTO");
            
        } while (opMenu != 0);
    }
    
    private static void Insert() throws Exception {
        Utilities.ConsoleUI.RequestDataInsert();
        Product entiti = new Product();
        entiti.setCode(ConsoleUI.scanString("Código: "));
        entiti.setName(ConsoleUI.scanString("Nome: "));
        entiti.setPrice(ConsoleUI.scanDouble("Preço: "));
        OperationPackage result = _business.Insert(entiti);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }
    
    private static void Update() throws Exception {
        
        String code = ConsoleUI.scanString("Código do Produto: ");
        OperationPackage searchByCode = _business.Get(code);
        
        if (searchByCode.ValidOperation) {
            Product newEntiti = new Product();
            newEntiti.setId(((Product) searchByCode.Data).getId());
            Utilities.ConsoleUI.RequestDataUpdate();
            newEntiti.setCode(ConsoleUI.scanString("Código: "));
            newEntiti.setName(ConsoleUI.scanString("Nome: "));
            newEntiti.setPrice(ConsoleUI.scanDouble("Preço: "));
            OperationPackage result = _business.Update(newEntiti);
            Utilities.ConsoleUI.FeedBackCRUD(result);
        } else {
            Utilities.ConsoleUI.FeedBackCRUD(searchByCode);
        }
    }
    
    private static void Delete() throws Exception {
        String code = ConsoleUI.scanString("Código do Produto: ");
        OperationPackage result = _business.Delete(code);
        Utilities.ConsoleUI.FeedBackCRUD(result);
    }
    
    private static void List() throws Exception {
        
        OperationPackage getAll = _business.GetAll();
        
        if (getAll.ValidOperation) {
            
            ArrayList<Product> list = (ArrayList<Product>) getAll.Data;
            
            if (list.isEmpty()) {
                Utilities.ConsoleUI.PrintWhiteSpace();
                Utilities.ConsoleUI.PrintMessage("Nenhum produto cadastrado!");
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
        
        OperationPackage getAll = _business.ReportProductsSold();
        
        if (getAll.ValidOperation) {
            
            ArrayList<SaleItem> list = (ArrayList<SaleItem>) getAll.Data;
            
            if (list.isEmpty()) {
                Utilities.ConsoleUI.PrintWhiteSpace();
                Utilities.ConsoleUI.PrintMessage("Nenhum produto vendido!");
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
