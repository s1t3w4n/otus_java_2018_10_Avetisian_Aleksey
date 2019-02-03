public class MainHW08 {
    public static void main(String[] args) {
        //Разные варианты создание АТМ в департаменте
        Department department = new Department(new ATM(Nominal.ONE,Nominal.TWO),
                                               new ATM(Nominal.ONE, Nominal.FIVE));
        department.addAtm(new ATM(Nominal.ONE,Nominal.TEN));
        ATM atm = new ATM(Nominal.ONE,Nominal.TEN,Nominal.FIVE);
        department.addAtm(atm);

        atm.takeMoney(new Note(Nominal.ONE));// проверка на добавление купюры у которой есть ячейка
        atm.takeMoney(new Note(Nominal.TWO));// проверка на добавление купюры у которой нет ячейки
        atm.takeMoney(new Note(Nominal.FIVE));
        atm.takeMoney(new Note(Nominal.FIVE));

        atm.giveMoney(-12); // выдать отрицательное
        atm.giveMoney(12); // выдать больше баланса
        atm.giveMoney(6);// выдать реальный вариант

        atm.balance();
        atm.fullStateSave();// сохранить состояние
        atm.takeMoney(new Note(Nominal.FIVE));// изменить состояние
        atm.giveMoney(9);// выдать неподходящий по купюрам вариант

        atm.balance();// узнать баланс
        atm.fullStateRestore();// восстановить состояние
        atm.balance();

        fillDepartment(department, new Note(Nominal.ONE)); // заполнить все АТМ одной купюрой ONE
        department.save();// сохранить состояние департамента
        System.out.println(department.rest() + " Department Balance");//узнать остаток в департаменте
        fillDepartment(department, new Note(Nominal.FIVE)); // заполнить все АТМ одной купюрой FIVE
        System.out.println(department.rest() + " Department Balance");//узнать остаток в департаменте

        department.restore();// восстановить состояние департамента
        System.out.println(department.rest() + " Department Balance");//узнать остаток в департаменте


    }


    private static void fillDepartment(Department department, Note note) {
        for (ATM atm :
                department.getAtms()) {
            atm.takeMoney(note);
        }
    }
}
