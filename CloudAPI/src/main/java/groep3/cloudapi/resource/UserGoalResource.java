package groep3.cloudapi.resource;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Module;
import groep3.cloudapi.presentation.model.GoalPresenter;
import groep3.cloudapi.service.GoalService;
import groep3.cloudapi.service.UserService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/users" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class UserGoalResource extends BaseResource
{
    private final UserService userService;
    private final GoalService goalService;
    private final GoalPresenter goalPresenter;
     
    @Inject
    public UserGoalResource (UserService userService, GoalService goalService, GoalPresenter goalPresenter)
    {
        this.userService = userService;
        this.goalService = goalService;
        this.goalPresenter = goalPresenter;
    }
    
    //Get Calls - Goals
    @GET
    @Path("/{userId}/goals")
    public List<Goal> getAllGoalsFromUser(@PathParam("userId") String userId) 
    {
        List<Goal> allGoalsFromUser = goalService.getAllGoalsFromUser(userId);
        return allGoalsFromUser;
    }
    
    @GET
    @Path("/{userId}/modules/{moduleId}/goals")
    public List<Goal> getGoalsFormModule(@PathParam("userId") String userId, @PathParam("moduleId") String moduleId) 
    {
        List<Goal> goalsFromModule = goalService.getGoalsFromModule(moduleId);
        return goalsFromModule;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    @GET
    @Path("/{userId}/goals/{ModulesId}/goals")
    public List<Goal> get(@PathParam("userId") String userId, @PathParam("ModuleId") String moduleId) 
    {   
        List<Goal> goals = goalService.GetAll(moduleId);
        
        return goals;
    }
    
    @GET
    @Path("/{userId}/goals/{ModulesId}/goals/{GoalId}")
    public Goal get(@PathParam("userId") String userId, @PathParam("ModuleId") String moduleId, @PathParam("GoalId") String goalId) 
    {
        Goal goal = goalService.GetGoal( goalId );
        
        return goal;
    }
        
    @POST
    @Path("/{UserId}/modules/{ModuleId}/goals/{GoalId}")
    public Goal post(@PathParam("userId") String userId, @PathParam("ModuleId") String moduleId, @PathParam("GoalId") String goalId) 
    {
        Module module = GetModule(moduleId); //<-- method van Tim om specifieke module te verkrijgen
        
        // pak alle goalid's
        // [Lijst goalId's in module].Add(goalId)
        // moduleService.save(module);
        
        return null;
    }
    
    // Tijdelijk, tot methode van Tim er is
    public List<Module> GetModules(String userId)
    {
        return null;
    }
    // Tijdelijk, tot methode van Tim er is
    public Module GetModule(String userId)
    {
        return null;
    }
    // Tijdelijk, tot methode van Tim er is
    public Module GetModule()
    {
        return null;
    }
}
