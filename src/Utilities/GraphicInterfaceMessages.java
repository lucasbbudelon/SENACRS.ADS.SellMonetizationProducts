/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import javax.swing.JOptionPane;

/**
 *
 * @author lucas.budelon
 */
public class GraphicInterfaceMessages {

    public static void printMessageError(String msg) {
        JOptionPane.showMessageDialog(null,
                msg,
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void printMessageSuccess(String msg) {
        JOptionPane.showMessageDialog(null,
                msg,
                "Sucesso",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static void printUnselectedItem() {
        JOptionPane.showMessageDialog(null,
                "Selecione um item na lista!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void printFeedBackCRUD(OperationPackage CRUDPackage) {
        
        if (CRUDPackage.HasError) {
            
            printMessageError(CRUDPackage.Message 
                    + "\n\n" 
                    + "Exception: " + CRUDPackage.Exception.getMessage());
        }
        
        else if (CRUDPackage.Success) {
            printMessageSuccess(CRUDPackage.Message);
        } else {
            printMessageError(CRUDPackage.Message);
        }
    }
}
