package groep3.cloudapi.resource;

import groep3.cloudapi.model.Appointment;
import groep3.cloudapi.model.Calendar;
import groep3.cloudapi.model.User;
import groep3.cloudapi.service.CalendarService;
import io.dropwizard.auth.Auth;
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
    private final CalendarService calendarService;
     
    @Inject
    public UserCalendarResource (CalendarService userService)
    {
        this.calendarService = userService;
    }
    
    //Get Calls - Calendar
    @GET
    @Path( "/{userId}/calendar")
    public Calendar getCalendar(@PathParam("id") String id, @Auth User authenticatedUser)
    {
        Calendar calendar = calendarService.getCalendar(id);
        return calendar;
    }
    
    //Post Calls - Calendar
    @POST
    @Path( "/{userId}/calendar/appointment")
    public Calendar addAppointment(@PathParam("id") String id, @Valid Appointment appointment, @Auth User authenticatedUser)
    {
        calendarService.addAppointment(id, appointment);
        Calendar calendar = calendarService.getCalendar(id);
        return calendar;
    }
}
