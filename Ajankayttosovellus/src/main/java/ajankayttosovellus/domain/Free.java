package ajankayttosovellus.domain;

public class Free implements TypeOfTime {

    String type;

    public Free() {
        this.type = "free";
    }

    @Override
    public String getTypeOfTime() {
        return this.type;
    }
    public String toString() {
        return this.type;
    }

}
