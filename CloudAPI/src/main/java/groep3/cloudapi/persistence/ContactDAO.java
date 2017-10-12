
package groep3.cloudapi.persistence;

import groep3.cloudapi.model.User;
import java.util.List;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;

public class ContactDAO extends BaseDAO<User>{
    
    @Inject
    public ContactDAO(Datastore ds)
    {
        super(User.class, ds);
    }
}
