package groep3.cloudapi.service;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Module;
import groep3.cloudapi.persistence.GoalDAO;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/Goals" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class GoalService extends BaseService
{
    private final GoalDAO goalDAO;

    @Inject
    public GoalService(GoalDAO goalDAO)
    {
        this.goalDAO = goalDAO;
    }

    public Goal GetGoal(String goalId)
    {
        return goalDAO.get(goalId);
    }
    
    public List<Goal> GetAll()
    {
        return goalDAO.getAll();
    }
    
    public List<Goal> GetAll(String moduleId)
    {
        // Moet nog toegevoegd worden in de baseDAO/goalDAO
        //return goalDAO.getAll(moduleId);
        return null;
    }
    
    public List<Goal> GetAll(List<Module> modules)
    {
        // Moet nog toegevoegd worden in de baseDAO/goalDAO
        return goalDAO.getAll(modules);
    }

    public void create(Goal newGoal)
    {
        goalDAO.save(newGoal);
    }
}
