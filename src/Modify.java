
public class Modify {

    String userInput = null;
    int questionNumber;
    public void changeSurvey(Survey survey) {

        if (survey.isTaken()) {
            System.out.print("Survey was already taken.\n");
        } else {
            while (true) {
                System.out.print("Which question would you like to modify? (0 to quit)\n");
                System.out.print("Survey has " + survey.listOfQuestions.size() + " questions.\n");

                do {
                    userInput = IOUtils.numberInAndBetween(0, survey.listOfQuestions.size());
                } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid input"));

                if (userInput.equals("0")) {
                    System.out.print("Returning to main menu.\n");
                    break;
                }

                /*Subtracting one since arrays index starts at 0*/
                questionNumber = Integer.parseInt(userInput) - 1;
                changePrompt(survey, questionNumber);
                changeChoices(survey, questionNumber);
                survey.original = "";
            }
        }
    }

    public void changeTest(Test test) {
        if (test.listOfQuestions.size() == 0) {
            System.out.print("You must have a test loaded in order to modify it.\n");
        } else if (test.isTaken()) {
            System.out.print("Test was already taken.\n");
        } else {
            while (true) {
                System.out.print("Which question would you like to modify? (0 to quit)\n");
                System.out.print("Test has " + test.listOfQuestions.size() + " questions.\n");

                do {
                    userInput = IOUtils.numberInAndBetween(0, test.listOfQuestions.size());
                } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid input"));

                if (userInput.equals("0")) {
                    System.out.print("Returning to main menu.\n");
                    break;
                }

                /*Subtracting one since arrays index starts at 0*/
                questionNumber = Integer.parseInt(userInput) - 1;
                changePrompt(test, questionNumber);
                changeChoices(test, questionNumber);
                changeCorrectAnswer(test, questionNumber);
                test.original = "";
            }
        }
    }

    public void changePrompt(Paper paper, int questionNumber) {
        System.out.print("Prompt: " + paper.listOfQuestions.get(questionNumber).getQuestion()+"\n");
        System.out.print("Would you like to modify the prompt? (Yes or No)\n");
        userInput = IOUtils.userInput();

        if (userInput.equals("Yes")) {

            System.out.print("Enter a new prompt:\n");
            do {
                userInput = IOUtils.userInput();
            } while(!IOUtils.errorOutputIfFalse(userInput, "Invalid prompt.\n"));

            paper.listOfQuestions.get(questionNumber).setQuestion(userInput);
        }
    }

    private void changeCorrectAnswer(Test test, int questionNumber) {
        Answers answerObj = test.listOfCorrectAnswers.get(questionNumber);
        String answer;

        switch (test.listOfQuestions.get(questionNumber).getQuestionType()) {
            case "essay" -> System.out.print("There are no correct answers listed for question.\n");
            case "True/False" -> {
                System.out.print("Correct Answer: \n");
                System.out.print(answerObj.response.get(questionNumber) + "\n");
                System.out.print("Would you like to modify the correct answer(s)? (Yes or No)\n");
                userInput = IOUtils.userInput();
                if (userInput.equals("Yes")) {

                    test.listOfQuestions.get(questionNumber).displayAnswersChoices();

                    do {
                        System.out.print("Enter the new correct answer(s):\n");
                        userInput = IOUtils.userInput();
                    } while (!test.listOfQuestions.get(questionNumber).validate(test.listOfAnswers.get(questionNumber), userInput));

                    answerObj.response.set(0, userInput);
                }
            }
            case "short answer" -> {
                System.out.print("Correct Answer(s): \n");
                for (int i = 0; i < answerObj.response.size(); i++) {
                    System.out.print((i+1)+") "+ answerObj.response.get(i) + "\n");
                }
                System.out.print("Would you like to modify the correct answer(s)? (Yes or No)\n");
                userInput = IOUtils.userInput();

                if (userInput.equals("Yes")) {
                    System.out.print("Which correct answer would you like to modify?\n");
                    do {
                        answer = IOUtils.numberInAndBetween(1, test.listOfCorrectAnswers.get(questionNumber).response.size());
                    } while(!IOUtils.errorOutputIfFalse(answer, "Invalid choice.\n"));

                    System.out.print("Enter the new correct answer:\n");
                    do {
                        userInput = IOUtils.userInput();
                        if (!test.listOfQuestions.get(questionNumber).validate(test.listOfAnswers.get(questionNumber), userInput)) {
                            userInput = "-1";
                        }
                    } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid answer.\n"));

                    answerObj.response.set(Integer.parseInt(answer)-1, userInput);
                }
            }
            case "date"  -> {
                System.out.print("Correct Answer(s): \n");
                for (int i = 0; i < answerObj.response.size(); i++) {
                    System.out.print((i+1)+") "+ answerObj.response.get(i) + "\n");
                }
                System.out.print("Would you like to modify the correct answer(s)? (Yes or No)\n");
                userInput = IOUtils.userInput();

                if (userInput.equals("Yes")) {
                    System.out.print("Which correct answer would you like to modify?\n");
                    do {
                        answer = IOUtils.numberInAndBetween(1, test.listOfCorrectAnswers.get(questionNumber).response.size());
                    } while(!IOUtils.errorOutputIfFalse(answer, "Invalid choice.\n"));

                    System.out.print("Enter the new correct answer (YYYY-MM-DD):\n");
                    do {
                        userInput = IOUtils.userInput();
                        if (!test.listOfQuestions.get(questionNumber).validate(test.listOfAnswers.get(questionNumber), userInput)) {
                            userInput = "-1";
                        }
                    } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid answer.\n"));

                    answerObj.response.set(Integer.parseInt(answer), userInput);
                }
            }
            case "matching" -> {
                System.out.print("Correct Answer(s): \n");
                for (int i = 0; i < answerObj.response.size(); i++) {
                    System.out.print((i+1)+") "+ answerObj.response.get(i) + "\n");
                }

                System.out.print("Would you like to modify the correct answer(s)? (Yes or No)\n");
                userInput = IOUtils.userInput();

                if (userInput.equals("Yes")) {
                    System.out.print("Which correct answer would you like to modify?\n");
                    do {
                        answer = IOUtils.numberInAndBetween(1, test.listOfCorrectAnswers.get(questionNumber).response.size());
                    } while(!IOUtils.errorOutputIfFalse(answer, "Invalid choice.\n"));

                    System.out.print("Enter the new correct answer (Ex: A2):\n");
                    do {
                        userInput = IOUtils.userInput();
                        if (!test.listOfQuestions.get(questionNumber).validate(test.listOfAnswers.get(questionNumber), userInput)) {
                            userInput = "-1";
                        }
                    } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid option.\n"));

                    answerObj.response.set(Integer.parseInt(answer)-1, userInput);
                }
            }
            case "multiple choice" -> {
                System.out.print("Correct Answer(s): \n");
                for (int i = 0; i < answerObj.response.size(); i++) {
                    System.out.print((i+1)+") "+ answerObj.response.get(i) + "\n");
                }
                System.out.print("Would you like to modify the correct answer(s)? (Yes or No)\n");
                userInput = IOUtils.userInput();

                if (userInput.equals("Yes")) {
                    System.out.print("Which correct answer would you like to modify?\n");
                    do {
                        answer = IOUtils.numberInAndBetween(1, test.listOfCorrectAnswers.get(questionNumber).response.size());
                    } while(!IOUtils.errorOutputIfFalse(answer, "Invalid choice.\n"));

                    test.listOfQuestions.get(questionNumber).displayAnswersChoices();

                    System.out.print("Enter the new correct answer (Enter the letter that represents the choice):\n");
                    do {
                        userInput = IOUtils.numberInAndBetween(1, test.listOfQuestions.get(questionNumber).answerChoices.size());
                        if (!test.listOfQuestions.get(questionNumber).validate(test.listOfAnswers.get(questionNumber),userInput)) {
                            userInput = "-1";
                        }
                    } while(!IOUtils.errorOutputIfFalse(userInput, "Invalid option.\n"));
                    userInput = test.listOfQuestions.get(questionNumber).answerChoices.get(Integer.parseInt(userInput) - 1);

                    answerObj.response.set(Integer.parseInt(answer)-1, userInput);
                }
            }
        }
    }

