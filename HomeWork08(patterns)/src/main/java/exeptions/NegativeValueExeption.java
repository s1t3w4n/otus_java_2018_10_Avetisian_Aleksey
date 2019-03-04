package exeptions;

public class NegativeValueExeption extends MyException {
    private String message = "The value of money must be positive";

    @Override
    public String getMessage() {
        return message;
    }
}
