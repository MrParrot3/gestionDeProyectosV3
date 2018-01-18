/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.ui.controller;

import gestiondeproyectos.logic.ClientesManager;
import gestiondeproyectos.logic.FacturasManager;
import gestiondeproyectos.logic.ProyectosManager;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jdk.nashorn.internal.ir.CatchNode;


/**
 *
 * @author Iker 
 */
public class VentanaProyectosController {
    
    private static final Logger logger = Logger.getLogger("controller.class");
    private Stage stage;
    private ProyectosManager proyectosManager;
    private ClientesManager clientesManager;
    private FacturasManager facturasManager;
    
    private ObservableList<ProyectosBean> proyectos;
    
    @FXML
    private Button btnAnadir;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnSalir;
    @FXML
    private TextField tfCliente;
    @FXML
    private TextField tfConcepto;
    @FXML
    private TextField tfHorasEstimadas;
    @FXML
    private TextField tfHorasfinales;
    @FXML
    private TextField tfImporte;
    @FXML
    private TextField tfImporteFinal;
    @FXML
    private DatePicker dpFechaEntrega;
    @FXML
    private DatePicker dpFechaFinal;
    @FXML
    private TextField tfCifCliente;
    @FXML
    private CheckBox cbProyectosFinal;
    @FXML
    private CheckBox cbProyectosSinFinal;
    @FXML
    private TableView<ProyectosBean> tvProyectos;
    @FXML
    private TableColumn tvcolN;
    @FXML
    private TableColumn tvcolCliente;
    @FXML
    private TableColumn tvcolConcepto;
    @FXML
    private TableColumn tvcolServicios;
    @FXML
    private TableColumn tvcolHorasEstimadas;
    @FXML
    private TableColumn tvcolFechaEntrega;
    @FXML
    private TableColumn tvcolImporte;
    @FXML
    private TableColumn tvcolHorasFinales;
    @FXML
    private TableColumn tvcolFechaFinal;
    @FXML
    private TableColumn tvcolImporteFinal;
    @FXML
    private Menu menuServicios;
    @FXML
    private Menu menuFacturas;
    @FXML
    private Menu menuClientes;
            
    
    private Integer np;
    
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
   
    
    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void setManager(FacturasManager facturasManager, 
       ProyectosManager proyectosManager, ClientesManager clientesManager) {
       this.facturasManager =facturasManager;
       this.proyectosManager = proyectosManager;
       this.clientesManager = clientesManager;
    }
    public void initStage(Parent root){
        
        logger.info("Inicializacion proyectos.");
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("PROYECTOS");
        stage.setResizable(false);
        stage.setOnShowing(this::handleWindowShowing);
        btnEliminar.setOnAction(this::handleButtonEliminar);
        btnAnadir.setOnAction(this::handleButtonAnadir);
        btnModificar.setOnAction(this::handleButtonModificar);
        btnSalir.setOnAction(this::handleButtonSalir);
        btnBuscar.setOnAction(this::handleButtonBuscar);
        
        MenuItem itemC = new MenuItem(); 
        menuClientes.getItems().add(itemC);
        MenuItem itemS = new MenuItem();
        menuServicios.getItems().add(itemS);
        MenuItem itemF = new MenuItem();
        menuFacturas.getItems().add(itemF);
        stage.show();
            
    }
    
