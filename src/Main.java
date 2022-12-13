
public class Main {

    public static void main(String[] args) {
        String userInput;

        System.out.println("Hello and welcome to the Main Menu!\n");
        System.out.println("Would you like to: \n");
        System.out.print("1: Start the survey maker\n");
        System.out.print("2: Start the test maker\n");
        System.out.print("3: Quit\n");

        /*Loops and outputs error statement until number is between 1 and 3.*/
        do {
            userInput = IOUtils.numberInAndBetween(1, 3);
        } while (!IOUtils.errorOutputIfFalse(userInput, "Not a valid command.\n"));

        chooseMenu(userInput);
    }

    private static void chooseMenu(String userInput) {
        switch (userInput) {
            case "1" -> {
                System.out.print("Hello and welcome to the Survey Maker!\n");
                SurveyMenu surveyMenu = new SurveyMenu();
                surveyMenu.menu();
                main(null);
            }
            case "2" -> {
                System.out.print("Hello and welcome to the Test Maker!\n");
                TestMenu testMenu = new TestMenu();
                testMenu.menu();
                main(null);
            }
            case "3" -> System.exit(0);
        }
    }
}