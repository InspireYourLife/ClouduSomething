package groep3.cloudapi.resource;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.presentation.model.GoalPresenter;
import groep3.cloudapi.service.GoalService;
import groep3.cloudapi.service.UserService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

    // Returns all goals from a user (all modules)
    @GET
    @Path("/{userId}/goals")
    @RolesAllowed( {"CLIENT", "CARETAKER"} )
    public List<Goal> getAllGoalsFromUser(@PathParam("userId") String userId) 
    {
        List<Goal> allGoalsFromUser = goalService.getAllGoalsFromUser(userId);
        return allGoalsFromUser;
    }
    
    // Returns a list of goals from a specific user
    @GET
    @Path("/{userId}/modules/{moduleId}/goals")
    @RolesAllowed( {"CLIENT", "CARETAKER"} )
    public List<Goal> getGoalsFormModule(@PathParam("userId") String userId, @PathParam("moduleId") String moduleId) 
    {
        List<Goal> goalsFromModule = goalService.getGoalsFromModule(moduleId);
        return goalsFromModule;
    }
    
    // Returns specific goal form a specific user
    @GET
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}")
    @RolesAllowed( {"CLIENT", "CARETAKER"} )
    public Goal getGoal(@PathParam("goalId") String goalId)
    {
        Goal goal = goalService.getGoal(goalId);
        return goal;
    }
    
    // Assigns a goal to a module
    @POST
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}")
    @RolesAllowed( "CARETAKER" )
    public Boolean assignGoalToModule(@PathParam("userId") String userId, @PathParam("moduleId") String moduleId, @PathParam("goalId") String goalId) 
    {
        boolean hasSucceeded = goalService.assignGoalToModule(moduleId, goalId);
        return hasSucceeded;
    }
    
    // Remove a goal form a specific user
    @DELETE
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}")
    @RolesAllowed( "CARETAKER" )
    public Boolean removeGoal(@PathParam("goalId") String goalId)
    {
        boolean hasSucceeded = goalService.removeGoal(goalId);
        return hasSucceeded;
    }
    
    // Changes approval state of a goal
    @PUT
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}/approve")
    @RolesAllowed( "CARETAKER" )
    public Boolean switchApproveBool(@PathParam("goalId") String goalId)
    {
        boolean hasSucceeded = goalService.switchApproveBool(goalId);
        return hasSucceeded;
    }
    
    // Changes completion state of a goal
    @PUT
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}/complete")
    @RolesAllowed( "CARETAKER" )
    public Boolean switchCompleteBool(@PathParam("goalId") String goalId)
    {
        boolean hasSucceeded = goalService.switchCompleteBool(goalId);
        return hasSucceeded;
    }
}
