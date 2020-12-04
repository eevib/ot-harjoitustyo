package ajankayttosovellus.domain;

public class Reserved implements TypeOfTime {

    String type = "reserved";
    
    public Reserved(String type) {
        this.type = type;
    }

    @Override
    public String getTypeOfTime() {
        return this.type;
    }

}
