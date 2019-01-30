import java.util.Objects;

public class Cell implements Comparable<Cell>{
    private Cell next;
    private final Nominal nominal;

    private int count;

    public Cell(Nominal nominal) {
        this.nominal = nominal;
        count = 0;
    }

    public Cell getNext(){
        return next;
    }

    public void setNext(Cell next) {
        this.next = next;
    }

    public Nominal getNominal() {
        return nominal;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(Cell cell) {
        return this.nominal.getValue() - cell.getNominal().getValue();
    }
}
