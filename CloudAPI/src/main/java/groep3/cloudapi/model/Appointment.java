package groep3.cloudapi.model;

import java.util.Date;
import java.util.List;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity (value = "appointments")
public class Appointment extends EntityModel 
{
    @Reference
    List<User> participants;
    
    Date date;
    String description;

    public List<User> getParticipants()
    {
        return participants;
    }

    public void setParticipants(List<User> participants)
    {
        this.participants = participants;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
    
}
