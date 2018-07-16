/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import Business.CustomerBusiness;
import Business.ProductBusiness;
import Business.SaleBusiness;
import Entities.Customer;
import Entities.Product;
import Entities.Sale;
import Entities.SaleItem;
import Utilities.GraphicInterfaceHelper;
import Utilities.OperationPackage;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author lucas.budelon
 */
public class SaleController implements Initializable {

    // <editor-fold defaultstate="collapsed" desc="Control Variables">
    
    public static Sale currentSale;

    private SaleBusiness saleBusiness;
    private CustomerBusiness customerBusiness;
    private ProductBusiness productBusiness;

    private ObservableList<Sale> observableListSales;
    private ObservableList<Customer> observableListCustomers;
    private ObservableList<Product> observableListProducts;
    private ObservableList<SaleItem> observableListItems;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Bind Form">
    @FXML
    private VBox panelSaleForm;

    @FXML
    private TextField txtCustomer;

    @FXML
    private TableView<SaleItem> tableSaleItem;

    @FXML
    private TableColumn<SaleItem, String> tableColumnSaleItemProduct;

    @FXML
    private TableColumn<SaleItem, Integer> tableColumnSaleItemQuantity;

    @FXML
    public void findCustomer(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SaleCustomer.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Selecionar Cliente");
        stage.initOwner(panelSaleForm.getScene().getWindow());
        stage.showAndWait();
        loadCurrentSale();
    }

    @FXML
    public void addProduct(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SaleProduct.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Adicionar Produto");
        stage.initOwner(panelSaleForm.getScene().getWindow());
        stage.showAndWait();
        loadCurrentSale();
    }

    @FXML
    public void finalizeSale(ActionEvent event) throws IOException {
        OperationPackage result = saleBusiness.Insert(currentSale);
        Utilities.GraphicInterfaceHelper.printFeedBackCRUD(result);
        currentSale = null;
        closeVBox(panelSaleForm);
    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        closeVBox(panelSaleForm);
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bind List">
    
    @FXML
    private VBox panelList;

    @FXML
    private TableView<Sale> tableViewSales;

    @FXML
    private TableColumn<Sale, String> tableColumnCode;

    @FXML
    private TableColumn<Sale, String> tableColumnDate;

    @FXML
    private TableColumn<Sale, Double> tableColumnCustomer;

    @FXML
    private TableColumn<Sale, Double> tableColumnItems;

    @FXML
    private TableColumn<Sale, Double> tableColumnTotal;

    @FXML
    public void create(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SaleForm.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Venda");
        stage.initOwner(panelList.getScene().getWindow());
        stage.showAndWait();

        listSales();
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bind SaleCustomer">
    
    @FXML
    private VBox panelSaleCustomerList;

    @FXML
    private TableView<Customer> tableSaleCustomer;

    @FXML
    private TableColumn<Customer, String> tableColumnSaleCustomerCpf;

    @FXML
    private TableColumn<Customer, String> tableColumnSaleCustomerName;

    @FXML
    private TableColumn<Customer, Double> tableColumnSaleCustomerEmail;

    @FXML
    public void selectCustomer(ActionEvent event) throws IOException {
        currentSale.setCustomer(tableSaleCustomer.getSelectionModel().getSelectedItem());
        closeVBox(panelSaleCustomerList);
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bind SaleProduct">
    
    @FXML
    private VBox panelSaleProductList;

    @FXML
    private TextField txtQuantityProduct;

    @FXML
    private TableView<Product> tableSaleProduct;

    @FXML
    private TableColumn<Product, String> tableColumnSaleProductCode;

    @FXML
    private TableColumn<Product, String> tableColumnSaleProductName;

    @FXML
    private TableColumn<Product, Double> tableColumnSaleProductPrice;

    @FXML
    public void selectProduct(ActionEvent event) throws IOException {

        try {

            SaleItem saleItem = new SaleItem();
            saleItem.setProduct(tableSaleProduct.getSelectionModel().getSelectedItem());
            saleItem.setQuantity(Integer.parseInt(txtQuantityProduct.getText()));

            currentSale.getItems().add(saleItem);

            closeVBox(panelSaleProductList);

        } catch (NumberFormatException ex) {
            txtQuantityProduct.setText("");
            Utilities.GraphicInterfaceHelper.printMessageError("Informe uma quantidade válida!");
        }
    }

    // </editor-fold>
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        saleBusiness = new SaleBusiness();
        customerBusiness = new CustomerBusiness();
        productBusiness = new ProductBusiness();
        

        loadCurrentSale();

        if (tableViewSales != null) {
            listSales();
        }

        if (tableSaleCustomer != null) {
            listCustomers();
        }

        if (tableSaleProduct != null) {
            listProducts();
        }
    }

    private void listSales() {

        tableColumnCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sale, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sale, String> cell) {
                final Sale sale = cell.getValue();
                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(
                        sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT))
                );
                return simpleObject;
            }

        });
        tableColumnCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        tableColumnItems.setCellValueFactory(new PropertyValueFactory<>("itemsResume"));
        tableColumnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        OperationPackage getAll = saleBusiness.GetAll();

        if (getAll.ValidOperation) {
            observableListSales = FXCollections.observableArrayList((ArrayList<Sale>) getAll.Data);
            tableViewSales.setItems(observableListSales);
        } else {
            Utilities.GraphicInterfaceHelper.printFeedBackCRUD(getAll);
        }
    }

    private void listCustomers() {

        tableColumnSaleCustomerCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnSaleCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnSaleCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        OperationPackage getAll = customerBusiness.GetAll();

        if (getAll.ValidOperation) {
            observableListCustomers = FXCollections.observableArrayList((ArrayList<Customer>) getAll.Data);
            tableSaleCustomer.setItems(observableListCustomers);
        } else {
            Utilities.GraphicInterfaceHelper.printFeedBackCRUD(getAll);
        }
    }

    private void listProducts() {

        tableColumnSaleProductCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableColumnSaleProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnSaleProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        OperationPackage getAll = productBusiness.GetAll();

        if (getAll.ValidOperation) {
            observableListProducts = FXCollections.observableArrayList((ArrayList<Product>) getAll.Data);
            tableSaleProduct.setItems(observableListProducts);
        } else {
            Utilities.GraphicInterfaceHelper.printFeedBackCRUD(getAll);
        }
    }

    private void loadCurrentSale() {

        if (currentSale == null) {
            currentSale = new Sale();
        }
        
        if (txtCustomer != null && currentSale.getCustomer().getName() != null) {
            txtCustomer.setText(currentSale.getCustomer().getName());
        }

        if (currentSale.getItems().size() > 0) {
            tableColumnSaleItemProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
            tableColumnSaleItemQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            observableListItems = FXCollections.observableArrayList(currentSale.getItems());
            tableSaleItem.setItems(observableListItems);
        }
    }

    private void closeVBox(VBox vbox) {
        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.close();
    }
}
