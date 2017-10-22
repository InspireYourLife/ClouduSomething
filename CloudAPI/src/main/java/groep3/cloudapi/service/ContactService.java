package groep3.cloudapi.service;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.UserDAO;
import java.util.List;

public class ContactService extends BaseService{
    
    private final UserDAO userDAO;
    
    public ContactService (UserDAO userDAO){
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
    
    //Verplaatsen naar NotificationService
    public void sendMessage(String userId, String contactId, Notification newMessage) {
        int cId = Integer.parseInt(contactId);
        User user = userDAO.get(userId);
        List<User> contactsToReturn = user.getContacts();
        
        User contact = contactsToReturn.get(cId);
        
        userDAO.sendMessage(contact, newMessage);
    }

    public void deleteContact(String userId, String contactId) {
        
        int cId = Integer.parseInt(contactId);
        User user = userDAO.get(userId);
        List<User> contacts = user.getContacts();
        
        contacts.remove(cId);
    }
}
