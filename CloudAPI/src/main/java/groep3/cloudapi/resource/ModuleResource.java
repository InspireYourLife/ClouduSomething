/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groep3.cloudapi.resource;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.service.ModuleService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author Tim
 */

@Path( "/Modules" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class ModuleResource extends BaseResource
{
    private final ModuleService moduleService;
    
    @Inject
    public ModuleResource (ModuleService moduleSurvice, ModulePresenter modulePresenter)
    {
        this.moduleService = moduleService;
        //this.modulePresenter = modulePresenter;
    }
    
    // Get all modules
    @GET
    public Array <Module> getAll()
    {
        Array<Module> modules = moduleService.getAll();
        return modules;
    }
    
    
}
