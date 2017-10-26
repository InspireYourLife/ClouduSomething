package groep3.cloudapi.service;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Task;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.GoalDAO;
import groep3.cloudapi.persistence.TaskDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

public class TaskService extends BaseService{

    private final TaskDAO taskDAO;
    private final GoalDAO goalDAO;
    private final UserDAO userDAO;
    
    @Inject
    public TaskService(TaskDAO taskDAO, GoalDAO goalDAO, UserDAO userDAO)
    {
        this.taskDAO = taskDAO;
        this.goalDAO = goalDAO;
        this.userDAO = userDAO;
    }

    public List<Task> getTasks(String userId, String moduleId, String goalId) 
    {    
        Goal goal = goalDAO.get(goalId);
        requireResult(goal, "Goal not found");
        
        List<Task> tasks = goal.getTasks();
        requireResult(tasks, "No tasks found in this list");
        return tasks;
    }

    public Task getSpecificTask(String userId, String moduleId, String goalId, String taskId) 
    {   
        Task task = taskDAO.get(taskId);
        requireResult(task, "Task not found");
        return task;
    }

    public void createTask(String userId, String moduleId, String goalId, Task newTask)
    {
        Date currentTime = Date.from(Instant.now());
        newTask.setCreationDate(currentTime);
        
        Goal goal = goalDAO.get(goalId);
        requireResult(goal, "Goal not found");
        List<Task> tasks = new ArrayList<>();
        
        if (goal.getTasks() != null)
        {
            tasks = goal.getTasks();
        }
        
        tasks.add(newTask);
        goal.setTasks(tasks);
        
        User owner = userDAO.get(userId);
        requireResult(owner, "User not found");
        newTask.setOwner(owner);
        
        if(newTask.getId() == null){
            throw new BadRequestException();
        }
        taskDAO.create(newTask);
        goalDAO.update(goal);        
    }

    public void deleteTask(String userId, String moduleId, String goalId, String taskId) 
    {
        Task task = taskDAO.get(taskId);
        requireResult(task, "Task not found");
        taskDAO.delete(task);
    }

    public boolean taskStatus(String userId, String moduleId, String goalId, String taskId) {
        
        Task task = taskDAO.get(taskId);
        requireResult(task, "Task not found");
        boolean isComplete = task.getIsComplete();
        
        if(!isComplete){
            isComplete = true;
            task.setIsComplete(isComplete);
            taskDAO.update(task);
        } else {
            isComplete = false;
            task.setIsComplete(isComplete);
            taskDAO.update(task);
        }
        
        return isComplete;
    }
}
