package groep3.cloudapi.service;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.NotificationDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

public class NotificationService extends BaseService
{
    private final NotificationDAO notificationDAO;
    private final UserDAO userDAO;

    @Inject
    public NotificationService(NotificationDAO notificationDAO, UserDAO userDAO)
    {
        this.notificationDAO = notificationDAO;
        this.userDAO = userDAO;
    }

    public List<Notification> getReceivedNotifications(String id)
    {
        User u = userDAO.get(id);
        
        List<Notification> notifications = notificationDAO.getAllByReceiver(u);
        
        if (notifications.isEmpty())
        {
            throw new NotFoundException("No notifications were found");
        }
        
        return notifications;
    }
    
    public List<Notification> getSentNotifications(String id)
    {
        User u = userDAO.get(id);
        
        List<Notification> notifications = notificationDAO.getAllBySender(u);
        
        if (notifications.isEmpty())
        {
            throw new NotFoundException("No notifications were found");
        }
        
        return notifications;
    }

    public Boolean deleteReceivedNotification(String uid, String nid)
    {
        User u = userDAO.get(uid);
        Notification n = notificationDAO.get(nid);
        
        notificationDAO.deleteReceivedNotification(u, n.getId());
        
        if (notificationDAO.get(nid) == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }  
    
}
