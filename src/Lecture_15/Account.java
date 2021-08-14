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

    public void takeMoney(String login, long password, int sum) {
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
