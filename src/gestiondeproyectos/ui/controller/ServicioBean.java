/*
 * CLASE FACTURA
 */
package gestiondeproyectos.ui.controller;

import java.io.Serializable;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iker Jon Mediavilla
 */
@XmlRootElement(name="servicio")
public class ServicioBean implements Serializable {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty descripcion;
    
    public ServicioBean(Integer id, String nombre, String descripcion){
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        
    }
    
    public ServicioBean(){
        this.id = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty();
        this.descripcion = new SimpleStringProperty();
        
    }

    public Integer getId() {
        return this.id.get();
    }
    
    public void setId(Integer id){
        this.id.set(id);
    }

    public String getNombre() {
        return this.nombre.get();
    }
    
    public void setNombre(String nombre){
        this.nombre.set(nombre);
    }
    
    public String getDescripcion() {
        return this.descripcion.get();
    }
    
    public void setDescripcion(String descripcion){
        this.nombre.set(descripcion);
    }
    
    
    
    
}
