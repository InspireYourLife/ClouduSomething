package groep3.cloudapi.resource;

import groep3.cloudapi.service.ContactService;
import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.Role;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.model.ContactPresenter;
import groep3.cloudapi.presentation.model.ContactView;
import groep3.cloudapi.service.UserService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
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
    private final ContactPresenter contactPresenter;
     
    @Inject
    public UserContactResource (UserService userService, ContactService contactService, ContactPresenter contactPresenter)
    {
        this.userService = userService;
        this.contactService = contactService;
        this.contactPresenter = contactPresenter;
    }
    
    //Get Calls - Contacts
    @GET
    @Path ("{userId}/contacts")
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CLIENT})
    public List<ContactView> getAllContacts(@PathParam ("userId") String userId)
    {
        List<User> contacts = contactService.getAllContacts(userId);
        return contactPresenter.presentAllContacts(contacts);
    }
    
    @GET
    @Path ("{userId}/contacts/{contactId}")
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    public ContactView getContact(@PathParam ("userId") String userId, @PathParam ("contactId") String contactId)
    {
        User contact = contactService.getContact(userId, contactId);
        return contactPresenter.presentContact(contact);
    }
    
    @POST
    @Path ("{userId}/contacts/{contactId}/sendMessage")
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CLIENT, Role.Labels.CARETAKER})
    public Notification sendMessage(@PathParam ("userId") String userId, @PathParam ("contactId") String contactId, @Valid Notification newMessage)
    {
        contactService.sendMessage(userId, contactId, newMessage);
        return newMessage;
    }
    
    @DELETE
    @Path ("{userId}/contacts/{contactId}")
    @RolesAllowed(Role.Labels.ADMIN)
    public void deleteContact(@PathParam ("userId") String userId, @PathParam ("contactId") String contactId)
    {
        contactService.deleteContact(userId, contactId);
    }
}
