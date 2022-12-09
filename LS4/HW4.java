package LS4;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class HW4 {
 
    static int size;
    static int[] start = new int[size];
    static int[] finishOne = new int[size];
    static int[] finishTwo = new int[size];
    static int[][] pointIndexes = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public static void main(String[] args) {
        int height = 15;
        int width = 40;
        int[] size = {height, width};
        int[][] map = createField(size);
        fillField(map);
        searchWay(map);
        drawingField(map);
    }

    /**
     * Создаем поле, точку старта и 2 точки финиша. Точке старта присваем значение -2, точкам финиша -3. Понадобится
     * далее для отрисовки
     */
    public static int[][] createField(int[] size) {
        int[][] field = new int[size[0]][size[1]];

        for (int i = 0; i < size[1]; i++) {
            field[0][i] = -1;
        }
        for (int i = 0; i < size[1]; i++) {
            field[size[0] - 1][i] = -1;
        }
        for (int i = 0; i < size[0]; i++) {
            field[i][0] = -1;
        }
        for (int i = 0; i < size[0]; i++) {
            field[i][size[1] - 1] = -1;
        }
        int tmp;
        for (int i = 1; i < size[0] - 1; i++) {
            for (int j = 1; j < size[1] - 1; j++) {
                tmp = ThreadLocalRandom.current().nextInt(0, 8);
                if (tmp == 7) {
                    field[i][j] = -1;
                }
            }
        }
        int startPoint = -2;
        int finishPoint = -3;
        start = choosePoint(startPoint, field);
        finishOne = choosePoint(finishPoint, field);
        finishTwo = choosePoint(finishPoint, field);
        return field;
    }

    public static void fillField(int[][] map) {
        LinkedList<int[]> list = new LinkedList<>();
        list.offer(start);
        while (list.peek() != null) {
            int[] currentPoint = list.poll();
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (map[currentPoint[0] + i][currentPoint[1] + j] == 0) {
                        int[] temp = new int[3];
                        temp[0] = currentPoint[0] + i;
                        temp[1] = currentPoint[1] + j;
                        temp[2] = currentPoint[2] + 1;
                        list.offer(temp);
                        map[currentPoint[0] + i][currentPoint[1] + j] = temp[2];
                    }
                }
            }
        }
    }

    /**
     * Данный метод реализует поиск кратчайшего пути между заданными точками старта и финиша. Кратчайший путь обозначаем
     * как -4, понабодится далее для отрисовки.
     */
    public static void searchWay(int[][] map) {
        searchMinValue(finishOne, map);
        searchMinValue(finishTwo, map);
        int min;
        int[] currentPoint = new int[2];
        if (finishOne[2] < finishTwo[2]) {
            currentPoint[0] = finishOne[0] + pointIndexes[finishOne[3]][0];
            currentPoint[1] = finishOne[1] + pointIndexes[finishOne[3]][1];
            min = finishOne[2];
        } else {
            currentPoint[0] = finishTwo[0] + pointIndexes[finishTwo[3]][0];
            currentPoint[1] = finishTwo[1] + pointIndexes[finishTwo[3]][1];
            min = finishTwo[2];
        }
        map[currentPoint[0]][currentPoint[1]] = -4;

        while (min > 0) {
            for (int i = 0; i < 8; i++) {
                if (map[currentPoint[0] + pointIndexes[i][0]][currentPoint[1] + pointIndexes[i][1]] < min
                        && (map[currentPoint[0] + pointIndexes[i][0]][currentPoint[1] + pointIndexes[i][1]] > 0
                        || map[currentPoint[0] + pointIndexes[i][0]][currentPoint[1] + pointIndexes[i][1]] == -2)) {
                    min = map[currentPoint[0] + pointIndexes[i][0]][currentPoint[1] + pointIndexes[i][1]];
                    currentPoint[0] = currentPoint[0] + pointIndexes[i][0];
                    currentPoint[1] = currentPoint[1] + pointIndexes[i][1];
                    if (min == -2) ;
                    else map[currentPoint[0]][currentPoint[1]] = -4;
                    break;
                }
            }
        }
    }

    /**
     * Ищем свободную ячейки для установки точек входа - выхода
     */
    public static int[] choosePoint(int sign, int[][] map) {
        int sizeY = map.length - 1;
        int sizeX = map[0].length - 1;
        int coordinateY;
        int coordinateX;
        int[] point = new int[4];
        boolean check = false;
        while (!check) {
            coordinateX = ThreadLocalRandom.current().ints(1, sizeX).findFirst().getAsInt();
            coordinateY = ThreadLocalRandom.current().ints(1, sizeY).findFirst().getAsInt();
            if (map[coordinateY][coordinateX] == 0) {
                map[coordinateY][coordinateX] = sign;
                point[0] = coordinateY;
                point[1] = coordinateX;
                point[2] = 0;
                point[3] = 0;
                check = true;
            }
        }
        return point;
    }

    /**
     * Ищит минимальное значение в окресности точки. Записывает в переданный массив под индексом 2,
     * минимальное значение в окресности точки, а под индексом 3 номер соответсвующей точки
     */
    public static void searchMinValue(int[] point, int[][] map) {
        for (int i = 0; i < 8; i++) {
            if (map[point[0] + pointIndexes[i][0]][point[1] + pointIndexes[i][1]] > 0 &&
                    point[2] > map[point[0] + pointIndexes[i][0]][point[1] + pointIndexes[i][1]]) {
                point[2] = map[point[0] + pointIndexes[i][0]][point[1] + pointIndexes[i][1]];
                point[3] = i;
            } else if (map[point[0] + pointIndexes[i][0]][point[1] + pointIndexes[i][1]] > 0 && point[2] == 0) {
                point[2] = map[point[0] + pointIndexes[i][0]][point[1] + pointIndexes[i][1]];
                point[3] = i;
            }
        }
    }

    /**
     * Рисуем поле.
     *
     * @param map передаем массив массивов, пробегаем циклом и меняем обусловленные ячейки на значения, используемые
     *            для отрисовки поля.
     */
    public static void drawingField(int[][] map) {
        int sizeY = map.length;
        int sizeX = map[0].length;
        for (int[] ints : map) {
            for (int j = 0; j < sizeX; j++) {
                if (ints[j] == -1) System.out.print('┃');
                else if (ints[j] == 0) System.out.print(' ');
                else if (ints[j] == -2) System.out.print('⒮');
                else if (ints[j] == -3) System.out.print('⒡');
                else if (ints[j] == -4) System.out.print('♙');
                else System.out.print(" ");
            }
            System.out.println();
        }
    }
}

