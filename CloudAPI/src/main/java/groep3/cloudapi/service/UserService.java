package groep3.cloudapi.service;

import groep3.cloudapi.model.Calendar;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.CalendarDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;

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

    public List<User> GetAll(String role, String username)
    {
        List<User> users = new ArrayList<User>();
        
        if(role != null && username == null)
        {
            users = userDAO.getAllByRole(role);
        
            if (users.isEmpty() == true)
            {
                throw new NotFoundException("There are no users in the database");
            }
        }
        
        else if (role == null && username != null)
        {
            users = userDAO.getAllByUserName(username);
        
            if (users.isEmpty() == true)
            {
                throw new NotFoundException("There are no users in the database");
            }
        }
        
        else if (role != null && username != null)
        {
            users = userDAO.getAllByRoleAndUserName(role, username);
        
            if (users.isEmpty() == true)
            {
                throw new NotFoundException("There are no users in the database");
            }
        }
        
        else
        {
            users = userDAO.getAll();
        
            if (users.isEmpty() == true)
            {
                throw new NotFoundException("There are no users in the database");
            }
        }
        
        return users;
    }

        public User getUserById(String id)
    {
        User u = userDAO.get(id);
        
        requireResult(u, "User not found");
        
        return u;
    }
        
    public int getPoints(String id)
    {
        User u = userDAO.get(id);
        
        requireResult(u, "User not found");
        
        return u.getCollectedPoints();
    }
            
    public Boolean create(User newUser)
    {
        List<User> usersPreCreate = userDAO.getAll();
        List<Calendar> calendarsPreCreate = calendarDAO.getAll();
        
        Date currentTime = Date.from(Instant.now());
        newUser.setCreationDate(currentTime);
        
        calendarDAO.create(newUser.getCalendar());
        
        userDAO.create(newUser);
        
        List<User> usersPostCreate = userDAO.getAll();
        List<Calendar> calendarsPostCreate = calendarDAO.getAll();
        
        if(usersPreCreate.size() < usersPostCreate.size())
        {
            if(calendarsPreCreate.size() < calendarsPostCreate.size())
            {
                return true;
            }
            
            else
            {
                throw new ProcessingException("Calendar has not been added");
            }
            
        }
        else
        {
            throw new ProcessingException("User has not been added");
        }
    }

    public Boolean editUser(User editedUser, String id)
    {
        User originalUser = userDAO.get(id);
        
        requireResult(originalUser, "User not found");
        
        userDAO.update(editedUser);
        User newUser = userDAO.get(id);
        
        if (originalUser == newUser)
        {
            return false;
        }
        
        else
        {
            throw new ProcessingException("User has not been edited");
        }
    }

    public Boolean addPoints(int value, String id)
    {
        User originalUser = userDAO.get(id);
        
        requireResult(originalUser, "User not found");
        
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
            throw new ProcessingException("Points have not been edited");
        }
         
    }

    public Boolean deleteUserById(String id)
    {
        User u = userDAO.get(id);
        userDAO.delete(u);
        
        Calendar c = u.getCalendar();
        
        calendarDAO.delete(c);
        
        if (userDAO.get(id) == null)
        {
            return true;
        }
        else
        {
            throw new ProcessingException("User has not been deleted");
        }
    }
}
