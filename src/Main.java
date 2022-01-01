import java.util.Scanner;
    public class Main {
        public static final int MAIN_MENU_CREAT_ACCOUNT=1;
        public static final int MAIN_MENU_LOGIN=2;
        public static final int MAIN_MENU_EXIT=3;
        public static final int SECONDARY_MENU_PUBLISHING_NEW_PROPERTY=1;
        public static final int SECONDARY_MENU_REMOVE_PROPERTY_ADVERTISING=2;
        public static final int SECONDARY_MENU_VIEW_ALL_ASSETS_SYSTEM=3;
        public static final int SECONDARY_MENU_VIEW_ALL_PROPERTIES_USER=4;
        public static final int SECONDARY_MENU_PROPERTY_SEARCH_BY_PARAMETERS=5;
        public static final int SECONDARY_MENU_LOGOUT_AND_BACK_TO_MAIN_MENU=6;

        public static void main(String[] args) {
            RealEstate realEstate = new RealEstate();
            mainMenuAct(realEstate);
        }

        public static void mainMenuAct(RealEstate realEstate) {
            int choice = 0;
            while (choice != MAIN_MENU_EXIT) {
                choice = getChoiceMainMenu();
                switch (choice) {
                    case MAIN_MENU_CREAT_ACCOUNT:
                        realEstate.createUser();
                        break;
                    case MAIN_MENU_LOGIN:
                        User user = realEstate.login();
                        if (user != null) {
                            secondaryMenuOptionsAct(realEstate, user);
                        } else {
                            System.out.println("Invalid login information!");
                        }
                        break;
                }
            }

        }


        public static int getChoiceMainMenu() {
            int choice;
            System.out.println("1- Creat account"
                    + "\n2- Login\n3- Exit");
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
            }
            while (choice < MAIN_MENU_CREAT_ACCOUNT || choice > MAIN_MENU_EXIT);
            return choice;
        }

        public static int getChoiceSecondaryMenu() {
            int choice;
            Scanner scanner = new Scanner(System.in);
            System.out.println("1-Publishing a new property" +
                    "\n2-Remove property advertising"
                    + "\n3-View all assets in the system" +
                    "\n4-View properties posted by the user" +
                    "\n5-Property search by parameters" +
                    "\n6-logout and Back to the main menu");
            do {
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
            } while (choice < SECONDARY_MENU_PUBLISHING_NEW_PROPERTY || choice > SECONDARY_MENU_LOGOUT_AND_BACK_TO_MAIN_MENU);
            return choice;
        }

        public static void secondaryMenuOptionsAct(RealEstate realEstate, User user) {
            int choice = 0;
            while (choice != SECONDARY_MENU_LOGOUT_AND_BACK_TO_MAIN_MENU) {
                choice = getChoiceSecondaryMenu();
                switch (choice) {
                    case SECONDARY_MENU_PUBLISHING_NEW_PROPERTY:
                        realEstate.postNewProperty(user);
                        break;
                    case SECONDARY_MENU_REMOVE_PROPERTY_ADVERTISING:
                        realEstate.removeProperty(user);
                        break;
                    case SECONDARY_MENU_VIEW_ALL_ASSETS_SYSTEM:
                        realEstate.printAllProperties();
                        break;
                    case SECONDARY_MENU_VIEW_ALL_PROPERTIES_USER:
                        realEstate.printAllUserProperties(user);
                        break;
                    case SECONDARY_MENU_PROPERTY_SEARCH_BY_PARAMETERS:
                        realEstate.search();
                        break;
                }
            }
        }

    }
