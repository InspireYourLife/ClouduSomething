package groep3.cloudapi.service;

import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.UserDAO;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/users" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class UserService extends BaseService
{
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    public List<User> GetAll()
    {
        return userDAO.getAll();
    }

        public User getUserById(String id)
    {
        return userDAO.get(id);
    }
    
    public void create(User newUser)
    {
        Date currentTime = Date.from(Instant.now());
        newUser.setCreationDate(currentTime);
        
        userDAO.create(newUser);
    }
    
}
