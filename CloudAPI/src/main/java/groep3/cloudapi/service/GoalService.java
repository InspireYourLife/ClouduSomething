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
        Goal goal = goalDAO.get(goalId);
        return goal;
    }
    
    public List<Goal> GetAll()
    {
        List<Goal> goals = goalDAO.getAll();
        return goals;
    }
    
    public List<Goal> GetAll(List<Module> modules)
    {
        List<Goal> goals = goalDAO.getAll(modules);
        return goals;
    }
    
    public List<Goal> GetAll(Module module)
    {
        List<Goal> goals = goalDAO.getAll(module);
        return goals;
    }

    public void create(Goal newGoal)
    {
        Date currentTime = Date.from(Instant.now());
        newGoal.setCreationDate(currentTime);
        
        goalDAO.create(newGoal);
    }
}
