package groep3.cloudapi.service;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.ModuleDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path ("/modules")
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class ModuleService extends BaseService
{
    private final ModuleDAO moduleDAO;
    private final UserDAO userDAO;
    
    @Inject
    public ModuleService (ModuleDAO moduleDAO, UserDAO userDAO)
    {
        this.moduleDAO = moduleDAO;
        this.userDAO = userDAO;
    }
    
    // get all modules
    public List<Module> getAllModules()
    {
        return moduleDAO.getAll();
    }
    
    //Create a new module
    public void createModule (Module newModule)
    {
        moduleDAO.create(newModule);
    }
    
    // get all modules from a specific user
    public List<Module> getModulesByUserId(String id, List userModules)
    {
        User u = userDAO.get(id);
        List<Module> m = u.getModules();
        
        return m;
        
        //TODO m foreach loop -> module doe je een get op de ModuleDAO en die stop je in een nieuwe lijst.
        //for (Module mod : m)
        //
        
    }
    
    //Assign a module to a specific user
    public void assignModule (String id, Module modId)
    {
        User u = userDAO.get(id);
        Module m = moduleDAO.get(id);
        
        moduleDAO.create(m);
    }   
    
    //Get specific module from specific user
    public Module getUserModule(String userId, String moduleId)
    {
        int modId = Integer.parseInt(moduleId);
        
        User u = userDAO.get(userId);
        List<Module> m = u.getModules();
        
        Module module = m.get(modId);
        
        return module; //temp null
    }
}