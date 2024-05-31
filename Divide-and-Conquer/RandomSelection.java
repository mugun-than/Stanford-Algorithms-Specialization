// RANDOM-SELECTION - DIVIDE AND CONQUER
import java.io.*;
import java.util.*;

public class RandomSelection {
    // Getting the input array elements from the user, in which the array-size may vary each and every time
    private static Integer[] getInput() {
        // Filepath for the input file
        String filepath = "./Input-files/RandomSelection_input.txt";
        ArrayList<Integer> list = new ArrayList<>();

        // Traversing the file and initialising the ArrayList
        try {
            Scanner sc = new Scanner(new File(filepath));
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                int num = Integer.parseInt(line);
                list.add(num);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        // Converting ArrayList to Integer array
        Integer[] array = list.toArray(new Integer[list.size()]);
        return array;
    }


    // Method used to swap the elements of ith and jth index in array a
    private static void swap(Integer[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    // Method used to return a integer randomly inclusive of the range 
    private static void getRandomInteger(Integer[] a, int min, int max) {
        Random r = new Random();
        int pivot =  r.nextInt(max - min + 1)+min;
        swap(a, pivot, min);
    }


    // Method used to rearrange the acoording to the pivot element
    private static int partition(Integer[] a, int l, int r) {
        getRandomInteger(a, l, r);
        int p = a[l];
        int i = l + 1;

        for(int j = l + 1; j <= r; j++) if (a[j] < p) swap(a, i++, j);
        swap(a, l, --i);

        return i;
    }


    // Randomized QuickSelect
    private static int quickSelect(Integer[] arr, int left, int right, int k) {
        if (left <= right) {
            int pivot = partition(arr, left, right);
            if (pivot == k) {
                return arr[pivot];
            } else if (pivot < k) {
                return quickSelect(arr, pivot + 1, right, k);
            } else {
                return quickSelect(arr, left, pivot - 1, k);
            }
        }
        return -1; // Error case
    }


    // Performing random selection 
    private static int randomSelection(Integer[] arr, int k) {
        if(k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid value of K");
        }
        return quickSelect(arr, 0, arr.length - 1, k);
    }


    public static void main(String[] args) {
        Integer[] array = getInput();
        int OrderStatistic = 190;
        int answer = randomSelection(array, OrderStatistic - 1);
        System.out.println(OrderStatistic + "th smallest element: " + answer);
    }
}
