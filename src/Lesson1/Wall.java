package Lesson1;

public class Wall extends Barrier{
    private final int size;

    public Wall(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isPassed(Able able) {
        if(able.jump(getSize())) {
            System.out.println("Продолжает испытание.");
            return true;
        } else {
            System.out.println("Завершает прохождение испытания.");
            return false;
        }
    }
}
