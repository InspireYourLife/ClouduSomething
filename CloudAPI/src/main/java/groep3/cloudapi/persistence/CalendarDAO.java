package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Calendar;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;

public class CalendarDAO extends BaseDAO<Calendar>{
    
    @Inject
    public CalendarDAO(Datastore ds)
    {
        super(Calendar.class, ds);
    }
}
