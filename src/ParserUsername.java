import java.util.Scanner;

public class ParserUsername extends ParserBase {
    public ParserUsername(String request, Object helperData) {
        super(request, helperData);
    }
    public ParserUsername(String request) {
        this(request, null);
    }

    @Override
    protected boolean checkInput(String userName, Object helperData) {
        RealEstate re = (RealEstate) helperData;
        boolean existUserName=false;
        for (User currentUser : re.getUsers()) {
            if (currentUser.getUserName().equals(userName)) {
                existUserName = true;
                break;
            }
        }
        return !existUserName;
    }
}
