package groep3.cloudapi.service;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.GoalDAO;
import groep3.cloudapi.persistence.ModuleDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
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
    private final GoalDAO goalDAO;
    
    @Inject
    public ModuleService (ModuleDAO moduleDAO, UserDAO userDAO, GoalDAO goalDAO)
    {
        this.moduleDAO = moduleDAO;
        this.userDAO = userDAO;
        this.goalDAO = goalDAO;
    }
    
    // get all modules
    public List<Module> getAllModules()
    {
        List<Module> modules = moduleDAO.getAll();
        
        //werking van requireResult
        if (modules.isEmpty() == true)
        {
            throw new NotFoundException("There are no Modules in the database");
        }
        
        return modules;
    }
    
    // get specific module by id
    public Module getModuleById(String moduleId)
    {
        Module tempModule = moduleDAO.get(moduleId);
        requireResult(tempModule, "Module not found");
        
        return tempModule;
    }
    
    // get all modules from a specific user
    public List<Module> getModulesByUserId(String id)
    {
        User u = userDAO.get(id);
        requireResult(u, "User not found");
        
        List<Module> m = u.getModules();
        requireResult (m, "No Modules were found");
        
        return m;    
    }
    
    //Create a new module
    public void createModule (Module newModule)
    {
        if (newModule != null)
            {
                throw new BadRequestException("A module with this name already exists");
            }
        
        Date currentTime = Date.from(Instant.now());
        newModule.setCreationDate(currentTime);
        
        newModule.getIsTemplate();
        
        moduleDAO.create(newModule);
    }
    
    //Assign a module to a specific user
    public boolean assignModule (String id, String modId)
    {
        
        User u = userDAO.get(id);
        requireResult(u, "User not found");
                
        List<Module> m = u.getModules();  
        requireResult (m, "No Modules were found");
        
        Module mToAdd = moduleDAO.get(modId);
        requireResult(mToAdd, "Module not found");
        
        mToAdd.setIsTemplate(false);
        
        if (m.contains(mToAdd))
        {
            throw new BadRequestException("This module has already been assigned to the user");
        }
        
        m.add(mToAdd);                
        u.setModules(m);
        
        if (mToAdd == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }   
    
    //Get specific module from specific user
    public Module getUserModule(String userId, String modId)
    {
        int mId = Integer.parseInt(modId);
        
        User u = userDAO.get(userId);
        requireResult(u, "User not found");
        
        List<Module> m = u.getModules();
        requireResult (m, "No Modules were found");
        
        Module uMod = m.get(mId);
        requireResult(uMod, "Module not found");
        
        return uMod;
    }
    
    //Delete TEMPLATE module
    public boolean deleteModule(String modId)
    {  
        Module m = moduleDAO.get(modId);
        requireResult(m, "Module not found");
        
        moduleDAO.delete(m);
        
        if (moduleDAO.get(modId) == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Delete specific module from specific user
    public boolean deleteModuleFromUser(String userId, String modId)
    {
        int mId = Integer.parseInt(modId);
        
        User u = userDAO.get(userId);
        requireResult(u, "User not found");
        
        List<Module> m = u.getModules();
        requireResult(m, "No modules were found");
        
        Module mToRemove = m.get(mId);
        requireResult(mToRemove, "Module not found");

        moduleDAO.delete(mToRemove);
        
        if (mToRemove == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}