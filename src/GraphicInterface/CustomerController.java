/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import Business.CustomerBusiness;
import Entities.Customer;
import Utilities.GraphicInterfaceHelper;
import Utilities.OperationPackage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author lucas.budelon
 */
public class CustomerController implements Initializable {

    // <editor-fold defaultstate="collapsed" desc="Control Variables">
    private CustomerBusiness productBusiness;
    private ObservableList<Customer> observableListCustomers;

    private Customer selectedItem;

    public Customer getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Customer selectedItem) {
        this.selectedItem = selectedItem;
        txtCPF.setText(selectedItem.getCpf());
        txtName.setText(selectedItem.getName());
        txtEmail.setText(selectedItem.getEmail());
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bind Form">
    @FXML
    private AnchorPane panelForm;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;

    @FXML
    public void save(ActionEvent event) throws IOException {

        Stage stage = (Stage) panelForm.getScene().getWindow();

        if (selectedItem == null) {
            insert();
        } else {
            update();
        }

        stage.close();
    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) panelForm.getScene().getWindow();
        stage.close();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Bind List">
    @FXML
    private VBox panelList;

    @FXML
    private TableView<Customer> tableViewCustomers;

    @FXML
    private TableColumn<Customer, String> tableColumnCPF;

    @FXML
    private TableColumn<Customer, String> tableColumnName;

    @FXML
    private TableColumn<Customer, Double> tableColumnEmail;

    @FXML
    public void create(ActionEvent event) throws IOException {

        selectedItem = null;

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("CustomerForm.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(panelList.getScene().getWindow());
        stage.showAndWait();

        list();
    }

    @FXML
    public void update(ActionEvent event) throws IOException {

        selectedItem = tableViewCustomers.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerForm.fxml"));

            Parent root = (Parent) loader.load();

            CustomerController controller = (CustomerController) loader.getController();
            controller.setSelectedItem(selectedItem);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(panelList.getScene().getWindow());
            dialogStage.showAndWait();
            list();

        } else {
            GraphicInterfaceHelper.printUnselectedItem();
        }
    }

    @FXML
    public void delete(ActionEvent event) throws IOException {

        selectedItem = tableViewCustomers.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            delete();
        } else {
            GraphicInterfaceHelper.printUnselectedItem();
        }
    }

    // </editor-fold>
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            productBusiness = new CustomerBusiness();

        if (tableViewCustomers != null) {
            list();
        }
    }

    private void insert() {

        Customer entiti = new Customer();

        entiti.setCpf(txtCPF.getText());
        entiti.setName(txtName.getText());
        entiti.setEmail(txtEmail.getText());

        OperationPackage result = productBusiness.Insert(entiti);
        Utilities.GraphicInterfaceHelper.printFeedBackCRUD(result);
    }

    private void update() {

        OperationPackage searchByCPF = productBusiness.Get(selectedItem.getCpf());

        if (searchByCPF.ValidOperation) {

            selectedItem.setId(((Customer) searchByCPF.Data).getId());
            selectedItem.setCpf(txtCPF.getText());
            selectedItem.setName(txtName.getText());
            selectedItem.setEmail(txtEmail.getText());

            OperationPackage result = productBusiness.Update(selectedItem);

            Utilities.GraphicInterfaceHelper.printFeedBackCRUD(result);
        } else {
            Utilities.GraphicInterfaceHelper.printFeedBackCRUD(searchByCPF);
        }
    }

    private void delete() {

        Alert dialogoExe = new Alert(Alert.AlertType.WARNING);

        ButtonType btnYes = new ButtonType("Sim");
        ButtonType btnNo = new ButtonType("Não");

        dialogoExe.setTitle("Confirmação de exclusão");
        dialogoExe.setHeaderText("Esta ação não poderá ser desfeita!");
        dialogoExe.setContentText("Deseja excluir?");

        dialogoExe.getButtonTypes().setAll(btnYes, btnNo);
        dialogoExe.showAndWait().ifPresent(b -> {
            if (b == btnYes) {
                OperationPackage result = productBusiness.Delete(selectedItem.getCpf());
                Utilities.GraphicInterfaceHelper.printFeedBackCRUD(result);
                if (result.Success) {
                    list();
                }
            }
        });
    }

    private void list() {

        tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        OperationPackage getAll = productBusiness.GetAll();

        if (getAll.ValidOperation) {
            observableListCustomers = FXCollections.observableArrayList((ArrayList<Customer>) getAll.Data);
            tableViewCustomers.setItems(observableListCustomers);
        } else {
            Utilities.GraphicInterfaceHelper.printFeedBackCRUD(getAll);
        }
    }
}
