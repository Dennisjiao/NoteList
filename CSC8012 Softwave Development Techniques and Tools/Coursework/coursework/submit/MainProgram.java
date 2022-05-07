import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.*;
import java.util.Scanner;

public class MainProgram {
    public static SortedArrayList<Event> eventList;
    public static SortedArrayList<Client> clientList;

    //Main function
    public static void main(String[] args) throws IOException {
        readtxt();
        welcome();
        Scanner input = new Scanner(System.in);

        while(true) {
            OutputList();
            String inputcontrol=null;
            inputcontrol = input.next();

            switch (inputcontrol) {
                case "e":
                    CheckEvent();
                    break;
                case "c":
                    CheckClinet();
                    break;
                case "b":
                    BuyTicket();
                    break;
                case "r":
                    RefundTicket();
                    break;
                case "f":
                    System.exit(0);
                default:
                    System.out.println("Sorry! We do not have this option!");
            }

            System.out.println("Do you want to continue? (If you want to Stop. Please press N or n. If not, Press any key to continue)");
            Scanner sc = new Scanner(System.in);
            String choices = sc.nextLine();
            if((choices.equals("N"))||(choices.equals("n"))){
                System.out.println("-------------------------------------------------------");
                break;
            }
            System.out.println("-------------------------------------------------------");
        }
    }

    //output welcome menu
    public static void welcome(){
        System.out.println("------------------------------------------");
        System.out.println("-------Welcome to the ticket System-------");
        System.out.println("------------------------------------------");
    }

    //output choice menu
    public static void OutputList(){

        System.out.println("Please press 'e' to display all the events detail");
        System.out.println("Please press 'c' to display all the clients detail");
        System.out.println("Please press 'b' to buy the events ticket");
        System.out.println("Please press 'r' to refund the ticket");
        System.out.println("Please press 'f' to finish the system");

    }


    //Read file (input.txt) and handle the data
    public static void readtxt() throws IOException {
        List<String> arrayList = new ArrayList<>();

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line=null;
            while((line=bf.readLine())!=null){
                arrayList.add(line);
            }
            int venueNumber = Integer.parseInt(arrayList.get(0));
            int userNumber = Integer.parseInt(arrayList.get((venueNumber*2)+1));


            eventList = new SortedArrayList<>();
            for(int i=0;i<venueNumber;i++){
                String eventName =arrayList.get((i*2)+1);
                int eventNumber = Integer.parseInt(arrayList.get((i*2)+2));
                eventList.adda(new Event(eventName,eventNumber));
            }
            clientList = new SortedArrayList<>();

            for(int i=0;i<userNumber;i++){
                clientList.adda(new Client(arrayList.get((venueNumber*2)+2+i)));
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }



    // Option e display event detail
    public static void CheckEvent(){
        for (Event event : eventList) {
            System.out.println(event);
        }
    }
    // Option c display client detail
    private static void CheckClinet() {
        for (Client client : clientList) {
            System.out.println(client);
        }
    }
    // Option b buy ticket

    public static void BuyTicket(){
        System.out.println("Please input the user name");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        Client client = getClientName(userName);
        if(client != null){
            System.out.println("Please input the event name");
            String eventName = sc.nextLine();
            Event event = getEventName(eventName);
            if(event != null){
                if((client.getEventCount()==3) && !client.hasEventTicket(event)){
                    System.out.println("Sorry! This user has already purchased three tickets about different events.");
                    return;
                }
                System.out.println("How many tickets do you want to buy?");
                int tic = Integer.parseInt(sc.nextLine());
                if(tic > event.getTickets()){
                    System.out.println("Sorry, we do not have enough ticket!");
                    WriteticketException(client, event);
                    return;
                }
                event.setTickets(event.getTickets()-tic);
                client.addEvent(event,tic);
                System.out.println("You successfully bought the ticket");
            }else {
                System.out.println("There is a wrong event name:"+eventName);
                return;
            }
        }else{
            WriteClientException(client);
            System.out.println("There is nobody called "+userName);
            return;
        }
    }



    //Get Client name and Event name
    private static Client getClientName(String userName) {
        for (Client client : clientList) {
            if (client.getFullName().equals(userName)) {
                return client;
            }
        }
        return null;
    }

    private static Event getEventName(String eventName) {
        for (Event event : eventList) {
            if (event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null;
    }


    // Option r refund ticket
    public static void RefundTicket(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the user name");
        String userName = sc.nextLine();
        Client client = getClientName(userName);
        if(client != null){
            System.out.println("Please input the event name");
            String eventName = sc.nextLine();
            Event event = getEventName(eventName);
            if(event != null){
                if(!client.hasEventTicket(event)){
                    System.out.println("The user did not buy the events yet");
                    return;
                }
                System.out.println("How many tickets do you want to refund?");
                int refundticnumber = Integer.parseInt(sc.nextLine());
                int tic = client.getEventTicket(event);

                event.setTickets(event.getTickets() + refundticnumber);
                client.removeEvent(event,tic-refundticnumber);
                System.out.println("You successfully refund the ticket");

            }else{
                System.out.println("You input wrong event name");
                return;
            }

        }else{
            WriteClientException(client);
            System.out.println("Sorry! no one called that");
            return;
        }

    }

    //Use bufferedwriter to write error information to letters.txt
    //Insufficient tickets error
    public static void WriteticketException(Client client,Event event){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("letters.txt",true))){
            bw.write("----------------Ticket Error----------------------");
            bw.write("\n");
            bw.write("Sorry "+client.getFullName()+", There are only "+event.getTickets()+" tickets left ");
            bw.write("\n");
            bw.write("Please buy ticket under this quantity.");
            bw.write("\n");
            bw.write("--------------------------------------------------");
            bw.write("\n");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //Client does not exist error
    public static void WriteClientException(Client client){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("letters.txt",true))){
            bw.write("----------------Client Error----------------------");
            bw.write("\n");
            bw.write("Sorry, there's no one named that. Please check your name.");
            bw.write("\n");
            bw.write("--------------------------------------------------");
            bw.write("\n");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


