import notes.Note;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ATM {
    private BigInteger balance = BigInteger.valueOf(0);
    private List<Note> bankNotes = new ArrayList();

    public void putMoney(Note note) {
        bankNotes.add(note);
        balance = balance.add(BigInteger.valueOf(note.getValue()));
    }
    public void takeMoney(int integer){
        if (getBalance().compareTo(BigInteger.valueOf(integer)) < 0) {
            System.out.println("Sorry. Not enough money...");
        }
    }
    public BigInteger getBalance() {
        return balance;
    }

}
