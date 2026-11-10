package lw04.unguided;

import java.util.Scanner;

public class SortBotWarehouse {
    static int steps;
    public static void main(String[] args) {
        Scanner sc = new Scanner(SortBotWarehouse.class.getResourceAsStream("parcels.txt"));

        int n = sc.nextInt();
        int[] shelf = new int[n];
        for (int i = 0; i < n; i++) {
            shelf[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        bubbleSortDescending(shelf, 0, 2);

        int[] afterTwoPasses = new int[n];
        for (int i = 0; i < n; i++) {
            afterTwoPasses[i] = shelf[i];
        }

       bubbleSortDescending(shelf, 2, n - 1);

       System.out.println("==== After 2 Passes ====");
        printArray(afterTwoPasses);
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        printArray(shelf);
        System.out.println();

        System.out.println("==== Search Results ====");
        for (int i = 0; i < m; i++) {
            int index = binarySearchDescending(shelf, targets[i]);
            if (index != -1) {
                System.out.println(targets[i] + ": FOUND at index " + index + " (steps: " + steps + ")");
            } else {
                System.out.println(targets[i] + ": NOT FOUND (steps: " + steps + ")");
            }
        }
    }

    static void bubbleSortDescending(int[] a, int startPass, int endPass) {
        for (int i = startPass; i < endPass; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] < a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

   static int binarySearchDescending(int[] a, int target) {
        int low = 0;
        int high = a.length - 1;
        steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;

            if (a[mid] == target) {
                return mid;
            } else if (a[mid] > target) {
                 low = mid + 1;
            } else {
               high = mid - 1;
            }
        }
        return -1;
    }

    static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(a[i]);
        }
        System.out.println();
    }
}