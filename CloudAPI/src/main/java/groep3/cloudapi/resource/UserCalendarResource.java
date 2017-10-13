package groep3.cloudapi.resource;

import groep3.cloudapi.model.Goal;
import groep3.cloudapi.model.Module;
import groep3.cloudapi.service.ContactService;
import groep3.cloudapi.model.Calendar;
import groep3.cloudapi.model.Notification;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.model.GoalPresenter;
import groep3.cloudapi.presentation.model.UserPresenter;
import groep3.cloudapi.service.GoalService;
import groep3.cloudapi.service.NotificationService;
import groep3.cloudapi.service.UserService;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/users" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class UserCalendarResource extends BaseResource
{
    private final UserService userService;
     
    @Inject
    public UserCalendarResource (UserService userService)
    {
        this.userService = userService;
    }
    
    //Get Calls - Calendar
    @GET
    @Path( "/{id}/calendar")
    public Calendar getCalendar(@PathParam("id") String id)
    {
        Calendar calendar = userService.getCalendar(id);
        return calendar;
    }
}
