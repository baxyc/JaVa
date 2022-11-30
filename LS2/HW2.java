package LS2;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Реализовать алгоритм сортировки массива слиянием
 */

public class HW2 {
    public static void main(String[] args) {
        int[] arr1 = createArr(-10, 10, 15);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(mergeArr(arr1)));
    }

    /**
     * Создаем массив из рандомных чисел
     *
     * @param from начало границы рандома
     * @param to   конец границы рандома
     * @param size размер массива
     * @return созданный массив int[] arr
     */
    public static int[] createArr(int from, int to, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            int rand = ThreadLocalRandom.current().nextInt(from, to + 1);
            arr[i] = rand;
        }
        return arr;
    }

    /**
     * Реализация ручного метода сортировки массива слиянием. Вкратце:
     * На вход приходит массив, делим его на 2 равных(или примерно равных массива), оба массива сортируем
     * по возрастанию, затем сравниваем поэлементно каждый элемент из массивов и наименьший добавляем в итоговый массив
     * @param someArr - массив на вход, который надо отсортировать слиянием
     * @return возвращаем отсортированный массив
     */
    public static int[] mergeArr(int[] someArr) {
        int[] arr1 = divideArray(someArr, 1);
        int arr1Index = 0;
        sortArray(arr1);
        int[] arr2 = divideArray(someArr, 2);
        int arr2Index = 0;
        sortArray(arr2);
        int[] merged = new int[someArr.length];

        for (int i = 0; i < someArr.length; i++) {
            if (arr1Index == arr1.length) {
                merged[i] = arr2[arr2Index];
                arr2Index++;
            } else if (arr2Index == arr2.length) {
                merged[i] = arr1[arr1Index];
                arr1Index++;
            } else {
                if (arr1[arr1Index] < arr2[arr2Index]) {
                    merged[i] = arr1[arr1Index];
                    arr1Index++;
                } else {
                    merged[i] = arr2[arr2Index];
                    arr2Index++;
                }
            }
        }
        return merged;
    }

    /**
     * Метод для деления массива на две равные(или примерно равные, в случае, если длина массива нечетная) части
     * @param someArr - принимает на вход массив
     * @param half - индекс половины (1я или 2я половина массива)
     * @return возвращаем массив(необходимую половину массива)
     */
    public static int[] divideArray(int[] someArr, int half) {
        int size = 0;
        if (half == 1) {
            size = someArr.length / 2;
        } else {
            size = someArr.length - someArr.length / 2;
        }
        int[] arr = new int[size];
        if (half == 1) {
            for (int i = 0; i < size; i++) {
                arr[i] = someArr[i];
            }
        } else {
            if (someArr.length % 2 == 0) {
                for (int j = 0; j < size; j++) {
                    arr[j] = someArr[size + j];
                }
            } else {
                for (int j = 0; j < size; j++) {
                    arr[j] = someArr[size - 1 + j];
                }
            }
        }
        return arr;
    }

    /**
     * Метод ручной сортировки массива по возрастанию
     * @param someArr - принимаем массив на вход, который будем сортировать по возрастанию
     * @return возвращаем отсортированный массив
     */
    public static int[] sortArray(int[] someArr) {
        int temp = 0;
        for (int i = 0; i < someArr.length - 1; i++) {
            for (int j = 0; j < someArr.length; j++) {
                if (someArr[j] > someArr[i + 1]) {
                    temp = someArr[j];
                    someArr[j] = someArr[i + 1];
                    someArr[i + 1] = temp;
                }
            }
        }
        return someArr;
    }
}
