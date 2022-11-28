package LS1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализовать функцию возведения числа а в степень b. a, b ∈ Z. Сводя количество выполняемых действий к минимуму.
 * Пример 1: а = 3, b = 2, ответ: 9
 * Пример 2: а = 2, b = -2, ответ: 0.25
 * Пример 3: а = 3, b = 0, ответ: 1
 * Пример 4: а = 0, b = 0, ответ: не определено
 * Пример 5
 * входные данные находятся в файле input.txt в виде
 * b 3
 * a 10
 * Результат нужно сохранить в файле output.txt
 * 1000
 */

public class Hw1 {
    public static void main(String[] args) throws IOException {
        System.out.println(pow(2, -2)); // проверяем как работает минусовая степень
        System.out.println(pow(5, 0)); // проверяем как работает нулевая степень
        Path pathInput = Path.of("src\\Lesson1\\Input.txt");
        Path pathOutput = Path.of("src\\Lesson1\\Output.txt");
        String result = Double.toString(pow(numParse(pathInput).get(1), numParse(pathInput).get(0)));
        Files.writeString(pathOutput, result);


    }

    public static List<Integer> numParse(Path path) throws IOException {
        List<String> originList = Files.readAllLines(path); // создаем список из строк файла
        List<Integer> listOfInts = new ArrayList<>();
        for (String s : originList) { //создаем список интов
            listOfInts.add(Integer.parseInt(s.replaceAll("[^0-9?!\\.]", "")));
        }
        return listOfInts;
    }

    public static double pow(int a, int b) {
        double result = 1;
        for (int i = 1; i <= Math.abs(b); i++) {
            result *= a;
        }
        if (b < 0) {
            result = 1 / result;
        } else if (a == 0) {
            System.out.println("Не определено");
            result = 0;
        } else if (b == 0) {
            result = 1;
        }
        return result;
    }
}