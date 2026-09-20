package lw01.prelab;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();   

        try (Scanner scanner = new Scanner(new File("src/lw01/prelab/jobs.txt"))) {
            while (scanner.hasNext()) {
                String type = scanner.next();       
                String id = scanner.next();         
                int pages = scanner.nextInt();      

                if (type.equals("MONO")) {
                    jobs.add(new MonoPrint(id, pages));
                } else if (type.equals("COLOUR")) {
                    jobs.add(new ColourPrint(id, pages));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File jobs.txt tidak ditemukan.");
            return;
        }

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
    
}
