package designpatterns.observer;

import static org.junit.Assert.assertEquals;

public class TestObserver {
    public static void main(String[] args) {
        Farmer farmer = new Farmer();
        ShopKeepers shopKeepers = new ShopKeepers();
//        FruitKeepers fruitKeepers = new FruitKeepers();  if we want to add another type of observer we can do so like this

        farmer.addShopkeepers(shopKeepers);

        farmer.conveyChanges("kopila lago");
        System.out.println(shopKeepers.getRipeStatus());
    }
}
