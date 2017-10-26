package groep3.cloudapi.resource;

import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.UserPresenter;
import groep3.cloudapi.presentation.model.UserView;
import groep3.cloudapi.service.UserService;
import io.dropwizard.auth.Auth;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    private final UserPresenter userPresenter;
     
    @Inject

    public UserResource (UserService userService, UserPresenter userPresenter)
    {
        this.userService = userService;
        this.userPresenter = userPresenter;
    }
    
    //Get Calls - User
    @GET
    public List <UserView> getAll(@Auth User authenticatedUser)
    {
        List<User> users = userService.GetAll();
        List<UserView> usersToReturn = userPresenter.present(users);
        return usersToReturn;
    }
    
    @GET
    @Path( "/{userId}" )
    public UserView getUserById(@PathParam( "userId") String id, @Auth User authenticatedUser)
    {
        User user = userService.getUserById(id);
        UserView userToReturn = userPresenter.present(user);
        return userToReturn;
    }
    
    @GET
    @Path( "/{userId}/points")
    public int getPoints(@PathParam( "userId") String id, @Auth User authenticatedUser)
    {
        int points = userService.getPoints(id);
        return points;
    }
    
    //Post Calls - User
    @POST
    public User create(@Valid User newUser)
    {
        userService.create(newUser);
        return newUser;
    }
    
    //Put Calls - User
    @PUT
    @Path( "/{userId}")
    public Boolean editUser(@Valid User editedUser, @PathParam( "userId") String id, @Auth User authenticatedUser)
    {
        Boolean success = userService.editUser(editedUser, id);
        return success;
    }
    
    @PUT
    @Path( "/{userId}/points" )
    public Boolean addPoints(int value, @PathParam( "userId") String id, @Auth User authenticatedUser)
    {
        Boolean success = userService.addPoints(value, id);
        return success;
    }
    
    //Delete Calls - User
    @DELETE
    @Path( "/{userId}" )
    public Boolean deleteUserById(@PathParam( "id") String id, @Auth User authenticatedUser)
    {
        Boolean success = userService.deleteUserById(id);
        return success;
    }
}
