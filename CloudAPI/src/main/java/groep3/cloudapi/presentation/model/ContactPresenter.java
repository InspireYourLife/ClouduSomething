package groep3.cloudapi.presentation.model;

import groep3.cloudapi.model.User;
import java.util.ArrayList;
import java.util.List;


public class ContactPresenter {

    public ContactView presentContact(User contact) {
        ContactView cView = new ContactView();
        
        cView.name = contact.getName();
        cView.email = contact.getEmail();
        cView.telephone = contact.getTelephone();
        
        return cView;
    }

    public List<ContactView> presentAllContacts(List<User> contacts) {
        int counter = contacts.size();
        int i = 0;
        
        List<ContactView> contactData = new ArrayList<>();
        
        while (i < counter){
            
            User c = contacts.get(i);
            ContactView cView = new ContactView();
            cView.name = c.getName();
            cView.email = c.getEmail();
            cView.telephone = c.getTelephone();
            
            contactData.add(cView);
            i++;
        }
        
        return contactData;
    }
    
}
