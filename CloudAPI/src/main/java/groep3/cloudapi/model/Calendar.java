package groep3.cloudapi.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity (value = "calendar")
public class Calendar extends EntityModel 
{
    @Reference
    @NotEmpty
    User owner;
    
    @Reference
    List<Goal> deadlines = new ArrayList<Goal>();
    
    @Reference
    List<Appointment> appointments = new ArrayList<Appointment>();

    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User owner)
    {
        this.owner = owner;
    }

    public List<Goal> getDeadlines()
    {
        return deadlines;
    }

    public void setDeadlines(List<Goal> deadlines)
    {
        this.deadlines = deadlines;
    }

    public List<Appointment> getAppointments()
    {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments)
    {
        this.appointments = appointments;
    }
    
    
}
