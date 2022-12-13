import java.io.Serializable;
import java.util.ArrayList;

abstract class Questions implements Serializable {
    protected String question;
    protected ArrayList<String> answerChoices = new ArrayList<>();
    String questionType;
    private int maxResponses = 1;
    private int charLimit = 500;

    protected int getMaxResponses() {
        return maxResponses;
    }

    protected int getCharLimit() {
        return this.charLimit;
    }

    public String getQuestionType() {
        return this.questionType;
    }

    protected String getQuestion() {
        return this.question;
    }

    protected void setMaxResponses(int maxResponses) {
        this.maxResponses = maxResponses;
    }

    protected void setCharLimit(int maxCharLimit) {
        this.charLimit = maxCharLimit;
    }

    protected void setQuestion(String userInput) {
        this.question = userInput;
    }

    protected void displayAnswersChoices() {
        System.out.print("\n");
    }

    protected void addAnswerChoices(String userInput) {
        this.answerChoices.add(userInput);
    }

    protected void changeAnswerChoices(String userInput, int position) {
        this.answerChoices.set(position, userInput);
    }

    abstract boolean validate(Answers listOfAnswers, String userInput);


}
