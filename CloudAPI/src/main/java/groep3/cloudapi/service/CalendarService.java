package groep3.cloudapi.service;

import groep3.cloudapi.model.Appointment;
import groep3.cloudapi.model.Calendar;
import groep3.cloudapi.model.User;
import groep3.cloudapi.persistence.AppointmentDAO;
import groep3.cloudapi.persistence.CalendarDAO;
import groep3.cloudapi.persistence.UserDAO;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.bson.types.ObjectId;

public class CalendarService extends BaseService
{
    private final UserDAO userDAO;
    private final CalendarDAO calendarDAO;
    private final AppointmentDAO appointmentDAO;

    @Inject
    public CalendarService(UserDAO userDAO, CalendarDAO calendarDAO, AppointmentDAO appointmentDAO)
    {
        this.userDAO = userDAO;
        this.calendarDAO = calendarDAO;
        this.appointmentDAO = appointmentDAO;
    }

    public Calendar getCalendar(String id)
    {
        User u = userDAO.get(id);
        
        Calendar c = u.getCalendar();
        
        return c;
    }

    public void addAppointment(String id, Appointment appointment)
    {
        Date currentTime = Date.from(Instant.now());
        appointment.setCreationDate(currentTime);
        
        appointmentDAO.create(appointment);
        
        User u = userDAO.get(id);
        ObjectId calendarId = u.getCalendar().getId();
        Calendar c = calendarDAO.get(calendarId);
        
        List<Appointment> appointments = c.getAppointments();
        appointments.add(appointment);
        c.setAppointments(appointments);
        
        calendarDAO.update(c);
        
        u.setCalendar(c);
        
        userDAO.update(u);      
    }
}
