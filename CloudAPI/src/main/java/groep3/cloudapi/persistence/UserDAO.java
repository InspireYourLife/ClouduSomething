package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.User;
import java.util.List;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

public class UserDAO extends BaseDAO<User>
{       
    @Inject
    public UserDAO(Datastore ds)
    {
        super(User.class, ds);
    }
    
    public User getbyName (String username)
    {
        Query<User> query = createQuery().field("name").equal(username);
        
        return findOne(query);
    }

    public List<User> getAllByRole(String role)
    {
        Query<User> query = createQuery().field("role").equal(role);
        
        return find(query).asList();
    }

    public List<User> getAllByUserName(String username)
    {
        Query<User> query = createQuery().field("name").equal(username);
        
        return find(query).asList();
    }

    public List<User> getAllByRoleAndUserName(String role, String username)
    {
        Query<User> query = createQuery();
        query.and(query.criteria("name").equal(username), query.criteria("role").equal(role));
        
        return find(query).asList();
    }
}
