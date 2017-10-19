package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Appointment;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;

public class AppointmentDAO extends BaseDAO<Appointment>{
    
    @Inject
    public AppointmentDAO(Datastore ds)
    {
        super(Appointment.class, ds);
    }
}
