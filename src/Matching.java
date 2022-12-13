import java.io.Serializable;

public class Matching extends Questions implements Serializable {

    Matching() {
        super();
        this.questionType = "matching";
    }

    @Override
    public void displayAnswersChoices() {
        int first = 1, second = 0;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String placeholder;

        /*Finds how many choices are between the first and second column*/
        for (int i = 1; i < this.answerChoices.size(); i ++) {
            if (this.answerChoices.get(i).equals("Second Column")) {
                second = i;
                break;
            }
        }

        /*Prints first and second column up to minimum amount of choices between both columns */
        for (int i = 1; i < second; i++ ) {
            if (i+second == this.answerChoices.size()) {
                break;
            }
            first = i;
            placeholder = String.format("%c) %-15s "+ i +") %s\n", alphabet.charAt(i-1), this.answerChoices.get(i), this.answerChoices.get(i+second));
            System.out.print(placeholder);
        }

        /*Prints the rest of the first column if it's longer than second column.*/
        while (second != first+1) {
            first += 1;
            placeholder = String.format("%c) %s\n", alphabet.charAt(first), this.answerChoices.get(first));
            System.out.print(placeholder);
        }

        /*Prints the rest of the second column if it's longer than first column.*/
        while (first+second < this.answerChoices.size()-1) {
            first += 1;
            placeholder = String.format("%20o) %s\n", first, this.answerChoices.get(first+second));
            System.out.print(placeholder);
        }
    }

    @Override
    boolean validate(Answers listOfAnswers, String userInput) {
        int first = 0, second = this.answerChoices.size();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "123456789";

        if (userInput.equals("-1")) {
            System.out.print("Response cannot be empty.\n");
            return false;
        }

        for (int i = 0; i <= this.answerChoices.size(); i++) {
            if (this.answerChoices.get(i).equals("Second Column")) {
                first = i-1;
                break;
            }
        }
        second = second-first-2;

        /*Checks first character is within the limits of the answer in first column, then the second in second column, then for already added similar input*/
        if (userInput.length() == 2) {
            if (alphabet.indexOf(userInput.charAt(0)) != -1 && alphabet.indexOf(userInput.charAt(0)) <= first-1) {
                if (numbers.indexOf(userInput.charAt(1)) != -1 && numbers.indexOf(userInput.charAt(1)) <= second-1) {
                    if (listOfAnswers.response.contains(userInput)) {
                        System.out.print("Already chose this answer.\n");
                        return false;
                    } else {return true;}
                } else {System.out.print("Invalid number.\n"); return false;}
            } else {System.out.print("Invalid letter.\n"); return false;}
        } else {System.out.print("Invalid input.\n"); return false;}
    }
}
