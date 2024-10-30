package stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class StreamEx1 {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("사과", "포도", "귤", "바나나", "수박");
        String[] strArr = { "비행기", "자동차", "배", "자전거", "버스" };

        // 배열 정렬
        // System.out.println("정렬 전 " + Arrays.toString(strArr));
        // Arrays.sort(strArr); // 원본 자체가 정렬됨
        // System.out.println("정렬 후 " + Arrays.toString(strArr));

        // 컬렉션(List, Set) 정렬
        // System.out.println("정렬 전 " + strList);
        // Collections.sort(strList); // 원본 자체가 정렬됨
        // System.out.println("정렬 후 " + strList);

        // 원본이 아닌 복사본 정렬 => 스트림 처리
        Stream<String> stream1 = Arrays.stream(strArr);
        Stream<String> stream2 = strList.stream();

        // 정렬 후
        stream1.sorted().forEach(System.out::print);
        System.out.println();
        stream2.sorted().forEach(System.out::print);
        System.out.println();

        // 원본
        System.out.println("원본 " + Arrays.toString(strArr));
        System.out.println("원본 " + strList);

    }
}
