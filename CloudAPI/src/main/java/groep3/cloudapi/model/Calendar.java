package groep3.cloudapi.model;

import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity (value = "calendars")
public class Calendar extends EntityModel 
{
    
    @Reference
    private List<Goal> deadlines = new ArrayList<Goal>();
    
    @Reference
    private List<Appointment> appointments = new ArrayList<Appointment>();

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
