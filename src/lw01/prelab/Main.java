package lw01.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();   

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("jobs.txt")); 
            while (scanner.hasNext()) {
                String type = scanner.next();       
                String id = scanner.next();         
                int pages = scanner.nextInt();      

                PrintJob job;

                if (type.equals("MONO")) {
                    jobs.add(new MonoPrint(id, pages));
                } else if (type.equals("COLOUR")) {
                    jobs.add(new ColourPrint(id, pages));
                }
            }

        scanner.close();

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
    
}
