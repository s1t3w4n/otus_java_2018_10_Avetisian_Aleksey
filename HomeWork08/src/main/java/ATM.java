import java.util.SortedSet;
import java.util.TreeSet;

public class ATM {
    SortedSet<Cell> cells;
    public ATM(Nominal... nominals) {
        cells = new TreeSet<>();
        for (Nominal nominal : nominals) {
            cells.add(new Cell(nominal));
        }
        for (Cell cell : cells) {
            System.out.println(cell.getNominal());
        }
    }
}
