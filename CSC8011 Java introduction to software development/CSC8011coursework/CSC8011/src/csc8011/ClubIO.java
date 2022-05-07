package csc8011;
import java.util.Scanner;

/*csc8011*/
/*Student ID:200952811*/
/*Student Name:JIAO RUIPENG*/
/*Provide users with the following operations*/
/*Provides the user with an option menu (print to console)*/
/*Enter the name of the club*/
/*Read runner and time information from the current directory named race.csv*/
/*After entering the club name, print the person and record information of the club name on the console*/
/*Print the average time of this club and the complete information of the fastest runner to the console*/
/*At the end of the operation, the user will return to the menu to select other options*/



public class ClubIO {
    public static void main(String[] args){

        System.out.println("----------------------------------------------------------");
        System.out.println("Welcome to Running Club Manager System!");
        System.out.println("Choice a number what you want to do");
        System.out.println("----------------------------------------------------------");
        System.out.println("Please input the club name:");
        Scanner input = new Scanner(System.in);
        String clubname = input.next();

        while(true) {

            //input this sentence into if()↓
            System.out.println("Press Number '1' to check all detail of Club");
            System.out.println("Press Number '2' to check the average of club and the fastman and fast time");
            System.out.println("Press Number '0' to exit");

            //1 Retrieve the contents of the table
            //2 Average and fastest running scores in the club

            //0 exit
            //Enter an option

            int number=0;
            number = input.nextInt();


            switch (number) {
                case 1:

                    System.out.println("Club name: " + clubname);
                    Runner.readcsv();
                    break;
                case 2:
                    Club.AverageFastTime();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Sorry! We do not have this option!");
            }

            System.out.println("Do you want to continue? (If you want to Stop. Please press N or n. If not, Press any key to continue)");
            Scanner sc = new Scanner(System.in);
            String choices = sc.nextLine();
            if((choices.equals("N"))||(choices.equals("n"))){
                break;

            }

        }

    }

}
