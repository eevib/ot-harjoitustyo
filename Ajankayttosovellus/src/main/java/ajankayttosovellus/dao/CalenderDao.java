package ajankayttosovellus.dao;

import ajankayttosovellus.domain.Calender;
import java.util.List;

public interface CalenderDao {

    Calender create(Calender calender);
    List<Calender> getAll();
    Calender saveCalender(Calender calender);
}