    /**
     * 
     * @param event Al mostrarse la ventana se rellena la tabla
     */
    @FXML
    private void handleWindowShowing(WindowEvent event){
        
        logger.info("Al mostrar la ventana.");
        tvcolN.setCellValueFactory(new PropertyValueFactory<>("nProyecto"));
        tvcolCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tvcolConcepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        tvcolServicios.setCellValueFactory(new PropertyValueFactory<>("servicios"));
        tvcolHorasEstimadas.setCellValueFactory(new PropertyValueFactory<>("horasEstimadas"));
        tvcolFechaEntrega.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));
        tvcolImporte.setCellValueFactory(new PropertyValueFactory<>("importe"));
        tvcolHorasFinales.setCellValueFactory(new PropertyValueFactory<>("horasFinales"));
        tvcolFechaFinal.setCellValueFactory(new PropertyValueFactory<>("fechaFinal"));
        tvcolImporteFinal.setCellValueFactory(new PropertyValueFactory<>("importeFinal"));
        ObservableList<ProyectosBean> proyectosDatos = FXCollections.observableArrayList(proyectosManager.getAllProyectos());
        tvProyectos.setItems(proyectosDatos);
        tvProyectos.getSelectionModel().selectedItemProperty().addListener(this::handleProyectosTableSelectionChanged);
    
    }
    
    /**
     * Al seleccionar un proyecto de la tabla rellena los campos 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    @FXML
    public void handleProyectosTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue){
        
        logger.info("Al seleccionar al un proyecto de la tabla.");
        if(newValue!=null){
            ProyectosBean proyecto = (ProyectosBean) newValue;
            np=proyecto.getNProyecto();
            tfCliente.setText(proyecto.getCliente());
            tfConcepto.setText(proyecto.getConcepto());
            tfHorasEstimadas.setText(proyecto.getHorasEstimadas().toString());
            tfHorasfinales.setText(proyecto.getHorasFinales().toString());
            tfImporte.setText(proyecto.getImporte().toString());
            tfImporteFinal.setText(proyecto.getImporteFinal().toString());
            dpFechaEntrega.setValue(LocalDate.parse(proyecto.getFechaEntrega(), formatter));
            dpFechaFinal.setValue(LocalDate.parse(proyecto.getFechaFinal(), formatter));
            
            btnAnadir.setDisable(true);
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);
        }
        else{
           limpiarCampos();

            btnAnadir.setDisable(false);
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
            
        }
    }
    @FXML
    public void setProyectosManager(ProyectosManager businessLogicController){
        this.proyectosManager=businessLogicController;
    }
    
    /**
     * 
     * @param event Cuando pulsas el boton añadir comprueba que los campos de cliente, concepto
     *horas estimadas, importe y fecha no esten vacios. Ademas de eso controla que
     *el contenido de todos los campos sea valido, despues añade los datos y actualiza la tabla 
     */
    @FXML
    private void handleButtonAnadir(ActionEvent event) {
        logger.info("Evento del boton añadir en la ventana proyectos");
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
        
        if(this.tfCliente.getText().trim().equals("") || this.tfConcepto.getText().trim().equals("")
            || this.tfHorasEstimadas.getText().trim().equals("") || 
            this.tfImporte.getText().trim().equals("") || this.dpFechaEntrega.getValue().equals("")){
            
            Alert alert = new Alert(Alert.AlertType.ERROR, "Contenido de los campos Cliente, Concepto, Horas estimadas, Importe o Fecha de Entrega estan vacios.");         //decir campos
            alert.showAndWait();
        }
        
        else{
           String ff;
           float imf;
           Integer hf;
           try{
                if(dpFechaFinal.getValue()==null){
                    ff="";
                }
                else{
                    ff=dpFechaFinal.getValue().format(formatter);
                }
                if(tfImporteFinal.getText().equals("")){
                    imf=0;
                }
                else{
                    imf=Float.parseFloat(tfImporteFinal.getText());
                }
                if(tfHorasfinales.getText().equals("")){
                    hf=0;
                }
                else{
                    hf=Integer.parseInt(tfHorasfinales.getText());
                }
               
                Integer.parseInt(tfHorasEstimadas.getText());    
                Float.parseFloat(tfImporte.getText());    
                dpFechaEntrega.getValue().format(formatter);
                
                ProyectosBean proyecto = proyectosManager.setNuevoProyecto(tfCliente.getText(),
                        tfConcepto.getText(), Integer.parseInt(tfHorasEstimadas.getText()),
                        hf, Float.parseFloat(tfImporte.getText()), imf,
                        dpFechaEntrega.getValue().format(formatter), ff);
                
                proyectos = FXCollections.observableArrayList(proyectosManager.getAllProyectos());
                tvProyectos.setItems(proyectos);
                tvProyectos.refresh();
                limpiarCampos(); 
               
                
                
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Contenido de los campos Horas estimadas o Importe no son validos.");         //decir campos
                alert.showAndWait();
            }
            logger.info("Añadido el proyecto");
            tvProyectos.refresh();
            logger.info("Actualizar");
            
            limpiarCampos();
            
            btnAnadir.setDisable(false);
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
        
            
        }
        
    }
    
    /**
     * 
     * @param event Elimina el proyecto que con el que estan rellenos los campos,
     * luego actualiza la tabla y vacia los campos.
     */
    @FXML
    private void handleButtonEliminar(ActionEvent event) {
        logger.info("Evento del boton eliminar en la ventana proyectos");
        Optional<ButtonType> result;
         Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION, "No podrás recuperar la información. ¿Estas seguro?");
        alert.setTitle("¡OJO!");
        alert.setHeaderText("Presta mucha atención!");
        result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if(this.tfCliente.getText().trim().equals("") || this.tfConcepto.getText().trim().equals("")
                || this.tfHorasEstimadas.getText().trim().equals("") || 
                this.tfImporte.getText().trim().equals("") || this.dpFechaEntrega.getValue().equals("")){

                alert = new Alert(Alert.AlertType.ERROR, "Contenido de los campos Cliente, Concepto, Horas estimadas, Importe o Fecha de Entrega estan vacios");         //decir campos
                alert.showAndWait();
            }
            else{
                proyectosManager.eliminarProyecto(tvProyectos.getSelectionModel().getSelectedItem());
                proyectos = FXCollections.observableArrayList(proyectosManager.getAllProyectos());
                tvProyectos.setItems(proyectos);
                tvProyectos.refresh();

                limpiarCampos();      

                btnAnadir.setDisable(false);
                btnModificar.setDisable(true);
                btnEliminar.setDisable(true);
            }
        }
    }
    
    /**
     * 
     * @param event al pulsar el boton cambia los datos del proyecto por los de 
     * los campos controlando que los valores sean valido, luego actualiza la tabla
     * y vacia los campos
     */
    @FXML
    private void handleButtonModificar(ActionEvent event) {
        logger.info("Evento del boton modificar en la ventana proyectos");
        if(this.tfCliente.getText().trim().equals("") || this.tfConcepto.getText().trim().equals("")
            || this.tfHorasEstimadas.getText().trim().equals("") || 
            this.tfImporte.getText().trim().equals("") || this.dpFechaEntrega.getValue().equals("")){
            
            Alert alert = new Alert(Alert.AlertType.ERROR, "Contenido de los campos Cliente, Concepto, Horas estimadas, Importe o Fecha de Entrega estan vacios");         //decir campos
            alert.showAndWait();
        }
        else{
            String ff;
            float imf;
            Integer hf;
            try{
                if(dpFechaFinal.getValue()==null){
                    ff="";
                }
                else{
                    ff=dpFechaFinal.getValue().format(formatter);
                }
                if(tfImporteFinal.getText().equals("")){
                    imf=0;
                }
                else{
                    imf=Float.parseFloat(tfImporteFinal.getText());
                }
                if(tfHorasfinales.getText().equals("")){
                    hf=0;
                }
                else{
                    hf=Integer.parseInt(tfHorasfinales.getText());
                }
               
                ProyectosBean selecProyectos=tvProyectos.getSelectionModel().getSelectedItem();
                selecProyectos.setCliente(tfCliente.getText());
                selecProyectos.setConcepto(tfConcepto.getText());
                selecProyectos.setHorasEstimadas(Integer.parseInt(tfHorasEstimadas.getText()));
                selecProyectos.setHorasFinales(hf);
                selecProyectos.setImporte(Float.parseFloat(tfImporte.getText()));
                selecProyectos.setImporteFinal(imf);
                selecProyectos.setFechaEntrega(dpFechaEntrega.getValue().format(formatter));
                selecProyectos.setFechaFinal(ff);

                
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Contenido de los campos Horas estimadas o Importe no son validos.");         //decir campos
                alert.showAndWait();//alert correcto   
            }
            tvProyectos.refresh();
            limpiarCampos();

            btnAnadir.setDisable(true);    
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);
        }

    }
    
    /**
     * 
     * @param event Busca por los criterios cliente, proyecto finalizado y proyecto ain finalizar
     */
    @FXML
    private void handleButtonBuscar(ActionEvent event) {
        logger.info("Evento del boton buscar en la ventana proyectos");
        boolean pf=false, psf=false;
        if(cbProyectosFinal.isSelected()){
            pf=true;
        }
        
        if(cbProyectosSinFinal.isSelected()){
            psf=true;
        }
        
        proyectos =  FXCollections.observableArrayList(proyectosManager.getProyectosFiltrados(tfCifCliente.getText(), pf, psf));
        tvProyectos.setItems(proyectos);
        tvProyectos.refresh();
    }
    
    /**
     * 
     * @param event cierra la aplicacion al salir
     */
    @FXML
    private void handleButtonSalir(ActionEvent event) {
        logger.info("Evento del boton salir en la ventana proyectos");
        Platform.exit();
    }
    
    /**
     * 
     * @param e cierra esta ventana y nos lleva a la de clientes
     */
        @FXML
    public void handleMenuClientes (Event e){
        logger.info("Evento del boton menu clientes en la ventana proyectos");
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
     * 
     * @param e cierra esta ventana y nos lleva a la de facturas
     */
    @FXML
    private void handleMenuFacturas(Event e){
        logger.info("Evento del boton menu facturas en la ventana proyectos");
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
     * 
     * @param e cierra esta ventana y nos lleva a la de servicios
     */
    @FXML
    private void handleMenuServicios(Event e){
        logger.info("Evento del boton menu servicios en la ventana proyectos");

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/gestiondeproyectos/ui/view/ventanaServiciosObras.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            VentanaServiciosController controlador = loader.getController();
            controlador.setManager(facturasManager,proyectosManager,clientesManager);
            controlador.setStage(stage);
            ((Stage)btnSalir.getScene().getWindow()).close();
            controlador.initStage(root);
            
            
        } catch (IOException ex) {
            Logger.getLogger(VentanaClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpiarCampos(){
        tfCliente.setText("");
        tfConcepto.setText("");
        tfHorasEstimadas.setText("");
        tfHorasfinales.setText("");
        tfImporte.setText("");
        tfImporteFinal.setText("");
        dpFechaEntrega.setValue(null);
        dpFechaFinal.setValue(null);
    }
            
}
