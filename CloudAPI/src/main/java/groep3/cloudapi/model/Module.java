package groep3.cloudapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity (value = "modules")
public class Module extends EntityModel 
{
    @Embedded
    @NotEmpty
    String name;
    
    //@Embedded
    //@NotEmpty
    //String moduleCode;
    
    @Reference
    @NotEmpty
    List<Goal> goals  = new ArrayList<Goal>();
    
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

//    public String getModuleCode()
//    {
//        return moduleCode;
//    }
//
//    public void setModuleCode(String moduleCode)
//    {
//        this.moduleCode = moduleCode;
//    }

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
        return isCompleted;
    }

    public void setIsComplete(Boolean isComplete)
    {
        this.isCompleted = isComplete;
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
