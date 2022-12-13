import java.io.Serializable;
import java.util.HashMap;

public class ValidDate extends Questions  implements Serializable {

    ValidDate(){
        super();
        this.questionType = "date";
    }

    @Override
    public void displayAnswersChoices() {
        System.out.print("A date should be entered in the following format: YYYY-MM-DD\n");
    }

    @Override
    boolean validate(Answers listOfAnswers, String userInput) {
        String[] date = userInput.split("-");
        HashMap<Integer, Integer> monthDays = new HashMap<>();
        int year, month, day;


        /*Ensuring that the three argument's length is correct yyyy mm dd string format*/
        try {
            if (date[0].length() != 4) {
                System.out.print("Incorrect year format.\n");
                return false;
            }
            if (date[1].length() != 2) {
                System.out.print("Incorrect month format.\n");
                return false;
            }
            if (date[2].length() != 2) {
                System.out.print("Incorrect day format.\n");
                return false;
            }
        } catch (Exception e) {
            System.out.print("Not in correct format.\n");
            return false;
        }

        /*Ensuring that the three arguments are parsable into int.*/
        try {
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[2]);
        } catch (NumberFormatException e) {
            System.out.print("Invalid numbers.\n");
            return false;
        }

        /*Setting up my calendar with the correct amount of days based on the year.*/
        monthDays.put(1, 31);
        if (year%4 == 0 && year%400 == 0 && year%100 != 0) { monthDays.put(2,29); } else {monthDays.put(2,28);}
        monthDays.put(3, 31);
        monthDays.put(4, 30);
        monthDays.put(5, 31);
        monthDays.put(6, 30);
        monthDays.put(7, 31);
        monthDays.put(8, 31);
        monthDays.put(9, 30);
        monthDays.put(10, 31);
        monthDays.put(11, 30);
        monthDays.put(12, 31);

        /*Checking year, month, and day values*/
        if (date.length == 3) {
            if (year >= 0 & year <= 9999) {
                if (month > 0 && month < 13) {
                    if (listOfAnswers.response.contains(userInput)) {
                        System.out.print("Already contains a similar response.\n");
                        return false;
                    }
                    else {
                        if (day > 0 && day <= monthDays.get(month)) {
                            return true;
                        }
                        else {
                            System.out.print("Invalid day.\n");
                            return false;
                        }
                    }
                }
                else {
                    System.out.print("Invalid month.\n");
                    return false;
                }
            }
            else {
                System.out.print("Invalid Year.\n");
                return false;
            }
        }
        System.out.print("Numbers are not within the limits (Year 0000-9999) (Month 01-12) (Day 01-31).\n");
        return false;
    }

}
