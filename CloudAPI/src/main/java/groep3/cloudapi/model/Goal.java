package groep3.cloudapi.model;

import java.util.Date;
import java.util.List;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity (value = "goal")
public class Goal extends EntityModel 
{
    String name;
    String goalCode;
    String description;
    
    @Reference
    List <Task> tasks;
    
    Date deadline;
    Date completionDate;
    Boolean isComplete;
    Boolean isApproved;
    
    Date creationDate;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getGoalCode()
    {
        return goalCode;
    }

    public void setGoalCode(String goalCode)
    {
        this.goalCode = goalCode;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<Task> getTasks()
    {
        return tasks;
    }

    public void setTasks(List<Task> tasks)
    {
        this.tasks = tasks;
    }

    public Date getDeadline()
    {
        return deadline;
    }

    public void setDeadline(Date deadline)
    {
        this.deadline = deadline;
    }

    public Date getCompletionDate()
    {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate)
    {
        this.completionDate = completionDate;
    }

    public Boolean getIsComplete()
    {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete)
    {
        this.isComplete = isComplete;
    }

    public Boolean getIsApproved()
    {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved)
    {
        this.isApproved = isApproved;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    
}
