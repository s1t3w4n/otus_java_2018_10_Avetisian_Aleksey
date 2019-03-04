import java.math.BigInteger;

public class GetBalance implements Command {
    private ATM atm;
    public GetBalance(ATM atm) {
        this.atm = atm;
    }
    @Override
    public BigInteger execute() {
        return atm.balance();
    }
}
