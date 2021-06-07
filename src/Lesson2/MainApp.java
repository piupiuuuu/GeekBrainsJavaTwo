package Lesson2;

public class MainApp {

    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if(array.length != 4) throw new MyArraySizeException();
        int sum = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i].length != 4) throw new MyArraySizeException();
            for(int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        try {
            String[][] array = {
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
            };
            System.out.println(sumArray(array));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        try {
            String[][] array = {
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
            };
            System.out.println(sumArray(array));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        try {
            String[][] array = {
                    {"1", "1", "1", "1"},
                    {"1", "A", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
            };
            System.out.println(sumArray(array));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        try {
            String[][] array = {
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
            };
            System.out.println(sumArray(array));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }


}
