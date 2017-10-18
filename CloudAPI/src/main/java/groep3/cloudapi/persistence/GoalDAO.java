package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Goal;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;

public class GoalDAO extends BaseDAO<Goal>
{       
    @Inject
    public GoalDAO(Datastore ds)
    {
        super(Goal.class, ds);
    }
}
