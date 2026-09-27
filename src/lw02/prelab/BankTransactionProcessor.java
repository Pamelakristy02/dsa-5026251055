package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BankTransactionProcessor {

    public static void main(String[] args) {

        LinkedList<String[]> transactions = new LinkedList<>();

        Scanner fileScanner = new Scanner(BankTransactionProcessor.class.getResourceAsStream("transactions.txt"));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            
            String[] parts = line.split("\\s+");
            transactions.add(parts);
        }
        fileScanner.close();

        
        LinkedList<String[]> customers = new LinkedList<>();

        for (String[] transaction : transactions) {
            String name = transaction[0];
            if (findCustomer(customers, name) == null) {
                customers.add(new String[]{name, "0"});
            }
        }

        
        Queue<String[]> transactionQueue = new LinkedList<>();
        transactionQueue.addAll(transactions);

        
        Stack<String[]> failedWithdrawals = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] transaction = transactionQueue.poll();
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = findCustomer(customers, name);
            int balance = Integer.parseInt(customer[1]);

            if (type.equalsIgnoreCase("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equalsIgnoreCase("WITHDRAW")) {
                if (amount > balance) {
                    // Failed withdrawal: balance stays the same
                    failedWithdrawals.push(transaction);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedWithdrawals.isEmpty()) {
            String[] failed = failedWithdrawals.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }


    private static String[] findCustomer(LinkedList<String[]> customers, String name) {
        for (String[] customer : customers) {
            if (customer[0].equals(name)) {
                return customer;
            }
        }
        return null;
    }
}
