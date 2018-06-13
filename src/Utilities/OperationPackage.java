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
public class OperationPackage<T> {

    public String Message;
    public boolean HasError;
    public boolean Success;
    public boolean ValidOperation;
    public Exception Exception;
    public T Data;

    public OperationPackage() {
        HasError = false;
        Exception = null;
        Data = null;
    }

    public OperationPackage(String message, boolean success) {
        Message = message;
        Success = success;
        HasError = false;
        ValidOperation = success;
        Exception = null;
    }

    public OperationPackage(String message, Exception exception) {
        Message = message;
        Success = false;
        HasError = true;
        ValidOperation = false;
        Exception = exception;
    }

    public OperationPackage(String message, boolean success, T entiti) {
        Message = message;
        Success = success;
        HasError = false;
        ValidOperation = success;
        Exception = null;
        Data = entiti;
    }

    public OperationPackage(String message, Exception exception, T entiti) {
        Message = message;
        Success = false;
        HasError = true;
        ValidOperation = false;
        Exception = exception;
        Data = entiti;
    }
}
