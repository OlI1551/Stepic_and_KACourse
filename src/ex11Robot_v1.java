public class ex11Robot_v1 {
    public static void main(String[] args) {

        int randomDirection = (int) (Math.random() * 3 + 1);
        int startX = (int) (Math.random() * 20 - 10);
        int startY = (int) (Math.random() * 20 - 10);
        int finishX = (int) (Math.random() * 20 - 10);
        int finishY = (int) (Math.random() * 20 - 10);

        Robot robot = switch (randomDirection) {
            case 1 -> new Robot(startX, startY, Direction.DOWN);
            case 2 -> new Robot(startX, startY, Direction.RIGHT);
            case 3 -> new Robot(startX, startY, Direction.UP);
            case 4 -> new Robot(startX, startY, Direction.LEFT);
            default -> throw new IllegalStateException("Unexpected value: " + randomDirection);
        };
        moveRobot(robot, finishX, finishY);
        System.out.println(finishX + ", " + finishY);
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static class Robot {
        private int x;
        private int y;
        private Direction dir;

        public Robot(int x, int y, Direction dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public Direction getDirection() {
            return dir;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void turnLeft() {
            if (dir == Direction.UP) {
                dir = Direction.LEFT;
            } else if (dir == Direction.DOWN) {
                dir = Direction.RIGHT;
            } else if (dir == Direction.LEFT) {
                dir = Direction.DOWN;
            } else if (dir == Direction.RIGHT) {
                dir = Direction.UP;
            }
        }

        public void turnRight() {
            if (dir == Direction.UP) {
                dir = Direction.RIGHT;
            } else if (dir == Direction.DOWN) {
                dir = Direction.LEFT;
            } else if (dir == Direction.LEFT) {
                dir = Direction.UP;
            } else if (dir == Direction.RIGHT) {
                dir = Direction.DOWN;
            }
        }

        public void stepForward() {
            if (dir == Direction.UP) {
                y++;
            }
            if (dir == Direction.DOWN) {
                y--;
            }
            if (dir == Direction.LEFT) {
                x--;
            }
            if (dir == Direction.RIGHT) {
                x++;
            }
        }
    }


    public static void moveRobot(Robot robot, int toX, int toY) {
        while (robot.getDirection() != Direction.UP) {
            robot.turnLeft();
        }
        if ((robot.getX() <= toX) && (robot.getY() <= toY)) {
            robot.turnRight();
        } else if ((robot.getX() <= toX) && (robot.getY() >= toY)) {
            robot.turnRight();
            robot.turnRight();
        } else if ((robot.getX() >= toX) && (robot.getY() <= toY)) {
            robot.turnLeft();
        } else if ((robot.getX() >= toX) && (robot.getY() >= toY)) {
            robot.turnLeft();
            robot.turnLeft();
        }
        while (robot.getX() != toX) {
            robot.stepForward();
        }
        robot.turnLeft();
        while (robot.getY() != toY) {
            robot.stepForward();
        }
        System.out.println(robot.getX() + ", " + robot.getY());// пишите тут свой код на здоровье
    }
}