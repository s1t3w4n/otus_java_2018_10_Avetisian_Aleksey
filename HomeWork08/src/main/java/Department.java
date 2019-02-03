import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Department {
    private List<ATM> atms;
    public Department(ATM... atms) {
        this.atms = new ArrayList<>();
        this.atms.addAll(Arrays.asList(atms));
    }

    public void save() {
        for (ATM atm :
                atms) {
            atm.fullStateSave();
        }
    }

    public void restore() {
        for (ATM atm :
                atms) {
            atm.fullStateRestore();
        }
    }


    public BigInteger rest() {
        BigInteger rest = BigInteger.valueOf(0);
        for (ATM atm :
                atms) {
            rest = rest.add(atm.balance());
        }
        return rest;
    }

    public List<ATM> getAtms() {
        return atms;
    }

    public void addAtm(ATM atm) {
        atms.add(atm);
    }
}
