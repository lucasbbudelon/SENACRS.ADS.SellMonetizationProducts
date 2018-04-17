/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleApplication;

import java.util.Scanner;

/**
 *
 * @author lucas.budelon
 */
public class ConsoleUI {

    public static String scanString(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextLine());
    }

    public static int scanInt(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextInt());
    }

    public static double scanDouble(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextDouble());
    }

    public static float scanFloat(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextFloat());
    }

    public static boolean scanBoolean(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextBoolean());
    }

    public static char scanChar(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.next().charAt(0));
    }

    public static void ShowMenuCRUD(String entity){
        
        System.out.println("-- " + entity + " -------------");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Alterar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Listar");
        System.out.println("5 - Voltar");
        
        PrintLine();
        PrintWhiteSpace();
    }
    
    public static void PrintWhiteSpace(){
        System.out.println("");
    }
    
    public static void PrintLine(){
        System.out.println("-------------------");
    }
    
    public static void EndApplication(){
        PrintWhiteSpace();
        PrintLine();
        System.out.println("Fim da aplicação!");  
        PrintLine();
    }
}
