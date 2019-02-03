package Exeptions;

public class NotEnoughMoneyExeption extends Exception {
    String message = "Sorry, not enough money.";

    @Override
    public String getMessage() {
        return message;
    }
}
