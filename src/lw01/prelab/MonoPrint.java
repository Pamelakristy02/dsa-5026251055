package lw01.prelab;

public class MonoPrint extends PrintJob{
    public MonoPrint (String id, int pages) {
        super(id, pages); 
        //super() digunakan untuk memanggil constructor dari class induk (PrintJob) agar dapat mengakses private id dan pages
    }

    @Override
    public int calculateCharge() {
        return getPages() * 500;
    }
    
    @Override
    public String label() {
        return "Mono";
    }
    
}
