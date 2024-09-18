import java.math.*;
import java.util.Arrays;

public class ex11Robot_v2 {
    public static void main(String[] args) {
        Robot robot = new Robot(0,0, Direction.DOWN);
        moveRobot(robot, -10, 20);
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static class Robot {
        int x;
        int y;
        Direction dir;

        public Robot (int x, int y, Direction dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
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
    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        int x = robot.getX();
        int y = robot.getY();

        //1 quadrant
        if (x >= toX && y >= toY) {

            //turn robot left, since it's to the right side of toX coordinate
            while (!(robot.getDirection() == Direction.LEFT)) { //while not looking left
                robot.turnRight();
            }
            //move to right x coordinate
            while (!(robot.getX() == toX)) {
                robot.stepForward();
            }

            //turn robot down, sice it's upper than toY
            while (!(robot.getDirection() == Direction.DOWN)) { //while not looking left
                robot.turnRight();
            }
            //move to right y coordinate
            while (!(robot.getY() == toY)) {
                robot.stepForward();
            }

        }

        //2 quadrant
        else if (x >= toX && y <= toY) {

            //turn robot left, since it's to the right side of toX coordinate
            while (!(robot.getDirection() == Direction.LEFT)) { //while not looking left
                robot.turnRight();
            }
            //move to right x coordinate
            while (!(robot.getX() == toX)) {
                robot.stepForward();
            }

            //turn robot down, sice it's lower than toY
            while (!(robot.getDirection() == Direction.UP)) { //while not looking left
                robot.turnRight();
            }
            //move to right y coordinate
            while (!(robot.getY() == toY)) {
                robot.stepForward();
            }
        }

        //3 quadrant
        else if (x <= toX && y <= toY) {

            //turn robot left, since it's to the left of toX coordinate
            while (!(robot.getDirection() == Direction.RIGHT)) { //while not looking right
                robot.turnRight();
            }
            //move to right x coordinate
            while (!(robot.getX() == toX)) {
                robot.stepForward();
            }

            //turn robot down, sice it's lower than toY
            while (!(robot.getDirection() == Direction.UP)) { //while not looking left
                robot.turnRight();
            }
            //move to right y coordinate
            while (!(robot.getY() == toY)) {
                robot.stepForward();
            }
        }

        //4 quadrant
        else if (x <= toX && y >= toY) {

            //turn robot left, since it's to the right side of toX coordinate
            while (!(robot.getDirection() == Direction.RIGHT)) { //while not looking right
                robot.turnRight();
            }
            //move to right x coordinate
            while (!(robot.getX() == toX)) {
                robot.stepForward();
            }

            //turn robot down, sice it's upper than toY
            while (!(robot.getDirection() == Direction.DOWN)) { //while not looking left
                robot.turnRight();
            }
            //move to right y coordinate
            while (!(robot.getY() == toY)) {
                robot.stepForward();
            }
        }
        System.out.println(robot.getX() + ", " + robot.getY());// пишите тут свой код на здоровье
    }
}