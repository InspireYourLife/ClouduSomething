package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Notification;
import java.util.List;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

public class NotificationDAO extends BaseDAO<Notification>
{       
    @Inject
    public NotificationDAO(Datastore ds)
    {
        super(Notification.class, ds);
    }

    public List<Notification> getAllByReceiverId(String id)
    {
        Query<Notification> query = createQuery().field("recipient").equal(id);
        return find(query).asList();
    }
    
    public List<Notification> getAllBySenderId(String id)
    {
        Query<Notification> query = createQuery().field("sender").equal(id);
        return find(query).asList();
    }

    public void deleteReceivedNotification(String uid, String nid)
    {
        Query<Notification> query = createQuery();
        query.and(query.criteria("_id").equal(nid), query.criteria("recipient").equal(uid));
    }
}
