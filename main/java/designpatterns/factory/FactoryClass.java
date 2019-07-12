package designpatterns.factory;

public class FactoryClass {

    public static ComputerSuperClass getComputer(String type,String cpu,String ram,String hdd){
        if("PC".equalsIgnoreCase(type)) return new PC(cpu,ram,hdd);
        if("SERVER".equalsIgnoreCase(type)) return new Server(cpu,ram,hdd);

        return null;
    }

}
