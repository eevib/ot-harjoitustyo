package ajankayttosovellus.domain;

import ajankayttosovellus.dao.CalenderDao;
import ajankayttosovellus.dao.FileUserDao;
import ajankayttosovellus.dao.UserDao;
import java.util.List;

public class CalenderService {

    private Calender calender;
    String day;
    private UserDao userDao;
    private CalenderDao calenderDao;
    private User loggedUser;

    /**
     * Konstruktori, CalenderServicelle.
     * @param calenderDao asetetaan CalenderDaoksi.
     * @param userDao asetetaan UserDaoksi.
     */
    public CalenderService(CalenderDao calenderDao, UserDao userDao) {
        this.userDao = userDao;
        this.calenderDao = calenderDao;
        this.day = "";
    }

    /**
     * Metodi kirjaa käyttäjän sisään, asettamalla kirjautuneeksi käyttäjäksi
     * kirjautuneen ja hakee käyttäjän kalenterin..
     *
     * @param name Merkkijonomuotoinen käyttäjän nimi.
     * @param password Merkkijonomuotoinen käyttäjän salasana.
     * @return True jos käyttäjä löytyy, muuten false.
     */
    public boolean login(String name, String password) {
        User user = userDao.findByName(name);
        if (user == null) {
            return false;
        } else if (user.getPassword().equals(password)) {
            this.loggedUser = user;
            this.calender = this.calenderDao.findByUser(this.loggedUser);
            return true;
        }
        return false;
    }

    /**
     * Metodi etsii kirjautuneen käyttäjän kalenterin, jos kalenteria ei löydy
     * metodi luo uuden kalenterin.
     *
     * @return Calender, käyttäjän kalenteri tai uuden käyttjälle luoto uusi
     * kalenteri.
     */
    public Calender getCalender() {
        List<Calender> calenders = this.calenderDao.getAll();
        for (Calender userCalender : calenders) {
            if (userCalender.getUser().getName().equals(this.loggedUser.getName())) {
                return userCalender;
            }
        }
        Calender newCalender = new Calender("");
        newCalender.setUser(loggedUser);
        return newCalender;
    }

    /**
     * Metodi palauttaa listan kalentereista sopivassa muodossa.
     * @return Merkkijonomuotoisen listan kalenterista.
     */
    public List<String> drawCalender() {
        return getCalender().calenderToList();
    }

    /**
     * Luo uuden käyttäjän ja asettaa käyttäjän kirjautuneeksi ja luo käyttäjälle kalenterin.
     * @param name Merkkijonomuotoinen käyttäjän nimi.
     * @param password Merkkijonomuotoinen salasana.
     * @return False jos käyttäjä on jo olemassa, tai käyttäjän luonti ei onnistu, muuten true.
     */
    public boolean createUser(String name, String password) {
        if (userDao.findByName(name) != null) {
            return false;
        }
        User newUser = new User(name, password);
        try {
            userDao.create(newUser);
        } catch (Exception exception) {
            return false;
        }
        this.loggedUser = newUser;
        Calender newCalender = new Calender(newUser.getName() + "'s calender");
        newCalender.setUser(newUser);
        this.calender = newCalender;
        this.calenderDao.create(newCalender);
        return true;
    }

    /**
     * Luo uuden todon. 
     * @param name merkkijonomuotoinen nimi todolle.
     */
    public void createTodo(String name) {
        int id = this.calender.getTodoIdCalc();
        calender.addTodoToList(new Todo(name, id));
    }
    
    /**
     * Palauttaa listan ajastamattomilla todoilla.
     * @return Listan ajastamattomista todoista.
     */
    public List getUnScheduledTodos() {
        if (this.calender.getUnScheduledTodos() == null) {
            return null;
        }
        return this.calender.getUnScheduledTodos();
    }

    /**
     * Metodi ajastaa Todon.
     * @param day Merkkijonomuotoinen esitys päivästä.
     * @param time Merkkijonomuotoinen esitys ajasta
     * @param todo Todo, joka ajastetaan.
     * @return Palauttaa true, jos ajastaminen onnistuu, muuten false.
     */
    public boolean scheduleTodo(String day, String time, Todo todo) {
        if (getDay(day) == false || checkTime(time) == false) {
            return false;
        }
        getDay(day);
        Boolean schedulingIsPossible = calender.scheduleTodo(this.day, time, todo);
        return schedulingIsPossible;
    }

    /**
     * Tallentaa kalenterin.
     */
    public void saveCalender() {
        this.calenderDao.saveCalender(this.calender);
    }

    /**
     * Palauttaa Todon.
     * @param todoId Parametrina haluttavan Todon Id.
     * @return Palauttaaa Todon.
     */
    public Todo getTodo(int todoId) {
        return this.calender.getTodo(todoId);
    }

    /**
     * Palautttaa viimeksi tallennettu Todo.
     * @return Viimeksi kalenteriin lisätty Todo.
     */
    public Todo getLastTodo() {
        return this.calender.getLastTodo();
    }

    /**
     * Palauttaa ajastamattomien tehtävien listan koon.
     * @return Kokonaislukuna ajastamattomien todoiden listan koon.
     */
    public int getUnchedueldTodosSize() {
        return this.calender.getUnScheduledTodos().size();
    }

    /**
     * Palauttaa ajastettut Todot.
     * @return Listan ajastetuilla Todoilla.
     */
    public List getScheduledTodos() {
        return this.calender.getScheduledTodos();
    }

    /**
     * Metodi tarkistaa onko aika hyväksytyssä muodossa.
     *
     * @param time aika annaetaan merkkijonona.
     * @return palauttaa true jos kokonaisluku on 0 - 24, muuten false.
     */
    public boolean checkTime(String time) {
        for (int i = 0; i < 24; i++) {
            if (time.equals("" + i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi päivittää kalenterin annetulla kalenterilla.
     *
     * @param calender Calender muotoinen kalenteri.
     */
    public void setCalender(Calender calender) {
        this.calender = calender;
    }

    public User getLoggedUser() {
        return this.loggedUser;
    }

    /**
     * Metodi palauttaa merkkijonona saatavana päivän muutettuna sovelluksen
     * ymmärtämään luun, tämäkin merkkijonomuodossa.
     *
     * @param day Annetaan viikonpääivää vastaava merkkijono, esim. "Mon"
     * @return True jos annettua merkkijonoa vastaa jokin päivä, muuten false.
     */
    public boolean getDay(String day) {
        if (day.equals("Mon")) {
            this.day = "0";
            return true;
        } else if (day.equals("Tue")) {
            this.day = "1";
            return true;
        } else if (day.equals("Wed")) {
            this.day = "2";
            return true;
        } else if (day.equals("Thu")) {
            this.day = "3";
            return true;
        } else if (day.equals("Fri")) {
            this.day = "4";
            return true;
        } else if (day.equals("Sat")) {
            this.day = "5";
            return true;
        } else if (day.equals("Sun")) {
            this.day = "6";
            return true;
        }
        return false;
    }
}
