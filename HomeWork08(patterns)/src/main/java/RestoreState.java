import java.math.BigInteger;

public class RestoreState implements Command {
    private ATM atm;
    public RestoreState(ATM atm) {
        this.atm = atm;
    }
    @Override
    public BigInteger execute() {
        atm.fullStateRestore();
        return null;
    }
}
