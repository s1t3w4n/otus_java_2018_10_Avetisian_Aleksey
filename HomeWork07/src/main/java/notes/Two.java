package notes;

public class Two implements Note {
    private static final int VALUE = 2;
    @Override
    public int getValue() {
        return VALUE;
    }
    @Override
    public String toString() {
        return "Two:" + VALUE;
    }
}
