package exeptions;

public class MultiplyNotesMoneyExeption extends MyException {
    private String message = "Not enough notes";

    public String getMessage() {
        return message;
    }
}
