public class ex23BankCredit {
    public static void main(String[] args) {
        BankClient client1 = new BankClient(1);
        BankClient client2 = new BankClient(2);
        BankClient client3 = new BankClient(0);
        BankWorker employee = new BankWorker();

        System.out.println(getCreditForClient(employee, client1));
        System.out.println(getCreditForClient(employee, client2));
        System.out.println(getCreditForClient(employee, client3));


    }
    // Ваша задача - реализовать метод getCreditForClient, который принимает работника банка и клиента,
    // желающего получить кредит. Метод должен возвращать true - если кредит выдать можно и все условия соблюдены
    // и false если есть какие-то проблемы. Если клиенту отказали в выдаче кредита по причине плохой банковской истории
    // - метод должен выводить в консоль сообщение "Проблемы с банковской историей",
    // если клиенту отказали по причине проблем с законом, то ничего выводить на экран не нужно.
    public static boolean getCreditForClient(BankWorker bankWorker, BankClient bankClient)  {
        try {
            return bankWorker.checkClientForCredit(bankClient);
        } catch (BadCreditHistoryException e) {
            System.out.println("Проблемы с банковской историей");
            return false;
        } catch (ProblemWithLawException e) {
            return false;
        }
    }
}

class BadCreditHistoryException extends RuntimeException {}

class ProblemWithLawException extends RuntimeException {}

// Есть интерфейс BankWorker. Объект класса, который реализует этот интерфейс является работником банка,
// в задачу которого входит одобрение или отклонение заявок на кредиты.
//У него есть метод checkClientForCredit, который на вход принимает экземпляр BankClient и возвращает true,
// если всё в порядке и кредит переданному клиенту можно выдавать, или false - если клиент не подходит под условия кредита.
// Также, этот метод может выбросить исключение BadCreditHistoryException, если у клиента плохая кредитная история.
// Или ProblemWithLawException, если у клиента есть проблемы с законом.
class BankWorker {
    public boolean checkClientForCredit(BankClient bankClient) throws BadCreditHistoryException, ProblemWithLawException {
        switch (bankClient.getNumber()) {
            case BankClient.GOOD_CLIENT:
                return true;
            case BankClient.BAD_CREDIT:
                throw new BadCreditHistoryException();
            case BankClient.CRIMINAL:
                throw new ProblemWithLawException();
        }
        return false;
    }
}

class BankClient {  // Есть класс BankClient - это класс, описывающий клиента банка
    public final static int GOOD_CLIENT = 0;
    public final static int BAD_CREDIT = 1;
    public final static int CRIMINAL = 2;
    private int number;

    public BankClient(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
