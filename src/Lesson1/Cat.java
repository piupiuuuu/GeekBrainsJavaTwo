package Lesson1;

public class Cat implements Able {

    private final String name;
    private static final int LIMIT_RUN = 500;
    private static final int LIMIT_JUMP = 10;

    public Cat(String name) {
        this.name = name;
    }

    public String getInfo() {
        return "Кот " + this.name;
    }

    public static int getLimitRun() {
        return LIMIT_RUN;
    }

    public static int getLimitJump() {
        return LIMIT_JUMP;
    }

    @Override
    public boolean jump(int distance) {
        if( getLimitJump() >= distance) {
            System.out.print(getInfo() + " успешно прыгнул: " + distance + " м. ");
            return true;
        } else {
            System.out.print(getInfo() + " не смог прыгнуть: " + distance + " м. ");
            return false;
        }
    }

    @Override
    public boolean run(int distance) {
        if( getLimitRun() >= distance) {
            System.out.print(getInfo() + " успешно пробежал: " + distance + " м. ");
            return true;
        } else {
            System.out.print(getInfo() + " не смог пробежать: " + distance + " м. ");
            return false;
        }
    }
}