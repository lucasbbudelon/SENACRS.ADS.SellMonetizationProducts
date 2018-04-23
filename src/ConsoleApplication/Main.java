package ConsoleApplication;

import Utilities.ConsoleUI;

/**
 *
 * @author lucas.budelon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        ShowMenu();

        int opMenu = 0;
        
        do {
            opMenu = ConsoleUI.scanInt("Informe a opção de menu: ");
            
            ConsoleUI.PrintWhiteSpace();
            
            switch (opMenu){
                case 1:{
                    CostomerConsoleApplication.main(args);
                    break;
                }
                
                case 2:{
                    ProductConsoleApplication.main(args);
                    break;
                }
                    
                case 3:{
                    //WithdrawalCRUD.main(args);
                    break;
                }
                
                case 4:{
                    //DevolutionCRUD.main(args);
                    break;
                }
                
                case 0:{
                    //CRUD.EndApplication();
                }
                
                default:{
                    System.err.println("Opção inválida!");
                }    
            }
            
            ShowMenu();
            
        } while(opMenu != 0); 
    }
        
    private static void ShowMenu(){
        
        System.out.println("-------------------");
        System.out.println("1 - Clientes");
        System.out.println("2 - Produtos");
        System.out.println("3 - Operações de monetização");
        System.out.println("4 - Vendas");
        System.out.println("5 - Relatórios");
        System.out.println("0 - Finalizar");
        System.out.println("-------------------");
        System.out.println("");
    }
}