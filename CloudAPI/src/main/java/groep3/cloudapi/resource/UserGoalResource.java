package groep3.cloudapi.resource;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Role;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.GoalPresenter;
import groep3.cloudapi.presentation.model.GoalView;
import groep3.cloudapi.service.GoalService;
import groep3.cloudapi.service.UserService;
import io.dropwizard.auth.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api ("goals")
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
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER, Role.Labels.CLIENT, Role.Labels.FAMILY})
    @ApiOperation("Gets all goals from user")
    @Path("/{userId}/goals")
    public List<GoalView> getAllGoalsFromUser(@Auth User authenticatedUser, @PathParam("userId") String userId) 
    {
        List<Goal> allGoalsFromUser = goalService.getAllGoalsFromUser(authenticatedUser, userId);
        return goalPresenter.presentListOfGoals(allGoalsFromUser);
    }
    
    // Returns a list of goals from a specific user
    @GET
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER, Role.Labels.CLIENT, Role.Labels.FAMILY})
    @ApiOperation("Gets all goals from a specific module from a specififc user")
    @Path("/{userId}/modules/{moduleId}/goals")
    public List<GoalView> getGoalsFormModule(@Auth User authenticatedUser, @PathParam("userId") String userId, @PathParam("moduleId") String moduleId) 
    {
        List<Goal> goalsFromModule = goalService.getGoalsFromModule(moduleId);
        return goalPresenter.presentListOfGoals(goalsFromModule);
    }
    
    // Returns specific goal form a specific user
    @GET
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER, Role.Labels.CLIENT, Role.Labels.FAMILY})
    @ApiOperation("Gets a specific goal from a specific module from a specififc user")
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}")
    public GoalView getGoal(@Auth User authenticatedUser, @PathParam("goalId") String goalId)
    {
        Goal goal = goalService.getGoal(goalId);
        return goalPresenter.presentGoal(goal);
    }
    
    // Assigns a goal to a module
    @POST
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    @ApiOperation("Assigns a goal to a module of a user")
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}")
    public Boolean assignGoalToModule(@Auth User authenticatedUser, @PathParam("userId") String userId, @PathParam("moduleId") String moduleId, @PathParam("goalId") String goalId) 
    {
        boolean hasSucceeded = goalService.assignGoalToModule(moduleId, goalId);
        return hasSucceeded;
    }
    
    // Remove a goal form a specific user
    @DELETE
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    @ApiOperation("Removes a goal from a module of a user")
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}")
    public Boolean removeGoal(@Auth User authenticatedUser, @PathParam("moduleId") String moduleId, @PathParam("goalId") String goalId)
    {
        boolean hasSucceeded = goalService.removeGoal(moduleId, goalId);
        return hasSucceeded;
    }
    
    // Changes approval state of a goal
    @PUT
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    @ApiOperation("Changes the state of approval of a goal")
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}/approve")
    public Boolean switchApproveBool(@PathParam("goalId") String goalId)
    {
        boolean hasSucceeded = goalService.switchApproveBool(goalId);
        return hasSucceeded;
    }
    
    // Changes completion state of a goal
    @PUT
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    @ApiOperation("Changes the state of completion of a goal")
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}/complete")
    public Boolean switchCompleteBool(@PathParam("goalId") String goalId)
    {
        boolean hasSucceeded = goalService.switchCompleteBool(goalId);
        return hasSucceeded;
    }
}
