package groep3.cloudapi.service;

import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.NotificationDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;

public class ContactService extends BaseService{
    
    private final UserDAO userDAO;
    private final NotificationDAO notificationDAO;
    
    @Inject
    public ContactService (UserDAO userDAO, NotificationDAO notificationDAO){
        this.userDAO = userDAO;
        this.notificationDAO = notificationDAO;
    }

    public List<User> getAllContacts(String userId) {
        
        User user = userDAO.get(userId);
        requireResult(user, "Üser not found");
        
        List<User> contactsToReturn = user.getContacts();
        requireResult(contactsToReturn, "Empty list");
        
        return contactsToReturn;
    }

    public User getContact(String userId, String contactId) {
        
        int cId = Integer.parseInt(contactId);
        
        User user = userDAO.get(userId);
        requireResult(user, "User not found");
        
        List<User> contactsToReturn = user.getContacts();
        requireResult(contactsToReturn, "Empty list");
        
        User contact = contactsToReturn.get(cId);
        
        return contact;
    }
    
    //Verplaatsen naar NotificationService
    public void sendMessage(String userId, String contactId, Notification newMessage) {
        
        Date currentTime = Date.from(Instant.now());
        newMessage.setCreationDate(currentTime);
        
        User user = userDAO.get(userId);
        requireResult(user, "User not found");
        
        User contact = userDAO.get(contactId);
        requireResult(contact, "Contact not Found");
        
        newMessage.setSender(user);
        newMessage.setRecipient(contact);
        
        if(newMessage.getId() == null){
            throw new BadRequestException();
        }
        
        notificationDAO.create(newMessage);
        
    }

    public Boolean deleteContact(String userId, String contactId) {
        
        User user = userDAO.get(userId);
        requireResult(user, "User not found");
        
        User contact = userDAO.get(contactId);
        requireResult(contact, "Contact not found");
        
        List<User> contacts = user.getContacts();
        
        if (contacts.isEmpty())
        {
            throw new NotFoundException("No notifications were found");
        }
        
        if(contact.getId() == null){
            throw new BadRequestException("Contact does not exsist");
        }
        
        contacts.remove(contact);
        userDAO.update(user);
        
        if(userDAO.get(contactId) == null){
            return true;
        } else {
            throw new ProcessingException("Contact was not removed");
        }
    }
}
