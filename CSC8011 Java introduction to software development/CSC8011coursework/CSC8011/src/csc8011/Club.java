package csc8011;
import java.io.*;
import java.util.ArrayList;
import java.io.File;

/*This class is used to store and retrieve
the name of the club and the list of running members
*/

/*Including find and return methods*/
/*Show and record the fastest runner*/
/*Record the average running time in the club and output it(In Second)*/


public class Club {
    public static void AverageFastTime(){
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

        //Handle strings in ArrayList (Character to Int)

        int length = arrayList.size();
        int width = arrayList.get(0).split(",").length;
        int array[][] = new int[length][width];
        String allarray[][] = new String[length][width];
        int i=0,j=0;

        //All data store allarray[][]
        for (i=0;i<length;i++){
            for(j=0;j<width;j++){
                String s = arrayList.get(i).split(",")[j];
                allarray[i][j] = s;
            }
        }
        //Run results store array[][]
        for(i=1;i<length;i++){
            for(j=2;j<width;j++){
                String s=arrayList.get(i).split(",")[j];
                array[i][j] = Integer.parseInt(s);
            }
        }

        //Find the fastest runner(Bubble sorting)
        for(i=1;i<length;i++){
            for(j=2;j<width;j++){
                String s = arrayList.get(i).split(",")[j];
                array[i][j] = Integer.parseInt(s);
            }
        }
        int performance[] = new int[length];
        for(i=1;i<length;i++){
            for(j=2;j<width;j++){
                performance[i] = array[i][j];
            }
        }


        //Bubble sort to find the lowest running score
        int temp=0;
        for(i=1;i<length-1;i++){
            for(j=1 ; j<length-i;j++)
            if(performance[j]>performance[j+1]){
                temp = performance[j];
                performance[j] = performance[j+1];
                performance[j+1] = temp;
            }
        }


        /*Find the column of the fastest person and
         output the information and time of the fastest person (in minutes)*/
        int secfastman = 0, minfastman = 0;
        int runnerrow = 0;
        minfastman = performance[1] / 60;
        secfastman = performance[1] % 60;
        for (i=1;i<length;i++){
            for(j=2;j<width;j++){
                if(performance[1]==array[i][j]){
                    runnerrow=i;
                }
            }
        }
        System.out.println("Fastest runner: "+allarray[runnerrow][1]+" ("+allarray[runnerrow][0]+"), "+minfastman+"m "+secfastman+"s");

        //求得以分钟数的表示的成绩
        int minperformance[] = new int[length];
        int secperformance[] = new int[length];
        for(i=1;i<length;i++){
            for(j=0;j<width;j++){
                minperformance[i] = array[i][j] / 60;
                secperformance[i] = array[i][j] % 60;
            }
        }

        //Calculate average score
        int sum = 0, Aver=0;
        int Averm=0,Avers=0;

        for(i=1;i<length;i++){
            for(j=2;j<width;j++){
                sum = sum + array[i][j];
                Aver = sum / (length-1);
            }
        }
        Averm = Aver / 60;
        Avers = Aver % 60;

        System.out.println("Average time: "+ Averm + "m "+ Avers +"s");
    }
}
