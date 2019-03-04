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
        List<Command> commands = new ArrayList<>();
        atms.forEach(atm -> commands.add(new SaveState(atm)));

        commands.forEach(command -> command.execute());
    }

    public void restore() {
        List<Command> commands = new ArrayList<>();
        atms.forEach(atm -> commands.add(new RestoreState(atm)));

        commands.forEach(command -> command.execute());
    }


    public BigInteger rest() {
        List<Command> commands = new ArrayList<>();
        atms.forEach(atm -> commands.add(new GetBalance(atm)));

        BigInteger rest = BigInteger.valueOf(0);
        for (Command command : commands) {
            rest = rest.add(command.execute());
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
