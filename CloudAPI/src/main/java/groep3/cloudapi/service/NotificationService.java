package groep3.cloudapi.service;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.persistence.NotificationDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

public class NotificationService extends BaseService
{
    private final NotificationDAO notificationDAO;

    @Inject
    public NotificationService(NotificationDAO notificationDAO, UserDAO userDAO)
    {
        this.notificationDAO = notificationDAO;
    }

    public List<Notification> getReceivedNotifications(String id)
    {
        List<Notification> notifications = notificationDAO.getAllByReceiverId(id);
        
        if (notifications.isEmpty())
        {
            throw new NotFoundException("No notifications were found");
        }
        
        return notifications;
    }
    
    public List<Notification> getSentNotifications(String id)
    {
        List<Notification> notifications = notificationDAO.getAllBySenderId(id);
        
        if (notifications.isEmpty())
        {
            throw new NotFoundException("No notifications were found");
        }
        
        return notifications;
    }

    public Boolean deleteReceivedNotification(String uid, String nid)
    {
        notificationDAO.deleteReceivedNotification(uid, nid);
        
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
