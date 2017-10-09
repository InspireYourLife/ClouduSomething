package groep3.cloudapi.service;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Task;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.TaskDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class TaskService extends BaseService{

    private final TaskDAO taskDAO;
    private final GoalDAO goalDAO;
    
    public TaskService(TaskDAO taskDAO, GoalDAO goalDAO)
    {
        this.taskDAO = taskDAO;
        this.goalDAO = goalDAO;
    }

    public List<Task> getTasks(String userId, int moduleId, int goalId) 
    {    
        Goal goal = goalDAO.getById(goalId);
        List<Task> tasks = goal.getTasks();
        return tasks;   
    }

    public Task getSpecificTask(String userId, int moduleId, int goalId, int taskId) 
    {
        Goal goal = goalDAO.getById(goalId);
        List<Task> tasks = goal.getTasks();
        
        Task task = tasks.get(taskId);
        
        return task;
    }

    public void createTask(String userId, int moduleId, int goalId, Task newTask)
    {
        Date currentTime = Date.from(Instant.now());
        newTask.setCreationDate(currentTime);
        
        Goal goal = goalDAO.getById(goalId);
        List<Task> tasks = goal.getTasks();
        tasks.add(newTask);
        
        taskDAO.create(newTask);
        
    }
}
