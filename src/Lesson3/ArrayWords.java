package Lesson3;
import java.util.*;

/**
 * Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов,
 * из которых состоит массив (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
 */

public class ArrayWords {
    // выводит слова, которые не повторяются (в одном экземпляре)
    public static void getUniqueWords1(String[] array) {
        // V getOrDefault(Object k, V defaultValue): возвращает значение объекта, ключ которого равен k
        Map<String, Integer> map = new HashMap<>();
        for (String s : array) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for(Map.Entry<String, Integer> m : map.entrySet()) {
            if(m.getValue() == 1) {
                System.out.print(m.getKey() + " ");
            }
        }
        System.out.println(" ");
    }

    // выводит слова, у которых нет повторов
    public static void getUniqueWords2(String[] array) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set, array);
        set.forEach(a -> System.out.print(a + " "));
        System.out.println(" ");
    }

    //сколько раз встречается слово в массиве
    public static void createMap(String[] array) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : array) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        map.forEach((a,b) -> System.out.println("Слово: " + a + ", количество повторов: " + b));
    }

    public static void main(String[] args) {
        String[] array = {"aaa", "bbb", "ccc", "ddd", "eee", "fff", "aaa", "aaa", "aaa", "aaa",
                "ccc", "ccc", "ddd", "ddd", "ddd", "ddd", "ddd", "ddd", "ddd", "fff",};

        getUniqueWords1(array);
        getUniqueWords2(array);
        createMap(array);
    }
}
