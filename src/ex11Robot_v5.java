public class ex11Robot_v5 {
    public static void main(String[] args) {
        Robot robot = new Robot(Direction.RIGHT, 10, 10);
        moveRobot(robot, 11, 3);
    }

    public enum Direction {
        UP, LEFT, DOWN, RIGHT;
    }

    public static class Robot {
        Direction direction;
        private int x;
        private int y;

        Robot(Direction d, int xCord, int yCord) {
            this.direction = d;
            this.x = xCord;
            this.y = yCord;
        }

        public Direction getDirection() {
            return direction;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void turnLeft() {
            switch (direction) {
                case UP -> direction = Direction.LEFT;
                case RIGHT -> direction = Direction.UP;
                case DOWN -> direction = Direction.RIGHT;
                case LEFT -> direction = Direction.DOWN;
            }
        }

        public void turnRight() {
            // повернуться на 90 градусов по часовой стрелке
            switch (direction) {
                case UP -> direction = Direction.RIGHT;
                case RIGHT -> direction = Direction.DOWN;
                case DOWN -> direction = Direction.LEFT;
                case LEFT -> direction = Direction.UP;
            }
        }

        public void stepForward() {
            // шаг в направлении взгляда
            // за один шаг робот изменяет одну свою координату на единицу
            switch (direction) {
                case UP -> y++;
                case RIGHT -> x++;
                case DOWN -> y--;
                case LEFT -> x--;
            }
        }
    }

    public static void moveRobot(Robot robot, int toX, int toY) { // за 4 цикла
        int dx = robot.getX() - toX, dy = robot.getY() - toY;

        Direction dirX = dx > 0 ? Direction.LEFT : Direction.RIGHT;
        Direction dirY = dy > 0 ? Direction.DOWN : Direction.UP;

        dx = Math.abs(dx);
        dy = Math.abs(dy);

        while (robot.getDirection() != dirX) robot.turnRight();
        while (dx-- > 0) robot.stepForward();

        while (robot.getDirection() != dirY) robot.turnRight();
        while (dy-- > 0) robot.stepForward();

        System.out.println(robot.getX() + ", " + robot.getY());
    }
}
