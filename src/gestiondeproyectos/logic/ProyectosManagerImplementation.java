/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.logic;

import gestiondeproyectos.rest.ProyectoRESTClient;
import gestiondeproyectos.ui.controller.ProyectosBean;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;

/**
 * Class that implements the business logic interface by returning data
 * obtained from the REST service for the Proyectos entity.
 * 
 * @author Iker Jon Mediavilla
 */
public class ProyectosManagerImplementation implements ProyectosManager{
    
     //REST proyectos web client
    private ProyectoRESTClient webClient;
    private static final Logger LOGGER=Logger.getLogger("javafxapplicationud3example");
    
    
    public ProyectosManagerImplementation(){
        webClient=new ProyectoRESTClient();
    }
    
    
    @Override
    public Collection getAllProyectos() {
        LOGGER.info("ProyectosManager: Finding all proyectos from REST service (XML).");
        Collection<ProyectosBean> proyectos = webClient.findAll_XML(new GenericType<Collection<ProyectosBean>>() {});
        return proyectos;
    }
    

    @Override
    public ProyectosBean setNuevoProyecto(String cliente, String concepto, Integer horasEstimadas, Integer horasFinales, float importe, float importeFinal, String fechaEntrega, String fechaFinal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getProyectosFiltrados(String clienteF, Boolean pf, Boolean psf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarProyecto(ProyectosBean proyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
