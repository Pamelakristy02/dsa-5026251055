package lw02.unguided;

import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foodStock = new LinkedList<>();
        LinkedList<String[]> drinkStock = new LinkedList<>();
        LinkedList<String[]> successfulOrders = new LinkedList<>();

        Queue<String[]> orderQueue = new LinkedList<>();

        Stack<String[]> failedOrders = new Stack<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("orders.txt"));

         while (sc.hasNextLine()) {
            String[] order = new String[4];
            order[0] = sc.next(); 
            order[1] = sc.next();
            order[2] = sc.next(); 
            order[3] = sc.next(); 
            orders.add(order);
        }
        sc.close();

        foodStock.add(new String[]{"Bakso", "2"});
        foodStock.add(new String[]{"Sate", "1"});
        foodStock.add(new String[]{"Soto", "2"});
        
        drinkStock.add(new String[]{"EsTeh", "4"});
        drinkStock.add(new String[]{"EsJeruk", "2"});

        orderQueue.addAll(orders);

        while (!orderQueue.isEmpty()) {
            String[] order = orderQueue.poll();
            String food = order[1];
            String drink = order[2];

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            //cek stok makanan kalau customer order bukan "-" (ada pesanan)
            String[] foodRecord = null;
            if (!food.equals("-")) {
                foodRecord = findItem(foodStock, food);
                int stock = Integer.parseInt(foodRecord[1]);
                if (stock <= 0) {
                    foodAvailable = false;
                }
            }

            //cek stok minuman kalau customer order bukan "-" (ada pesanan)
            String[] drinkRecord = null;
            if (!drink.equals("-")) {
                drinkRecord = findItem(drinkStock, drink);
                int stock = Integer.parseInt(drinkRecord[1]);
                if (stock <= 0) {
                    drinkAvailable = false;
                }
            }

            //order berhasil hanya jika semua item yang dipesan tersedia
            if (foodAvailable && drinkAvailable) {
                if (foodRecord != null) {
                    int stock = Integer.parseInt(foodRecord[1]);
                    foodRecord[1] = String.valueOf(stock - 1);
                }
                if (drinkRecord != null) {
                    int stock = Integer.parseInt(drinkRecord[1]);
                    drinkRecord[1] = String.valueOf(stock - 1);
                }
                successfulOrders.add(order);
            } else {
                failedOrders.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successfulOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println();
        System.out.println("=== Remaining Food Stock ===");
        for (String[] food : foodStock) {
            System.out.println(food[0] + " : " + food[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Drink Stock ===");
        for (String[] drink : drinkStock) {
            System.out.println(drink[0] + " : " + drink[1]);
        }

        System.out.println();
        System.out.println("=== Failed Orders ===");
        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }

    private static String[] findItem(LinkedList<String[]> stockList, String name) {
        for (String[] item : stockList) {
            if (item[0].equals(name)) {
                return item;
            }
        }
        return null;
    }
}