package lw01.prelab;

public interface Chargeable { //interface bisa memiliki lebih dari satu ... misal Chargeable, ..., ...
    int calculateCharge();
}

//interface class yang berperan untuk mengawasi class-class yang menggunakan method calculateCharge() agar class-class tersebut memiliki method calculateCharge() yang mengembalikan nilai int
//interface class tidak memiliki body

