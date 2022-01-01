import java.util.Scanner;

public class ParserPhone extends ParserBase {
    public static final String START_PHONE_NUMBER = "05";
    public ParserPhone(String request, Object helperData) {
        super(request, helperData);
    }
    public ParserPhone(String request) {
        this(request, null);
    }
    @Override
    protected boolean checkInput(String phoneNumber, Object helperData) {

        boolean isProperPhoneNumber=false;
        String start = phoneNumber.substring(0,2);
        if (start.equals(START_PHONE_NUMBER)){
            if (phoneNumber.length()==10){
                isProperPhoneNumber=true;
            }
        }
        return isProperPhoneNumber;
    }
}
