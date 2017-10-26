package groep3.cloudapi.resource;

import groep3.cloudapi.model.Appointment;
import groep3.cloudapi.model.Calendar;
import groep3.cloudapi.model.Role;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.AppointmentPresenter;
import groep3.cloudapi.presentation.CalendarPresenter;
import groep3.cloudapi.presentation.model.CalendarView;
import groep3.cloudapi.service.CalendarService;
import io.dropwizard.auth.Auth;
import javax.annotation.security.RolesAllowed;
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
    private final CalendarPresenter calendarPresenter;
    private final AppointmentPresenter appointmentPresenter;
     
    @Inject
    public UserCalendarResource (CalendarService calendarService, CalendarPresenter calendarPresenter, AppointmentPresenter appointmentPresenter)
    {
        this.calendarService = calendarService;
        this.calendarPresenter = calendarPresenter;
        this.appointmentPresenter = appointmentPresenter;
    }
    
    //Get Calls - Calendar
    @GET
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CLIENT, Role.Labels.CARETAKER})
    @Path( "/{userId}/calendar")
    public CalendarView getCalendar(@PathParam("id") String id, @Auth User authenticatedUser)
    {
        Calendar calendar = calendarService.getCalendar(id);
        CalendarView calendarView = calendarPresenter.present(calendar);
        return calendarView;
    }
    
    //Post Calls - Calendar
    @POST
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    @Path( "/{userId}/calendar/appointment")
    public Calendar addAppointment(@PathParam("id") String id, @Valid Appointment appointment, @Auth User authenticatedUser)
    {
        calendarService.addAppointment(id, appointment);
        Calendar calendar = calendarService.getCalendar(id);
        return calendar;
    }
}
