package exeptions;

public class NotEnoughMoneyExeption extends MyException {
    private String message = "Sorry, not enough money.";

    @Override
    public String getMessage() {
        return message;
    }
}
