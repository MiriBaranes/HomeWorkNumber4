import java.util.Scanner;

public class ParserMediator extends ParserBase {
    public ParserMediator(String request, Object helperData) {
        super(request, helperData);
    }

    public ParserMediator(String request) {
        this(request, null);
    }

    @Override
    protected boolean checkInput(String mediatorOrUser, Object helperData) {

        boolean invalidSelection = false;
        if (mediatorOrUser.equals("1") || mediatorOrUser.equals("2")) {
            invalidSelection = true;
        }
        return invalidSelection;
    }
}
