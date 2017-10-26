package groep3.cloudapi.resource;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.presentation.model.GoalPresenter;
import groep3.cloudapi.service.GoalService;
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
    @RolesAllowed( "CARETAKER" )
    public List <Goal> getAll()
    {
        List<Goal> goals = goalService.getAll();
        return goals;
    }
    
    @POST
    @RolesAllowed( "ADMIN" )
    public Goal create(@Valid Goal newGoal)
    {
        goalService.create(newGoal);
        return newGoal;
    }
}
