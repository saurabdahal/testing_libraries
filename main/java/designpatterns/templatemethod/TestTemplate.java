package designpatterns.templatemethod;

public class TestTemplate {
    public static void main(String[] args) {
        Illam_tea it = new Illam_tea();
        DarjeelingTea dt = new DarjeelingTea();

        it.makeTea();
        dt.makeTea();
    }
}
