import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tabulate {
    protected static void tabulateSurvey(Survey survey) {
        String userInput;

        if (survey.isTaken()) {
            System.out.print("You must have a survey loaded in order to tabulate it. It cannot be a response.\n");
        }
        else {
            System.out.print("What question do you want to tabulate?\n");
            for (int i = 0; i < survey.listOfQuestions.size(); i++) {
                System.out.print("Question " +(i+1)+") " + survey.listOfQuestions.get(i).question+"\n");
            }

            /*Get valid input, 1-x*/
            do {
                userInput = IOUtils.numberInAndBetween(1, survey.listOfQuestions.size());
            } while(!IOUtils.errorOutputIfFalse(userInput, "Invalid input.\n"));

            /*Print question and answers choices*/
            System.out.print(survey.listOfQuestions.get(Integer.parseInt(userInput)-1).question + "\n");
            survey.listOfQuestions.get(Integer.parseInt(userInput)-1).displayAnswersChoices();

            tabulateSurveyQuestion(survey, Integer.parseInt(userInput)-1);
        }
    }

    protected static void tabulateSurveyQuestion(Survey survey, int questionNumber) {
        Load load = new Load();
        Survey surveyClone;
        String responseString;
        File f1 = new File(System.getProperty("user.dir")+"/Responses");
        File[] sortedResponseFiles = f1.listFiles((dir, name) -> name.endsWith("sers"));
        ArrayList<String> answerList = new ArrayList<>();
        Map<String, Integer> answerMap = new HashMap<>();

        if (sortedResponseFiles == null) {
            System.out.print("There are no responses for this survey.\n");
        }
        else {
            //Open .sers Response files, for each file check if survey.original = loaded.original
            //Print the survey.listOfAnswers.get(Integer.parseInt(userInput)-1).response
            System.out.print("Responses \n");
            for (File sortedResponseFile : sortedResponseFiles) {
                surveyClone = load.deserializeSurvey(sortedResponseFile);

                if (surveyClone.original.equals(survey.original)) {
                    surveyClone.listOfAnswers.get(questionNumber).displayResponse();
                    System.out.print("\n");
                    for (int i = 0; i < surveyClone.listOfAnswers.get(questionNumber).response.size(); i++) {
                        responseString = surveyClone.listOfAnswers.get(questionNumber).response.get(i);

                        if (!answerList.contains(responseString)) {
                            answerList.add(responseString);
                            answerMap.put(responseString, 1);
                        }
                        else {
                            answerMap.put(responseString, answerMap.get(responseString)+1);
                        }
                    }
                }
            }
            System.out.print("\nTabulation\n");
            for (String s : answerList) {
                System.out.print(s + " : " + answerMap.get(s) + "\n");
            }
        }
    }

    protected static void tabulateTest(Test test) {
        String userInput;

        if (test.isTaken()) {
            System.out.print("You must have a survey loaded in order to tabulate it. It cannot be a response.\n");
        }
        else {
            System.out.print("What question do you want to tabulate?\n");
            for (int i = 0; i < test.listOfQuestions.size(); i++) {
                System.out.print("Question " +(i+1)+") " + test.listOfQuestions.get(i).question+"\n");
            }

            /*Get valid input, 1-x*/
            do {
                userInput = IOUtils.numberInAndBetween(1, test.listOfQuestions.size());
            } while(!IOUtils.errorOutputIfFalse(userInput, "Invalid input.\n"));

            /*Print question and answers choices*/
            System.out.print(test.listOfQuestions.get(Integer.parseInt(userInput)-1).question + "\n");
            test.listOfQuestions.get(Integer.parseInt(userInput)-1).displayAnswersChoices();

            tabulateTestQuestion(test, Integer.parseInt(userInput)-1);
        }
    }

    protected static void tabulateTestQuestion(Test test, int questionNumber) {
        Load load = new Load();
        Test testClone;
        String responseString;
        File f1 = new File(System.getProperty("user.dir")+"/Responses");
        File[] sortedResponseFiles = f1.listFiles((dir, name) -> name.endsWith("sert"));
        ArrayList<String> answerList = new ArrayList<>();
        Map<String, Integer> answerMap = new HashMap<>();

        if (sortedResponseFiles == null) {
            System.out.print("There are no responses for this survey.\n");
        }
        else {
            //Open .sert Response files, for each file check if test.original = loaded.original
            //Print the test.listOfAnswers.get(Integer.parseInt(userInput)-1).response
            System.out.print("Responses \n");
            for (File sortedResponseFile : sortedResponseFiles) {
                testClone = load.deserializeTest(sortedResponseFile);

                if (testClone.original.equals(test.original)) {
                    testClone.listOfAnswers.get(questionNumber).displayResponse();
                    for (int i = 0; i < testClone.listOfAnswers.get(questionNumber).response.size(); i++) {
                        responseString = testClone.listOfAnswers.get(questionNumber).response.get(i);

                        if (!answerList.contains(responseString)) {
                            answerList.add(responseString);
                            answerMap.put(responseString, 1);
                        }
                        else {
                            answerMap.put(responseString, answerMap.get(responseString)+1);
                        }

                    }
                }
            }
            System.out.print("Tabulation\n");
            for (String s : answerList) {
                System.out.print(s + ": " + answerMap.get(s) + "\n");
            }
        }
    }
}
