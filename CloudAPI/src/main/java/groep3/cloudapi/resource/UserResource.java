package groep3.cloudapi.resource;

import groep3.cloudapi.service.ContactService;
import groep3.cloudapi.model.Calendar;
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
    private final ContactService contactService;
    private final NotificationService notificationService;
    //private final UserPresenter userPresenter;
     
    @Inject
    public UserResource (UserService userService, NotificationService notificationService, ContactService contactService, UserPresenter userPresenter)
    {
        this.userService = userService;
        this.notificationService = notificationService;
        this.contactService = contactService; 

       // this.userPresenter = userPresenter;
    }
    
    @GET
    public List <User> getAll()
    {
        List<User> users = userService.GetAll();
        return users;
    }
    
    @GET
    @Path ( "/{UserId}" )
    public List <User> getAllContacts(@PathParam ("UserId") String userId)
    {
        List<User> contacts = contactService.getAllContacts(userId);
        return contacts;
    }
    
    @GET
    @Path ("/{UserId}/{ContactId}")
    public User getContact(@PathParam ("UserId") String userId, @PathParam ("ContactId") String contactId)
    {
        User contact = contactService.getContact(userId, contactId);
        return contact;
    }
    
    @POST
    @Path ("/{UserId}/{ContactId}")
    public Notification sendMessage(@PathParam ("UserId") String userId, @PathParam ("ContactId") int contactId, Notification newMessage)
    {
        contactService.sendMessage(userId, contactId, newMessage);
        return newMessage;
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
    
    @GET
    @Path( "/{id}/calendar")
    public Calendar getCalendar(@PathParam("id") String id)
    {
        Calendar calendar = userService.getCalendar(id);
        return calendar;
    }
}
