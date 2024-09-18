/*
Вспомним нашего старого знакомого робота.
Теперь мы будем давать роботу команды удаленно, подключаясь к нему по беспроводному каналу связи.

Подключение к роботу представляется в программе интерфейсом RobotConnection:
public interface RobotConnection extends AutoCloseable {
    void moveRobotTo(int x, int y);
    @Override
    void close();
}
Да, робот с тех пор поумнел и стал понимать команду на перемещение в заданную точку (метод moveRobotTo).
Ему больше не нужны пошаговые инструкции. RobotConnection - это временно устанавливаемое соединение, которое надо закрывать,
когда оно больше не нужно. Для закрытия соединения в интерфейсе есть метод close().

За установку соединения отвечает RobotConnectionManager:
public interface RobotConnectionManager {
    RobotConnection getConnection();
}
Метод getConnection() делает попытку соединиться с роботом и возвращает установленное соединение,
через которое можно отдавать роботу команды.
Установка соединения может завершиться неуспешно, а также уже установленное соединение может внезапно разорваться.
Всякое бывает. Поэтому любой метод RobotConnectionManager и RobotConnection
может бросить непроверяемое исключение RobotConnectionException:
public class RobotConnectionException extends RuntimeException {
    public RobotConnectionException(String message) {
        super(message);
    }
    public RobotConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
Ваша задача - реализовать метод который устанавливает соединение с роботом,
отдает ему команду на перемещение в заданную точку и затем закрывает соединение,
выполняя при необходимости повтор этой последовательности до трех раз.
При этом:
Попытка отдать команду роботу считается успешной, если удалось установить соединение и выполнить
метод RobotConnection.moveRobotTo() без исключений.
Ошибка закрытия соединения не важна и должна игнорироваться.
Если первая попытка подключиться и отдать команду оказалась неуспешной, то закрываем соединение и выполняем вторую попытку.
Если вторая тоже не удалась, то выполняется третья. После третьей неудачи метод должен бросить исключение RobotConnectionException.
Метод отвечает за открытие и закрытие соединения. Если соединение удалось установить,
то оно обязательно должно быть закрыто несмотря на возможные исключения, случившиеся в дальнейшем во время работы метода.
Если во время работы метода случилось исключение любого типа, отличного от RobotConnectionException,
метод должен завершиться и выбросить это исключение, не выполняя повторных попыток пообщаться с роботом.
Единственное, что метод должен сделать перед выбросом этого исключения — закрыть открытое соединение RobotConnection.
В решение должен использоваться классический try-catch.
*/

