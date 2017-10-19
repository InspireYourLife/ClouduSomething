package groep3.cloudapi.service;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Task;
import groep3.cloudapi.persistence.GoalDAO;
import groep3.cloudapi.persistence.TaskDAO;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class TaskService extends BaseService{

    private final TaskDAO taskDAO;
    private final GoalDAO goalDAO;
    
    @Inject
    public TaskService(TaskDAO taskDAO, GoalDAO goalDAO)
    {
        this.taskDAO = taskDAO;
        this.goalDAO = goalDAO;
    }

    public List<Task> getTasks(String userId, String moduleId, String goalId) 
    {    
        Goal goal = goalDAO.get(goalId);
        List<Task> tasks = goal.getTasks();
        return tasks;
    }

    public Task getSpecificTask(String userId, String moduleId, String goalId, String taskId) 
    {   
        Task task = taskDAO.get(taskId);   
        return task;
    }

    public void createTask(String userId, String moduleId, String goalId, Task newTask)
    {
        Date currentTime = Date.from(Instant.now());
        newTask.setCreationDate(currentTime);
        
        Goal goal = goalDAO.get(goalId);
        List<Task> tasks = goal.getTasks();
        tasks.add(newTask);
        
        taskDAO.create(newTask);
        goalDAO.create(goal);
        
    }
}
