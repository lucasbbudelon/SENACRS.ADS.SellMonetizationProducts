/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import Business.AccountBusiness;
import Business.CustomerBusiness;
import Entities.Customer;
import Utilities.OperationPackage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author lucas.budelon
 */
public class AccountController implements Initializable {

    // <editor-fold defaultstate="collapsed" desc="Control Variables">
    
    private CustomerBusiness customerBusiness;
    private AccountBusiness accountBusiness;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bind AccountOperations">
    @FXML
    private AnchorPane accountOperations;
        
    @FXML
    public void openDeposit(ActionEvent event) throws IOException {
        openAnchorPane("AccountDeposit.fxml", "Depósito");
    }
    
    @FXML
    public void openWithdrawal(ActionEvent event) throws IOException {
        openAnchorPane("AccountWithdrawal.fxml", "Saque");
    }
    
    @FXML
    public void openTransfer(ActionEvent event) throws IOException {
        openAnchorPane("AccountTransfer.fxml", "Transferência");
    }

    @FXML
    public void openBalance(ActionEvent event) throws IOException {
        openAnchorPane("AccountBalance.fxml", "Saldo");
    }
        
    @FXML
    public void cancel(ActionEvent event) throws IOException {
        closeAnchorPane(accountOperations);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bind Deposit">
    
    @FXML
    private AnchorPane depositForm;
    
    @FXML
    private TextField txtCpfDeposit;
    
    @FXML
    private TextField txtValueDeposit;
    
    @FXML
    public void completeDeposit(ActionEvent event) throws IOException {
        
        double value;
        
        try {
            value = Double.parseDouble(txtValueDeposit.getText());
        } 
        catch (NumberFormatException e)  {
            Utilities.GraphicInterfaceHelper.printMessageError("Informe um valor válido!");
            txtValueDeposit.setText("");
            return;
        }
        
        String cpf = txtCpfDeposit.getText();
        
        OperationPackage result = accountBusiness.Deposit(cpf, value);
        Utilities.GraphicInterfaceHelper.printFeedBackCRUD(result);
        
        closeAnchorPane(depositForm);
    }
    
    @FXML
    public void cancelDeposit(ActionEvent event) throws IOException {
        closeAnchorPane(depositForm);
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bind withdrawal">
    
    @FXML
    private AnchorPane withdrawalForm;
    
    @FXML
    private TextField txtCpfWithdrawal;
    
    @FXML
    private TextField txtValueWithdrawal;
    
    @FXML
    public void completeWithdrawal(ActionEvent event) throws IOException {
        
        double value;
        
        try {
            value = Double.parseDouble(txtValueWithdrawal.getText());
        } 
        catch (NumberFormatException e)  {
            Utilities.GraphicInterfaceHelper.printMessageError("Informe um valor válido!");
            txtValueDeposit.setText("");
            return;
        }
        
        String cpf = txtCpfWithdrawal.getText();
        
        OperationPackage result = accountBusiness.Withdrawal(cpf, value);
        Utilities.GraphicInterfaceHelper.printFeedBackCRUD(result);
        
        closeAnchorPane(withdrawalForm);
    }
    
    @FXML
    public void cancelWithdrawal(ActionEvent event) throws IOException {
        closeAnchorPane(withdrawalForm);
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bind Transfer">
    
    @FXML
    private AnchorPane transferForm;
    
    @FXML
    private TextField txtCpfOrigenTransfer;
    
    @FXML
    private TextField txtCpfDestinyTransfer;
    
    @FXML
    private TextField txtValueTransfer;
    
    @FXML
    public void completeTransfer(ActionEvent event) throws IOException {
        
        double value;
        
        try {
            value = Double.parseDouble(txtValueTransfer.getText());
        } 
        catch (NumberFormatException e)  {
            Utilities.GraphicInterfaceHelper.printMessageError("Informe um valor válido!");
            txtValueDeposit.setText("");
            return;
        }
        
        String cpfOrigen = txtCpfOrigenTransfer.getText();
        String cpfDestiny = txtCpfDestinyTransfer.getText();
        
        OperationPackage result = accountBusiness.Transfer(cpfOrigen, cpfDestiny, value);
        Utilities.GraphicInterfaceHelper.printFeedBackCRUD(result);
        
        closeAnchorPane(transferForm);
    }
    
    @FXML
    public void cancelTransfer(ActionEvent event) throws IOException {
        closeAnchorPane(transferForm);
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bind Balance">
    
    @FXML
    private AnchorPane balanceForm;
    
    @FXML
    private TextField txtCpfBalance;
        
    @FXML
    public void completeBalance(ActionEvent event) throws IOException {
                
        String cpf = txtCpfBalance.getText();

        OperationPackage result = customerBusiness.Get(cpf);
        
        if (result.ValidOperation){
            
            Customer customer =  (Customer) result.Data;
            
            String messageBalance = "Consulta efetuada com sucesso!\n"
                    + "\nCliente: " + customer.getName()
                    + "\nCPF: " + customer.getCpf()
                    + "\nConta: R$ " + customer.getAccount().getNumber()
                    + "\nSaldo: R$ " + customer.getAccount().getBalance();
            
            Utilities.GraphicInterfaceHelper.printMessageSuccess(messageBalance);
            
        }else{
            Utilities.GraphicInterfaceHelper.printFeedBackCRUD(result);
        }
        
        closeAnchorPane(transferForm);
    }
    
    @FXML
    public void cancelBalance(ActionEvent event) throws IOException {
        closeAnchorPane(balanceForm);
    }

    // </editor-fold>
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerBusiness = new CustomerBusiness();
        accountBusiness = new AccountBusiness();
    }
    
    private void openAnchorPane(String fxml, String title) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.initOwner(accountOperations.getScene().getWindow());
        stage.showAndWait();
    }
    
    private void closeAnchorPane(AnchorPane anchorPane){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    } 
}
