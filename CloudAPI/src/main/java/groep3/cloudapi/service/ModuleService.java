/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groep3.cloudapi.service;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.persistence.ModuleDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Tim
 */

@Path ("/Modules")
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class ModuleService extends BaseService
{
    private final ModuleDAO moduleDAO;
    
    @Inject
    public ModuleService (ModuleDAO moduleDAO, UserDAO userDAO)
    {
        this.moduleDAO = moduleDAO;
        this.userDAO = userDAO;
    }
    
    public List<Module> GetAll()
    {
        return moduleDAO.getAll();
    }
    
    public Module getModulesByUserId(String id)
    {
        return userDAO.get(id);
    }
}
