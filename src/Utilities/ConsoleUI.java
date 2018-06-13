/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.Scanner;

/**
 *
 * @author lucas.budelon
 */
public class ConsoleUI {

    public static String scanString(Object out) {
        try {
            System.out.print(out);
            Scanner scanner = new Scanner(System.in);
            String scan = scanner.nextLine();
            if (scan == null || scan.isEmpty()){
                PrintMessageError("Você deve informar um texto válido!");
                return scanString(out);
            }else{
                return (scan);
            }
        } catch (Exception e) {
            PrintMessageError("Você deve informar um texto válido!");
            return scanString(out);
        }
    }

    public static int scanInt(Object out) {
        try {
            System.out.print(out);
            Scanner scanner = new Scanner(System.in);
            return (scanner.nextInt());
        } catch (Exception e) {
            PrintMessageError("Você deve informar um número válido!");
            return scanInt(out);
        }
    }

    public static double scanDouble(Object out) {
        try {
            System.out.print(out);
            Scanner scanner = new Scanner(System.in);
            return (scanner.nextDouble());
        } catch (Exception e) {
            PrintMessageError("Você deve informar um número válido!");
            return scanDouble(out);
        }
    }

    public static float scanFloat(Object out) {
        try {
            System.out.print(out);
            Scanner scanner = new Scanner(System.in);
            return (scanner.nextFloat());
        } catch (Exception e) {
            PrintMessageError("Você deve informar um número válido!");
            return scanFloat(out);
        }
    }

    public static boolean scanBoolean(Object out) {
        try {
            System.out.print(out);
            Scanner scanner = new Scanner(System.in);
            return (scanner.nextBoolean());
        } catch (Exception e) {
            PrintMessageError("Você deve informar Verdadeiro ou Falso!");
            return scanBoolean(out);
        }
    }

    public static char scanChar(Object out) {
        try {
            System.out.print(out);
            Scanner scanner = new Scanner(System.in);
            return (scanner.next().charAt(0));
        } catch (Exception e) {
            PrintMessageError("Você deve informar um caracter válido!");
            return scanChar(out);
        }
    }

    public static void PrintMessage(String message) {
        System.out.println(message);
    }

    public static void PrintMessageError(String message) {
        System.err.println(message);
    }

    public static void ShowMenuCRUD(String entity) {

        System.out.println("-- " + entity + " ------------------------");
        System.out.println("1 - Cadastro");
        System.out.println("2 - Alterar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Listar");
        System.out.println("5 - Relatório");
        System.out.println("0 - Voltar");

        PrintLine();
        PrintWhiteSpace();
    }

    public static void PrintWhiteSpace() {
        System.out.println("");
    }

    public static void PrintLine() {
        System.out.println("---------------------------------");
    }

    public static void EndApplication() {
        PrintWhiteSpace();
        PrintLine();
        System.out.println("Fim da aplicação!");
        PrintLine();
    }

    public static void FeedBackCRUD(OperationPackage CRUDPackage) throws Exception {

        if (CRUDPackage.HasError) {
            
            PrintWhiteSpace();
            System.err.println(CRUDPackage.Message 
                    + " | " 
                    + CRUDPackage.Exception.getMessage());
            PrintWhiteSpace();
            
            //throw new Exception(CRUDPackage.Message, CRUDPackage.Exception);
        }
        
        else if (CRUDPackage.Success) {
            PrintWhiteSpace();
            System.out.println(CRUDPackage.Message);
            PrintWhiteSpace();
        } else {
            PrintWhiteSpace();
            System.err.println(CRUDPackage.Message);
            PrintWhiteSpace();
        }
    }

    public static int RequestOptionMenu() {
        return scanInt("Informe a opção de menu: ");
    }

    public static void RequestDataInsert() {
        System.out.println("Informe os dados para o cadastro");
    }

    public static void RequestDataUpdate() {
        System.out.println("Informe os dados para alterar");
    }
}
