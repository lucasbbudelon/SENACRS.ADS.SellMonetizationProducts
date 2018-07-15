/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import Business.ProductBusiness;
import Entities.Product;
import Utilities.GraphicInterfaceMessages;
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
public class ProductController implements Initializable {

    // <editor-fold defaultstate="collapsed" desc="Control Variables">
    private ProductBusiness business;
    private ObservableList<Product> observableListProducts;

    private Product selectedItem;

    public Product getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Product selectedItem) {
        this.selectedItem = selectedItem;
        txtCode.setText(selectedItem.getCode());
        txtName.setText(selectedItem.getName());
        txtPrice.setText(Double.toString(selectedItem.getPrice()));
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bind Form">
    @FXML
    private AnchorPane panelForm;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;

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
    private TableView<Product> tableViewProducts;

    @FXML
    private TableColumn<Product, String> tableColumnCode;

    @FXML
    private TableColumn<Product, String> tableColumnName;

    @FXML
    private TableColumn<Product, Double> tableColumnPrice;

    @FXML
    public void create(ActionEvent event) throws IOException {

        selectedItem = null;

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ProductForm.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(panelList.getScene().getWindow());
        stage.showAndWait();

        list();
    }

    @FXML
    public void update(ActionEvent event) throws IOException {

        selectedItem = tableViewProducts.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductForm.fxml"));

            Parent root = (Parent) loader.load();

            ProductController controller = (ProductController) loader.getController();
            controller.setSelectedItem(selectedItem);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(panelList.getScene().getWindow());
            dialogStage.showAndWait();
            list();

        } else {
            GraphicInterfaceMessages.printUnselectedItem();
        }
    }

    @FXML
    public void delete(ActionEvent event) throws IOException {

        selectedItem = tableViewProducts.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            delete();
        } else {
            GraphicInterfaceMessages.printUnselectedItem();
        }
    }

    // </editor-fold>
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = new ProductBusiness();

        if (tableViewProducts != null) {
            list();
        }
    }

    private void insert() {

        Product entiti = new Product();

        entiti.setCode(txtCode.getText());
        entiti.setName(txtName.getText());
        entiti.setPrice(Double.parseDouble(txtPrice.getText()));

        OperationPackage result = business.Insert(entiti);
        Utilities.GraphicInterfaceMessages.printFeedBackCRUD(result);
    }

    private void update() {

        OperationPackage searchByCode = business.Get(selectedItem.getCode());

        if (searchByCode.ValidOperation) {

            selectedItem.setId(((Product) searchByCode.Data).getId());
            selectedItem.setCode(txtCode.getText());
            selectedItem.setName(txtName.getText());
            selectedItem.setPrice(Double.parseDouble(txtPrice.getText()));

            OperationPackage result = business.Update(selectedItem);

            Utilities.GraphicInterfaceMessages.printFeedBackCRUD(result);
        } else {
            Utilities.GraphicInterfaceMessages.printFeedBackCRUD(searchByCode);
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
                OperationPackage result = business.Delete(selectedItem.getCode());
                Utilities.GraphicInterfaceMessages.printFeedBackCRUD(result);
                if (result.Success) {
                    list();
                }
            }
        });
    }

    private void list() {

        tableColumnCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        OperationPackage getAll = business.GetAll();

        if (getAll.ValidOperation) {
            observableListProducts = FXCollections.observableArrayList((ArrayList<Product>) getAll.Data);
            tableViewProducts.setItems(observableListProducts);
        } else {
            Utilities.GraphicInterfaceMessages.printFeedBackCRUD(getAll);
        }
    }
}
