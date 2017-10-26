package groep3.cloudapi.resource;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.Role;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.NotificationPresenter;
import groep3.cloudapi.presentation.model.NotificationView;
import groep3.cloudapi.service.NotificationService;
import io.dropwizard.auth.Auth;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
    private final NotificationService notificationService;
    private final NotificationPresenter notificationPresenter;
     
    @Inject
    public UserNotificationResource (NotificationService notificationService, NotificationPresenter notificationPresenter)
    {
        this.notificationService = notificationService;
        this.notificationPresenter = notificationPresenter;
    }
 
    //Get Calls - Notifications
    @GET
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CLIENT, Role.Labels.CARETAKER})
    @Path( "/{userId}/notifications")
    public List<NotificationView> getNotifications(@PathParam( "userId") String id, @Auth User authenticatedUser)
    {
        List<Notification> notifications = notificationService.getNotifications(id);
        List<NotificationView> notificationsToReturn = notificationPresenter.present(notifications);
        return notificationsToReturn;
    }
    
    @DELETE
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CLIENT, Role.Labels.CARETAKER})
    @Path("/{userId}/notifications/notifications/{notificationId}")
    public Boolean deleteSpecificNotification(@PathParam("userId") String uid, @PathParam("notificationId") String nid, @Auth User authenticatedUser)
    {
        Boolean success = notificationService.deleteSpecificNotification(uid, nid);
        return success;
    }
}
