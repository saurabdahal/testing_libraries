package designpatterns.observer;

public class FruitKeepers implements Edibles{
    private String ripeStatus;

    @Override
    public void updateRipeStatus(Object o) {
        this.setRipeStatus((String) o);
    }

    void setRipeStatus(String ripeStatus) {
        this.ripeStatus = ripeStatus;
    }

    public String getRipeStatus() {
        return this.ripeStatus;
    }
}
