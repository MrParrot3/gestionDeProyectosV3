/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.logic;

import gestiondeproyectos.ui.controller.ProyectosBean;
import java.util.Collection;

/**
 *
 * @author Iker
 */
public interface ProyectosManager {
    
    /**
     * 
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
    public ProyectosBean setNuevoProyecto(String cliente, String concepto,
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
    
}
