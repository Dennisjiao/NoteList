import java.util.HashMap;
import java.util.Map;

public class Client implements Comparable<Client>{

    private String firstName;
    private String lastName;
    private Map<Event, Integer> eventTicketMap = new HashMap<>();


    //Space split first and last names add to Client
    public Client(String name){
        this.firstName = name.split(" ")[0];
        this.lastName = name.split(" ")[1];
    }


    @Override
    public String toString() {
        String message = firstName + " " + lastName + "\n";
        for (Event event : eventTicketMap.keySet()) {
            message += "  |-" + event.getEventName() + ": " + eventTicketMap.get(event) + "\n";
        }
        return message.trim();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addEvent(Event event, int count) {
        eventTicketMap.put(event,  count);
    }

    public boolean hasEventTicket(Event event) {
        return eventTicketMap.containsKey(event);
    }

    public int getEventTicket(Event event) {
        if (hasEventTicket(event)) {
            return eventTicketMap.get(event);
        }
        return 0;
    }

    public int getEventCount() {
        return eventTicketMap.size();
    }

    public void removeEvent(Event event,int count){
        //int tmp = eventTicketMap.getOrDefault(event,);
        //eventTicketMap.remove(event);
        eventTicketMap.put(event, count);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int compareTo(Client client) {
        return this.lastName.compareTo(client.lastName) == 0 ? this.firstName.compareTo(client.firstName)
                : this.lastName.compareTo(client.lastName);
    }


}
