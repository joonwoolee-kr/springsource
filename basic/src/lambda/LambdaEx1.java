package lambda;

// 람다식: 메소드를 하나의 식으로 표현
// 리턴타입 메소드명 제거
// 1) (int a, int b) -> { return a > b ? a : b; }
// 2) return 구문 생략 가능(실행할 구문이 하나인 경우 / {} 생략)
// (int a, int b) -> a > b ? a : b;
// 3) 매개변수가 추측이 가능한 상태라면 타입 생략 가능
// (a, b) -> a > b ? a : b;

// (x) -> x * x;
// () -> (int) (Math.random() * 6);
// (int[] arr) -> { 안 내용 동일 }

class Lambda1 {
    int max(int a, int b) {
        return a > b ? a : b;
    }

    int square(int x) {
        return x * x;
    }

    int roll() {
        return (int) (Math.random() * 6);
    }

    int sumArr(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }
}

public class LambdaEx1 {
    public static void main(String[] args) {
        Lambda1 obj = new Lambda1();
        System.out.println(obj.max(6, 3));
    }
}