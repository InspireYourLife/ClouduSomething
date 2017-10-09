package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Task;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.mongodb.morphia.Datastore;

@Path ("/users")
@Consumes (MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public class TaskDAO extends BaseDAO<Task>{
    
    @Inject
    public TaskDAO(Datastore ds)
    {
        super(Task.class, ds);
    }
}
