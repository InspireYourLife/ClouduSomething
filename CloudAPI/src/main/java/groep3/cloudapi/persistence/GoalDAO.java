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
    
//    public List<Goal> getAll(List<Module> modules)
//    {
//        List<Goal> allGoals = new ArrayList<>();
//        
//        // Haal alle goals op uit de lijst van meegegeven modules, en voeg ze toe aan de 'allGoals' list
//        
//        return allGoals;
//    }
//    
//    public List<Goal> getAll(Module module)
//    {
//        List<Goal> allGoals = new ArrayList<>();
//        
//        // Haal alle goals op uit de lijst van meegegeven modules, en voeg ze toe aan de 'allGoals' list
//        
//        return allGoals;
//    }
    
//    public Goal get(String goalId)
//    {
//        // Haal alle goals op uit de lijst van meegegeven modules, en voeg ze toe aan de 'allGoals' list
//        
//        return null;
//    }
}
