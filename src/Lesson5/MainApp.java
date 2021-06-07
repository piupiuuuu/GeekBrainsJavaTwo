package Lesson5;

import java.util.Arrays;

/**
 * 1) Создают одномерный длинный массив
 * 2) Заполняют этот массив единицами;
 * 3) Засекают время выполнения: long a = System.currentTimeMillis();
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * 5) Проверяется время окончания метода System.currentTimeMillis();
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
 * Отличие первого метода от второго:
 * Первый просто бежит по массиву и вычисляет значения.
 * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
 */

public class MainApp {

    public static void main(String[] args) {
        float[] array = new float[1000000];
        System.out.println(countTimeOneTread(array));
        System.out.println(countTimeTwoTread(array));
    }

    public static long countTimeOneTread(float[] array) {
        Arrays.fill(array, 1.0f);
        long time1 = System.currentTimeMillis();
        for(int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return System.currentTimeMillis() - time1;
    }

    public static long countTimeTwoTread(float[] array) {
        int size = array.length/2;
        Arrays.fill(array, 1.0f);
        long time1 = System.currentTimeMillis();

        Thread thread1 = new Thread (() -> {
            float[] array1 = new float[size];
            System.arraycopy(array,0, array1, 0, size);
            for(int i = 0; i < array1.length; i++) {
                array1[i] = (float)(array1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(array1,0, array, 0, size);
        });

        Thread thread2 = new Thread (() -> {
            float[] array2 = new float[size];
            System.arraycopy(array,size, array2, 0, size);
            for(int i = 0; i < array2.length; i++) {
                array2[i] = (float)(array2[i] * Math.sin(0.2f + (i+size) / 5) * Math.cos(0.2f + (i+size) / 5) * Math.cos(0.4f + (i+size) / 2));
            }
            System.arraycopy(array2,0, array, size, size);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return System.currentTimeMillis() - time1;
    }

}

