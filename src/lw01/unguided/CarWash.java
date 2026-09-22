package lw01.unguided;

public class CarWash extends WashService {
    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override 
    public int calculateCharge() {
        int total;
        int days = getDays();

        if (days <= 3) {
            total = days * 35000;
        } else {
            total = (3 * 35000) + ((days - 3) * 15000);
        }
        return total + 15000;
    }

    @Override 
    public String label() {
        return "Car";
    }
}


