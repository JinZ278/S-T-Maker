import java.io.Serializable;
import java.util.ArrayList;

public class Answers implements Serializable {
    protected ArrayList<String> response = new ArrayList<>();

    protected void addResponse(String response) {
        this.response.add(response);
    }

    protected void displayResponse() {
        for (String s : this.response) {
            System.out.print(s + "\n");
        }
    }

    protected void clearResponse() {
        response = new ArrayList<>();
    }
}


