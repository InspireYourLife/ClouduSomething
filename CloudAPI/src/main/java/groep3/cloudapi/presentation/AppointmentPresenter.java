package groep3.cloudapi.presentation;

import groep3.cloudapi.model.Appointment;
import groep3.cloudapi.presentation.model.AppointmentView;

public class AppointmentPresenter extends BasePresenter
{

    public AppointmentView present(Appointment appointment)
    {
        AppointmentView av = new AppointmentView();
        
        av.comments = appointment.getComments();
        av.date = appointment.getDate();
        av.description = appointment.getDescription();
        av.location = appointment.getLocation();
        av.participants = appointment.getParticipants();
        
        return av;
    }
    
}
