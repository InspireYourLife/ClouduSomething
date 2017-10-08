package groep3.cloudapi.service;

import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.ContactDAO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes (MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public class ContactService extends BaseService{
    
    private final ContactDAO contactDAO;
    
    public ContactService (ContactDAO contactDAO){
        
        this.contactDAO = contactDAO;
    }

    public List<User> getAllContacts(String userId) {
        
        User user = contactDAO.get(userId);
        
        contactDAO.getAllContacts(user);
        
    }
}
