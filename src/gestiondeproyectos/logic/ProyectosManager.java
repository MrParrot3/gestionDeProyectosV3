/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.logic;

import gestiondeproyectos.ui.controller.ClienteBean;
import gestiondeproyectos.ui.controller.ProyectosBean;
import gestiondeproyectos.ui.controller.ServicioBean;
import java.util.Collection;
import javafx.collections.ObservableList;

/**
 *
 * @author Iker
 */
public interface ProyectosManager {
    
    /**
     * Interfaz que encapsula los métodos de negocio para la gestión de proyectos.
     * @return devuelve todos los proyectos
     */
    public Collection getAllProyectos();
   
    /**
     * introduce nuevo proyecto
     * @param cliente
     * @param concepto
     * @param horasEstimadas
     * @param horasFinales
     * @param importe
     * @param importeFinal
     * @param fechaEntrega
     * @param fechaFinal
     * @return 
     */
    public ProyectosBean setNuevoProyecto(ClienteBean cliente, String concepto, Collection<ServicioBean> servicios,
            Integer horasEstimadas, Integer horasFinales, float importe,
            float importeFinal, String fechaEntrega, String fechaFinal);
    
    /**
     * filtra los proyectos
     * @param clienteF
     * @param pf
     * @param psf
     * @return 
     */
    public Collection getProyectosFiltrados(String clienteF, Boolean pf, Boolean psf); 
    
    public void eliminarProyecto(ProyectosBean proyecto);
    
    public void modificarProyecto(ProyectosBean proyecto);
    
    
}
