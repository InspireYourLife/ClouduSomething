package groep3.cloudapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity (value = "appointments")
public class Appointment extends EntityModel 
{
    @Reference
    @NotEmpty
    private List<User> participants = new ArrayList<User>();
    
    @Embedded
    @NotEmpty
    private Date date;
    
    @Embedded
    @NotEmpty
    private String description;
    
    @Embedded
    @NotEmpty
    private String location;
    
    @Embedded
    private String comments;
    
    @Embedded
    @NotEmpty
    private Date creationDate;

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
    
     public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
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
