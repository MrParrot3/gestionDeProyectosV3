/*
 * CONTROLADOR VENTANA FACTURAS
 */
package gestiondeproyectos.ui.controller;

import gestiondeproyectos.logic.ClientesManager;
import gestiondeproyectos.logic.FacturasManager;
import gestiondeproyectos.logic.ProyectosManager;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Arantzazu Azkona Villaverde
 */
public class VentanaFacturasController  {
    
    private static final Logger logger = Logger.getLogger("controller.class");
    private Stage stage;
    private Parent root;
    private FacturasManager facturasManager;
    private ClientesManager clientesManager;
    private ProyectosManager proyectosManager;
    private ObservableList<FacturaBean> facturasDatosFiltrados;
    
    @FXML
    private Button btnAnadir;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private MenuBar barraMenu;
    @FXML
    private Label lblNumFactura;
    @FXML
    private TextField txtNumFactura;
    @FXML
    private Label lblCliente;
    @FXML
    private TextField txtCliente;
    @FXML
    private Label lblFechaEmision;
    @FXML
    private DatePicker dateFechaEmision;
    @FXML
    private Label lblImporte;
    @FXML
    private TextField txtImporte;
    @FXML
    private Label lblFechaVencimiento;
    @FXML
    private DatePicker dateFechaVencimiento;
    @FXML
    private CheckBox cbEstado;
    @FXML
    private CheckBox cbFiltarFacturasPagadas;
    @FXML
    private Label lblFiltarCliente;
    @FXML
    private TextField txtFiltrarCliente;
    @FXML
    private Label lblFechaDesde;
    @FXML
    private DatePicker dateFechaDesde;
    @FXML
    private Label lblFechaHasta;
    @FXML
    private DatePicker datFechaHasta;
    @FXML
    private TableView<FacturaBean> tabla;
    @FXML
    private TableColumn columNumFactura;
    @FXML
    private TableColumn columCliente;
    @FXML
    private TableColumn columFechaEmision;
    @FXML
    private TableColumn columFechaVencimiento;
    @FXML
    private TableColumn columImporte;
    @FXML
    private Button btnSalir;
    @FXML
    private Menu menuClientes;
    @FXML
    private Menu menuProyectos;
    @FXML
    private Menu menuServicios;
    int numero;    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String hoy=formatter.format(LocalDate.now());

    public void setStage(Stage stage){
        this.stage=stage;
    }
    
