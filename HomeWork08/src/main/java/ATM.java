import java.math.BigInteger;
import java.util.*;

import Exeptions.MultiplyNotesMoneyExeption;
import Exeptions.NegativeValueExeption;
import Exeptions.NotEnoughMoneyExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATM {
    private SortedSet<Cell> cells;
    private ATM next; // chain просто чтоб был этот шаблон, задачи, которые он решал в прошлом д.з. решил SortedSet.
    private static int count;
    private final int ID = ++count;
    private static Logger logger = LoggerFactory.getLogger(ATM.class);

    ATM(Nominal... nominals) {
        cells = new TreeSet<>();
        for (Nominal nominal :
                nominals) {
            cells.add(new Cell(nominal));
        }
        logger.info(this + " successful configurated");
    }



    public void fullStateSave(){
        for (Cell cell :
                cells) {
            cell.holdState();
        }
        logger.info(this + " resent state of ATM saved.");
    }

    public void fullStateRestore() {
        for (Cell cell :
                cells) {
            cell.abortChanges();
        }
        logger.info(this + " All data recovered to the latest saved state.");
    }

    BigInteger balance() {
        long sum = 0;
        for (Cell cell :
                cells) {
            sum += cell.getNominal().getValue()*cell.getCount();
        }
        logger.info(this + " balance is " + sum);
        return BigInteger.valueOf(sum);
    }

    void takeMoney(Note note) {
        boolean noCell = true;
        for (Cell cell :
                cells) {
            if (cell.retain(note)) {
                logger.info(note.getNominal() + " is successful retained " + this);
                noCell = false;
            }
        }
        if (noCell) {
            logger.info("Sorry, " + this + " does not accept " + note.getNominal() + " note nominals.");
        }
    }

    void giveMoney(int money) {

        try {
            if (BigInteger.valueOf(money).compareTo(balance()) > 0) throw new NotEnoughMoneyExeption();
            if (money <= 0) throw new NegativeValueExeption();
            Map<Nominal,Integer> greeds = new LinkedHashMap<>();
            int moneyToGive = money;
            for (Cell cell :
                    cells) {
                greeds.put(cell.getNominal(),cell.getCount());
            }
            for (Map.Entry<Nominal,Integer> cell:// проверка на возможность выдачи без остатка
                    greeds.entrySet()) {
                while (money >= cell.getKey().getValue() && cell.getValue() > 0) {
                    money -= cell.getKey().getValue();
                    cell.setValue(cell.getValue() - 1);
                }
            }
            if (money != 0) {
                throw new MultiplyNotesMoneyExeption();
            } else {
                logger.info("Starting to give money...");
            }
            for (Cell cell :
                    cells) {
                moneyToGive = cell.produce(moneyToGive);
                logger.info("Giving " + cell.getNominal() + " notes " + moneyToGive + " money left to give");
                if (moneyToGive == 0) {
                    break;
                }
            }
        } catch (NegativeValueExeption e) {
            logger.error(e.getMessage());
        } catch (MultiplyNotesMoneyExeption e) {
            logger.error(e.getMessage());
        } catch (NotEnoughMoneyExeption e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return ATM.class.getCanonicalName() + " " + ID;
    }
}
