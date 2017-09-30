package groep3.cloudapi.model;

import java.util.Date;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity (value = "notifications")
public class Notification extends EntityModel 
{
    String subject;
    String body;
    
    @Reference
    User sender;
    
    @Reference
    User recipient;
    
    Date creationDate;

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public User getSender()
    {
        return sender;
    }

    public void setSender(User sender)
    {
        this.sender = sender;
    }

    public User getRecipient()
    {
        return recipient;
    }

    public void setRecipient(User recipient)
    {
        this.recipient = recipient;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    
}
