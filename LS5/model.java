package LS5;

public class model {
    // высчитываем формулу
    static int triangularNumber(int n) {
        int resultNum = ((n + 1) * n) / 2;
        return resultNum;
    }

    // создаем массив и заполняем его в виде треугольника
    static int[][] createArray(int n) {
        int[][] array = new int[n][n];
        return array;
    }

    // визуализируем
    static int[][] arrayTriangular(int[][] array, int n) {
        for (int f = 1; f < n;) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < f; j++) {
                    array[i][j] = f;
                }
                f++;
            }
        }

        return array;
    }

    static void printRow(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] > 0) {
                    System.out.printf((char) 27 + "\033[1;93m" + "o" + (char) 27 + "[0m");
                } else if (arr[i][j] == 0) {
                    System.out.print(" ");
                }
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
