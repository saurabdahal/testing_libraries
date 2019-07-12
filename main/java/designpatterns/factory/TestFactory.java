package designpatterns.factory;


public class TestFactory {
    public static void main(String[] args) {
        ComputerSuperClass pc = FactoryClass.getComputer("pc","2.4 GHz","2 GB","500 GB");
        ComputerSuperClass server = FactoryClass.getComputer("server","5.7 GHz","100 GB","3 TB");
        System.out.println("Factory PC Config::"+pc);
        System.out.println("Factory Server Config::"+server);
    }
}
