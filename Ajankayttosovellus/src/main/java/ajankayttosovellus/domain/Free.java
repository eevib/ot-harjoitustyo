package ajankayttosovellus.domain;

public class Free implements TypeOfTime {

    String type;

    public Free() {
        this.type = "free";
    }
    public Free(String type) {
        this.type = type;
    }

    @Override
    public String getTypeOfTime() {
        return this.type;
    }
    public String toString() {
        return this.type;
    }

}
