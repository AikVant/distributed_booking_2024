package booking;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Path;
import java.util.*;

class Menu{

    public Menu(){

    }
    public static void main(String[] args) {
        Accommodation accommodationObj = new Accommodation();
        ReservationDateRange reservationDateRangeObject = new ReservationDateRange();
        AvailabilityOfAccommodations availabilityObject = new AvailabilityOfAccommodations();
        ReadJson readJsonObj = new ReadJson();
        Client clientObj;
        Tenant tenantObj = new Tenant("Georgios Papadopoulos");
        Area areaObject = new Area();
        int Manager = 1;
        int Client = 2;
        boolean flag = false;
        int s = 0;
        Scanner scanner = new Scanner(System.in);

        while (!flag) {
            System.out.println("Welcome! Please choose between Manager and Client mode!");
            System.out.println("1 : MANAGER MODE --- 2 : CLIENT MODE ");
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

        //Scanner scanner1 = new Scanner(System.in);
        int selection;
        if(s == 1){

            for (String string : Arrays.asList("1 : Change your accommodation to available. ",
                    "2 : Add your accommodation.", "3 : To add range of available dates. ",
                    "4 : To view your booked accommodations. ")) {
                System.out.println(string);
            }
            selection = scanner.nextInt();
            switch (selection){
                case 1:
                    System.out.println("--Enter the name of your accommodation : ");
                    String selection2 = scanner.nextLine();
                    reservationDateRangeObject.setAvailable(true);
                    System.out.println("--Your accommodation " + selection2 + "is now available");
                    //reservationDateRangeObject.setTo();
                    //reservationDateRangeObject.setFrom();
                case 2:
                    selection2 = scanner.nextLine();
                    //clientObj = new Client(selection2);
                case 3:
                    //reservationDateRangeObject.setFrom(ReservationDate);
                    //reservationDateRangeObject.setTo();
                case 4:
                    //reservationDateRangeObject.isAvailable()
                    }

            }
        else {
            System.out.println("1:  Filter by area. ");
            System.out.println("2 : Filter by date range. ");
            System.out.println("3 : Filter by number of people. ");
            System.out.println("4 : Filter by price. ");
            System.out.println("5 : Filter by ranking. ");
            System.out.println("6 : Logout. ");
            int selection1 = scanner.nextInt();

            switch (selection1){
                case 1:
                    System.out.println("Area name : ");
                    String selection2 = scanner.nextLine();
                    //areaObject.getCity();

                case 2:
                    //reservationDateRangeObject.setTo();
                    //reservationDateRangeObject.setFrom();
                case 3:
                    //tenantObj.setName();
                case 4:
                case 5:
                    System.out.println("Select ranking from 1 to 5 stars : ");
                    int rank = scanner.nextInt();


            }
        }
        scanner.close();
    }
}
