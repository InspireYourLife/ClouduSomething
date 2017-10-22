package groep3.cloudapi.service;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Module;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.GoalDAO;
import groep3.cloudapi.persistence.ModuleDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
        Goal tempGoal = goalDAO.get(goalId);
        return tempGoal;
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
    
    //Assign goal to module
    public Boolean assignGoalToModule(String moduleId, String goalId)
    {
        boolean hasSucceeded = false;
        
        List<Goal> oldGoalList = moduleDAO.get(moduleId).getGoals();
        
        Module module = moduleDAO.get(moduleId);
        Goal goal = goalDAO.get(goalId);
        
        List<Goal> goalsFromModule = module.getGoals();
        goalsFromModule.add(goal);
        module.setGoals(goalsFromModule);
        
        moduleDAO.update(module);
        
        List<Goal> newGoalList = moduleDAO.get(moduleId).getGoals();
        
        if (oldGoalList != newGoalList) 
        {
            hasSucceeded = true;
        }
        
        return hasSucceeded;
    }
    
    
    public Boolean switchApproveBool(String goalId)
    {
        boolean itemHasChanged = false;
        Boolean originalBoolean;
        
        Goal goal = goalDAO.get(goalId);
        originalBoolean = goal.getIsApproved();
        
        boolean isApproved = goal.getIsApproved();
        goal.setIsApproved(!isApproved);
        
        goalDAO.update(goal);
        
        boolean boolCheck = goalDAO.get(goalId).getIsApproved();
        
        if (originalBoolean != boolCheck)
        {
            itemHasChanged = true;
        }
        
        return itemHasChanged;
    }
    
    public Boolean switchCompleteBool(String goalId)
    {
        boolean itemHasChanged = false;
        Boolean originalBoolean;
        
        Goal goal = goalDAO.get(goalId);
        originalBoolean = goal.getIsCompleted();
        
        boolean isCompleted = goal.getIsCompleted();
        goal.setIsCompleted(!isCompleted);
        
        goalDAO.update(goal);
        
        boolean boolCheck = goalDAO.get(goalId).getIsCompleted();
        
        if (originalBoolean != boolCheck)
        {
            itemHasChanged = true;
        }
        
        return itemHasChanged;
    }
    
    public Boolean removeGoal(String goalId)
    {
        boolean hasSucceeded = false;
        
        Goal goal = goalDAO.get(goalId);
        goalDAO.delete(goal);
        
        Goal goalAfterDeletion = goalDAO.get(goalId);
        
        if (goalAfterDeletion == null) 
        {
            hasSucceeded = true;
        }
        
        return hasSucceeded;
    }
    
    public void create(Goal newGoal)
    {
        Date currentTime = Date.from(Instant.now());
        newGoal.setCreationDate(currentTime);
        
        goalDAO.create(newGoal);
    }
}
