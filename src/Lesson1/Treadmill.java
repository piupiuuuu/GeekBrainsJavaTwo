package Lesson1;

public class Treadmill extends Barrier {
    private final int size;

    public Treadmill(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isPassed(Able able) {
        if(able.run(getSize())) {
            System.out.println("Продолжает испытание.");
            return true;
        } else {
            System.out.println("Завершает прохождение испытания.");
            return false;
        }
    }

}
