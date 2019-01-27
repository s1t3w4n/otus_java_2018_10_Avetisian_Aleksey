import notes.Five;
import notes.One;
import notes.Two;

public class MainHW07 {
    public static void main(String[] args) {
        ATM atm = new ATM();
        System.out.println("Balance: " + atm.getBalance()); // стартовый баланс

        //кладём банкноты
        atm.putMoney(new Five());
        atm.putMoney(new Two());
        atm.putMoney(new One());
        atm.putMoney(new One());
        atm.putMoney(new Two());
        atm.putMoney(new Five());

        System.out.println("Balance: " + atm.getBalance()); // баланс после пополнения
        atm.takeMoney(20); // получить больше баланса
        atm.takeMoney(6);
        System.out.println("Balance: " + atm.getBalance()); // баланс после снятия


    }
}
