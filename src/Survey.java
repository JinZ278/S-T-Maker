import java.io.Serializable;

public class Survey extends Paper implements Serializable {
    public void menu() {
        System.out.print("\n-------------------------------");
        System.out.print("\n-----Survey---Maker---Menu-----");
        System.out.print("\n-------------------------------\n");
        System.out.print("1) Create a new Survey.\n");
        System.out.print("2) Display an existing Survey.\n");
        System.out.print("3) Load an existing Survey.\n");
        System.out.print("4) Save the current Survey.\n");
        System.out.print("5) Take the current Survey.\n");
        System.out.print("6) Modify the current Survey\n");
        System.out.print("7) Tabulate a survey\n");
        System.out.print("8) Return to previous menu\n");
        System.out.print("What would you like to do?\n");
    }
}
