package groep3.cloudapi.model;

import java.util.Date;
import java.util.List;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity (value = "modules")
public class Module extends EntityModel 
{
    String name;
    String moduleCode;
    
    @Reference
    List<Goal> goals;
    
    Boolean isComplete;
    Boolean isApproved;
    
    Date CreationDate;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getModuleCode()
    {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode)
    {
        this.moduleCode = moduleCode;
    }

    public List<Goal> getGoals()
    {
        return goals;
    }

    public void setGoals(List<Goal> goals)
    {
        this.goals = goals;
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
        return CreationDate;
    }

    public void setCreationDate(Date CreationDate)
    {
        this.CreationDate = CreationDate;
    }
    
    
}
