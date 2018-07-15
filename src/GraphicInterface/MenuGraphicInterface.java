/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author lucas.budelon
 */
public class MenuGraphicInterface extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        Parent list = FXMLLoader.load(getClass().getResource("MenuGraphicInterface.fxml"));
        
        Scene scene = new Scene(list);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @FXML
    private AnchorPane panelMenu;
    
    @FXML
    public void showCustomerGraphicInterfaceList(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("CustomerGraphicInterfaceList.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(panelMenu.getScene().getWindow());
        stage.showAndWait();
    }
    
    @FXML
    public void showProductGraphicInterfaceList(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ProductGraphicInterfaceList.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(panelMenu.getScene().getWindow());
        stage.showAndWait();
    }
    
    @FXML
    public void showMoveAccount(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(panelMenu.getScene().getWindow());
        stage.showAndWait();
    }
    
    @FXML
    public void showSales(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(panelMenu.getScene().getWindow());
        stage.showAndWait();
    }
}
