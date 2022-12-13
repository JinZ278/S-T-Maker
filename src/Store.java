import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Store{
    String userInput = null;

    Store(){
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/Papers"));
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/Responses"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean store() {

        System.out.print("Do you want to store the survey? (Yes or No)\n");
        userInput = IOUtils.userInput();

        if (userInput.equals("Yes")) {
            return true;
        }
        else {
            System.out.print("Survey was not stored, returning to main menu.\n");
            return false;
        }
    }
    public String choosePath(Paper paper) {

        System.out.print("What file would you like to store the paper in?\n");
        System.out.print("(Note: Saving paper with a the same name as another file will delete the latter.)\n");

        /*Checking for empty filename*/
        do {
            userInput = IOUtils.userInput();
        } while (!IOUtils.errorOutputIfFalse(userInput, "Filename cannot be empty.\n"));

        System.out.print("Filename chosen.\n");

        /*Figures out if it is a paper or a response*/
        if (!paper.isTaken()) {
            return (System.getProperty("user.dir") + "/Papers/" + userInput);
        }
        else {
            return (System.getProperty("user.dir") + "/Responses/" + userInput);
        }

    }

    public void serializeSurvey(Survey survey, String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream((path+".sers"));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(survey.original);

            for (int i = 0; i < survey.listOfQuestions.size(); i++) {
                out.writeObject(survey.listOfQuestions.get(i));
                out.writeObject(survey.listOfAnswers.get(i));
            }

            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in "+path+".sers\n");
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
    public void serializeTest(Test test, String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream((path+".sert"));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(test.original);

            for (int i = 0; i < test.listOfQuestions.size(); i++) {
                out.writeObject(test.listOfQuestions.get(i));
                out.writeObject(test.listOfAnswers.get(i));
                out.writeObject(test.listOfCorrectAnswers.get(i));
            }

            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in "+path+".sert\n");
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
