import java.io.*;
import java.util.Objects;

public class Load {

    public File loadSurveyOptions() {
        File f1 = new File(System.getProperty("user.dir")+"/Papers");
        File[] sortedPaperFiles = f1.listFiles((dir, name) -> name.endsWith("sers"));
        File f2 = new File(System.getProperty("user.dir")+"/Responses");
        File[] sortedResponseFiles = f2.listFiles((dir, name) -> name.endsWith("sers"));

        String userInput;
        int counter = 0;

        /*Shows a list of loadable Survey .sers files in the current directory */
        System.out.print("Loadable surveys.\n");
        for (; counter < Objects.requireNonNull(sortedPaperFiles).length; counter++ ) {
            System.out.print((counter+1)+") " + sortedPaperFiles[counter].getName()+"\n");
        }

        System.out.print("Loadable survey responses.\n");
        for (; counter < Objects.requireNonNull(sortedResponseFiles).length+sortedPaperFiles.length; counter++ ) {
            System.out.print((counter+1)+") " + sortedResponseFiles[counter-sortedPaperFiles.length].getName()+"\n");
        }

        /*Error checking for non-valid int inputs*/
        System.out.print("Choose a survey to load (0 to exit).\n");
        do {
            userInput = IOUtils.numberInAndBetween(0, sortedPaperFiles.length+sortedResponseFiles.length);
        } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid choice.\n"));

        if (userInput.equals("0")) {
            return null;
        }

        if (Integer.parseInt(userInput) > sortedPaperFiles.length) {
            return sortedResponseFiles[Integer.parseInt(userInput)-1-sortedPaperFiles.length];
        }
        else {
            return sortedPaperFiles[Integer.parseInt(userInput) - 1];
        }
    }

    public File loadTestOptions() {
        File f1 = new File(System.getProperty("user.dir")+"/Papers");
        File[] sortedPaperFiles = f1.listFiles((dir, name) -> name.endsWith("sert"));
        File f2 = new File(System.getProperty("user.dir")+"/Responses");
        File[] sortedResponseFiles = f2.listFiles((dir, name) -> name.endsWith("sert"));

        String userInput;
        int counter = 0;

        /*Shows a list of loadable test .sert files in the current directory */
        System.out.print("Loadable tests.\n");
        for (; counter < Objects.requireNonNull(sortedPaperFiles).length; counter++ ) {
            System.out.print((counter+1)+") " + sortedPaperFiles[counter].getName()+"\n");
        }

        System.out.print("Loadable test responses.\n");
        for (; counter < Objects.requireNonNull(sortedResponseFiles).length+sortedPaperFiles.length; counter++ ) {
            System.out.print((counter+1)+") " + sortedResponseFiles[counter-sortedPaperFiles.length].getName()+"\n");
        }

        /*Error checking for non-valid int inputs*/
        System.out.print("Choose a test to load (0 to exit).\n");
        do {
            userInput = IOUtils.numberInAndBetween(0, sortedPaperFiles.length+sortedResponseFiles.length);
        } while (!IOUtils.errorOutputIfFalse(userInput, "Invalid choice.\n"));

        if (userInput.equals("0")) {
            return null;
        }

        if (Integer.parseInt(userInput) > sortedPaperFiles.length) {
            return sortedResponseFiles[Integer.parseInt(userInput)-1-sortedPaperFiles.length];
        }
        else {
            return sortedPaperFiles[Integer.parseInt(userInput) - 1];
        }
    }

    public Survey deserializeSurvey(File userInput) {
        Survey survey = new Survey();
        Questions question;
        Answers answer;

        /*Deserializing*/
        try {
            FileInputStream fileIn = new FileInputStream(userInput);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            survey.original = (String) in.readObject();

            /*Loops all objects in survey and deserializes them.*/
            while (true) {
                try {
                    question = (Questions) in.readObject();
                    survey.listOfQuestions.add(question);
                    answer = (Answers) in.readObject();
                    survey.listOfAnswers.add(answer);
                } catch (Exception D) {
                    break;
                }
            }

            in.close();
            fileIn.close();
            return survey;
        }

        catch (IOException | ClassNotFoundException i) {
            System.out.print("Unable to load.\n");
            return null;
        }
    }

    public Test deserializeTest(File userInput) {
        Test test = new Test();
        Questions question;
        Answers answer;

        /*Deserializing*/
        try {
            FileInputStream fileIn = new FileInputStream(userInput);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            test.original = (String) in.readObject();

            /*Loops all objects in survey and deserializes them.*/
            while (true) {
                try {
                    question = (Questions) in.readObject();
                    test.listOfQuestions.add(question);
                    answer = (Answers) in.readObject();
                    test.listOfAnswers.add(answer);
                    answer = (Answers) in.readObject();
                    test.listOfCorrectAnswers.add(answer);
                } catch (Exception D) {
                    break;
                }
            }

            in.close();
            fileIn.close();
            return test;
        }

        catch (IOException | ClassNotFoundException i) {
            System.out.print("Unable to load.\n");
            return null;
        }
    }


}
