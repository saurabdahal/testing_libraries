package designpatterns.templatemethod;

public abstract class TeaTemplate {

    public final void makeTea() {
        addWater();
        addSugar();
        addTea();
        addMilk();
    }


    void addSugar() {
        System.out.println("adding white sugar");
    }


    void addMilk() {
        System.out.println("adding brown cow milk");
    }

    abstract void addWater();

    abstract void addTea();
}
