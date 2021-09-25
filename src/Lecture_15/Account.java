package Lecture_15;

public class Account {

    private int money;
    private String login;
    private long password;

    public Account(int money, String login, long password) {
        super();
        this.money = money;
        this.login = login;
        this.password = password;
    }

    // Синхронизируем метод для обеспечения единовременного доступа к нему только одного потока
    /*public synchronized void takeMoney(String login, long password, int sum) {
        if (!checkPassAndLogin(login, password)) {
            System.out.println("Wrong login or password");
            return;
        }
        if (!checkMoney(sum)) {
            System.out.println("You don't have such money sum");
            return;
        }
        transaction();
        changeBalance(sum);
        System.out.println(this);
    }*/
    public synchronized void takeMoney(String login, long password, int sum) {
        // Использование для синхронизации оператора "synchronized". Синхронизируется текущий объект (this). Можно применять, если нет возможности написать синхронизированный метод, либо нет доступа к исходному коду метода
        synchronized (this) {
            if (!checkPassAndLogin(login, password)) {
                System.out.println("Wrong login or password");
                return;
            }
            if (!checkMoney(sum)) {
                System.out.println("You don't have such money sum");
                return;
            }
            transaction();
            changeBalance(sum);
            System.out.println(this);
        }
    }
    /*
    Какой подход когда использовать
    В общем случае результат работы обоих подходов будет одинаковым.

    Если вы пишете приложение, которое точно будет работать в многопоточном режиме, то лучше использовать синхронизированные методы.
    Если приложение написано не вами и нельзя модифицировать исходный код, либо при написании приложения оно не было рассчитано на многопоточное выполнение, тогда следует использовать оператор synchronized.
     */

    // Методы проверок и снятия денег. Метод transaction() специально задерживает основной поток, имитируя долгую работу
    private boolean checkPassAndLogin(String login, long password) {
        return login.equals(this.login) && this.password == password;
    }

    private boolean checkMoney(int money) {
        return this.money >= money;
    }

    private void transaction() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeBalance(int money) {
        this.money -= money;
    }

    @Override
    public String toString() {
        return "Account [money = " + money + "]";
    }
}
