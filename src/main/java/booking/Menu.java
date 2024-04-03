package booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Menu{

    public Menu(){

    }
    public static void main(String[] args) {
        Accommodation accommodationObj = new Accommodation();
        ReservationDateRange reservationDateRangeObject = new ReservationDateRange();
        AvailabilityOfAccommodations availabilityObject = new AvailabilityOfAccommodations();
        ReadJson readJsonObj = new ReadJson();
        Tenant tenantObj = new Tenant("Georgios Papadopoulos");
        Area areaObject = new Area();
        int Manager = 1;
        int Client = 2;
        boolean flag = false;
        int s = 0;
        Scanner scanner = new Scanner(System.in);
        while (!flag) {
            System.out.println("Choose between Manager and Client mode!");
            System.out.println("For Manager mode press 1 , for Client mode press 2");
            int selection = scanner.nextInt();
            if(selection == Manager){
                System.out.println("You are now ready to perform as a Manager!");
                s = selection;
                flag = true;
            } else if (selection == 2) {
                System.out.println("You are now ready to perform as a Client!");
                s = selection;
                flag = true;
            }else{
                System.out.println("Invalid selection");
            }

        }
        Scanner scanner1 = new Scanner(System.in);
        int selection;
        if(s == 1){

            System.out.println("To change your accommodations to available press 1 :");
            System.out.println("To add your accommodation press 2 :");
            System.out.println("To add range of available dates press 3 :");
            System.out.println("To view your booked accommodations press 4 :");
            selection = scanner1.nextInt();
            switch (selection){
                case 1:
                    System.out.println("Enter the name of your accommodation : ");
                    String selection2 = scanner1.nextLine();
                    reservationDateRangeObject.setAvailable(true);
                    System.out.println("Your accommodation " + selection2 + "is now available");
                    //reservationDateRangeObject.setTo();
                    //reservationDateRangeObject.setFrom();
                case 2:
                    accommodationObj.createAccommodation();
                case 3:
                    //reservationDateRangeObject.setFrom(ReservationDate);
                    //reservationDateRangeObject.setTo();
                case 4:
                    //reservationDateRangeObject.isAvailable()
                    }

            }
        else {
            System.out.println("Filter by wished area : 1");
            System.out.println("Filter by wished date range : 2");
            System.out.println("Filter by wished number of people : 3");
            System.out.println("Filter by wished price : 4");
            System.out.println("Filter by wished ranking : 5");
            int selection1 = scanner1.nextInt();

            switch (selection1){
                case 1:
                    System.out.println("Area name : ");
                    String selection2 = scanner1.nextLine();
                    //areaObject.getCity();

                case 2:
                    //reservationDateRangeObject.setTo();
                    //reservationDateRangeObject.setFrom();
                case 3:
                    //tenantObj.setName();
                case 4:
                case 5:
                    System.out.println("Select raking from 1 to 5 stars : ");

            }
        }
        Accommodation e =  new Accommodation("Bed and Breakfast" , "202" , 3 , new Area("Corfu"), 2 , 10 , new Image("BBImage") , true);

    }
}
