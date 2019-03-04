package cells;

import notes.Note;
import notes.Two;

import java.util.LinkedList;

public class CellTwo extends Cell {
    private LinkedList<Note> notes = new LinkedList<>();

    @Override
    protected void take(Note note) {
        notes.add(note);
        System.out.println("Cell Two size: " + notes.size());
    }

    protected int give(int money) {
        if (notes.size() != 0) {
            Note note = notes.getLast();
            int value = note.getValue();
            while (value <= money) {
                System.out.println("Giving " + note);
                notes.removeLast();
                money -= value;
            }
            System.out.println("Cell Two size: " + notes.size());
        }
        return money;
    }

    @Override
    protected boolean check(Note note) {
        return note.getClass().equals(Two.class);
    }
}
