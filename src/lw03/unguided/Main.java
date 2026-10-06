package lw03.unguided;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        Set<String> registered = new HashSet<>();

        while (sc1.hasNext()) {
            String id = sc1.next();
            registered.add(id);
        }
        sc1.close();

        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        Set<String> checkedIn = new HashSet<>();
        int rejected = 0;

        System.out.println("===== Event Check-In Results =====");
        while (sc2.hasNext()) {
            String id = sc2.next();

            if (!registered.contains(id)) {
                System.out.println(id + ": Rejected (not registered)");
                rejected++;
            } else if (checkedIn.contains(id)) {
                System.out.println(id + ": Rejected (already checked in)");
                rejected++;
            } else {
                checkedIn.add(id);
                System.out.println(id + ": Checked in");
            }
        }
        sc2.close();

        int absent = registered.size() - checkedIn.size();

        System.out.println("\n ===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + checkedIn.size());
        System.out.println("Absent students: " + absent);
        System.out.println("Rejected attempts: " + rejected);
    }
}