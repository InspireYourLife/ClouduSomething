package groep3.cloudapi.resource;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.model.UserPresenter;
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
    
    @Inject
    public UserResource (UserService userService, NotificationService notificationService, UserPresenter userPresenter)
    {
        this.userService = userService;
        this.notificationService = notificationService;
       // this.userPresenter = userPresenter;
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
}
