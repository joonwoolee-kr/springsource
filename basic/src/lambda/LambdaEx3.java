package lambda;

@FunctionalInterface
interface Lambda3 {
    void method(int x);
}

@FunctionalInterface
interface Lambda4 {
    int method(int x, int y);
}

@FunctionalInterface
interface Lambda5 {
    int min(int x, int y);
}

public class LambdaEx3 {
    public static void main(String[] args) {
        Lambda3 obj = (x) -> System.out.println(x);
        obj.method(10);

        obj = (x) -> System.out.println(x * x);
        obj.method(10);

        Lambda4 obj1 = (x, y) -> x > y ? x : y;
        int result = obj1.method(5, 3);
        System.out.println("큰 수는 " + result);

        Lambda5 obj2 = (x, y) -> x < y ? x : y;
        result = obj2.min(5, 3);
        System.out.println("작은 수는 " + result);
    }
}
