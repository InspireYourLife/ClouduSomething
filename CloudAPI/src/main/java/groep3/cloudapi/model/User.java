package groep3.cloudapi.model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity (value = "users")
public class User extends EntityModel implements Principal
{
    @Embedded
    @NotEmpty
    private String name;
    
    @Embedded
    private String telephone;
    
    @Embedded
    @NotEmpty
    private String email;
    
    @Embedded
    @NotEmpty
    private String password;
    
    @Embedded
    private List<String> address = new ArrayList<String>();
    
    @Embedded
    private int collectedPoints;
    
    @Embedded
    @NotNull
    private Role role;
    
    @Reference
    private List<Module> modules = new ArrayList<Module>();
    
    @Reference
    private List<User> contacts = new ArrayList<User>();
    
    @Reference
    private Calendar calendar = new Calendar();
    
    @Embedded
    private String image;
    
    @Embedded
    private Date creationDate;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public List<String> getAddress()
    {
        return address;
    }

    public void setAddress(List<String> address)
    {
        this.address = address;
    }

    public int getCollectedPoints()
    {
        return collectedPoints;
    }

    public void setCollectedPoints(int collectedPoints)
    {
        this.collectedPoints = collectedPoints;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }
    
    public List<Module> getModules()
    {
        return modules;
    }

    public void setModules(List<Module> modules)
    {
        this.modules = modules;
    }

    public List<User> getContacts()
    {
        return contacts;
    }

    public void setContacts(List<User> contacts)
    {
        this.contacts = contacts;
    }

    public Calendar getCalendar()
    {
        return calendar;
    }

    public void setCalendar(Calendar calendar)
    {
        this.calendar = calendar;
    }
    
    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public boolean hasRole(String role)
    {
        return this.role.toString().equals(role);
    }
}
