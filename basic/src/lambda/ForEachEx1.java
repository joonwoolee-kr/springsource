package lambda;

import java.util.Arrays;
import java.util.List;

public class ForEachEx1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("사과", "딸기", "수박", "바나나", "배", "메론");

        for (String s : list) {
            System.out.println(s);
        }

        // forEach
        // <? super String>: String 타입이나 상위 타입
        // 하나씩 꺼내서 출력하는 용도로 주로 사용
        list.forEach((x) -> System.out.println(x));
    }
}
