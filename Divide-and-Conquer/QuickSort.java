// QUICK-SORT - DIVIDE AND CONQUER
import java.io.*;
import java.util.*;

class QuickSort {
    // Getting the input array elements from the user, in which the array-size may vary each and every time
    static Integer[] getInput() {
        // Filepath for the input file
        String filepath = "./Input-files/QuickSort_input.txt";
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
    private static void getRandomInteger(Integer[] a,int min, int max) {
        Random r = new Random();
        int pivot =  r.nextInt(max - min)+min;
        swap(a, pivot, min);
    }
    // Method used to rearrange the acoording to the pivot element
    private static int partition(Integer[] a, int l, int r) {
        //getRandomInteger(a, l, r);
        int p = a[l];
        int i = l + 1;

        for(int j = l + 1; j <= r; j++) if (a[j] < p) swap(a, i++, j);
        swap(a, l, --i);

        return i;
    }


    // Actual QuickSort algorithm
    private static int Quick_Sort(Integer[] a, int l, int r) {
        int comparisons = 0;
        if (l < r) {
            int pivot = partition(a, l, r);
            comparisons += (r - l);
            
            comparisons += Quick_Sort(a, l, pivot - 1);
            
            comparisons += Quick_Sort(a, pivot + 1, r);
        }

        return comparisons;
    }


    public static void main(String[] args) {
        Integer[] array = getInput();
        int noOfComparisons = Quick_Sort(array, 0, array.length - 1);
        System.out.println("Number of Comparisons: " + noOfComparisons);

        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
}