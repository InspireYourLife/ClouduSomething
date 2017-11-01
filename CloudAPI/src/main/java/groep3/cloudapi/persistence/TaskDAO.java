package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Task;
import groep3.cloudapi.model.User;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

@Path ("/users")
@Consumes (MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public class TaskDAO extends BaseDAO<Task>{
    
    @Inject
    public TaskDAO(Datastore ds)
    {
        super(Task.class, ds);
    }
    
    public Task getTaskByName(String name)
    {
        Query<Task> query = createQuery().field("name").equal(name);
        return findOne(query);
    }
    
    public List<Task> getTaskByOwner(User owner)
    {
        Query<Task> query = createQuery().field("owner").equal(owner);
        return find(query).asList();
    }
}
