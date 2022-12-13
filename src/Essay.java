import java.io.Serializable;

public class Essay extends Questions implements Serializable {

    Essay(){
        super();
        this.questionType = "essay";
    }

    @Override
    boolean validate(Answers listOfAnswers, String userInput) {
        if (userInput.equals("-1")) {
            System.out.print("Response cannot be empty.\n");
            return false;
        }

        if (userInput.length() > this.getCharLimit()) {
            System.out.print("Too much characters, the character limit is "+this.getCharLimit()+".\n");
            return false;
        }
        else {
            return true;
        }
    }

}
