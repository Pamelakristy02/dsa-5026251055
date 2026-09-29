package lw02.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        //step 1: buat semua linkedlist yang dibutuhkan

        //linkedlist ke-1 trnasaction dengan data type string array format: [name, type, amount]
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        //queue merupakan interface yang berupa sifat, sedangkan linkedlist adalah class merupakan implementasi dari queue. Queue digunakan untuk memproses transaksi secara berurutan (FIFO)
        Queue<String[]> transactionQueue = new LinkedList<>();

        //stack merupakan class yang memiliki sifat sendiri (LIFO)
        Stack<String[]> failedWithdrawals = new Stack<>();

        //baca file transactions.txt
        Scanner fileScanner = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        //baca file line by line, split setiap line menjadi array string, dan tambahkan ke linkedlist transactions
        while (fileScanner.hasNextLine()) {
            String[] transaction = new String[3];
            transaction[0] = fileScanner.next();
            transaction[1] = fileScanner.next();
            transaction[2] = fileScanner.next();
            transactions.add(transaction);
        }
        fileScanner.close();

        //untuk mencegah duplikasi nama customer, kita akan menambahkan semua nama customer ke dalam linkedlist customers jika belum ada
        for (String[] transaction : transactions) {
            String name = transaction[0];
            if (findCustomer(customers, name) == null) {
                customers.add(new String[]{name, "0"});
            }
        }

        //tambahkan semua (addall) transaksi ke dalam queue
        transactionQueue.addAll(transactions);

        while (!transactionQueue.isEmpty()) {
            String[] transaction = transactionQueue.poll();
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = findCustomer(customers, name);
            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
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