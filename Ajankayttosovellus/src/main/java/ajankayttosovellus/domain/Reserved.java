package ajankayttosovellus.domain;

public class Reserved implements TypeOfTime {

    String type = "reserved";

    @Override
    public String getTypeOfTime() {
        return this.type;
    }

}
