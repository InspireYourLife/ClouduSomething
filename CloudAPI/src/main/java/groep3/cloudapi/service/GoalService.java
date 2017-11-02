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
import javax.ws.rs.ProcessingException;

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
        requireResult(tempGoal, "Goal could not be found");
        
        return tempGoal;
    }
    
    public List<Goal> getAll()
    {
        List<Goal> allGoals = goalDAO.getAll();
        requireResult(allGoals, "Goals could not be found");
        
        return allGoals;
    }

    public List<Goal> getAllGoalsFromUser(User authenticatedUser, String userId)
    {        
        List<Goal> allGoalsFromUser = new ArrayList<Goal>();
        
        User tempUser = userDAO.get(userId);
        requireResult(tempUser, "User could not be found");

        List<Module> allModulesFromUser = tempUser.getModules();
        requireResult(allModulesFromUser, "Modules could not be found");

        for (Module module : allModulesFromUser) 
        {
            List<Goal> allGoalsFromModule = module.getGoals();
            requireResult(allGoalsFromModule, "Goals could not be found");

            for (Goal goal : allGoalsFromModule) 
            {
                allGoalsFromUser.add(goal);
            }
        }

        return allGoalsFromUser;
    }
    
    public List<Goal> getGoalsFromModule(String moduleId)
    {
        List<Goal> goalsFromModule = moduleDAO.get(moduleId).getGoals();
        requireResult(goalsFromModule, "Goals could not be found");
        
        return goalsFromModule;
    }
    
    public Boolean assignGoalToModule(String moduleId, String goalId)
    {
        boolean hasSucceeded = false;
        
        List<Goal> oldGoalList = moduleDAO.get(moduleId).getGoals();
        
        Module module = moduleDAO.get(moduleId);
        requireResult(module, "Module could not be found");
        
        Goal goal = goalDAO.get(goalId);
        requireResult(goal, "Goal could not be found");
        
        List<Goal> goalsFromModule = module.getGoals();
        requireResult(goalsFromModule, "Goals could not be found");
        
        goalsFromModule.add(goal);
        module.setGoals(goalsFromModule);
        
        moduleDAO.update(module);
        
        List<Goal> newGoalList = moduleDAO.get(moduleId).getGoals();
        
        if (oldGoalList != newGoalList) 
        {
            hasSucceeded = true;
        }
        throw new ProcessingException("Could not assign goal to module");
    }
    
    public Boolean switchApproveBool(String goalId)
    {
        boolean itemHasChanged = false;
        Boolean originalBoolean;
        
        Goal goal = goalDAO.get(goalId);
        requireResult(goal, "Goal could not be found");
        originalBoolean = goal.getIsApproved();
        
        boolean isApproved = goal.getIsApproved();
        goal.setIsApproved(!isApproved);
        
        goalDAO.update(goal);
        
        boolean boolCheck = goalDAO.get(goalId).getIsApproved();
        
        if (originalBoolean != boolCheck)
        {
            itemHasChanged = true;
        }
        throw new ProcessingException("Could not switch approval bool");
    }
    
    public Boolean switchCompleteBool(String goalId)
    {
        boolean itemHasChanged = false;
        Boolean originalBoolean;
        
        Goal goal = goalDAO.get(goalId);
        requireResult(goal, "Goal could not be found");
        originalBoolean = goal.getIsCompleted();
        
        boolean isCompleted = goal.getIsCompleted();
        goal.setIsCompleted(!isCompleted);
        
        goalDAO.update(goal);
        
        boolean boolCheck = goalDAO.get(goalId).getIsCompleted();
        
        if (originalBoolean != boolCheck)
        {
            itemHasChanged = true;
        }
        throw new ProcessingException("Could not switch completion bool");
    }
    
    public Boolean removeGoal(String moduleId, String goalId)
    {
        boolean hasSucceeded = false;
        
        Goal goal = goalDAO.get(goalId);
        requireResult(goal, "Goal could not be found");
        Module module = moduleDAO.get(moduleId);
        requireResult(module, "Module could not be found");
        
        goalDAO.delete(goal);
        module.getGoals().remove(goal);
        
        Goal goalAfterDeletion = goalDAO.get(goalId);
        
        if (goalAfterDeletion == null && !module.getGoals().contains(goal)) 
        {
            hasSucceeded = true;
        }
        throw new ProcessingException("Could not remove goal from database or list in module");
    }
    
    public Boolean create(Goal newGoal)
    {
        Date currentTime = Date.from(Instant.now());
        newGoal.setCreationDate(currentTime);
        
        List<Goal> goalsBefore = goalDAO.getAll();
        
        goalDAO.create(newGoal);
        
        List<Goal> goalsAfter = goalDAO.getAll();
        
        if (goalsBefore != goalsAfter)
        {
            return true;
        }
        throw new ProcessingException("Could not create a new goal");
    }
}