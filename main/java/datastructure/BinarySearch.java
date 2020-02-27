package datastructure;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        new BinarySearch(arr, 6);
    }

    public BinarySearch(int[] arr, int element) {
        System.out.println("Element found at index = " + searchItems(arr, element,0));
    }

    public int searchItems(int[] arr, int element,int prev_mid) {
        int idx = -1;
        int mid_index = (int) (arr.length % 2 == 0 ? arr.length / 2 : ((arr.length / 2) + 1));
        int[] temp = new int[mid_index];

        if (element == arr[mid_index]) {
            return mid_index+prev_mid;
        }

        if (element < arr[mid_index]) {
            System.arraycopy(arr, 0, temp, 0, mid_index);
            return searchItems(temp, element,0);
        }

        if (element > arr[mid_index]) {
            System.arraycopy(arr, mid_index, temp, 0, mid_index);
            return searchItems(temp, element,mid_index);
        }

        return -1;

    }

}
