package groep3.cloudapi.resource;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.model.Role;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.ModulePresenter;
import groep3.cloudapi.presentation.model.ModuleView;
import groep3.cloudapi.service.ModuleService;
import groep3.cloudapi.service.UserService;
import io.dropwizard.auth.Auth;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/users" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class UserModuleResource extends BaseResource
{
     private final ModuleService moduleService;
     private final ModulePresenter modulePresenter;
     
    @Inject
    public UserModuleResource (ModuleService moduleService, ModulePresenter modulePresenter)
    {
        this.moduleService = moduleService;
        this.modulePresenter = modulePresenter;
    }

    //Get all modules from logged in user
    @GET
    @Path ("/{userId}/modules")
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CLIENT, Role.Labels.CARETAKER})
    public List<ModuleView> getModulesFromUser(@PathParam ("userId") String uId, @Auth User authenticatedUser)
    {
        String userId = uId;
        List<Module> allModulesFromUser = moduleService.getModulesByUserId(userId);
        List<ModuleView> modulesToReturn = modulePresenter.present(allModulesFromUser);
        
        return modulesToReturn;
    }
    
    //Get specific module from logged in user
    @GET
    @Path ("/{userId}/modules/{moduleId}")
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CLIENT, Role.Labels.CARETAKER})
    public ModuleView getModuleFromUser(@PathParam ("userId") String uId, @Auth User authenticatedUser,@PathParam ("moduleId") String modId)
    {
        String userId = uId;       
        Module m = moduleService.getUserModule(userId, modId);
        ModuleView moduleToReturn = modulePresenter.present(m);
        return moduleToReturn;
    }
    
    //Delete specific module from specific user
    @DELETE
    @Path("/{userId}/modules/{moduleId}")
    @RolesAllowed( "ADMIN, CARETAKER" )
    public boolean deleteUserModule(@PathParam ("userId") String uId,@PathParam ("moduleId") String modId)
    {
        Boolean deleted = moduleService.deleteModule(modId);
        
        return deleted;
    }
    
    //Assign a module to a user
    @PUT
    @Path ("/{userId}/modules/{moduleId}")
    @RolesAllowed( "ADMIN, CARETAKER" )
    public boolean assignModule (@PathParam ("userId") String uId,@PathParam ("moduleId") String modId)
    {
        Boolean mAssigned = moduleService.assignModule(uId, modId);
        
        return mAssigned;
    }
}