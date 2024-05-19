// COUNTING-INVERSIONS - DIVIDE AND CONQUER

import java.io.*;
import java.util.*;

class ArrayInversions {
    static Integer[] getInput() {
        // Filepath for the input file
        String filepath = "IntegerArray.txt";
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


    // Dividing the array into n/2 sub arrays and sorting recursively
    static long sortAndCount(Integer[] a, int p, int r) {
        // Base case
        long inversionCount = 0;
        if (p < r) {
            // Finding the pivot index
            int q = (p+r)/2;
            // Sorting the left sub array and updating the number of left inversions
            inversionCount += sortAndCount(a, p, q);
            // Sorting the right sub array and updating the number of right inversions
            inversionCount += sortAndCount(a, q+1, r);
            // Merging the two sorted sub arrays and updating the number of split inversions
            inversionCount += countSplitInversion(a, p, q, r);
        }
        return inversionCount;
    }


    // Merging the two sorted sub arrays 
    static long countSplitInversion(Integer[] A, int left, int pivot, int right){
        // Getting the size of two sub arrays
        int n1 = pivot - left + 1;
        int n2 = right - pivot;
        long splitInversion = 0;

        // Declaring two temporary sub arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Initalising the left sub array
        for (int i = 0; i < n1; i++) L[i] = A[left + i];
        
        // Initialising the right sub array
        for (int i = 0; i < n2; i++) R[i] = A[pivot + i + 1];

        // Merging the two sub arrays
        int i = 0, j = 0, k = left;
        while ( i < n1 && j < n2) {
            if (L[i] <= R[j]) A[k++] = L[i++];
            else {
                A[k++] = R[j++];
                // Counting the remaining elements in the left array and appending it to count
                splitInversion += L.length - i;
            }
        }

        while ( i < n1) A[k++] = L[i++];
        while (j < n2) A[k++] = R[j++];

        return splitInversion;
    }


    public static void main(String[] arg) {
        Integer[] array = getInput();
        System.out.println("Inversions: " + sortAndCount(array, 0, array.length - 1));
    }
}
