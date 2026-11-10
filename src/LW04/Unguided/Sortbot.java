package LW04.Unguided;

import java.util.Scanner;

public class Sortbot {

    public static void bubbleSort(int[] arr, int startPass, int endPass) {
        int n = arr.length;
        for (int i = startPass; i < endPass; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static int[] binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;
            if (arr[mid] == target) {
                return new int[]{mid, steps};
            } else if (target > arr[mid]) {
                high = mid - 1;   // heavier parcels are on the left
            } else {
                low = mid + 1;    // lighter parcels are on the right
            }
        }
        return new int[]{-1, steps};
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Sortbot.class.getResourceAsStream("parcels.txt"));

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

        int firstStage = 2;
        if (firstStage > n - 1) {
            firstStage = n - 1;
        }
        bubbleSort(shelf, 0, firstStage);

        int[] afterTwoPasses = new int[n];
        for (int i = 0; i < n; i++) {
            afterTwoPasses[i] = shelf[i];
        }
        
        bubbleSort(shelf, firstStage, n - 1);

        System.out.println("==== After 2 Passes ====");
        printArray(afterTwoPasses);
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        printArray(shelf);
        System.out.println();

        System.out.println("==== Search Results ====");
        for (int i = 0; i < m; i++) {
            int[] result = binarySearch(shelf, targets[i]);
            if (result[0] != -1) {
                System.out.println(targets[i] + ": FOUND at index " + result[0] + " (steps: " + result[1] + ")");
            } else {
                System.out.println(targets[i] + ": NOT FOUND (steps: " + result[1] + ")");
            }
        }
    }
}