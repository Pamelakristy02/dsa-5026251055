package lw03.prelab;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    //Problem 1: Playlist (List) 
    static void problem1() {
        Scanner fileScanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("ADD ")) {
                String song = line.substring(4);
                playlist.add(song);
            } else if (line.startsWith("INSERT ")) {
                String[] parts = line.split(" ", 3);
                int index = Integer.parseInt(parts[1]);
                playlist.add(index, parts[2]);
            } else if (line.startsWith("REMOVE ")) {
                String song = line.substring(7);
                playlist.remove(song);
            }
        }
        fileScanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
        System.out.println();
    }

    //Problem 2: Peserta Workshop (Set) 
    static void problem2() {
        Scanner fileScanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> participants = new LinkedHashSet<>(); // menjaga urutan kemunculan pertama
        int duplicates = 0;

        while (fileScanner.hasNextLine()) {
            String name = fileScanner.nextLine().trim();
            if (name.isEmpty()) continue;

            if (participants.contains(name)) {
                duplicates++;
            } else {
                participants.add(name);
            }
        }
        fileScanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int number = 1;
        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }
        System.out.println("Duplicate registrations: " + duplicates);
        System.out.println();
    }

    //Problem 3: Inventori Toko (Map)
    static void problem3() {
        Scanner fileScanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> stock = new LinkedHashMap<>(); // menjaga urutan kemunculan pertama
        int failedSales = 0;

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (line.isEmpty()) continue;

            
            String[] parts = line.split(" ");
            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (stock.containsKey(product)) {
                    stock.put(product, stock.get(product) + quantity);
                } else {
                    stock.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (stock.containsKey(product) && stock.get(product) >= quantity) {
                    stock.put(product, stock.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        fileScanner.close();

        System.out.println("===== Problem 3 =====");
        for (String product : stock.keySet()) {
            System.out.println(product + ": " + stock.get(product));
        }
        System.out.println("Failed sales: " + failedSales);
    }
}
