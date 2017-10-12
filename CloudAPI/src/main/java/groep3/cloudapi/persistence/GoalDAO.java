package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Module;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.mongodb.morphia.Datastore;

@Path( "/Goals" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class GoalDAO extends BaseDAO<Goal>
{       
    @Inject
    public GoalDAO(Datastore ds)
    {
        super(Goal.class, ds);
    }
    
    public List<Goal> getAll(List<Module> modules)
    {
        List<Goal> allGoals = new ArrayList<Goal>();
        
        // Haal alle goals op uit de lijst van meegegeven modules, en voeg ze toe aan de 'allGoals' list
        
        return allGoals;
    }
    
    public List<Goal> getAll(Module module)
    {
        List<Goal> allGoals = new ArrayList<Goal>();
        
        // Haal alle goals op uit de lijst van meegegeven modules, en voeg ze toe aan de 'allGoals' list
        
        return allGoals;
    }
    
    public Goal get(String goalId)
    {
        // Haal alle goals op uit de lijst van meegegeven modules, en voeg ze toe aan de 'allGoals' list
        
        return null;
    }
}
