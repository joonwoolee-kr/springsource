package lambda;

public class Student {
    private String name;
    private int math;
    private int eng;

    public Student(String name, int math, int eng) {
        this.name = name;
        this.math = math;
        this.eng = eng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", math=" + math + ", eng=" + eng + "]";
    }

}
