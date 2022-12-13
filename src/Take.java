
public class Take {

    public void takeSurvey(Survey survey) {
        Store store = new Store();
        String userInput;

        if (survey.isTaken()) {
            System.out.print("Survey was already taken.\n");
            return;
        }

        for (int i = 0; i < survey.listOfQuestions.size(); i++) {
            System.out.print(survey.listOfQuestions.get(i).getQuestion()+"\n");
            survey.listOfQuestions.get(i).displayAnswersChoices();

            for (int j = 0; j < survey.listOfQuestions.get(i).getMaxResponses(); j++) {

                do {
                    userInput = IOUtils.userInput();
                } while (!survey.listOfQuestions.get(i).validate(survey.listOfAnswers.get(i),userInput));

                survey.listOfAnswers.get(i).addResponse(userInput);
            }
        }
        userInput = store.choosePath(survey);
        store.serializeSurvey(survey, userInput);

        for (int i = 0; i < survey.listOfQuestions.size(); i++) {
            survey.listOfAnswers.get(i).clearResponse();
        }
    }

    public void takeTest(Test test) {
        Store store = new Store();
        String userInput;


        if (test.isTaken()) {
            System.out.print("Test was already taken.\n");
            return;
        }

        for (int i = 0; i < test.listOfQuestions.size(); i++) {
            System.out.print(test.listOfQuestions.get(i).getQuestion()+"\n");
            test.listOfQuestions.get(i).displayAnswersChoices();

            for (int j = 0; j < test.listOfQuestions.get(i).getMaxResponses(); j++) {

                do {
                    userInput = IOUtils.userInput();
                    if (!test.listOfQuestions.get(i).validate(test.listOfAnswers.get(i), userInput)) {
                        userInput = "-1";
                    }
                } while (!IOUtils.errorOutputIfFalse(userInput, ""));

                test.listOfAnswers.get(i).addResponse(userInput);
            }
        }
        userInput = store.choosePath(test);
        store.serializeTest(test, userInput);

        for (int i = 0; i < test.listOfQuestions.size(); i++) {
            test.listOfAnswers.get(i).clearResponse();
        }
    }
}
