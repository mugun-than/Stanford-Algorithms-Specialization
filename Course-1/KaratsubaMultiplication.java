// KARATSUBA-MULTIPLICATION - DIVIDE AND CONQUER
import java.util.*;

class KaratsubaMultiplication {
    static long number1;
    static long number2;


    // Getting the input from user
    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter First number: ");
        number1 = sc.nextLong();
        System.out.print("Enter Second number: ");
        number2 = sc.nextLong();
        sc.close();
    }


    // Multiplying using karatsuba multiplication algorithm recursively
    public static long Multiply(long x, long y) {
        // Base case
        if (x < 10 && y < 10) {
            return x*y;
        }

        // Getting the number of digits in each number
        int n1 = length(x);
        int n2 = length(y);

        // Maximum of the number of digits - n
        int n = Math.max(n1, n2);
        // Half of the maximum - n/2
        Integer halfn = (n/2) + (n%2);
        // Digit weightage
        long nten = (long) Math.pow(10, halfn);

        // Corresponding values for karatsuba multiplication
        long a = x / nten;
        long b = x % nten;
        long c = y / nten;
        long d = y % nten;

        // x*y = 10^n (a*c) + 10^n/2 (ad + bc) + bd
        long z1 = Multiply(a, c);
        long z2 = Multiply(a+b, c+d);
        long z3 = Multiply(b, d);

        return ((z1 * (long) Math.pow(10, n)) + ((z2 - z1 - z3) * (long) Math.pow(10, halfn)) + z3);
    }


    // Calculating the number of digits
    public static int length(long a) {
        int length = 0;
        while(a > 0) {
            length++;
            a /= 10;
        }
        return length;
    }


    public static void main(String[] args) {
        getInput();
        System.out.println("Product: "+ Multiply(number1, number2));
    }
}
