package memento;

public class State {
    private final int value;

    public State(int value) {
        this.value = value;
    }

    State(State state) {
        this.value = state.getValue();
    }

    public int getValue() {
        return value;
    }
}
