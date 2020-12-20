package ajankayttosovellus.domain;

import ajankayttosovellus.dao.CalenderDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FakeCalenderDao implements CalenderDao {

    public List<Calender> calenders;

    public FakeCalenderDao() {
        this.calenders = new ArrayList<>();
    }

    @Override
    public Calender create(Calender calender) {
        calenders.add(calender);
        return calender;
    }

    @Override
    public List<Calender> getAll() {
        return this.calenders;
    }

    @Override
    public Calender saveCalender(Calender calender) {
        Calender c = findByUser(calender.getUser());
        if (c == null) {
            this.calenders.add(calender);
        } else {
            c = calender;
        }

        return calender;

    }

    @Override
    public Calender findByUser(User user) {
        for (Calender calender : calenders) {
            if (calender.getUser().getName().equals(user.getName())) {
                return calender;
            }
        }
        return null;
    }

}
