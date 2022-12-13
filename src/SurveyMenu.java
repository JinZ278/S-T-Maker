import java.io.File;

public class SurveyMenu {
    Survey survey = new Survey();
    Store store = new Store();
    Load load = new Load();
    Take take = new Take();
    Modify modify = new Modify();
    String userInput = null;

    public void menu() {
        while (true) {
            survey.menu();

            userInput = IOUtils.numberInAndBetween(1,8);
            if (userInput.equals("8")) {
               break;
            }
            if (IOUtils.errorOutputIfFalse(userInput, "Not a valid command.\n")) {
                chooseOption(userInput);
            }
        }
    }

    private void chooseOption(String choice) {
        switch (choice) {
            case "1" -> {
                Survey survey2 = new Survey();
                survey2.build();
                if (survey2.listOfQuestions.size() == 0) {
                    System.out.print("No survey to store.\n");
                }
                else {
                    userInput = store.choosePath(survey2);
                    survey2.original = String.valueOf(hashCode());
                    store.serializeSurvey(survey2, userInput);
                }
            }
            case "2" -> {
                if (survey.listOfQuestions.size() == 0) {
                    System.out.print("In order to display a survey, please load it.\n");
                }
                else {
                    for (int i = 0; i < survey.listOfQuestions.size(); i++) {
                        System.out.print("\nQuestion " + (i + 1) + " " + survey.listOfQuestions.get(i).getQuestion() + "\n");
                        survey.listOfQuestions.get(i).displayAnswersChoices();
                        survey.listOfAnswers.get(i).displayResponse();
                    }
                }
            }
            case "3" -> {
                System.out.print("What file would you like to load the survey from?\n");
                File filename = load.loadSurveyOptions();
                if (filename != null) {
                    survey = load.deserializeSurvey(filename);
                    System.out.print("Survey loaded.\n");
                }
            }
            case "4" -> {
                if (survey.listOfQuestions.size() == 0) {
                    System.out.print("You must have a survey loaded in order to take it.\n");
                }
                else {
                    userInput = store.choosePath(survey);
                    store.serializeSurvey(survey, userInput);
                }
            }
            case "5" -> {
                if (survey.listOfQuestions.size() == 0) {
                    System.out.print("You must have a survey loaded in order to take it.\n");
                }
                else {
                    System.out.print("Taking a survey.\n");
                    take.takeSurvey(survey);
                }
            }
            case "6" -> {
                if (survey.listOfQuestions.size() == 0) {
                    System.out.print("You must have a survey loaded in order to modify it.\n");
                }
                else {
                    System.out.print("Modifying survey.\n");
                    modify.changeSurvey(survey);
                    if (store.store()) {
                        userInput = store.choosePath(survey);
                        store.serializeSurvey(survey, userInput);
                    }
                }
            }
            case "7" -> {
                if (survey.listOfQuestions.size() == 0) {
                    System.out.print("In order to tabulate a survey question, please load a survey.\n");
                }
                else {
                    System.out.print("Tabulate a survey question.\n");
                    Tabulate.tabulateSurvey(survey);
                }
            }
        }
    }
}
