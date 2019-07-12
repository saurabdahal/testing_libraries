package designpatterns.factory;

public abstract class ComputerSuperClass {

    abstract String getCPU();

    abstract String getHDD();

    abstract String getRAM();

    public String toString(){
        return "RAM= "+this.getRAM()+", HDD="+this.getHDD()+", CPU="+this.getCPU();
    }

}
