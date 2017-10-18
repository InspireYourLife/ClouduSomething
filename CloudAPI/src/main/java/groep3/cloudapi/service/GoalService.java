package groep3.cloudapi.service;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Module;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.GoalDAO;
import groep3.cloudapi.persistence.ModuleDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/goals" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class GoalService extends BaseService
{
    private final GoalDAO goalDAO;
    private final UserDAO userDAO;
    private final ModuleDAO moduleDAO;
    

    @Inject
    public GoalService(GoalDAO goalDAO, UserDAO userDAO, ModuleDAO moduleDAO)
    {
        this.goalDAO = goalDAO;
        this.userDAO = userDAO;
        this.moduleDAO = moduleDAO;
    }
    
    public List<Goal> getAll()
    {
        return goalDAO.getAll();
    }
    
    public List<Goal> getAllGoalsFromUser(String userId)
    {
        List<Goal> allGoalsFromUser = new ArrayList<Goal>();
        User tempUser = userDAO.get(userId);
        
        // Get all modules from a user
        List<Module> allModulesFromUser = tempUser.getModules();
        
        // Get all goals from every found module
        for (Module module : allModulesFromUser) 
        {
            List<Goal> allGoalsFromModule = module.getGoals();
            
            // Add goal into the 'big' List
            for (Goal goal : allGoalsFromModule) 
            {
                allGoalsFromUser.add(goal);
            }
        }
        
        return allGoalsFromUser;
    }
    
    
    

    public Goal GetGoal(String goalId)
    {
        return goalDAO.get(goalId);
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
        return null;
    }

    public void create(Goal newGoal)
    {
        goalDAO.save(newGoal);
    }
}
