import java.util.ArrayList;

public class Paper {

    public String original = "";
    ArrayList<Questions> listOfQuestions = new ArrayList<>();
    ArrayList<Answers> listOfAnswers = new ArrayList<>();

    String userInput = null;
    String amountOfChoices = null;

    public void build() {

        while (true) {
            System.out.print("1) Add a new T/F question.\n");
            System.out.print("2) Add a new multiple-choice question.\n");
            System.out.print("3) Add a new short answer question.\n");
            System.out.print("4) Add a new essay question.\n");
            System.out.print("5) Add a new date question.\n");
            System.out.print("6) Add a new matching question.\n");
            System.out.print("7) Return to previous menu.\n");

            userInput = IOUtils.numberInAndBetween(1, 7);
            if (userInput.equals("7")) {
                break;
            }
            if (IOUtils.errorOutputIfFalse(userInput, "Not a valid command.\n")) {
                addQuestion(userInput);
            }
        }
    }

    protected void addQuestion(String input) {
        switch (input) {
            case "1" -> addPrompt(new TrueFalse());
            case "2" -> addPrompt(new MultipleChoice());
            case "3" -> addPrompt(new ShortAnswer());
            case "4" -> addPrompt(new Essay());
            case "5" -> addPrompt(new ValidDate());
            case "6" -> addPrompt(new Matching());
        }
    }

    protected void addPrompt(Questions questionObject) {
        /*Loops until it gets a non-empty prompt*/
        do {
            System.out.print("Enter the prompt for your " + questionObject.getQuestionType() + " question:\n");
            userInput = IOUtils.userInput();
        } while (!IOUtils.errorOutputIfFalse(userInput, "Prompt cannot be empty.\n"));

        questionObject.setQuestion(userInput);
        addAnswersChoices(questionObject);
    }

    protected void addAnswersChoices(Questions questionObject) {
        String type = questionObject.getQuestionType();

        switch (type) {

            case "short answer", "essay", "date" -> addLimits(questionObject);

            case "matching" -> {
                /*Choosing amount of choices for column 1*/
                do {
                    System.out.print("Enter the number of choices for the first column of your matching question.\n");
                    amountOfChoices = IOUtils.numberInAndBetween(2, 5);
                } while (!IOUtils.errorOutputIfFalse(amountOfChoices, "Number must be between 2 and 5 (inclusive).\n"));

                /*Defaulting the max amount of responses to the amount of choices in the first(left) column*/
                questionObject.setMaxResponses(Integer.parseInt(amountOfChoices));

                /*Adding choices for column 1*/
                questionObject.addAnswerChoices("First Column");
                for (int i = 1; i < (Integer.parseInt(amountOfChoices) + 1); i++) {
                    System.out.print("Enter choice #" + i + ".\n");
                    userInput = IOUtils.userInput();

                    /*Ensuring that answer options cannot be empty*/
                    if (IOUtils.errorOutputIfFalse(userInput,"Answer choice cannot be empty.\n")) {
                        questionObject.addAnswerChoices(userInput);
                    }
                    else {
                        i -= 1;
                    }
                }

                /*Choosing amount of choices for column 2*/
                do {
                    System.out.print("Enter the number of choices for the second column of your matching question.\n");
                    amountOfChoices = IOUtils.numberInAndBetween(2, 5);
                } while (!IOUtils.errorOutputIfFalse(amountOfChoices, "Number must be between 2 and 5 (inclusive).\n"));

                /*Adding choices for column 2*/
                questionObject.addAnswerChoices("Second Column");
                for (int i = 1; i < (Integer.parseInt(amountOfChoices) + 1); i++) {
                    System.out.print("Enter choice #" + i + ".\n");
                    userInput = IOUtils.userInput();

                    /*Ensuring that answer options cannot be empty*/
                    if (IOUtils.errorOutputIfFalse(userInput,"Answer choice cannot be empty.\n")) {
                        questionObject.addAnswerChoices(userInput);
                    }
                    else {
                        i -= 1;
                    }
                }
                addLimits(questionObject);
            }

            case "True/False" -> {
                questionObject.addAnswerChoices("T");
                questionObject.addAnswerChoices("F");
                addLimits(questionObject);
            }

            case "multiple choice" -> {
                /*Error checking for invalid choice amounts as well as limiting amount of choices between 2 and 8*/
                do {
                    System.out.print("Enter the number of choices for your multiple-choice question.\n");
                    amountOfChoices = IOUtils.numberInAndBetween(2, 8);
                } while (!IOUtils.errorOutputIfFalse(amountOfChoices, "Amount of choices must be between 2 and 8 (inclusive).\n"));

                String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

                /*Adding answer choices based on user input*/
                for (int i = 1; i < (Integer.parseInt(amountOfChoices) + 1); i++) {
                    System.out.print("Enter choice " + alphabet.charAt(i-1) + ".\n");
                    userInput = IOUtils.userInput();

                    /*Ensuring that answer options cannot be empty*/
                    if (IOUtils.errorOutputIfFalse(userInput,"Answer choice cannot be empty.\n")) {
                        questionObject.addAnswerChoices(userInput);
                    }
                    else {
                        i -= 1;
                    }
                }
                addLimits(questionObject);
            }
        }
        System.out.print("\n");
    }

