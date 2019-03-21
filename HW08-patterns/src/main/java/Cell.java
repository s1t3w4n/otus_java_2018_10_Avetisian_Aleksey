import memento.Memento;
import memento.State;

import java.util.Comparator;

public class Cell implements Comparable<Cell>{
    private final Nominal nominal;
    private int count;
    private Cell next; // начало шаблона Chain of responsibility
    private Memento state;

    public Cell(Nominal nominal) {
        this.nominal = nominal;
        this.state = new Memento(new State(count));
    }

    public Cell getNext() {
        return next;
    }

    public void setNext(Cell next) {
        this.next = next;
    }

    public boolean retain(Note note) {
        if (nominal.equals(note.getNominal())) {
            count++;
            return true;
        } else {
            return false;
        }
    }

    public int produce(int money) {
        while (money >= nominal.getValue() && count > 0) {
            money -= nominal.getValue();
            count--;
        }
        return money;
    }

    public int getCount() {
        return count;
    }

    public Nominal getNominal() {
        return nominal;
    }

    public int compareTo(Cell o) {
        return o.getNominal().getValue() - this.getNominal().getValue();
    }

    public static Comparator<Cell> NominalComparator = new Comparator<Cell>() {
        @Override
        public int compare(Cell o1, Cell o2) {
            return o2.getNominal().getValue() - o1.getNominal().getValue();
        }
    };

    public void abortChanges() {
        this.count = state.getState().getValue();
    }

    public void holdState() {
        this.state = new Memento(new State(this.count));
    }

}
