package csc8011;
import java.io.*;
import java.util.ArrayList;
import java.io.File;



/*Store and output the information about
MembershipID, MembershipName and time recorded (in seconds)*/

public class Runner {
    public static void readcsv(){
        //Read race.csv file
        ArrayList<String> arrayList = new ArrayList<>();
        try{
            File file = new File("race.csv");
            InputStreamReader input = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(input);
            String str;
            while ((str = bf.readLine()) != null){
                arrayList.add(str);
            }
            bf.close();
            input.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        int length = arrayList.size();
        int width = arrayList.get(0).split(",").length;



        int array[][] = new int[length][width];
        String allarray[][] = new String[length][width];
        int i=0,j=0;

        //Loop assignment string to allarray
        for (i=0;i<length;i++){
            for(j=0;j<width;j++){
                String s = arrayList.get(i).split(",")[j];
                allarray[i][j] = s;
            }
        }

        //Assign the final score to the last line (in seconds)
        for(i=1;i<length;i++){
            for(j=2;j<width;j++){
                String s = arrayList.get(i).split(",")[j];
                array[i][j] = Integer.parseInt(s);
            }
        }

        //Get the score in minutes
        int minperformance[] = new int[length];
        int secperformance[] = new int[length];
        for(i=1;i<length;i++){
            for(j=0;j<width;j++){
                minperformance[i] = array[i][j] / 60;
                secperformance[i] = array[i][j] % 60;
            }
        }

        //Output format
        for(i=1;i<length;i++){
            for(j=0;j<width;j++){
            }
            System.out.println("Member Id: "+allarray[i][0]+" Name: "+allarray[i][1]+" Time: "+minperformance[i]+"m "+secperformance[i]+"s");
        }
    }
}
