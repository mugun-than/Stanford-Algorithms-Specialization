// MERGE-SORT
import java.util.*;

class MergeSort {
    public static Scanner sc = new Scanner(System.in);


    // Getting an array from the user, in which the array-size may vary each time
    public static int[] getInput() {
        System.out.print("Enter the array elements: ");

        // Getting the input as a String
        String input = sc.nextLine();

        // Converting the input String to array of tokens
        String[] tokens = input.split("\\s+");
        int[] array = new int[tokens.length];
        int i = 0;

        // Parsing each token into an Integer and storing in an array
        for (String s : tokens) {
            try {
                int number = Integer.parseInt(s);
                array[i++] = number;
            } catch (NumberFormatException e) {
                System.out.println("ENTER ONLY INTEGERS");
                System.exit(0);
            }
        }
        return array;
    }


    // Dividing the array into n/2 sub arrays and sorting recursively
    static void sort(int[] a, int p, int r) {
        // Base case
        if (p < r) {
            // Finding the pivot index
            int q = (p+r)/2;
            // Sorting the left sub array
            sort(a, p, q);
            // Sorting the right sub array
            sort(a, q+1, r);
            // Merging the two sorted sub arrays
            merge(a, p, q, r);
        }
    }


    // Merging the two sorted sub arrays 
    static void merge(int[] A, int left, int pivot, int right){
        // Getting the size of two sub arrays
        int n1 = pivot - left + 1;
        int n2 = right - pivot;
        // Declaring two temporary sub arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Initalising the left sub array
        for (int i = 0; i < n1; i++) {
            L[i] = A[left + i];
        }
        // Initialising the right sub array
        for (int i = 0; i < n2; i++) {
            R[i] = A[pivot + i + 1];
        }

        // Merging the two sub arrays
        int i = 0, j = 0, k = left;
        while ( i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            }
            else {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        while ( i < n1) {
            A[k] = L[i];
            k++;
            i++;
        }

        while (j < n2) {
            A[k] = R[j];
            k++;
            j++;
        }
    }
    public static void main (String[] args) {
        int[] array = getInput();
        sort(array, 0, array.length - 1);

        // Printing the sorted array
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}