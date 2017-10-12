package groep3.cloudapi.persistence;

import groep3.cloudapi.model.User;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;

public class UserDAO extends BaseDAO<User>
{       
    @Inject
    public UserDAO(Datastore ds)
    {
        super(User.class, ds);
    }
}
