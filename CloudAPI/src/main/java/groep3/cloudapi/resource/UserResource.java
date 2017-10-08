package groep3.cloudapi.resource;

import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.model.UserPresenter;
import groep3.cloudapi.service.ContactService;
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
    //private final UserPresenter userPresenter;
    
    @Inject
    public UserResource (UserService userService, UserPresenter userPresenter, ContactService contactService)
    {
        this.userService = userService;
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
    @Path ( "/{userId}" )
    public List <User> getAllContacts(@PathParam ("userId") String userId)
    {
        List<User> contacts = contactService.getAllContacts(userId);
        return contacts;
    }
    
    @POST
    public User create(@Valid User newUser)
    {
        userService.create(newUser);
        return newUser;
    }
}
