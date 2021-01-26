package com.example.security.model;

public class Dad {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        Dad d1 = new Dad();
        d1.setId(1);

        Dad d2 = d1;
        d2.setId(2);

        System.out.println("d1.getId() = " + d1.getId());
        System.out.println("d2.getId() = " + d2.getId());

        changeObject(d1);

        System.out.println("d1.getId() = " + d1.getId());
        System.out.println("d2.getId() = " + d2.getId());
    }

    public static void changeObject(Dad d) {
        d = null;
    }
}
