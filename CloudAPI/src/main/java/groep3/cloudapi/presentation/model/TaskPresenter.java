package groep3.cloudapi.presentation.model;

import groep3.cloudapi.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskPresenter extends BasePresenter{

    public TaskView presentSpecificTask(Task task) {
        TaskView tView = new TaskView();
        
        tView.name = task.getName();
        tView.owner = task.getOwner().getName();
        tView.description = task.getDescription();
        tView.points = task.getPoints();
        tView.creationDate = task.getCreationDate();
        tView.feedback = task.getFeedback();
        
        return tView;
    }

    public List<TaskView> presentAllTasks(List<Task> task) {
        int counter = task.size();
        int i = 0;
        
        List<TaskView> taskData = new ArrayList<>();
        
        if(i < counter){
        
            Task t = task.get(i);
            TaskView tView = new TaskView();
            tView.name = t.getName();
            tView.owner = t.getOwner().getName();
            tView.description = t.getDescription();
            tView.points = t.getPoints();
            tView.creationDate = t.getCreationDate();
            tView.feedback = t.getFeedback();
            
            taskData.add(tView);
            i++;
        }
        return taskData;
        
    }
    
}
