package groep3.cloudapi.service;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.model.User;
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
import org.bson.types.ObjectId;

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
        List<Module> modules = moduleDAO.getAll();
        
        //werking van requireResult op single objecten
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
        if (m.isEmpty() == true)
        {
            throw new NotFoundException("There are no modules in the database");
        }
        
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
        
        //newModule.getIsTemplate();
        
        moduleDAO.create(newModule);
    }
    
    //Assign a module to a specific user
    public boolean assignModule (String id, String modId)
    {       
        User u = userDAO.get(id);
        requireResult(u, "User not found");
                
        List<Module> mList = u.getModules();  

        Module mod = moduleDAO.get(modId);
        requireResult(mod, "Module not found");
        
        if (mod.getIsTemplate() == true)
        {
            Module mToAdd = mod;
            
//            for (Module m : mList)
//            {                            
//                if (mList.isEmpty() == false && m.getName() == mod.getName())
//                {
//                    throw new BadRequestException("This module has already been assigned to the user");
//                }
//            }
            
            mToAdd.setIsTemplate(false);
            mToAdd.setId(new ObjectId());

            mList.add(mToAdd);                
            u.setModules(mList);

            userDAO.update(u);

            return true;
        }
        else
        {
            throw new BadRequestException("The selected module is not a template");
        }
    }   
    
    //Get specific module from specific user
    public Module getUserModule(String userId, String modId)
    {       
        User u = userDAO.get(userId);
        requireResult(u, "User not found");
        
        Module m = moduleDAO.get(modId);
        requireResult(m, "Module not found");
                
        return m;
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
        User u = userDAO.get(userId);
        requireResult(u, "User not found");
        
        Module mId = moduleDAO.get(modId);
        requireResult(mId, "Module not found");
        
        moduleDAO.delete(mId);
               
        if (mId == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }   
}