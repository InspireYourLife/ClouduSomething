package groep3.cloudapi.resource;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Role;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.GoalPresenter;
import groep3.cloudapi.presentation.model.GoalView;
import groep3.cloudapi.service.GoalService;
import io.dropwizard.auth.Auth;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@Api ("goals")
@Path( "/goals" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class GoalResource extends BaseResource
{
    private final GoalService goalService;
    private final GoalPresenter goalPresenter;
    
    @Inject
    public GoalResource (GoalService goalService, GoalPresenter goalPresenter)
    {
        this.goalService = goalService;
        this.goalPresenter = goalPresenter;
    }
    
    @GET
    @RolesAllowed(Role.Labels.ADMIN)
    //@ApiOperation("Gets all goals")
    public List<GoalView> getAll(@Auth User authenticatedUser)
    {
        List<Goal> goals = goalService.getAll();
        return goalPresenter.presentListOfGoals(goals);
    }
    
    @POST
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    //@ApiOperation("Creates a new goal")
    public Boolean create(@Auth User authenticatedUser, @Valid Goal newGoal)
    {
        Boolean hasSucceeded = goalService.create(newGoal);
        return hasSucceeded;
    }
}