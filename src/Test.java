import java.io.Serializable;
import java.util.ArrayList;

public class Test extends Paper implements Serializable {

    ArrayList<Answers> listOfCorrectAnswers = new ArrayList<>();

    public void menu() {
        System.out.print("\n-------------------------------");
        System.out.print("\n-----Test---Maker---Menu-------");
        System.out.print("\n-------------------------------\n");
        System.out.print("1) Create a new Test.\n");
        System.out.print("2) Display an existing Test without correct Answers.\n");
        System.out.print("3) Display an existing Test with correct Answers.\n");
        System.out.print("4) Load an existing Test.\n");
        System.out.print("5) Save the current Test.\n");
        System.out.print("6) Take the current Test.\n");
        System.out.print("7) Modify the current Test\n");
        System.out.print("8) Tabulate a Test\n");
        System.out.print("9) Grade a Test\n");
        System.out.print("10) Return to previous menu\n");
        System.out.print("What would you like to do?\n");
    }

    @Override
    protected void addCorrectAnswer(Questions questionObject) {
        Answers answers = new Answers();

        switch (questionObject.getQuestionType()) {
            case "short answer" -> {
                for (int i = 0; i < questionObject.getMaxResponses(); i++) {
                    do {
                        System.out.print("Enter correct answer(s)\n");
                        userInput = IOUtils.userInput();
                        if (!questionObject.validate(answers, userInput)) {
                            userInput = "-1";
                        }
                    } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid answer.\n"));
                    answers.addResponse(userInput);
                }
                listOfCorrectAnswers.add(answers);
                addQuestionToPaper(questionObject);
            }
            case "essay" -> {
                listOfCorrectAnswers.add(answers);
                addQuestionToPaper(questionObject);
            }
            case "date" -> {
                for (int i = 0; i < questionObject.getMaxResponses(); i++) {
                    do {
                        System.out.print("Enter correct date(s) (YYYY-MM-DD)\n");
                        userInput = IOUtils.userInput();
                        if (!questionObject.validate(answers, userInput)) {
                            userInput = "-1";
                        }
                    } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid answer.\n"));
                    answers.addResponse(userInput);
                }
                listOfCorrectAnswers.add(answers);
                addQuestionToPaper(questionObject);
            }
            case "matching" -> {
                for (int i = 0; i < questionObject.getMaxResponses(); i++) {
                    do {
                        System.out.print("Enter correct choice(s)\n");
                        userInput = IOUtils.userInput();
                        if (!questionObject.validate(answers, userInput)) {
                            userInput = "-1";
                        }
                    } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid option.\n"));
                    answers.addResponse(userInput);
                }
                listOfCorrectAnswers.add(answers);
                addQuestionToPaper(questionObject);
            }
            case "multiple choice" -> {
                String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                System.out.print("Possible answer choices\n");
                for (int i = 0; i < questionObject.answerChoices.size(); i++) {
                    System.out.print(alphabet.charAt(i) + ") " + questionObject.answerChoices.get(i) + "\n");
                }
                for (int i = 0; i < questionObject.getMaxResponses(); i++) {
                    do {
                        System.out.print("Enter correct choice(s) (Enter the letter that represents the choice)\n");
                        userInput = IOUtils.userInput();
                        if (!questionObject.validate(answers, userInput)) {
                            userInput = "-1";
                        }
                    } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid option.\n"));

                    answers.addResponse(userInput);
                }
                listOfCorrectAnswers.add(answers);
                addQuestionToPaper(questionObject);
            }
            default -> {
                System.out.print("Possible answer choices\n");
                for (int i = 0; i < questionObject.answerChoices.size(); i++) {
                    System.out.print((i + 1) + ") " + questionObject.answerChoices.get(i) + "\n");
                }
                for (int i = 0; i < questionObject.getMaxResponses(); i++) {
                    do {
                        System.out.print("Enter correct choice(s) (Enter the number that represents the choice)\n");
                        userInput = IOUtils.numberInAndBetween(1, questionObject.answerChoices.size());
                    } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid option.\n"));
                    userInput = questionObject.answerChoices.get(Integer.parseInt(userInput)-1);

                    answers.addResponse(userInput);
                }
                listOfCorrectAnswers.add(answers);
                addQuestionToPaper(questionObject);
            }
        }
    }
}
