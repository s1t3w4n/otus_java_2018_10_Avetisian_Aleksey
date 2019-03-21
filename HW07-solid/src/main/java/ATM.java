import cells.*;
import notes.Note;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ATM {
    private BigInteger balance = BigInteger.valueOf(0);
    private static final List<Cell> cells;
    static {
        cells = new ArrayList<>();
        cells.add(new CellOne());
        cells.add(new CellTwo());
        cells.add(new CellFive());
        cells.add(new CellTen());
    }

    public void putMoney(Note note) {
        for (Cell cell : cells) {
            balance = cell.retain(balance, note);
        }
    }
    public void takeMoney(int sum){
        if (getBalance().compareTo(BigInteger.valueOf(sum)) < 0) {
            System.out.println("Sorry. Not enough money...");
        } else {
            for (int i = cells.size() - 1; i > 0; i--) {
                cells.get(i).setNext(cells.get(i - 1));
            }
            balance = cells.get(3).produce(balance,sum);
        }
    }
    public BigInteger getBalance() {
        return balance;
    }

}
