package LS3;

public class HW3 {

    public static void main(String[] args) {
        int a = 22;
        int b = 2022;
        int c = 3;
        int d = 1;
        System.out.println(recursiveFind(a, b, c, d));
        String showMinStepsWay = minWaySteps(a, b, c, d);
        System.out.printf(showMinStepsWay);
    }

    static int recursiveFind(int a, int b, int c, int d) {
        if (a == b) return 1;
        if (a > b) return 0;
        else return recursiveFind(a + d, b, c, d) + recursiveFind(a * c, b, c, d);
    }

    static String minWaySteps(int a, int b, int c, int d) {
        StringBuilder answer = new StringBuilder("-> " + b);
        int count = 0;
        while (a != b) {
            if (b % c == 0 && b / c >= a) {
                answer.insert(0, " -> (a * c)");
                b = b / c;
                count++;
            } else if (b - d >= a) {
                answer.insert(0, " -> (a + d)");
                b = b - d;
                count++;
            } else {
                answer = new StringBuilder("Impossible!");
                return answer.toString();
            }
        }
        answer.insert(0, "Min amount of steps: " + count + ". The way is:");
        return answer.toString();
    }
}