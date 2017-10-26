package groep3.cloudapi.presentation;

import groep3.cloudapi.model.Calendar;
import groep3.cloudapi.presentation.model.CalendarView;

public class CalendarPresenter extends BasePresenter
{

    public CalendarView present(Calendar calendar)
    {
        CalendarView cv = new CalendarView();
        
        cv.appointments = calendar.getAppointments();
        cv.deadlines = calendar.getDeadlines();
        
        return cv;
    }
}
