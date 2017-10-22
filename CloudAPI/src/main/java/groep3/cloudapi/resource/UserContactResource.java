package groep3.cloudapi.resource;

import groep3.cloudapi.service.ContactService;
import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.User;
import groep3.cloudapi.service.UserService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
    @Path ("{UserId}/contacts" )
    public List <User> getAllContacts(@PathParam ("UserId") String userId)
    {
        List<User> contacts = contactService.getAllContacts(userId);
        return contacts;
    }
    
    @GET
    @Path ("{UserId}/contacts/{ContactId}")
    public User getContact(@PathParam ("UserId") String userId, @PathParam ("ContactId") String contactId)
    {
        User contact = contactService.getContact(userId, contactId);
        return contact;
    }
    
    @POST
    @Path ("{UserId}/contacts/{ContactId}/sendMessage")
    public Notification sendMessage(@PathParam ("UserId") String userId, @PathParam ("ContactId") String contactId, Notification newMessage)
    {
        contactService.sendMessage(userId, contactId, newMessage);
        return newMessage;
    }
    
    @DELETE
    @Path ("{UserId}/contacts/{ContactId}")
    public void deleteContact(@PathParam ("UserId") String userId, @PathParam ("ContactId") String contactId)
    {
        contactService.deleteContact(userId, contactId);
    }
}
