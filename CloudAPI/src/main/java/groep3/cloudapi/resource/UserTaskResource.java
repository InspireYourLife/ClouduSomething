package groep3.cloudapi.resource;

import groep3.cloudapi.model.Role;
import groep3.cloudapi.model.Task;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.TaskPresenter;
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
import javax.ws.rs.QueryParam;
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
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}/tasks")
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CLIENT, Role.Labels.CARETAKER})
    public List<TaskView> getTasks(@PathParam ("userId") String userId, @PathParam ("moduleId") String moduleId, @PathParam ("goalId") String goalId, @QueryParam("owner") String owner)
    {
        List<Task> task = taskService.getTasks(userId, moduleId, goalId, owner);
        return taskPresenter.presentAllTasks(task);
    }
    
    @GET
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}/tasks/{taskId}")
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CLIENT, Role.Labels.CARETAKER})
    public TaskView getSpecificTask(@PathParam ("userId") String userId, @PathParam ("moduleId") String moduleId, @PathParam ("goalId") String goalId, @PathParam ("taskId") String taskId, @QueryParam("name") String name)
    {
        Task task = taskService.getSpecificTask(userId, moduleId, goalId, taskId, name);
        return taskPresenter.presentSpecificTask(task);
    }
    
    @POST
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}/tasks")
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    public TaskView createTask(@PathParam ("userId") String userId, @PathParam ("moduleId") String moduleId, @PathParam ("goalId") String goalId, @Valid Task newTask)
    {
        taskService.createTask(userId, moduleId, goalId, newTask);
        return taskPresenter.presentSpecificTask(newTask);
    }
    
    @DELETE
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}/tasks/{taskId}")
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    public void deleteTask(@PathParam ("userId") String userId, @PathParam ("moduleId") String moduleId, @PathParam ("goalId") String goalId, @PathParam ("taskId") String taskId)
    {
        taskService.deleteTask(userId, moduleId, goalId, taskId);
    }
    
    @PUT
    @Path("/{userId}/modules/{moduleId}/goals/{goalId}/tasks/{taskId}/complete")
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    public boolean taskStatus(@PathParam ("userId") String userId, @PathParam ("moduleId") String moduleId, @PathParam ("goalId") String goalId, @PathParam ("taskId") String taskId)
    {
        boolean taskIsCompleted = taskService.taskStatus(userId, moduleId, goalId, taskId);
        return taskIsCompleted;
    }
}
