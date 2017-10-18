package groep3.cloudapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity (value = "goals")
public class Goal extends EntityModel 
{
    @Embedded
    @NotEmpty
    String name;
    
    @Embedded
    @NotEmpty
    String description;
    
    @Reference
    @NotEmpty
    List <Task> tasks = new ArrayList<Task>();
    
    @Embedded
    Date deadline;
    
    @Embedded
    Date completionDate;
    
    @Embedded
    @NotEmpty
    Boolean isCompleted;
    
    @Embedded
    @NotEmpty
    Boolean isApproved;
    
    @Embedded
    @NotEmpty
    Date creationDate;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
        return isCompleted;
    }

    public void setIsComplete(Boolean isCompleted)
    {
        this.isCompleted = isCompleted;
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
