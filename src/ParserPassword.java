import java.util.Scanner;

public class ParserPassword extends ParserBase {
    public static final String VALID_CHARS_FOR_PASSWORD = "%$_";

    public ParserPassword(String request, Object helperData) {
        super(request, helperData);
    }
    public ParserPassword(String request) {
        this(request, null);
    }
    @Override
    protected boolean checkInput(String password, Object helperData) {
        boolean strong=false;
        boolean digit=false;
        boolean note=false;

        for (int i=0; i<password.length();i++){
            char currentChar=password.charAt(i);
            if (Character.isDigit(currentChar)){
                digit=true;
            }
            else if (VALID_CHARS_FOR_PASSWORD.indexOf(currentChar)!=-1 ){
                note=true;
            }
        }
        if (digit&&note){
            strong=true;
        }
        return strong;
    }
}
