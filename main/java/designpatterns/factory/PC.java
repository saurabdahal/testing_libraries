package designpatterns.factory;

public class PC extends ComputerSuperClass {

    private String cpu;
    private String hdd;
    private String ram;

    public PC(String cpu, String ram,String hdd) {
        this.cpu = cpu;
        this.ram = ram;
        this.hdd = hdd;
    }

    String getCPU() {
        return this.cpu;
    }

    String getHDD() {
        return this.hdd;
    }

    String getRAM() {
        return this.ram;
    }
}
