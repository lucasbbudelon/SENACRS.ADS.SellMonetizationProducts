/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author lucas.budelon
 */
public class CRUDPackage {

    public String Message;
    public boolean HasError;
    public boolean Success;
    public Exception Exception;

    public CRUDPackage() {
        HasError = false;
        Exception = null;
    }
    
    public CRUDPackage(String message, boolean success) {
        Message = message;
        Success = success;
        HasError = false;
        Exception = null;
    }
    
    public CRUDPackage(String message, Exception exception) {
        Message = message;
        Success = false;
        HasError = true;
        Exception = exception;
    }
}
