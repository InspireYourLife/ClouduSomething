package groep3.cloudapi.service;

import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.UserDAO;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.bson.types.ObjectId;

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
        
    public int getPoints(String id)
    {
        User u = userDAO.get(id);
        return u.getCollectedPoints();
    }
            
    public void create(User newUser)
    {
        Date currentTime = Date.from(Instant.now());
        newUser.setCreationDate(currentTime);
        
        userDAO.create(newUser);
    }
}
