public class Event implements Comparable<Event>{

    private String eventName;
    private int eventTicketNumber;

    @Override
    public String toString() {
        return eventName + ": " + eventTicketNumber;
    }

    public Event(String eventName, int eventTicketNumber){
        this.eventName = eventName;
        this.eventTicketNumber = eventTicketNumber;
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventTicketNumber() {
        return eventTicketNumber;
    }

    public void setEventTicketNumber(int eventTicketNumber) {
        this.eventTicketNumber = eventTicketNumber;
    }

    public void setTickets(int tickets) {
        this.eventTicketNumber = tickets;
    }

    public int getTickets() {
        return eventTicketNumber;
    }

    @Override
    public int compareTo(Event event) {
        return eventName.compareTo(event.eventName);
    }

}
