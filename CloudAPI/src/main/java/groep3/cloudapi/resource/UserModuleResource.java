package groep3.cloudapi.resource;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.model.User;
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
import org.bson.types.ObjectId;

@Path( "/users" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class UserModuleResource extends BaseResource
{
     private final ModuleService moduleService;
     private final UserService userService;
     
    @Inject
    public UserModuleResource (ModuleService moduleService, UserService userService)
    {
        this.moduleService = moduleService;
        this.userService = userService;
    }

    //What if I want to get a list of modules from a different user?
    //Get all modules from logged in user
    @GET
    @Path ("/{UserId}/modules")
    @RolesAllowed( "ADMIN, CLIENT, CARETAKER" )
    public List<Module> getModulesFromUser(@PathParam ("UserId")@Auth User authenticatedUser)
    {
        ObjectId userId = authenticatedUser.getId();
        List<Module> allModulesFromUser = moduleService.getModulesByUserId(userId.toString());
        
        return allModulesFromUser;
    }
    
    //Get specific module from logged in user
    @GET
    @Path ("/{UserId}/modules/{ModuleId}")
    @RolesAllowed( "ADMIN, CLIENT, CARETAKER" )
    public Module getModuleFromUser(@PathParam ("UserId") @Auth User authenticatedUser,@PathParam ("ModuleId") String moduleId)
    {
        ObjectId userId = authenticatedUser.getId();       
        Module m = moduleService.getUserModule(userId.toString(), moduleId);
        
        return m;
    }
    
    //Get specific module from specific user
    @GET
    @Path ("/{UserId}/modules/{ModuleId}")
    @RolesAllowed( "ADMIN, CLIENT, CARETAKER, FAMILY" )
    public Module getModule(@PathParam ("ModuleId") String moduleId)
    {
        Module module = moduleService.getModuleById(moduleId);
        
        return module;
    }
    
    //Delete specific module from specific user
    @DELETE
    @Path("/{UserId}/modules/{ModuleId}")
    @RolesAllowed( "ADMIN, CARETAKER" )
    public boolean deleteUserModule(@PathParam ("id") String modId)
    {
        Boolean deleted = moduleService.deleteModule(modId);
        
        return deleted;
    }
    
    //Assign a module to a user
    @PUT
    @Path ("/{UserId}/modules/{ModuleId}")
    @RolesAllowed( "ADMIN, CARETAKER" )
    public boolean assignModule (@PathParam ("UserId") String id,@PathParam ("ModuleId") String modId)
    {
        Boolean mAssigned = moduleService.assignModule(id, modId);
        
        return mAssigned;
    }
}