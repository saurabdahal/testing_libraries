package designpatterns.templatemethod;

public class DarjeelingTea extends TeaTemplate {
    void addWater() {
        System.out.println("adding pure tap water");
    }

    void addTea() {
        System.out.println("adding darjeeling tea");
    }

    void addMilk() {
        System.out.println("adding darjeeling cow milk");
    }

    @Override
    void addSugar() {
        System.out.println("adding darjeeling sugar");
    }
}
