package notes;

public class Five implements Note {
    private static final int VALUE = 5;
    @Override
    public int getValue() {
        return VALUE;
    }
    @Override
    public String toString() {
        return "Five:" + VALUE;
    }
}
