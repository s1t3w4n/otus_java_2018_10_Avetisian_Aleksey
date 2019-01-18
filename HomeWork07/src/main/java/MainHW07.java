import notes.Five;
import notes.One;
import notes.Three;
import notes.Two;

public class MainHW07 {
    public static void main(String[] args) {
        ATM atm = new ATM();
        System.out.println(atm.getBalance()); // стартовый баланс

        //кладём банкноты
        atm.putMoney(new Five());
        atm.putMoney(new Three());
        atm.putMoney(new Two());
        atm.putMoney(new One());
        atm.putMoney(new One());
        atm.putMoney(new Five());

        System.out.println(atm.getBalance()); // баланс после пополнения
        atm.takeMoney(20); // получить больше баланса


    }
}
