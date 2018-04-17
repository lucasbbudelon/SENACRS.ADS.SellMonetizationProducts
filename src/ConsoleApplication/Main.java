package ConsoleApplication;

/**
 *
 * @author lucas.budelon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ShowMenu();

        int opMenu = 0;
        
        do {
            opMenu = ConsoleUI.scanInt("Informe a opção de menu: ");
            
            ConsoleUI.PrintWhiteSpace();
            
            switch (opMenu){
                case 1:{
                    AccountConsoleApplication.main(args);
                    break;
                }
                
                case 2:{
                    //BookCRUD.main(args);
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
        System.out.println("1 - Conta");
        System.out.println("2 - Cliente");
        System.out.println("3 - Produto");
        System.out.println("4 - Venda");
        System.out.println("0 - Finalizar");
        System.out.println("-------------------");
        System.out.println("");
    }
}