package groep3.cloudapi.service;

import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.UserDAO;
import java.util.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;


public class AuthenticationService implements Authenticator<BasicCredentials, User>
{
    private final UserDAO userDAO;
    
    @Inject
    public AuthenticationService(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }
    
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException
    {       
        User userFromDB = userDAO.getbyName(credentials.getUsername());
        
        if (userFromDB == null)
        {
            throw new NotFoundException("User does not exist in DB");
        }
        
        if (!credentials.getUsername().equals(userFromDB.getName()))
        {
            return Optional.empty();
        }
        
        if (credentials.getPassword().equals(userFromDB.getPassword()))
        {
            return Optional.of(userFromDB);                   
        }
        
        return Optional.empty();
    }
}
