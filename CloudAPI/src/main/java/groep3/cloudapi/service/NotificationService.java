package groep3.cloudapi.service;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.persistence.NotificationDAO;
import java.util.List;
import javax.inject.Inject;

public class NotificationService extends BaseService
{
    private final NotificationDAO notificationDAO;

    @Inject
    public NotificationService(NotificationDAO notificationDAO)
    {
        this.notificationDAO = notificationDAO;
    }

    public List<Notification> getNotifications(String id)
    {
        return notificationDAO.getAllByOwnerId(id);
    }

    public Boolean deleteSpecificNotification(String uid, String nid)
    {
        Notification n = notificationDAO.get(nid);
        notificationDAO.deleteById(n.getId());
        
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
