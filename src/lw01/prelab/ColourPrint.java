package lw01.prelab;

public class ColourPrint extends PrintJob {
    public ColourPrint (String id, int pages) {
        super(id, pages);
    }
    
    @Override 
    public int calculateCharge() {
        int total;

        if (getPages() <= 10) {
            total = getPages() * 1500;
        } else {
            total = (10 * 1500) + ((getPages() - 10) * 1000);
        }
        return total + 2000;
    }

    @Override 
    public String label() {
        return "Colour";
    }
}
