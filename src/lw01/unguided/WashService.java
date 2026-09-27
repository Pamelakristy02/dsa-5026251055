package lw01.unguided;

public abstract class WashService implements Billable {
    private String id;
    private int days;

    protected WashService(String id, int days) {
        if (days < 0) {
            throw new IllegalArgumentException("Days cannot be negative");
        }

        this.id = id;
        this.days = days;
        }
      
    public String getId() {
        return id;
      }

      public int getDays() {
        return days;
      }

      @Override 
      public abstract int calculateCharge(); 

      public int calculateCharge(int units) {
        if (units < 0) {
            throw new IllegalArgumentException("Units cannot be negative");
        }
        return units * calculateCharge(); 
      }
      
      public String label(){
        return "Service";
      }

      public String summary (int units) {
        return id + "|" + label() + " | " + calculateCharge(units);
      }
}
