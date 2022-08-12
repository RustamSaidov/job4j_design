package ru.job4j.condition;

/* Плохой код по принципу KISS:
public class SqMax {

    public static int max(int first, int second, int third, int forth) {
        int result = forth;
        if (first > second) {
            if (first > third) {
                if (first > forth) {
                    result = third;
                }
            }
        } else if (second > third) {
            if (second > forth) {
                result = first;
            }
        } else if (third > forth) {
            result = second;
        }
        return result;
    }
}
Хороший код по принципу KISS:
 */
public class SqMax {
    public static int max(int left, int right) {
        return left > right ? left : right;
    }

    public static int max(int first, int second, int third, int forth) {
        return max(
                max(first, second),
                max(third, forth)
        );
    }
}