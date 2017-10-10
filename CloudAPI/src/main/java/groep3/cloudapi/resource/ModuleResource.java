package groep3.cloudapi.resource;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.presentation.model.ModulePresenter;
import groep3.cloudapi.service.ModuleService;
import groep3.cloudapi.service.UserService;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Tim
 */

@Path( "/modules" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class ModuleResource extends BaseResource
{
    private final ModuleService moduleService;
    private final UserService userService;
    
    @Inject
    public ModuleResource (ModuleService moduleSurvice, ModulePresenter modulePresenter)
    {
        //this.moduleService = moduleService;
        //this.userService = userService;
        //this.modulePresenter = modulePresenter;
    }
    
    // Get all modules 
    @GET
    public List<Module> getAllModules()
    {
        List<Module> modules = moduleService.getAll();
        return modules;
    }
    
    //Create a new module
    @POST
    public Module createModule(@Valid Module newModule)
    {
            moduleService.create(newModule);
        return newModule;
    }
    
    //Get all modules from specific user
    @GET
    public List<Module> getAllUserModules()
    {
        //How to incorporate the user id here?
        List<Module> userModules = moduleService.getAll();
        return userModules;
    }
    
    //Assign module to specific userId
    @POST
    public Module assignModule();
    {
        
    }
    
    //Get specific module from specific user
    @GET
    public List<Module> getUserModule()
    {
        
    }
}
