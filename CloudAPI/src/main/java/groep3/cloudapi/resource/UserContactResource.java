package groep3.cloudapi.resource;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Module;
import groep3.cloudapi.service.ContactService;
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

public class UserContactResource extends BaseResource
{
    private final UserService userService;
    private final ContactService contactService;
     
    @Inject

    public UserContactResource (UserService userService, ContactService contactService)
    {
        this.userService = userService;
        this.contactService = contactService; 
    }
    
    //Get Calls - Contacts
    @GET
    @Path ( "/{UserId}/contacts" )
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
}
