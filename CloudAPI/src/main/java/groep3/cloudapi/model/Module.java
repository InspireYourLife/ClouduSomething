package groep3.cloudapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
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
 
    @Reference
    List<Goal> goals  = new ArrayList<Goal>();
    
    @Embedded
    @NotNull
    Boolean isCompleted;
    
    @Embedded
    @NotNull
    Boolean isApproved;
    
    @Embedded
    Date creationDate;
    
    @Embedded
    @NotNull
    Boolean isTemplate;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Goal> getGoals()
    {
        return goals;
    }

    public void setGoals(List<Goal> goals)
    {
        this.goals = goals;
    }

    public Boolean getIsCompleted()
    {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted)
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
    
    public Boolean getIsTemplate()
    {
        return isTemplate;
    }

    public void setIsTemplate(Boolean isTemplate)
    {
        this.isTemplate = isTemplate;
    }
}
