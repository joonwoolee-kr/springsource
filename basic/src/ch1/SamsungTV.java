package ch1;

public class SamsungTV implements TV {

    // 멤버변수(== 인스턴스 변수, 필드, 특성, 속성)
    private Speaker speaker;

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void powerOn() {
        System.out.println("SamsungTV - 전원 On");
    }

    @Override
    public void powerOff() {
        System.out.println("SamsungTV - 전원 Off");
    }

    @Override
    public void volumeUp() {
        // System.out.println("SamsungTV - 볼륨 Up");
        speaker.volumeUp();
    }

    @Override
    public void volumeDown() {
        // System.out.println("SamsungTV - 볼륨 Down");
        speaker.volumeDown();
    }
}
