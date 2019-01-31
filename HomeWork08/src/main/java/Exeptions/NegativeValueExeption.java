package Exeptions;

public class NegativeValueExeption extends Exception {
    private String message = "The value of money must be positive";

    @Override
    public String getMessage() {
        return message;
    }
}
