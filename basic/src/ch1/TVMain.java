package ch1;

public class TVMain {
    public static void main(String[] args) {
        // // LgTV 사용
        // LgTV lgTV = new LgTV();

        // lgTV.setBritzSpeaker(new BritzSpeaker());

        // lgTV.powerOn();
        // lgTV.volumeUp();
        // lgTV.volumeDown();
        // lgTV.powerOff();

        // SamsungTV samsungTV = new SamsungTV();
        // samsungTV.setBritzSpeaker(new BritzSpeaker());
        // samsungTV.powerOn();
        // samsungTV.volumeUp();
        // samsungTV.volumeDown();
        // samsungTV.powerOff();

        TV tv = new SamsungTV();

        // ((SamsungTV) tv).setBritzSpeaker(new SonySpeaker());

        tv = new LgTV();
        ((LgTV) tv).setSpeaker(new BritzSpeaker());

        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();
    }
}
