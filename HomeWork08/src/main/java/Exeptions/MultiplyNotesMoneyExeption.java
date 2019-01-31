package Exeptions;

public class MultiplyNotesMoneyExeption extends Exception {
    private String message = "Not enough notes";

    public String getMessage() {
        return message;
    }
}
