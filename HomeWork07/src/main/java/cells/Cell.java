package cells;

import notes.Note;

import java.math.BigInteger;

// реализация шаблона Chain of responsibility
abstract public class Cell {
    private Cell next;

    public Cell getNext() {
        return next;
    }

    public void setNext(Cell next) {
        this.next = next;
    }

    public BigInteger retain(BigInteger balance, Note note) {
        if (check(note)) {
            take(note);
            balance = balance.add(BigInteger.valueOf(note.getValue()));
        }
        return balance;
    }

    public BigInteger produce(BigInteger balance, int sum) {
        balance = balance.subtract(BigInteger.valueOf(sum));
        sum = give(sum);
        if (getNext() != null && sum != 0) {
            getNext().produce(balance,sum);
        }
        return balance;
    }


    abstract protected boolean check(Note note);
    abstract protected void take(Note note);
    abstract protected int give(int money);

}
