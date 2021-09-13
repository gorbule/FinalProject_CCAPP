public class Event extends DB{
    protected int eventID;
    protected String projectName;
    protected String eventName;
    protected int totalFinancesForEvent;

    public Event() {
    }

    public Event(String projectName, String eventName, int totalFinancesForEvent) {
        this.eventID = eventID;
        this.projectName = projectName;
        this.eventName = eventName;
        this.totalFinancesForEvent = totalFinancesForEvent;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", projectName='" + projectName + '\'' +
                ", eventName='" + eventName + '\'' +
                ", totalFinancesForEvent=" + totalFinancesForEvent +
                '}';
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getTotalFinancesForEvent() {
        return totalFinancesForEvent;
    }

    public void setTotalFinancesForEvent(int totalFinancesForEvent) {
        this.totalFinancesForEvent = totalFinancesForEvent;
    }
}
