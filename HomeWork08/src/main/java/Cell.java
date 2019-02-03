public class Cell implements Comparable<Cell>{
    private final Nominal nominal;
    private int count;
    private Memento state;

    public Cell(Nominal nominal) {
        this.nominal = nominal;
        this.state = new Memento();
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

    public void abortChanges() {
        this.count = state.stateCount;
    }

    public void holdState() {
        this.state = new Memento();
    }


    private class Memento { // С подсказки использую шаблон
        int stateCount;
        Memento() {
            stateCount = count;
        }
    }
}
