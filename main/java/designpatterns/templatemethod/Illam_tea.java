package designpatterns.templatemethod;

public class Illam_tea extends TeaTemplate {
    void addWater() {
        System.out.println("adding pure tap water");
    }

    void addTea() {
        System.out.println("adding illam tea");
    }

    void addMilk(){
        System.out.println("adding illam cow milk");
    }

    @Override
    void addSugar() {
        System.out.println("adding illam sugar");
    }
}