// TEST #1 FAILED WITH My Exception
// TEST #2 PASS
// TEST #3 PASS
// TEST #4 FAILED WITH My Exception
// TEST #5 PASS
// TEST #6 PASS
// TEST #7 PASS
// TEST #8 FAILED WITH My Exception
public class ex24NewRobot1 {

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        for (int i = 0; i < 3; i++) {
            RobotConnection connection = null;
            try {
                connection = robotConnectionManager.getConnection();
                connection.moveRobotTo(toX, toY);
                i = 3;
            } catch (RobotConnectionException e) {
                if (i == 2) {
                    throw e;
                }
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        Exception ignore;
                    }
                }
            }
        }
    }


    public static class RobotConnectionManagerImplementation implements RobotConnectionManager{
        private int throwNumberMove;
        private int throwNumberClose;
        private boolean isException;
        private int connectionAttempt;
        private int curCA = 0;
        private int curNM = 0;
        private int curNC = 0;

        public RobotConnectionManagerImplementation(int tnm, int tnc, boolean ie, int ca){
            throwNumberMove = tnm;
            throwNumberClose = tnc;
            isException = ie;
            connectionAttempt = ca;
        }

        @Override
        public RobotConnection getConnection() {
            if (curCA < connectionAttempt){
                curCA++;
                throw new RobotConnectionException("CONNECTION EXCEPTION");
            }
            boolean isTC = false;
            boolean isTM = false;
            if (curNC < throwNumberClose){
                isTC = true;
                curNC++;
            }

            if (curNM < throwNumberMove){
                isTM = true;
                curNM++;
            }
            RobotConnection rc = new Robot(0, 0, Direction.DOWN, isTM, isTC, isException);
            return rc;
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static class Robot implements RobotConnection{
        int x;
        int y;
        Direction dir;
        boolean isthrowMove;
        boolean isthrowClose;
        boolean isthrowException;


        public void close(){
            if (isthrowClose) {
                throw new RobotConnectionException("CLOSE EXCEPTION");
            }
        }

        public Robot (int x, int y, Direction dir, boolean itm, boolean itc, boolean ite) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            isthrowMove = itm;
            isthrowClose = itc;
            isthrowException = ite;
        }

        public Direction getDirection() {return dir;}

        public int getX() {return x;}

        public int getY() {return y;}

        public void turnLeft() {
            if      (dir == Direction.UP)    {dir = Direction.LEFT;}
            else if (dir == Direction.DOWN)  {dir = Direction.RIGHT;}
            else if (dir == Direction.LEFT)  {dir = Direction.DOWN;}
            else if (dir == Direction.RIGHT) {dir = Direction.UP;}
        }

        public void turnRight() {
            if      (dir == Direction.UP)    {dir = Direction.RIGHT;}
            else if (dir == Direction.DOWN)  {dir = Direction.LEFT;}
            else if (dir == Direction.LEFT)  {dir = Direction.UP;}
            else if (dir == Direction.RIGHT) {dir = Direction.DOWN;}
        }

        public void stepForward() {
            if (dir == Direction.UP)    {y++;}
            if (dir == Direction.DOWN)  {y--;}
            if (dir == Direction.LEFT)  {x--;}
            if (dir == Direction.RIGHT) {x++;}
        }

        public void moveRobotTo(int toX, int toY) {
            int dx = toX - getX();
            int dy = toY - getY();

            Direction needX = Direction.RIGHT;
            if (dx < 0){
                needX = Direction.LEFT;
            }
            while (getDirection() != needX) {
                turnRight();
            }

            for (dx = Math.abs(dx);dx > 0; dx--){
                stepForward();
            }

            Direction needY = Direction.UP;
            if (dy < 0){
                needY = Direction.DOWN;
            }
            while (getDirection() != needY) {
                turnRight();
            }

            for (dy = Math.abs(dy); dy > 0; dy--){
                stepForward();
            }

            if (isthrowException){
                throw new NullPointerException("ANOTHER EXCEPTION");
            }

            if (isthrowMove) {
                throw new RobotConnectionException("MOVE EXCEPTION");
            }
        }
    }

    public interface RobotConnection extends AutoCloseable {
        void moveRobotTo(int x, int y);
        @Override
        void close();
    }

    public interface RobotConnectionManager {
        RobotConnection getConnection();
    }

    public static class RobotConnectionException extends RuntimeException {

        public RobotConnectionException(String message) {
            super(message);

        }

        public RobotConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static void main(String[] args) {
        try{
            RobotConnectionManagerImplementation rcm = new RobotConnectionManagerImplementation(3, 3, false, 1);
            moveRobot(rcm, 2, 2);
        } catch (Exception r){
            System.out.println("TEST #1 " + ((r.getMessage() == "MOVE EXCEPTION") ? "PASS" : "FAILED WITH " + r.getMessage()));
        }

        try{
            RobotConnectionManagerImplementation rcm = new RobotConnectionManagerImplementation(0, 0, false, 0);
            moveRobot(rcm, 2, 2);
            System.out.println("TEST #2 PASS");
        } catch (RobotConnectionException r){
            System.out.println("TEST #2 FAILED WITH " + r.getMessage());
        }

        try{
            RobotConnectionManagerImplementation rcm = new RobotConnectionManagerImplementation(2, 0, false, 0);
            moveRobot(rcm, 2, 2);
            System.out.println("TEST #3 PASS");
        } catch (Exception r){
            System.out.println("TEST #3 FAILED WITH " + r.getMessage());
        }

        try{
            RobotConnectionManagerImplementation rcm = new RobotConnectionManagerImplementation(3, 0, false, 0);
            moveRobot(rcm, 2, 2);
        } catch (Exception r){
            System.out.println("TEST #4 " + ((r.getMessage() == "MOVE EXCEPTION") ? "PASS" : "FAILED WITH " + r.getMessage()));
        }

        try{
            RobotConnectionManagerImplementation rcm = new RobotConnectionManagerImplementation(3, 0, true, 0);
            moveRobot(rcm, 2, 2);
        } catch (Exception r){
            System.out.println("TEST #5 " + ((r.getMessage() == "ANOTHER EXCEPTION") ? "PASS" : "FAILED WITH " + r.getMessage()));
        }

        try{
            RobotConnectionManagerImplementation rcm = new RobotConnectionManagerImplementation(2, 5, false, 0);
            moveRobot(rcm, 2, 2);
            System.out.println("TEST #6 PASS");
        } catch (Exception r){
            System.out.println("TEST #6 FAILED WITH " + r.getMessage());
        }

        try{
            RobotConnectionManagerImplementation rcm = new RobotConnectionManagerImplementation(0, 5, false, 2);
            moveRobot(rcm, 2, 2);
            System.out.println("TEST #7 PASS");
        } catch (Exception r){
            System.out.println("TEST #7 FAILED WITH " + r.getMessage());
        }

        try{
            RobotConnectionManagerImplementation rcm = new RobotConnectionManagerImplementation(1, 0, false, 2);
            moveRobot(rcm, 2, 2);
        } catch (Exception r){
            System.out.println("TEST #8 " + ((r.getMessage() == "MOVE EXCEPTION") ? "PASS" : "FAILED WITH " + r.getMessage()));
        }
    }
}