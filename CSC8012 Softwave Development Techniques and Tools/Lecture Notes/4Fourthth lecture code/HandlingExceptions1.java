import java.io.*;
public class HandlingExceptions1 {
    static BufferedReader k = new
            BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int number;
        try {
            System.out.println("Enter integer: ");
            number = Integer.parseInt(k.readLine());
            System.out.println("You entered: " + number);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
}