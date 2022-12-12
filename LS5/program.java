package LS5;

public class program {
    public static void main(String[] args) {
        // Введите n для вычесления треугольного числа
        int n = 9;
        int result = model.triangularNumber(n);
        int[][] array = model.createArray(n);
        array = model.arrayTriangular(array, n);
        System.out.printf((char) 27 + "[34m" + "\t\t=============================================================" + (char) 27 + "[0m\n");
        System.out.printf((char) 27 + "[33m" + "\t\t|| Напишите программу вычисления n-ого треугольного числа. ||" + (char) 27 + "[0m\n");
        System.out.printf((char) 27 + "[34m" + "\t\t=============================================================" + (char) 27 + "[0m\n");
        System.out.println();
        System.out.printf("Треугольное число от " + n + " = " + result + "\n");
        System.out.println();
        model.printRow(array);
    }
        
    }
    