import java.math.BigInteger;

public class SaveState implements Command {
    private ATM atm;
    public SaveState(ATM atm) {
        this.atm = atm;
    }
    @Override
    public BigInteger execute() {
        atm.fullStateSave();
        return null;
    }
}