    protected void addLimits(Questions questionObject) {
        String type = questionObject.getQuestionType();
        switch (type) {
            case "matching", "True/False" -> addCorrectAnswer(questionObject);
            case "date" -> {
                do {
                    System.out.print("What is max amount of answers(1-5) a user can submit?\n");
                    userInput = IOUtils.numberInAndBetween(1,5);
                } while (!IOUtils.errorOutputIfFalse(userInput, "Number must be between 1 and 5 (inclusive).\n"));
                questionObject.setMaxResponses(Integer.parseInt(userInput));

                addCorrectAnswer(questionObject);
            }
            case "short answer" -> {
                do {
                    System.out.print("What is max amount of paragraphs(1-5) a user can submit?\n");
                    userInput = IOUtils.numberInAndBetween(1,5);
                } while (!IOUtils.errorOutputIfFalse(userInput, "Number must be between 1 and 5 (inclusive).\n"));
                questionObject.setMaxResponses(Integer.parseInt(userInput));

                do {
                    System.out.print("What is max amount of characters (10-5000) that a user can submit?\n");
                    userInput = IOUtils.numberInAndBetween(10,5000);
                } while (!IOUtils.errorOutputIfFalse(userInput, "Number must be between 10 and 5000 (inclusive).\n"));
                questionObject.setCharLimit(Integer.parseInt(userInput));

                addCorrectAnswer(questionObject);
            }

            case "essay" -> {
                do {
                    System.out.print("What is max amount of paragraphs(1-5) a user can submit?\n");
                    userInput = IOUtils.numberInAndBetween(1,5);
                } while (!IOUtils.errorOutputIfFalse(userInput, "Number must be between 1 and 5 (inclusive).\n"));
                questionObject.setMaxResponses(Integer.parseInt(userInput));

                do {
                    System.out.print("What is max amount of characters per paragraph (10-5000) that a user can submit?\n");
                    userInput = IOUtils.numberInAndBetween(10,5000);
                } while (!IOUtils.errorOutputIfFalse(userInput, "Number must be between 10 and 5000 (inclusive).\n"));
                questionObject.setCharLimit(Integer.parseInt(userInput));

                addCorrectAnswer(questionObject);
            }

            case "multiple choice" -> {
                do {
                    System.out.print("What is max amount of choices(1-"+(questionObject.answerChoices.size()-1)+") a user can choose?\n");
                    userInput = IOUtils.numberInAndBetween(1,(questionObject.answerChoices.size()-1));
                } while (!IOUtils.errorOutputIfFalse(userInput, "Number must be between 1 and "+(questionObject.answerChoices.size()-1)+" (inclusive).\n"));
                questionObject.setMaxResponses(Integer.parseInt(userInput));

                addCorrectAnswer(questionObject);
            }
        }
    }

    protected void addCorrectAnswer(Questions questionObject) {
        addQuestionToPaper(questionObject);
    }

    protected void addQuestionToPaper(Questions questionObject) {
        listOfQuestions.add(questionObject);
        listOfAnswers.add(new Answers());
        System.out.print("Question added successfully!\n");
    }

    public boolean isTaken() {
        return listOfAnswers.get(0).response.size() != 0;
    }


}
