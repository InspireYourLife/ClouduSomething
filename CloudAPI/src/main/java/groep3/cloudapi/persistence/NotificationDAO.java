package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Notification;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

@Path( "/users" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class NotificationDAO extends BaseDAO<Notification>
{       
    @Inject
    public NotificationDAO(Datastore ds)
    {
        super(Notification.class, ds);
    }

    public List<Notification> getAllByOwnerId(String id)
    {
        Query<Notification> query = createQuery().field("recipient").equal(id);
        return find(query).asList();
    }
}
