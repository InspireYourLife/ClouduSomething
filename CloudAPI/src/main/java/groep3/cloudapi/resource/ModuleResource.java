package groep3.cloudapi.resource;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.model.Role;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.ModulePresenter;
import groep3.cloudapi.presentation.model.ModuleView;
import groep3.cloudapi.service.ModuleService;
import io.dropwizard.auth.Auth;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path( "/modules" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class ModuleResource extends BaseResource
{
    private final ModuleService moduleService;
    private final ModulePresenter modulePresenter;
    
    @Inject
    public ModuleResource (ModuleService moduleService, ModulePresenter modulePresenter)
    {
        this.moduleService = moduleService;
        this.modulePresenter = modulePresenter;
    }
    
    // Get all modules 
    @GET
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CLIENT, Role.Labels.CARETAKER})
    public List<ModuleView> getAllModules(@Auth User authenticatedUser, @QueryParam("role") String role)
    {
        List<Module> modules = moduleService.getAllModules();
        List<ModuleView> modulesToReturn = modulePresenter.present(modules);
        return modulesToReturn;
    }
    
    //Get one module
    @GET
    @Path ("/{moduleId}")
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER, Role.Labels.CLIENT})
    public ModuleView getModule(@PathParam ("moduleId") String modId)
    {
        Module m = moduleService.getModuleById(modId);
        ModuleView moduleToShow = modulePresenter.present(m);
        
        return moduleToShow;
    }
    
    //Create a new module
    @POST
    @RolesAllowed({Role.Labels.ADMIN})
    public Module createModule(@Valid Module newModule)
    {
        moduleService.createModule(newModule);
        return newModule;
    }
    
    //Delete a module by Id
    @DELETE
    @Path("/{moduleId}")
    @RolesAllowed({Role.Labels.ADMIN})
    public boolean deleteModule(@PathParam ("id") String modId)
    {
        Boolean deleted = moduleService.deleteModule(modId);
        
        return deleted;
    }
}
