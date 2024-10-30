package stream;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lambda.Student;

public class StreamEx3 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("바둑", "바나나", "포도", "딸기", "바질", "강아지");

        // 바로 시작하는 문자 출력
        for (String s : list) {
            if (s.startsWith("바")) {
                System.out.println(s);
            }
        }

        // 스트림
        // 스트림 변환 => 연산 => 출력
        list.stream().filter(s -> s.startsWith("바")).forEach(System.out::println);

        List<Student> list2 = Arrays.asList(new Student("홍길동", 90, 96),
                new Student("김수정", 80, 85),
                new Student("송혜교", 80, 85),
                new Student("김희원", 80, 85));

        // 성이 김으로 시작하는 학생의 이름 출력
        for (Student student : list2) {
            if (student.getName().startsWith("김")) {
                System.out.println(student.getName());
            }
        }

        // 성이 김으로 시작하는 학생들만 새로운 리스트에 담고 출력
        list2
                .stream()
                .map(s -> s.getName()) // 이름만 모으기
                .filter(stu -> stu.startsWith("김")) // 김으로 시작하는 학생 모으기
                .collect(Collectors.toList()) // 리스트로 수집
                .forEach(System.out::println); // 출력

        list2
                .stream()
                .filter(stu -> stu.getName().startsWith("김")) // 김으로 시작하는 Student
                .peek(s -> System.out.println(s)) // 중간 결과 출력
                .map(s -> s.getName()) // 이름만 모으기
                .collect(Collectors.toList()) // 리스트로 수집
                .forEach(System.out::println); // 출력

        // 짝수 출력
        IntStream
                .rangeClosed(1, 10)
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);

        // distinct(): 중복 제거
        list = Arrays.asList("바둑", "바나나", "포도", "딸기", "바질", "강아지", "바둑");
        list.stream().distinct().forEach(System.out::println);

        // 파일 확장자를 추출하고 중복 제거한 후 출력
        File[] files = { new File("file.txt"), new File("file2.txt"), new File("Ex1"),
                new File("Ex2.bak"), new File("test.java") };

        // 스트림 변환 => 중간연산 - 이름 모으기 / 확장자만 추출 / 중복 제거 => 최종연산 - 출력
        Arrays
                .stream(files)
                .map(f -> f.getName())
                .filter(f -> f.contains(".")) // 확장자가 있는 파일명만
                .map(f -> f.substring(f.indexOf(".") + 1)) // 확장자만 수집
                .distinct()
                .forEach(System.out::println);

        list.stream().skip(2).limit(3).forEach(System.out::println);
    }
}
