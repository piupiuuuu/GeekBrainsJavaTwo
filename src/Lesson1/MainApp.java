package Lesson1;

/**
 * 1.Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса. Эти классы должны уметь бегать и прыгать
 * (методы просто выводят информацию о действии в консоль).
 * 2.Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие действия
 * (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
 * 3.Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
 * 4.У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки. Если участник не смог
 * пройти одно из препятствий, то дальше по списку он препятствий не идет.
 */

public class MainApp {
    public static void main(String[] args) {
        Barrier[] barriers = {
                new Treadmill(500),
                new Wall(2),
                new Treadmill(10000),
                new Wall(20)
        };

       Able[] ables = {
                new Human("Василий"),
                new Robot("Диего"),
                new Cat("Барсик")
        };

        for(Able able : ables) {
            for(Lesson1.Barrier barrier : barriers) {
                if(!barrier.isPassed(able)) break;
            }
        }
    }
}