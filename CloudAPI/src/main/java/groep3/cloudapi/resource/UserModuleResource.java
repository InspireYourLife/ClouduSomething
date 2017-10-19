package groep3.cloudapi.resource;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.model.User;
import groep3.cloudapi.service.ModuleService;
import groep3.cloudapi.service.UserService;
import io.dropwizard.auth.Auth;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

    @GET
    @Path ("/{UserId}/modules")
    public List<Module> getModule(@Auth User authenticatedUser)
    {
        ObjectId userId = authenticatedUser.getId();
        List<Module> allModulesFromUser = moduleService.getModulesByUserId(userId.toString());
        
        return allModulesFromUser;
    }
    
    @GET
    @Path ("/{UserId}/modules/{ModuleId}")
    public Module getModule(@PathParam ("ModuleId") String moduleId)
    {
        Module module = moduleService.getModuleById(moduleId);
        return module;
    }
}