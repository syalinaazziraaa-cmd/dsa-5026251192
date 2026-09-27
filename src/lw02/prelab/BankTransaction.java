package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BankTransaction {

    public static void main(String[] args) {
        // 1. Read and store transactions in a LinkedList
        LinkedList<String[]> transactions = new LinkedList<>();

        Scanner sc = new Scanner(
                BankTransaction.class.getResourceAsStream("transactions.txt")
        );
        while (sc.hasNext()) {
            String name = sc.next();
            String type = sc.next();
            String amount = sc.next();
            transactions.add(new String[]{name, type, amount});
        }
        sc.close();

        // 2. Create customer data (name, balance) in first-appearance order
        LinkedList<String[]> customers = new LinkedList<>();
        for (String[] t : transactions) {
            if (findCustomer(customers, t[0]) == null) {
                customers.add(new String[]{t[0], "0"});
            }
        }

        // 3. Move transactions from LinkedList into a Queue
        Queue<String[]> queue = new LinkedList<>();
        for (String[] t : transactions) {
            queue.add(t);
        }

        // 4. Process the queue (FIFO), push failed withdrawals onto a Stack
        Stack<String[]> failed = new Stack<>();

        while (!queue.isEmpty()) {
            String[] t = queue.poll();
            String[] customer = findCustomer(customers, t[0]);
            int balance = Integer.parseInt(customer[1]);
            int amount = Integer.parseInt(t[2]);

            if (t[1].equals("DEPOSIT")) {
                balance += amount;
            } else if (t[1].equals("WITHDRAW")) {
                if (amount > balance) {
                    failed.push(t);
                } else {
                    balance -= amount;
                }
            }
            customer[1] = String.valueOf(balance);
        }

        // 5. Display final balances and failed transactions
        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failed.isEmpty()) {
            String[] t = failed.pop();
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }
    }

    // Find a customer record by name, or return null if not present
    private static String[] findCustomer(LinkedList<String[]> customers, String name) {
        for (String[] c : customers) {
            if (c[0].equals(name)) {
                return c;
            }
        }
        return null;
    }
}