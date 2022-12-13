import java.io.Serializable;

public class TrueFalse extends Questions  implements Serializable {

    TrueFalse(){
        super();
        this.questionType = "True/False";
    }

    @Override
    public void displayAnswersChoices() {
        System.out.print("T/F\n");
    }

    @Override
    boolean validate(Answers listOfAnswers, String userInput) {
        switch (userInput) {
            case "T":
            case "F":
                return true;
            default:
                System.out.print("Invalid input.\n");
                return false;
        }
    }

}
