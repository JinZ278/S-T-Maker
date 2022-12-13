import java.io.File;

public class TestMenu {
    Test test = new Test();
    Store store = new Store();
    Load load = new Load();
    Take take = new Take();
    Modify modify = new Modify();
    String userInput = null;

    public void menu() {
        while (true) {
            test.menu();

            userInput = IOUtils.numberInAndBetween(1,10);
            if (userInput.equals("10")) {
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
                Test test2 = new Test();
                test2.build();
                if (test2.listOfQuestions.size() == 0) {
                    System.out.print("No test to store.\n");
                }
                else {
                    userInput = store.choosePath(test2);
                    test2.original = String.valueOf(hashCode());
                    store.serializeTest(test2, userInput);
                }
            }
            case "2" -> {
                if (test.listOfQuestions.size() == 0) {
                    System.out.print("In order to display a Test, please load it.\n");
                }
                else {
                    for (int i = 0; i < test.listOfQuestions.size(); i++) {
                        System.out.print("\nQuestion " + (i + 1) + " " + test.listOfQuestions.get(i).getQuestion() + "\n");
                        test.listOfQuestions.get(i).displayAnswersChoices();
                        test.listOfAnswers.get(i).displayResponse();
                    }
                }
            }
            case "3" -> {
                if (test.listOfQuestions.size() == 0) {
                    System.out.print("In order to display a Test, please load it.\n");
                }
                else {
                    for (int i = 0; i < test.listOfQuestions.size(); i++) {
                        System.out.print("\nQuestion " + (i + 1) + " " + test.listOfQuestions.get(i).getQuestion() + "\n");
                        test.listOfQuestions.get(i).displayAnswersChoices();
                        test.listOfAnswers.get(i).displayResponse();
                        test.listOfCorrectAnswers.get(i).displayResponse();
                    }
                }
            }
            case "4" -> {
                System.out.print("What file would you like to load the test from?\n");
                File filename = load.loadTestOptions();
                if (filename != null) {
                    test = load.deserializeTest(filename);
                    System.out.print("Survey loaded.\n");
                }
            }
            case "5" -> {
                if (test.listOfQuestions.size() == 0) {
                    System.out.print("You must have a test loaded in order to store it.\n");
                }
                else {
                    userInput = store.choosePath(test);
                    store.serializeTest(test, userInput);
                }
            }
            case "6" -> {
                if (test.listOfQuestions.size() == 0) {
                    System.out.print("You must have a test loaded in order to take it.\n");
                }
                else {
                    System.out.print("Taking a survey.\n");
                    take.takeTest(test);
                }
            }
            case "7" -> {
                if (test.listOfQuestions.size() == 0) {
                    System.out.print("You must have a test loaded in order to modify it.\n");
                }
                else {
                    System.out.print("Modifying test.\n");
                    modify.changeTest(test);
                    if (store.store()) {
                        userInput = store.choosePath(test);
                        store.serializeTest(test, userInput);
                    }
                }
            }
            case "8" -> {
                if (test.listOfQuestions.size() == 0) {
                    System.out.print("In order to tabulate a test question, please load a test.\n");
                }
                else {
                    System.out.print("Tabulate a survey question.\n");
                    Tabulate.tabulateTest(test);
                }
            }
            case "9" -> Grade.grade();
        }
    }
}
