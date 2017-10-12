package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Notification;
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
    
    public User userById(String userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sendMessage(User contact, Notification newMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
