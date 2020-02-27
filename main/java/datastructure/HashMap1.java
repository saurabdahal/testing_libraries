package datastructure;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

class HashMap1 {
    private HashMap<Integer, String> hm = new HashMap<>();

    public static void main(String[] args) {
        HashMap1 hashMap1 = new HashMap1();
    }

    private HashMap1(){
        hm = new HashMap<Integer, String>();

        // put value in hashmap
        this.putInMap();

        // retrive and print value
        System.out.println(this.getValue(100));
        System.out.println(this.getValue(101));
        System.out.println(this.getValue(102));
    }

    private void putInMap() {
        hm.put(100, "Amit");
        hm.put(101, "Vijay");
        hm.put(102, "Rahul");
    }

    private Object getValue(int index){
        return hm.get(index);
    }
}  