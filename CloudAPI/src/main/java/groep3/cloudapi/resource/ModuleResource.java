package groep3.cloudapi.resource;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.presentation.model.ModulePresenter;
import groep3.cloudapi.service.ModuleService;
import groep3.cloudapi.service.UserService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @RolesAllowed( "ADMIN, CLIENT, CARETAKER" )
    public List<Module> getAllModules()
    {
        List<Module> modules = moduleService.getAllModules();
        return modules;
    }
    
    //Create a new module
    @POST
    @RolesAllowed( "ADMIN" )
    public Module createModule(@Valid Module newModule)
    {
        moduleService.createModule(newModule);
        return newModule;
    }
    
    //Delete a module by Id
    @DELETE
    @Path("/{moduleId}")
    @RolesAllowed( "ADMIN" )
    public boolean deleteModule(@PathParam ("id") String modId)
    {
        Boolean deleted = moduleService.deleteModule(modId);
        
        return deleted;
    }
}
