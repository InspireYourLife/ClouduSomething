package groep3.cloudapi.presentation;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.presentation.model.GoalView;
import java.util.ArrayList;
import java.util.List;

public class GoalPresenter extends BasePresenter
{
    public List<GoalView> presentListOfGoals(List<Goal> goals)
    {
        List<GoalView> cleanGoalViewList = new ArrayList<GoalView>();
        
        for (Goal goal : goals) 
        {
            GoalView tempGoalView = new GoalView();
            
            tempGoalView.name = goal.getName();
            tempGoalView.description = goal.getDescription();
            tempGoalView.deadline = goal.getDeadline();
            tempGoalView.completionDate = goal.getCompletionDate();
            tempGoalView.isCompleted = goal.getIsCompleted();
            tempGoalView.isApproved = goal.getIsApproved();
            tempGoalView.creationDate = goal.getCreationDate();
            
            cleanGoalViewList.add(tempGoalView);
        }
        
        return cleanGoalViewList;
    }
    
    public GoalView presentGoal(Goal goal)
    {
        GoalView cleanGoalView = new GoalView();
        
        cleanGoalView.name = goal.getName();
        cleanGoalView.description = goal.getDescription();
        cleanGoalView.deadline = goal.getDeadline();
        cleanGoalView.completionDate = goal.getCompletionDate();
        cleanGoalView.isCompleted = goal.getIsCompleted();
        cleanGoalView.isApproved = goal.getIsApproved();
        cleanGoalView.creationDate = goal.getCreationDate();
        
        return cleanGoalView;
    }
}