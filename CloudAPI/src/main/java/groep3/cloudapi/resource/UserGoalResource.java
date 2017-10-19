package groep3.cloudapi.resource;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.presentation.model.GoalPresenter;
import groep3.cloudapi.service.GoalService;
import groep3.cloudapi.service.UserService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}")
    public Goal getGoal(@PathParam("goalId") String goalId)
    {
        Goal goal = goalService.getGoal(goalId);
        return goal;
    }
    
    @POST
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}")
    public Goal post(@PathParam("userId") String userId, @PathParam("moduleId") String moduleId, @PathParam("goalId") String goalId) 
    {
        //TODO: Should return a boolean?
        return null;
    }

    @PUT
    @Path("/{UserId}/modules/{ModuleId}/goals/{goalId}/approve")
    public Boolean switchApproveBool(@PathParam("goalId") String goalId)
    {
        boolean hasSucceeded = goalService.switchApproveBool(goalId);
        return hasSucceeded;
    }
    
    @PUT
    @Path("/{UserId}/modules/{ModuleId}/goals/{goalId}/complete")
    public Boolean switchCompleteBool(@PathParam("goalId") String goalId)
    {
        boolean hasSucceeded = goalService.switchCompleteBool(goalId);
        return hasSucceeded;
    }
}