    public void initStage(Parent root){
        this.root = root;
        logger.info("Inicialización de la ventana FACTURAS");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/gestiondeproyectos/ui/estilos/estilos.css").toExternalForm());
        stage.setTitle("FACTURAS");  
        stage.setResizable(false);
        stage.setOnShowing(this::handleWindowShowing);
        stage.show();
        
    }
    private void handleWindowShowing(WindowEvent e){
               
        columNumFactura.setCellValueFactory(new PropertyValueFactory<>("nFactura"));
        columCliente.setCellValueFactory(new PropertyValueFactory<>("nif"));
        columFechaEmision.setCellValueFactory(new PropertyValueFactory<>("fechaEmision"));
        columFechaVencimiento.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));
        //modificar el color de la fecha de vencimiento si ha superado la fecha actual
        columImporte.setCellValueFactory(new PropertyValueFactory<>("importe"));
        btnAnadir.setDisable(false);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        ObservableList<FacturaBean> facturasDatos=FXCollections.observableArrayList(facturasManager.getFacturas());
        tabla.setItems(facturasDatos);
        tabla.getSelectionModel().selectedItemProperty().addListener(this::handleFacturasTableSelectionChanged);
        
        MenuItem itemC = new MenuItem(); 
        menuClientes.getItems().add(itemC);
        MenuItem itemS = new MenuItem();
        menuProyectos.getItems().add(itemS);
        MenuItem itemF = new MenuItem();
        menuServicios.getItems().add(itemF);     
    }
    public void setManager(FacturasManager facturasManager, 
            ProyectosManager proyectosManager, ClientesManager clientesManager) {
       this.facturasManager =facturasManager;
       this.proyectosManager = proyectosManager;
       this.clientesManager = clientesManager;
    }
    private void handleFacturasTableSelectionChanged(ObservableValue observable,Object oldValue, Object newValue){
        if(newValue!=null){            
            FacturaBean factura = (FacturaBean)newValue;
            txtNumFactura.setText(factura.getNFactura().toString());
            txtCliente.setText(factura.getNif());
            dateFechaEmision.setValue(LocalDate.parse(factura.getFechaEmision(), formatter)); 
            txtImporte.setText(factura.getImporte().toString());
            if(factura.getEstado().equals("pagada")){
                cbEstado.setSelected(true);
            }
            else{cbEstado.setSelected(false);}
            dateFechaVencimiento.setValue(LocalDate.parse(factura.getFechaVencimiento(), formatter));            
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);
            btnAnadir.setDisable(true);        
    }
    }
    /**
     * MÉTODO PARA AÑADIR FACTURA
     */
    public void anyadirFactura(){  
        String estadoFactura;
        if(txtNumFactura.getText().trim().equals("")| dateFechaEmision.getValue()==null|dateFechaVencimiento.getValue()==null | txtCliente.getText().trim().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debe rellenar todos los campos");
            alert.showAndWait();
        }
        else{
            try{
            boolean existe=false;            
            int numFactura=Integer.parseInt(txtNumFactura.getText());
            logger.info("Comprobando que la factura "+numFactura+" no existe...");
            existe=buscarNFactura(numFactura);
            if(existe){
                Alert alerta=new Alert(Alert.AlertType.ERROR,"Ya existe una factura con ese número");
                alerta.showAndWait();
                txtNumFactura.requestFocus();                
            }
            else{
                if(cbEstado.isSelected()){
                    estadoFactura="pagada";
                }
                else{estadoFactura="no pagada";}
                ObservableList<FacturaBean> facturasDatos=FXCollections.observableArrayList(facturasManager.getFacturas());
                if( dateFechaVencimiento.getValue().compareTo(dateFechaEmision.getValue())<0){
                    Alert alerta=new Alert(Alert.AlertType.ERROR,"La fecha de vencimiento de la factura no puede ser inferior a la fecha de emisión");
                    alerta.showAndWait();
                    dateFechaVencimiento.requestFocus();
                    }
                else{            
                    int numeroFactura=Integer.parseInt(txtNumFactura.getText());                
                    float importe=Float.parseFloat(txtImporte.getText()); 
                    for(FacturaBean factura: facturasDatos){
                        if(factura.getNFactura().toString().equals(txtNumFactura.getText())){
                           existe=true;
                        }
                    }
                    if(existe){
                        Alert alerta=new Alert(Alert.AlertType.ERROR,"Ya existe una factura con ese número");
                        alerta.showAndWait();
                        txtNumFactura.requestFocus();
                    }
                    else{
                        tabla.getItems().add(new FacturaBean(numeroFactura,dateFechaEmision.getValue().format(formatter),importe,estadoFactura,dateFechaVencimiento.getValue().format(formatter),txtCliente.getText()));
                        facturasManager.anyadirFactura(new FacturaBean(numeroFactura,dateFechaEmision.getValue().format(formatter),importe,estadoFactura,dateFechaVencimiento.getValue().format(formatter),txtCliente.getText()));
                        logger.info("Factura añadida");
                        tabla.refresh();
                        eliminarTextoCampos();
                        }
                    }
                }
            }
            catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "El número de factura y el importe deben ser numéricos");                
                alert.showAndWait();
                txtNumFactura.requestFocus();
            }
        }
    }
    /**
     * MÉTODO PARA MODIFICAR FACTURA
     */
    public void modificarFactura(){
        try{
        FacturaBean selectedFactura = ((FacturaBean)tabla.getSelectionModel().getSelectedItem());
        if(selectedFactura.getNFactura().toString().equals(txtNumFactura.getText())){
            logger.info("el estado de la factura es "+cbEstado.getText());
            int numero=selectedFactura.getNFactura();
            selectedFactura.setNFactura(Integer.parseInt(txtNumFactura.getText()));
            selectedFactura.setFechaEmision(dateFechaEmision.getValue().format(formatter));  
            selectedFactura.setImporte(Float.parseFloat(txtImporte.getText()));
            if(cbEstado.isSelected()){
               selectedFactura.setEstado("pagada");
            }
            else{selectedFactura.setEstado("no pagada");}            
            selectedFactura.setFechaVencimiento(dateFechaVencimiento.getValue().format(formatter));
            selectedFactura.setNif(txtCliente.getText().trim());
            if( dateFechaVencimiento.getValue().compareTo(dateFechaEmision.getValue())<0){
                Alert alerta=new Alert(Alert.AlertType.ERROR,"La fecha de vencimiento de la factura no puede ser inferior a la fecha de emisión");
                alerta.showAndWait();
                dateFechaVencimiento.requestFocus();
            }
            else{
                facturasManager.modificarFactura(selectedFactura,numero);
                logger.info("Factura modificada");
                eliminarTextoCampos();
                tabla.refresh();                              
            }            
        }
        else{
            boolean resultado;
            int numFactura=Integer.parseInt(txtNumFactura.getText());
            logger.info("Comprobando que la factura "+numFactura+" no existe...");
            resultado=buscarNFactura(numFactura);
            if(resultado){
                Alert alerta=new Alert(Alert.AlertType.ERROR,"Ya existe una factura con ese número");
                alerta.showAndWait();
                txtNumFactura.requestFocus();                
             }
            else{      
                if( dateFechaVencimiento.getValue().compareTo(dateFechaEmision.getValue())<0){
                    Alert alerta=new Alert(Alert.AlertType.ERROR,"La fecha de vencimiento de la factura no puede ser inferior a la fecha de emisión");
                    alerta.showAndWait();
                    dateFechaVencimiento.requestFocus();
                    }
                else{
                    int numero=selectedFactura.getNFactura();
                    selectedFactura.setNFactura(Integer.parseInt(txtNumFactura.getText()));                    
                    selectedFactura.setFechaEmision(dateFechaEmision.getValue().format(formatter));  
                    selectedFactura.setImporte(Float.parseFloat(txtImporte.getText()));
                    selectedFactura.setEstado(cbEstado.getText());
                    selectedFactura.setFechaVencimiento(dateFechaVencimiento.getValue().format(formatter));
                    selectedFactura.setNif(txtCliente.getText().trim());
                    facturasManager.modificarFactura(selectedFactura,numero);
                    logger.info("Factura modificada");
                    tabla.refresh();                    
                    tabla.getSelectionModel().clearSelection();
                    eliminarTextoCampos();
                }  
            }
        }
        }
        catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "El número de factura y el importe deben ser numéricos");                
                alert.showAndWait();
                txtNumFactura.requestFocus();
        }
            
    }
    /**
     * MÉTODO PARA BORRAR FACTURA
     */   
    public void borrarFactura(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar la factura?");  
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            facturasManager.borrarFactura((FacturaBean)tabla.getSelectionModel().getSelectedItem());
            tabla.getItems().remove(tabla.getSelectionModel().getSelectedItem());
            logger.info("Factura eliminada");
            tabla.refresh();
            tabla.getSelectionModel().clearSelection();
            eliminarTextoCampos();    
        }     
        else{ 
            tabla.refresh();
            tabla.getSelectionModel().clearSelection();
            eliminarTextoCampos();
        }
    }
    /**
     * MÉTODO PARA BUSCAR FACTURAS 
     */
    public void buscarFacturas(){
        Boolean check=false;
        ObservableList<FacturaBean> facturasDatos=FXCollections.observableArrayList(facturasManager.getFacturas());
        if (cbFiltarFacturasPagadas.isSelected()){
            check=true;        
        }  
        facturasDatosFiltrados=FXCollections.observableArrayList(facturasManager.buscarFacturas(check,txtFiltrarCliente.getText().trim(),dateFechaDesde.getValue(),datFechaHasta.getValue()));
        if(facturasDatosFiltrados.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"No hay facturas que cumplan las condiciones de búsqueda");
            alert.showAndWait();
            eliminarTextoCampos();
        }
        else{
        tabla.setItems(facturasDatosFiltrados);            
        eliminarTextoCampos();
        }       
    }
    /**
     * MÉTODO PARA BUSCAR FACTURA
     * @param numero define el número de factura a buscar
     * @return devuelve una variable de tipo booleano que indica si la factura existe
     */
    public boolean buscarNFactura(int numero){
        boolean resultado;
        resultado=facturasManager.buscarNFactura(numero);
        return resultado;
    }
    
 @FXML
    public void handleMenuClientes (Event e){
        logger.info("En el evento del menu Clientes");
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/gestiondeproyectos/ui/view/ventanaClientes.fxml"));
            
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
    * Metodo que controla el evento que lanza el Menu servicios. Abre la ventana servicios
    * @param e Event
    */
    @FXML
    public void handleMenuServicios (Event e){
        logger.info("En el evento del menu servicios");

          FXMLLoader loader=new FXMLLoader(getClass().getResource("/gestiondeproyectos/ui/view/ventanaServiciosObras.fxml"));
            
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
    /**
    * Metodo que controla el evento que lanza el Menu Proyectos. Abre la ventana proyectos
    * @param e Event
    */
    @FXML
    public void handleMenuProyectos (Event e){
        logger.info("En el evento del menu proyectos");
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/gestiondeproyectos/ui/view/ventanaProyectos.fxml"));
            
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
    /**
     * MÉTODO QUE LIMPIA Y DESELECCIONA LOS CONTROLES, ACTUALIZA LA TABLA E INHABILITA BOTONES 
     */
    public void eliminarTextoCampos(){         
        txtNumFactura.requestFocus(); 
        txtNumFactura.setText("");
        dateFechaEmision.setValue(null);
        dateFechaEmision.setPromptText("");
        txtImporte.setText("");
        txtCliente.setText("");
        txtFiltrarCliente.setText("");
        dateFechaVencimiento.setValue(null);
        dateFechaVencimiento.setPromptText(null);
        dateFechaDesde.setValue(null);
        dateFechaDesde.setPromptText(null);
        datFechaHasta.setValue(null);
        datFechaHasta.setPromptText(null);
        cbEstado.setSelected(false);  
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnAnadir.setDisable(false);
        cbFiltarFacturasPagadas.setSelected(false); 
        tabla.refresh();        
    }
    /**
     * MÉTODO QUE PERMITE SALIR DE LA APLICACIÓN
     */
    public void salir(){
     Platform.exit();
    }
    public boolean comprobarEstado(int numero){
        boolean resultado;
        resultado=facturasManager.buscarNFactura(numero);
        return resultado;
    }
}
    

