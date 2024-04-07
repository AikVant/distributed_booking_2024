package booking;

import java.util.Scanner;

    public class Menu {
        private static final int MANAGER_MODE = 1;
        private static final int CLIENT_MODE = 2;
        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            int modeSelection = selectMode();

            switch (modeSelection) {
                case MANAGER_MODE:
                    managerMode();
                    break;
                case CLIENT_MODE:
                    clientMode();
                    break;
                default:
                    System.out.println("Invalid selection. Exiting...");
                    break;
            }
            scanner.close();
        }

        private static int selectMode() {
            int selection;
            do {
                System.out.println("Welcome! Please choose between Manager and Client mode:");
                System.out.println("1: Manager Mode");
                System.out.println("2: Client Mode");
                selection = scanner.nextInt();
            } while (selection != MANAGER_MODE && selection != CLIENT_MODE);
            return selection;
        }

        private static void managerMode() {
            System.out.println("You are now in Manager Mode!");
            int selection;
            do {
                displayManagerMenu();
                selection = scanner.nextInt();
                handleManagerSelection(selection);
            } while (selection != 0);
        }

        private static void displayManagerMenu() {
            System.out.println("Manager Menu:");
            System.out.println("1: Change accommodation availability");
            System.out.println("2: Add accommodation");
            System.out.println("3: Add range of available dates");
            System.out.println("4: View booked accommodations");
            System.out.println("0: Exit Manager Mode");
        }

        private static void handleManagerSelection(int selection) {
            switch (selection) {
                case 1:
                    changeAccommodationAvailability();
                    break;
                case 2:
                    addAccommodation();
                    break;
                case 3:
                    addAvailableDates();
                    break;
                case 4:
                    viewBookedAccommodations();
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }

        private static void changeAccommodationAvailability() {
            // Implement changing accommodation availability
        }

        private static void addAccommodation() {
            // Implement adding accommodation
        }

        private static void addAvailableDates() {
            // Implement adding available dates
        }

        private static void viewBookedAccommodations() {
            // Implement viewing booked accommodations
        }

        private static void clientMode() {
            System.out.println("You are now in Client Mode!");
            int selection;
            do {
                displayClientMenu();
                selection = scanner.nextInt();
                handleClientSelection(selection);
            } while (selection != 0);
        }

        private static void displayClientMenu() {
            System.out.println("Client Menu:");
            System.out.println("1: Filter by area");
            System.out.println("2: Filter by date range");
            System.out.println("3: Filter by number of people");
            System.out.println("4: Filter by price");
            System.out.println("5: Filter by ranking");
            System.out.println("0: Logout");
        }

        private static void handleClientSelection(int selection) {
            switch (selection) {
                case 1:
                    filterByArea();
                    break;
                case 2:
                    filterByDateRange();
                    break;
                case 3:
                    filterByNumberOfPeople();
                    break;
                case 4:
                    filterByPrice();
                    break;
                case 5:
                    filterByRanking();
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }

        private static void filterByArea() {
            // Implement filtering by area
        }

        private static void filterByDateRange() {
            // Implement filtering by date range
        }

        private static void filterByNumberOfPeople() {
            // Implement filtering by number of people
        }

        private static void filterByPrice() {
            // Implement filtering by price
        }

        private static void filterByRanking() {
            // Implement filtering by ranking
        }
    }


