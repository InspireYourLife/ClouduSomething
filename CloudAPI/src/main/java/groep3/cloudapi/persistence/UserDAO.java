package groep3.cloudapi.persistence;

import groep3.cloudapi.model.User;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.mongodb.morphia.Datastore;

@Path( "/users" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class UserDAO extends BaseDAO<User>
{       
    @Inject
    public UserDAO(Datastore ds)
    {
        super(User.class, ds);
    }
}
