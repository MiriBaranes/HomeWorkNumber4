import java.util.Scanner;

public class ParserBase {

    private final Scanner scanner = new Scanner(System.in);
    private final String request;
    private final Object helperData;

    public ParserBase(String request, Object helperData) {
        this.request = request;
        this.helperData = helperData;
    }

    public ParserBase(String request) {
        this(request, null);
    }

    protected boolean checkInput(String input, Object helperData) {
        return true;
    }

    public String getInput() {
        String input = null;
        do {
            System.out.println(request + ": ");
            input = scanner.nextLine();
        } while (!this.checkInput(input, helperData));
        return input;
    }
}
