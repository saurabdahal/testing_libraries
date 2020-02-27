import java.util.Hashtable;

class HashTableDemo {
    public static void main(String[] arg)
    {
        // creating a hash table 
        Hashtable<Integer, String> h =
                new Hashtable<Integer, String>();

        Hashtable<Integer, String> h1 =
                new Hashtable<Integer, String>();

        h.put(3, "Geeks");
        h.put(2, "forGeeks");
        h.put(1, "isBest");

        // checking clone h1 
        System.out.println("values in clone: " + h1);

        // clear hash table h 
        h.clear();

        // checking hash table h 
        System.out.println("after clearing: " + h);
    }
}