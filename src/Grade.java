import java.io.File;
import java.util.Collections;
import java.util.Objects;

public class Grade {


    public static void grade() {
        File f2 = new File(System.getProperty("user.dir")+"/Responses");
        File[] sortedResponseFiles = f2.listFiles((dir, name) -> name.endsWith("sert"));
        String userInput;


        System.out.print("Loadable test responses.\n");
        for (int counter = 0; counter < Objects.requireNonNull(sortedResponseFiles).length; counter++ ) {
            System.out.print((counter+1)+") " + sortedResponseFiles[counter].getName()+"\n");
        }

        /*Error checking for non-valid int inputs*/
        System.out.print("Choose a test to load (0 to exit).\n");
        do {
            userInput = IOUtils.numberInAndBetween(0, sortedResponseFiles.length);
        } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid choice.\n"));

        if (!userInput.equals("0")) {
            returnGradeForFile(sortedResponseFiles[Integer.parseInt(userInput)-1]);
        }
    }

    private static void returnGradeForFile(File sortedResponseFile) {
        Load load = new Load();
        Test cloneTest;
        double amountOfQuestions;
        double correct = 0;
        int essayQuestions = 0;
        double score;
        double availablePoints;
        String[] placeholder;

        cloneTest = load.deserializeTest(sortedResponseFile);
        amountOfQuestions = cloneTest.listOfQuestions.size();

        for (int i = 0; i < amountOfQuestions; i++) {
            if (cloneTest.listOfQuestions.get(i).getQuestionType().equals("essay")) {
                essayQuestions += 1;
            }
            else {
                correct += gradeQuestions(cloneTest.listOfQuestions.get(i),cloneTest.listOfAnswers.get(i), cloneTest.listOfCorrectAnswers.get(i));
            }
        }
        score = correct/amountOfQuestions*100;
        placeholder = String.valueOf(score).split("\\.");

        System.out.print("You received a " + placeholder[0] + ". The test was worth 100 points");
        if (essayQuestions != 0) {
            availablePoints = (amountOfQuestions-essayQuestions)/amountOfQuestions * 100;
            placeholder = String.valueOf(availablePoints).split("\\.");
            System.out.print(", but only " + placeholder[0] + " of those points could be auto graded because there was " + essayQuestions + " essay question.\n");
        }
        else {
            System.out.print(".\n");
        }

    }

    private static double gradeQuestions(Questions questions, Answers answers, Answers answers1) {
        double possibleAnswers = 0;
        double correct = 0;

        switch (questions.getQuestionType()) {
            default -> {
                Collections.sort(answers.response);
                Collections.sort(answers1.response);
                for (int i = 0; i < answers.response.size(); i++) {
                    possibleAnswers += 1;
                    if (answers.response.get(i).equalsIgnoreCase(answers1.response.get(i))) {
                        correct += 1;
                    }
                }
                return correct / possibleAnswers;
            }
        }
    }
    //when comparing checking matching and multiple choice responses,
    //does order matter? Say for MC question the user enters A,B but the correct response is B,A. Would this be marked as wrong?
    //Just sort out before comparing
}
