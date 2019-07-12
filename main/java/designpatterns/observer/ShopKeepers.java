package designpatterns.observer;

class ShopKeepers implements Edibles{
    private String ripeStatus;

    @Override
    public void updateRipeStatus(Object o) {
        this.setRipeStatus((String) o);
    }

    private void setRipeStatus(String ripeStatus) {
        this.ripeStatus = ripeStatus;
    }

    String getRipeStatus() {
        return this.ripeStatus;
    }
}
