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
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ProcessingException;

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
        
        requireResult(u, "User not found");
        
        Calendar c = u.getCalendar();
        
        return c;
    }

    public boolean addAppointment(String id, Appointment appointment)
    {
        List<Appointment> appointmentsPreCreate = appointmentDAO.getAll();
        
        Date currentTime = Date.from(Instant.now());
        appointment.setCreationDate(currentTime);
        
        appointmentDAO.create(appointment);
        
        User u = userDAO.get(id);
        
        requireResult(u, "User not found");
        
        Calendar c = u.getCalendar();
        
        requireResult(calendarDAO.get(c.getId()), "Calendar not found");
        
        List<Appointment> appointments = c.getAppointments();
        appointments.add(appointment);
        c.setAppointments(appointments);
        
        calendarDAO.update(c);
        
        u.setCalendar(c);
        
        userDAO.update(u);
        
        List<Appointment> appointmentsPostCreate = appointmentDAO.getAll();
        
        if(appointmentsPreCreate.size() < appointmentsPostCreate.size())
        {
            return true;
        }
        else
        {
            throw new ProcessingException("Appointment has not been added");
        }
    }

    public Appointment getAppointment(String uid, String aid)
    {
        Appointment a = appointmentDAO.get(aid);
        
        requireResult(a, "Appointment not found");
        
        User u = userDAO.get(uid);
        
        if (a.getParticipants().contains(u))
        {
            return a;
        }
        else
        {
            throw new BadRequestException("User is not a participant");
        }
    }

    public Boolean deleteAppointment(String uid, String aid)
    {
        Appointment a = appointmentDAO.get(aid);
        
        requireResult(a, "Appointment not found");
        
        appointmentDAO.delete(a);
        
        User u = userDAO.get(uid);
        
        requireResult(u, "User not found");
        
        Calendar c = u.getCalendar();
        
        List<Appointment> calendarAppointments = c.getAppointments();
        
        calendarAppointments.remove(a);
        
        c.setAppointments(calendarAppointments);
        
        calendarDAO.update(c);
        
        if (appointmentDAO.get(aid) == null)
        {
            return true;
        }
        
        else
        {
            throw new ProcessingException("Appointment has not been deleted");        
        }
    }

    public Boolean changeCalendar(String uid, String calendarId, Calendar c)
    {
        Calendar oldCalendar = calendarDAO.get(calendarId);
        
        requireResult(oldCalendar, "Calendar does not exist");
        
        calendarDAO.update(c);
        
        User u = userDAO.get(uid);;
        requireResult(u, "User does not exist");
        
        u.setCalendar(c);
        
        userDAO.update(u);
        
        if (oldCalendar == c)
        {
            return false;
        }
        
        else
        {
            throw new ProcessingException("Calendar has not been updated");
        }
    }

    public Boolean postCalendar(String id, Calendar calendar)
    {
        calendarDAO.create(calendar);
        return true;
    }
}
