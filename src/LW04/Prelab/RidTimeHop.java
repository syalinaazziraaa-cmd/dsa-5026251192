package LW04.Prelab;

import java.io.InputStream;
import java.util.Scanner;

public class RidTimeHop {
    static final int PASSES = 3;

    // Insertion sort descending, berhenti tepat setelah 'passes' pass
    static void insertionSortDesc(int[] a, int passes) {
        for (int i = 1; i <= passes && i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            // geser elemen yang lebih kecil dari key satu posisi ke kanan
            while (j >= 0 && a[j] < key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // Linear search dari index 0; kembalikan -1 jika tidak ditemukan
    static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        InputStream input = RidTimeHop.class.getResourceAsStream("branches.txt");
        if (input == null) {
            System.out.println("File branches.txt tidak ditemukan.");
            return;
        }

        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        insertionSortDesc(branches, PASSES);

        // Tampilkan array setelah 3 pass
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(" ");
            sb.append(branches[i]);
        }
        System.out.println(sb);

        // Tampilkan hasil pencarian sesuai urutan target
        for (int t : targets) {
            int idx = linearSearch(branches, t);
            if (idx != -1) {
                System.out.println(t + ": FOUND at index " + idx);
            } else {
                System.out.println(t + ": NOT FOUND");
            }
        }
    }
}