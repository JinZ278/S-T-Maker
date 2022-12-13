import java.io.Serializable;

public class ShortAnswer extends Questions implements Serializable {

    ShortAnswer(){
        super();
        this.questionType = "short answer";
    }

    @Override
    boolean validate(Answers listOfAnswers, String userInput) {

        if (userInput.equals("-1")) {
            System.out.print("Response cannot be empty.\n");
            return false;
        }

        else if (userInput.length() > this.getCharLimit()) {
            System.out.print("Too much characters, the character limit is "+this.getCharLimit()+".\n");
            return false;
        }
        else {
            return true;
        }
    }

}
