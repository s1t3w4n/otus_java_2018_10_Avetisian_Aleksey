package notes;

public class Ten implements Note {
    private static final int VALUE = 10;
    @Override
    public int getValue() {
        return VALUE;
    }
    @Override
    public String toString() {
        return "Ten:" + VALUE;
    }
}
