package groep3.cloudapi.presentation;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.presentation.model.NotificationView;
import java.util.ArrayList;
import java.util.List;

public class NotificationPresenter extends BasePresenter
{

    public List<NotificationView> present(List<Notification> notifications)
    {
        int listCount = notifications.size();
        int i = 0;
        
        List<NotificationView> safeData = new ArrayList<>();
        
         while (i < listCount)
        {
            Notification n = notifications.get(i);
            NotificationView nv = new NotificationView();
            
            nv.body = n.getBody();
            nv.subject = n.getSubject();
            nv.recipient = n.getRecipient();
            nv.sender = n.getSender();
            
            safeData.add(nv);
            i++;
        }
        
        return safeData;
    }
    
}
