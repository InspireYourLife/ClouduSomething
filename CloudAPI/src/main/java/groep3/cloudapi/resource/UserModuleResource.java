package groep3.cloudapi.resource;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.service.ModuleService;
import groep3.cloudapi.service.UserService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
     private final UserService userService;
     
    @Inject
    public UserModuleResource (ModuleService moduleService, UserService userService)
    {
        this.moduleService = moduleService;
        this.userService = userService;
    }
    
//Get all modules from specific user
//    @GET
//    @Path ("/{UserId}/modules")
//    public List<Module> getModulesByUserId(String Id, List userModule)
//    {
//        //How to incorporate the user id here?
//        List<Module> userModules = moduleService.getModulesByUserId(Id, userModule);
//        return userModules;
//    }
//    
//    //Assign module to specific userId
//    @POST
//    @Path ("/{UserId}/modules")
//    public Module assignModule(String userId, String modId);
//    {
//        moduleService.assignModule(userId, modId);
//        
//        
//        
//        
//    }
//  
//    //Get specific module from specific user
    @GET
    @Path ("/{UserId}/modules/{ModuleId}")
    public Module getModule(@PathParam ("UserId") String userId, @PathParam ("ModuleId") String moduleId)
    {
        Module module = moduleService.getUserModule(userId, moduleId);
        return module;
    }
}
