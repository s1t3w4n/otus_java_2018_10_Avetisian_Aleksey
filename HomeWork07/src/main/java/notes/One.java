package notes;

public class One implements Note {
    private static final int VALUE = 1;
    @Override
    public int getValue() {
        return VALUE;
    }

    @Override
    public String toString() {
        return "One:" + VALUE;
    }
}
