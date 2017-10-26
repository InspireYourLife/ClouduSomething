package groep3.cloudapi.presentation.model;

import groep3.cloudapi.model.User;
import java.util.Date;
import java.util.List;

public class AppointmentView
{
    public List<User> participants;
    public Date date;
    public String description;
    public String location;
    public String comments;
}
