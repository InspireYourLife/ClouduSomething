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
    private final ModulePresenter modulePresenter;
    
    @Inject
    public ModuleResource (ModuleService moduleService, UserService userService, ModulePresenter modulePresenter)
    {
        this.moduleService = moduleService;
        this.userService = userService;
        this.modulePresenter = modulePresenter;
    }
    
    // Get all modules 
    @GET
    public List<Module> getAllModules()
    {
        List<Module> modules = moduleService.getAllModules();
        return modules;
    }
    
    //Create a new module
    @POST
    @Path ("/modules")
    public Module createModule(@Valid Module newModule)
    {
        moduleService.createModule(newModule);
        return newModule;
    }
    
    //Get all modules from specific user
    @GET
    @Path ("/Users/{UserId}/modules")
    public List<Module> getModulesByUserId(String Id, List userModule)
    {
        //How to incorporate the user id here?
        List<Module> userModules = moduleService.getModulesByUserId(Id, userModule);
        return userModules;
    }
    
    //Assign module to specific userId
    @POST
    @Path ("/Users/{UserId}/modules")
    public Module assignModule(String id);
    {
        moduleService.assignModule(id);
        
    }
    
    //Get specific module from specific user
    @GET
    @Path ("/Users/{UserId}/modules/{ModuleId}")
    public List<Module> getUserModule()
    {
        
    }
}
