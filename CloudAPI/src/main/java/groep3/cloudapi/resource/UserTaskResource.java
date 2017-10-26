package groep3.cloudapi.resource;

import groep3.cloudapi.model.Task;
import groep3.cloudapi.presentation.model.TaskPresenter;
import groep3.cloudapi.presentation.model.TaskView;
import groep3.cloudapi.service.TaskService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path ("/users")
@Consumes (MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public class UserTaskResource extends BaseResource{
    
    private final TaskService taskService;
    private final TaskPresenter taskPresenter;
    
    @Inject
    public UserTaskResource (TaskService taskService, TaskPresenter taskPresenter){
        
        this.taskService = taskService;
        this.taskPresenter = taskPresenter;
    }
    
    @GET
    @Path("/{UserId}/modules/{ModuleId}/goals/{GoalId}/tasks")
    @RolesAllowed({"ADMIN", "CLIENT"})
    public List<TaskView> getTasks(@PathParam ("UserId") String userId, @PathParam ("ModuleId") String moduleId, @PathParam ("GoalId") String goalId)
    {
        List<Task> task = taskService.getTasks(userId, moduleId, goalId);
        return taskPresenter.presentAllTasks(task);
    }
    @GET
    @Path("/{UserId}/modules/{ModuleId}/goals/{GoalId}/tasks/{TaskId}")
    @RolesAllowed({"ADMIN", "CLIENT"})
    public TaskView getSpecificTask(@PathParam ("UserId") String userId, @PathParam ("ModuleId") String moduleId, @PathParam ("GoalId") String goalId, @PathParam ("TaskId") String taskId)
    {
        Task task = taskService.getSpecificTask(userId, moduleId, goalId, taskId);
        return taskPresenter.presentSpecificTask(task);
    }
    
    @POST
    @Path("/{UserId}/modules/{ModuleId}/goals/{GoalId}/tasks")
    @RolesAllowed("ADMIN")
    public TaskView createTask(@PathParam ("UserId") String userId, @PathParam ("ModuleId") String moduleId, @PathParam ("GoalId") String goalId, @Valid Task newTask)
    {
        taskService.createTask(userId, moduleId, goalId, newTask);
        return taskPresenter.presentSpecificTask(newTask);
    }
    
    @DELETE
    @Path("/{UserId}/modules/{ModuleId}/goals/{GoalId}/tasks/{TaskId}")
    @RolesAllowed("ADMIN")
    public void deleteTask(@PathParam ("UserId") String userId, @PathParam ("ModuleId") String moduleId, @PathParam ("GoalId") String goalId, @PathParam ("TaskId") String taskId)
    {
        taskService.deleteTask(userId, moduleId, goalId, taskId);
    }
    
    @PUT
    @Path("/{UserId}/modules/{ModuleId}/goals/{GoalId}/tasks/{TaskId}/complete")
    @RolesAllowed({"ADMIN", "CLIENT"})
    public boolean taskStatus(@PathParam ("UserId") String userId, @PathParam ("ModuleId") String moduleId, @PathParam ("GoalId") String goalId, @PathParam ("TaskId") String taskId)
    {
        boolean taskIsCompleted = taskService.taskStatus(userId, moduleId, goalId, taskId);
        return taskIsCompleted;
    }
}
