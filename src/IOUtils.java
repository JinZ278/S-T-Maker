import java.util.Scanner;

public class IOUtils {
    static Scanner myScanner = new Scanner(System.in);
    static String input;

    public static String numberInAndBetween(int minimum, int maximum) {
        input = myScanner.nextLine();
        try {
            int placeholder = Integer.parseInt(input);
            if (placeholder < minimum) {
                return "-1";
            } else if (placeholder > maximum) {
                return "-1";
            }
            else {
                return input;
            }
        } catch (Exception e) {
            System.out.print("Please enter a number.\n");
            return "-1";
        }
    }

    public static String userInput() {
        input = myScanner.nextLine();
        if (input.replaceAll("\\s", "").equals("")) {
            return "-1";
        }
        else {
            return input;
        }
    }

    public static boolean errorOutputIfFalse(String input , String errorStatement) {
        if (input.equals("-1")) {
            System.out.print(errorStatement);
            return false;
        }
        else {
            return true;
        }
    }
}
