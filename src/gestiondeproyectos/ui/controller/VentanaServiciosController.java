/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.ui.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import javafx.stage.Stage;
import gestiondeproyectos.logic.ClientesManager;
import gestiondeproyectos.logic.FacturasManager;
import gestiondeproyectos.logic.ProyectosManager;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;

/**
 * Clase que controla la ventana servicios
 * @author Miguel Axier Lafuente Peñas
 */
public class VentanaServiciosController {
    private static final Logger logger =  Logger.getLogger("gestiondeproyectos.ui.controller");
    private Stage stage;
    private ClientesManager clientesManager;
    private FacturasManager facturasManager;
    private ProyectosManager proyectosManager;
    
   
    @FXML
    private Menu menuFacturas;
    @FXML
    private Menu menuClientes;
    @FXML
    private Menu menuProyectos;
    @FXML
    private Button btnSalir;
    /**
     * Metono que establece los managers
     * @param facturasManager manager que controla las facturas
     * @param proyectosManager manager que controla los proyectos
     * @param clientesManager manager que controla los clientes
     */
    public void setManager(FacturasManager facturasManager,
            ProyectosManager proyectosManager,ClientesManager clientesManager){
        this.facturasManager = facturasManager;
        this.clientesManager = clientesManager;
        this.proyectosManager = proyectosManager;
    }
    /**
     * Metodo que establece el escenario
     * @param stage Stage
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }
    /**
     * Metodo que inicia la ventana
     * @param root Parent
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SERVICIOS");
        stage.setResizable(false);
        stage.setOnShowing(this::handleWindowShowing);
        stage.show();
        
        MenuItem menuItem = new MenuItem();
        menuFacturas.getItems().add(menuItem);
        MenuItem menuItem2 = new MenuItem();
        menuClientes.getItems().add(menuItem2);
        MenuItem menuItem3=new MenuItem();
        menuProyectos.getItems().add(menuItem3);
    }
    /**
     * Metodo que controla el evento que se lanza mientras se muestra la ventana
     * 
     * @param e evento de ventana
     */
    public void handleWindowShowing(WindowEvent e){
        
        
        MenuItem menuItem = new MenuItem();
        menuFacturas.getItems().add(menuItem);
        MenuItem menuItem2 = new MenuItem();
        menuClientes.getItems().add(menuItem2);
        MenuItem menuItem3=new MenuItem();
        menuProyectos.getItems().add(menuItem3);
    }
    
    /**
     * Metodo que controla el evento que lanza el botón de salir. Cierra la ventana.
     * @param e ActionEvent
     * @throws IOException 
     */
    @FXML
    public void handleOnActionSalir (ActionEvent e) throws IOException{
        Platform.exit();
    }
    /**
     * Metodo que controla el evento que lanza el menu facturas. Abre la ventana facturas
     * @param e evento
     */
    @FXML
    public void handleOnActionMenuFacturas (Event e){
     
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/gestiondeproyectos/ui/view/ventanaFacturas.fxml"));
            Parent root;
        try {
            root = (Parent) loader.load();
            VentanaFacturasController controlador = loader.getController();
            controlador.setManager(facturasManager,proyectosManager,clientesManager);
            controlador.setStage(stage);
            ((Stage)btnSalir.getScene().getWindow()).close();
            controlador.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(VentanaFacturasController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    /**
     * Metodo que controla el evento que lanza el menu clientes. Abre la ventana Clientes
     * @param e evento
     */
     @FXML
    public void handleOnActionMenuClientes (Event e){
        logger.info("En el evento del menu clientes");
          FXMLLoader loader=new FXMLLoader(getClass().getResource("/gestiondeproyectos/ui/view/ventanaClientes.fxml"));
            Parent root;
        try {
            root = (Parent) loader.load();
            VentanaClientesController controlador = loader.getController();
            controlador.setManager(facturasManager,proyectosManager,clientesManager);
            controlador.setStage(stage);
            ((Stage)btnSalir.getScene().getWindow()).close();
            controlador.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(VentanaClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    /**
     * Metodo que controla el evento que lanza el menu proyectos. Abre la ventana proyectos
     * @param e evento
     */
    @FXML
    public void handleOnActionMenuProyectos (Event e){
    
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/gestiondeproyectos/ui/view/ventanaProyectos.fxml"));
            Parent root;
        try {
            root = (Parent) loader.load();
            VentanaProyectosController controlador = loader.getController();
            controlador.setManager(facturasManager,proyectosManager,clientesManager);
            controlador.setStage(stage);
            ((Stage)btnSalir.getScene().getWindow()).close();
            controlador.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(VentanaProyectosController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
