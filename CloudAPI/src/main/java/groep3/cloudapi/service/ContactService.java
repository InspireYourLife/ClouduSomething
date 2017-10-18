package groep3.cloudapi.service;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.ContactDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bson.types.ObjectId;

@Consumes (MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public class ContactService extends BaseService{
    
    private final ContactDAO contactDAO;
    private final UserDAO userDAO;
    
    public ContactService (ContactDAO contactDAO, UserDAO userDAO){
        
        this.contactDAO = contactDAO;
        this.userDAO = userDAO;
    }

    public List<User> getAllContacts(String userId) {
        
        User user = userDAO.get(userId);
        List<User> contactsToReturn = user.getContacts();
        
        return contactsToReturn;
    }

    public User getContact(String userId, String contactId) {
        int cId = Integer.parseInt(contactId);
        
        User user = userDAO.get(userId);
        List<User> contactsToReturn = user.getContacts();
        
        User contact = contactsToReturn.get(cId);
        
        return contact;
    }

    public void sendMessage(String userId, int contactId, Notification newMessage) {
        
        User user = userDAO.get(userId);
        List<User> contactsToReturn = user.getContacts();
        
        User contact = contactsToReturn.get(contactId);
        
        userDAO.sendMessage(contact, newMessage);
    }
}
