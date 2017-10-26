package groep3.cloudapi.service;

import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.CalendarDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class UserService extends BaseService
{
    private final CalendarDAO calendarDAO;
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO, CalendarDAO calendarDAO)
    {
        this.userDAO = userDAO;
        this.calendarDAO = calendarDAO;
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
        
        calendarDAO.create(newUser.getCalendar());
        
        userDAO.create(newUser);
    }

    public Boolean editUser(User editedUser, String id)
    {
        User originalUser = userDAO.get(id);
        userDAO.update(editedUser);
        User newUser = userDAO.get(id);
        
        if (originalUser == newUser)
        {
            return false;
        }
        
        else
        {
            return true;
        }
    }

    public Boolean addPoints(int value, String id)
    {
        User originalUser = userDAO.get(id);
        int newPoints = originalUser.getCollectedPoints() + value;
        
        User editedUser = originalUser;
        editedUser.setCollectedPoints(newPoints);
         
        userDAO.update(editedUser);
        int editedPoints = userDAO.get(id).getCollectedPoints();
        
        if (originalUser.getCollectedPoints() == editedPoints)
        {
            return false;
        }
        
        else
        {
            return true;
        }
         
    }

    public Boolean deleteUserById(String id)
    {
        User u = userDAO.get(id);
        userDAO.deleteById(u.getId());
        
        if (userDAO.get(id) == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
