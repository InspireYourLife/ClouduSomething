package groep3.cloudapi.resource;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Module;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.model.GoalPresenter;
import groep3.cloudapi.presentation.model.UserPresenter;
import groep3.cloudapi.service.GoalService;
import groep3.cloudapi.service.UserService;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
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

public class UserResource extends BaseResource
{
    private final UserService userService;
    //private final UserPresenter userPresenter;
    private final GoalService goalService;
    private final GoalPresenter goalPresenter;
    
    @Inject
    public UserResource (UserService userService, UserPresenter userPresenter, GoalService goalService, GoalPresenter goalPresenter)
    {
        this.userService = userService;
       // this.userPresenter = userPresenter;
        this.goalService = goalService;
        this.goalPresenter = goalPresenter;
    }
    
    @GET
    public List <User> getAll()
    {
        List<User> users = userService.GetAll();
        return users;
    }
    
    @POST
    public User create(@Valid User newUser)
    {
        userService.create(newUser);
        return newUser;
    }
    
    @GET
    @Path("/{userId}/goals")
    public GoalPresenter get(@PathParam("userId") String userId) 
    {
        List<Module> modules = GetModules(); //TODO: Pas methode aan naar functie van tim om modules op te halen
        
        List<Goal> goals = goalService.GetAll(modules);
        
        return null;
        //return goalPresenter.present(goals);
    }
    
    @GET
    @Path("/{userId}/goals/{ModulesId}/goals")
    public GoalPresenter get(@PathParam("userId") String userId, @PathParam("ModuleId") String moduleId) 
    {
        Module module = GetModule(); //TODO: Pas methode aan naar functie van tim om een module op te halen
        
        List<Goal> goals = goalService.GetAll(module);
        
        return null;
        //return goalPresenter.present(goals);
    }
    
    @GET
    @Path("/{userId}/goals/{ModulesId}/goals/{GoalId}")
    public GoalPresenter get(@PathParam("userId") String userId, @PathParam("ModuleId") String moduleId, @PathParam("GoalId") String goalId) 
    {
        Goal goal = goalService.GetGoal( goalId );
        
        return null;
        //return goalPresenter.present(goal);
    }
    
    // Tijdelijk, tot methode van Tim er is
    public List<Module> GetModules()
    {
        return null;
    }
    // Tijdelijk, tot methode van Tim er is
    public Module GetModule()
    {
        return null;
    }
}
