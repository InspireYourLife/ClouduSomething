package groep3.cloudapi.resource;

import groep3.cloudapi.model.Task;
import groep3.cloudapi.service.TaskService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path ("/users")
@Consumes (MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public class TaskResource extends BaseResource{
    
    private final TaskService taskService;
    
    @Inject
    public TaskResource (TaskService taskService){
        
        this.taskService = taskService;
    }
    
    @GET
    @Path("/{UserId}/{ModuleId}/{GoalId}")
    public List<Task> getTasks(@PathParam ("UserId") String userId, @PathParam ("ModuleId") int moduleId, @PathParam ("GoalId") int goalId)
    {
        List<Task> task = taskService.getTasks(userId, moduleId, goalId);
        return task;
    }
    @GET
    @Path("/{UserId}/{ModuleId}/{GoalId}/{TaskId}")
    public Task getSpecificTask(@PathParam ("UserId") String userId, @PathParam ("ModuleId") int moduleId, @PathParam ("GoalId") int goalId, @PathParam ("TaskId") int taskId)
    {
        Task task = taskService.getSpecificTask(userId, moduleId, goalId, taskId);
        return task;
    }
    
}
