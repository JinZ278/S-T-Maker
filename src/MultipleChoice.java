import java.io.Serializable;

public class MultipleChoice extends Questions implements Serializable {

    MultipleChoice(){
        super();
        this.questionType = "multiple choice";
    }

    @Override
    boolean validate(Answers listOfAnswers, String userInput) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if (userInput.equals("-1")) {
            System.out.print("Response cannot be empty.\n");
            return false;
        }

        /*Checks that the answer choice is within alphabet*/
        if (alphabet.contains(userInput) && alphabet.indexOf(userInput) < this.answerChoices.size()) {
            if (listOfAnswers.response.contains(userInput)) {
                System.out.print("You already chose this answer.\n");
                return false;
            }
            else {
                return true;
            }
        }
        else {
            System.out.print("Invalid input.\n");
            return false;
        }
    }

    @Override
    public void displayAnswersChoices() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < this.answerChoices.size(); i++) {
            System.out.print(alphabet.charAt(i) +") "+ this.answerChoices.get(i)+"      ");
        }
        System.out.print("\n");
    }

}
