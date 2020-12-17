package ajankayttosovellus.dao;

import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.User;
import java.util.List;

public interface CalenderDao {

    Calender create(Calender calender);
    List<Calender> getAll();
    Calender saveCalender(Calender calender);
    Calender findByUser(User user);
}
