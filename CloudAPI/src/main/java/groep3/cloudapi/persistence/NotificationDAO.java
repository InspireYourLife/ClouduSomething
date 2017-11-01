package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.User;
import java.util.List;
import javax.inject.Inject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

public class NotificationDAO extends BaseDAO<Notification>
{       
    @Inject
    public NotificationDAO(Datastore ds)
    {
        super(Notification.class, ds);
    }

    public List<Notification> getAllByReceiver(User u)
    {
        Query<Notification> query = createQuery().field("recipient").equal(u);
        return find(query).asList();
    }
    
    public List<Notification> getAllBySender(User u)
    {
        Query<Notification> query = createQuery().field("sender").equal(u);
        return find(query).asList();
    }

    public void deleteReceivedNotification(User u, ObjectId nid)
    {
        Query<Notification> query = createQuery();
        query.and(query.criteria("_id").equal(nid), query.criteria("recipient").equal(u));
        Notification n = find(query).get();
        delete(n);
    }
}
