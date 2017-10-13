package groep3.cloudapi.resource;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.service.NotificationService;
import groep3.cloudapi.service.UserService;
import java.util.List;
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

public class UserNotificationResource extends BaseResource
{
    private final UserService userService;
    private final NotificationService notificationService;
     
    @Inject
    public UserNotificationResource (UserService userService, NotificationService notificationService)
    {
        this.userService = userService;
        this.notificationService = notificationService;
    }
 
    //Get Calls - Notifications
    @GET
    @Path( "/{id}/notifications")
    public List<Notification> getNotifications(@PathParam( "id") String id)
    {
        List<Notification> notifications = notificationService.getNotifications(id);
        return notifications;
    }
}
