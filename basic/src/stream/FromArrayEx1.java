package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import lambda.Student;

// 스트림
// 배열 => 스트림, 리스트 => 스트림
// 중간 연산 기능: 필터링, 매핑, 그룹
// map(): 스트림 요소에 저장된 값 중에서 원하는 필드만 추출하거나 특정 형태로 변환하는 경우 사용
// filter(): 조건과 일치하는 요소
// peak(): 중간 확인
// distinct(): 중복 제거
// skip(): 건너뛸 개수
// limit(): 스트림 요소를 개수 제한
// 최종 연산 기능: 합계, 평균, 개수, 최소, 최대
// forEach(), collect()
// 특징: 일회용

public class FromArrayEx1 {
    public static void main(String[] args) {
        String[] strArr = { "사과", "바나나", "딸기", "메론" };

        // 배열 => 스트림
        Stream<String> stream = Arrays.stream(strArr);
        stream.forEach(s -> System.out.println(s));

        // 리스트 => 스트림
        List<String> list = Arrays.asList("사과", "딸기", "수박", "바나나", "배", "메론");
        Stream<String> stream2 = list.stream();
        stream2.forEach(s -> System.out.println(s));

        List<Student> students = Arrays.asList(new Student("홍길동", 90, 96), new Student("김수정", 80, 85));
        Stream<Student> stream3 = students.stream();
        stream3.forEach(s -> System.out.println(s.getName() + ": " + s.getEng()));

        // 디렉토리에서 파일 읽어오기
        Path path = Paths.get("c:\\upload");
        try (Stream<Path> stream4 = Files.list(path)) {
            stream4.forEach(f -> System.out.println(f.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 정수 범위(long)를 소스로 하는 스트림 생성
        LongStream longStream = LongStream.rangeClosed(1, 10);
        longStream.forEach(l -> System.out.println(l));
        // 정수 범위(int)를 소스로 하는 스트림 생성
        IntStream intStream = IntStream.rangeClosed(1, 10);
        intStream.forEach(l -> System.out.println(l));
        intStream = IntStream.range(1, 10);
        intStream.forEach(l -> System.out.println(l));
    }
}
