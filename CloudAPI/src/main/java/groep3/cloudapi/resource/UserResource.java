package groep3.cloudapi.resource;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Module;
import groep3.cloudapi.model.Calendar;
import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.model.GoalPresenter;
import groep3.cloudapi.presentation.model.UserPresenter;
import groep3.cloudapi.service.GoalService;
import groep3.cloudapi.service.NotificationService;
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
    private final NotificationService notificationService;
    //private final UserPresenter userPresenter;
    private final GoalService goalService;
    private final GoalPresenter goalPresenter;
    
    @Inject
    public UserResource (UserService userService, UserPresenter userPresenter, GoalService goalService, GoalPresenter goalPresenter, NotificationService notificationService)
    {
        this.userService = userService;
        this.notificationService = notificationService;
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
    
    @GET
    @Path( "/{id}" )
    public User getUserById(@PathParam( "id") String id)
    {
        User user = userService.getUserById(id);
        return user;
    }
    
    @POST
    public User create(@Valid User newUser)
    {
        userService.create(newUser);
        return newUser;
    }
    
    @GET
    @Path("/{userId}/goals")
    public List<Goal> get(@PathParam("userId") String userId) 
    {
        List<Module> modules = GetModules(userId); //TODO: Pas methode aan naar functie van tim om modules op te halen
        
        List<Goal> goals = goalService.GetAll(modules);
        
        return goals;
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
    
    @Path( "/{id}/points")
    public int getPoints(@PathParam( "id") String id)
    {
        int points = userService.getPoints(id);
        return points;
    }
    
    @GET
    @Path( "/{id}/notifications")
    public List<Notification> getNotifications(@PathParam( "id") String id)
    {
        List<Notification> notifications = notificationService.getNotifications(id);
        return notifications;
    }
    
    @GET
    @Path( "/{id}/calendar")
    public Calendar getCalendar(@PathParam("id") String id)
    {
        Calendar calendar = userService.getCalendar(id);
        return calendar;
    }
}
