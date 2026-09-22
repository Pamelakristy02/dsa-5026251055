package lw01.prelab;

//abstract class adalah blue print dari kelas lain
//abstract class mencakup 2 class yg berada dalam family yang sama (tidak seperti interface

public abstract class PrintJob implements Chargeable {
      private String id;
      private int pages;
      
      protected PrintJob (String id, int pages) {
        if (pages < 0) {
            throw new IllegalArgumentException("Pages cannot be negative");
        }
        
        this.id = id;
        this.pages = pages;
      }

      public String getId() {
        return id; //sebagai perantara agar dapat mengakses private id 
      }

      public int getPages() {
        return pages; //sebagai perantara agar dapat mengakses private pages
      }

      @Override 
      public abstract int calculateCharge(); //memiliki abstract method calculateCharge() yang sama dengan interface Chargeable
      public int calculateCharge(int copies) {
        if (copies < 0) {
            throw new IllegalArgumentException("Copies cannot be negative");
        }
        return copies * calculateCharge(); 
      }
      
      public String label(){
        return "Print";
      }

      public String summary () {
        return id + "|" + label() + " | " + calculateCharge();
      }
      
}
