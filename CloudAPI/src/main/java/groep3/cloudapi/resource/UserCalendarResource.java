package groep3.cloudapi.resource;

import groep3.cloudapi.model.Appointment;
import groep3.cloudapi.model.Calendar;
import groep3.cloudapi.model.Role;
import groep3.cloudapi.model.User;
import groep3.cloudapi.presentation.AppointmentPresenter;
import groep3.cloudapi.presentation.CalendarPresenter;
import groep3.cloudapi.presentation.model.AppointmentView;
import groep3.cloudapi.presentation.model.CalendarView;
import groep3.cloudapi.service.CalendarService;
import io.dropwizard.auth.Auth;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    public CalendarView getCalendar(@PathParam("userId") String id, @Auth User authenticatedUser)
    {
        Calendar calendar = calendarService.getCalendar(id);
        CalendarView calendarView = calendarPresenter.present(calendar);
        return calendarView;
    }
    
    @GET
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CLIENT, Role.Labels.CARETAKER})
    @Path( "/{userId}/calendar/appointment/{appointmentId}")
    
    public AppointmentView getAppointment(@PathParam("userId") String uid, @PathParam("appointmentId") String aid, @Auth User authenticatedUser)
    {
        Appointment appointment = calendarService.getAppointment(uid, aid);
        
        AppointmentView appointmentView = appointmentPresenter.present(appointment);
        return appointmentView;
    }
    
    //Post Calls - Calendar
    @POST
    @RolesAllowed(Role.Labels.ADMIN)
    @Path("/{userId}/calendar/")
    
    public Boolean postCalendar(@PathParam("userId") String id, @Valid Calendar calendar, @Auth User authenticatedUser)
    {
        return calendarService.postCalendar(id, calendar);
    }
    
    
    @POST
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    @Path("/{userId}/calendar/appointment/")
    
    public Boolean addAppointment(@PathParam("userId") String id, @Valid Appointment appointment, @Auth User authenticatedUser)
    {
        return calendarService.addAppointment(id, appointment);
    }
    
    //Put Calls - Calendar
    @PUT
    @RolesAllowed(Role.Labels.ADMIN)
    @Path("/{userId}/calendar/{calendarId}")
    public Boolean changeCalendar(@PathParam("userId") String uid, @PathParam("calendarId") String calendarId, @Auth User authenticatedUser, @Valid Calendar c)
    {
        return calendarService.changeCalendar(uid, calendarId, c);
    }
    
    //Delete Calls
    @DELETE
    @RolesAllowed({Role.Labels.ADMIN, Role.Labels.CARETAKER})
    @Path( "/{userId}/calendar/appointment/{appointmentId}")
    
    public Boolean deleteAppointment(@PathParam("userId") String uid, @PathParam("appointmentId") String aid, @Auth User authenticatedUser)
    {
        return calendarService.deleteAppointment(uid, aid);
    }
}
