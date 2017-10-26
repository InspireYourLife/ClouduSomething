package groep3.cloudapi.presentation.model;

import groep3.cloudapi.model.User;
import java.util.Date;

public class TaskView {
    public String owner;
    public String name;
    public String description;
    public Integer points;
    public boolean isComplete;
    public Date creationDate;
    public String feedback;
}