    private void changeChoices(Paper paper, int questionNumber) {
        switch (paper.listOfQuestions.get(questionNumber).getQuestionType()) {
            case "multiple choice", "matching" -> {
                paper.listOfQuestions.get(questionNumber).displayAnswersChoices();
                System.out.print("Would you like to modify the choices? (Yes or No)\n");
                userInput = IOUtils.userInput();
                if (userInput.equals("Yes")) {
                    switch (paper.listOfQuestions.get(questionNumber).getQuestionType()) {
                        case "multiple choice" -> changeMultiChoice(paper.listOfQuestions.get(questionNumber));
                        case "matching" -> changeMatchChoice(paper.listOfQuestions.get(questionNumber));
                    }
                }
            }
            default -> {
            }
        }
    }

    private void changeMultiChoice(Questions questions) {
        System.out.print("What choice do you want to modify?\n");
        String choiceNumber;
        String answerChoice;

        do {
            System.out.print("Valid choice number (1-"+questions.answerChoices.size()+").\n");
            choiceNumber = IOUtils.numberInAndBetween(1, questions.answerChoices.size());

        } while (!IOUtils.errorOutputIfFalse(choiceNumber, "Invalid Input.\n"));

        do {
            System.out.print("What would you like the new answer choice to be.\n");
            answerChoice = IOUtils.userInput();
        } while (!IOUtils.errorOutputIfFalse(answerChoice, "Invalid Input.\n"));

        questions.changeAnswerChoices(answerChoice, Integer.parseInt(choiceNumber)-1);
    }

    private void changeMatchChoice(Questions questions) {
        int first = 0;
        int second = 0;
        int placeholder = 0;
        int column;
        int number;

        for (int i = 0; i < questions.answerChoices.size(); i++) {
            if (questions.answerChoices.get(i).equals("Second Column")) {
                second = i;
                break;
            }
        }

        do {
            System.out.print("What column do you want to modify?\n");
            userInput = IOUtils.numberInAndBetween(1, 2);
        } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid column number, only two columns.\n"));
        column = Integer.parseInt(userInput);

        if (column == 1) {
            placeholder = second;
        }

        if (column == 2) {
            placeholder = questions.answerChoices.size()-second;
        }

        do {
            System.out.print("Which choice do you want to modify?\n");
            userInput = IOUtils.numberInAndBetween(first+1, placeholder);
        } while (!IOUtils.errorOutputIfFalse(userInput, "There are only " + second + " choices .\n"));
        number = Integer.parseInt(userInput);

        do {
            System.out.print("What would you like to change the choice to? \n");
            userInput = IOUtils.userInput();
        } while (!IOUtils.errorOutputIfFalse(userInput, "Answer choices cannot be empty.\n"));

        if (column == 1) {
            questions.changeAnswerChoices(userInput, first+number);
        }

        if (column == 2) {
            questions.changeAnswerChoices(userInput, second+number);
        }

    }

}
