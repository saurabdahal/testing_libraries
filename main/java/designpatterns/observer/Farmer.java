package designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Farmer {

    private List<ShopKeepers> shopKeepersList = new ArrayList<>();

    void addShopkeepers(ShopKeepers shopKeepers){
        shopKeepersList.add(shopKeepers);
    }

    public void removeShopkeepers(ShopKeepers shopKeepers){
        shopKeepersList.remove(shopKeepers);
    }

    private List<ShopKeepers> getShopkeepers(){
        return shopKeepersList;
    }

    void conveyChanges(Object o){
        for(ShopKeepers shopKeeper:getShopkeepers()){
            shopKeeper.updateRipeStatus(o);
        }
    }

}
