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
    
    public Goal getGoal(String goalId)
    {
        return goalDAO.get(goalId);
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
    
    public List<Goal> getGoalsFromModule(String moduleId)
    {
        List<Goal> goalsFormModule = moduleDAO.get(moduleId).getGoals();
        return goalsFormModule;
    }
    
    
    

    
    
    
    public void create(Goal newGoal)
    {
        goalDAO.save(newGoal);
    }
}
