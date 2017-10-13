package groep3.cloudapi.service;

import groep3.cloudapi.model.Calendar;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.CalendarDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bson.types.ObjectId;

@Path( "/users" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class UserService extends BaseService
{
    private final UserDAO userDAO;
    private final CalendarDAO calendarDAO;

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
    
    public void create(User newUser)
    {
        Date currentTime = Date.from(Instant.now());
        newUser.setCreationDate(currentTime);
        
        userDAO.create(newUser);
    }

    public int getPoints(String id)
    {
        User u = userDAO.get(id);
        return u.getCollectedPoints();
    }

    public Calendar getCalendar(String id)
    {
        User u = userDAO.get(id);
        
        ObjectId calendarId = u.getCalendar().getId();
        Calendar c = calendarDAO.get(calendarId);
        
        return c;
    }
}
