package com.dev.schoolmanagement.models.enums;

import java.util.ArrayList;

public enum City {
    Tangier("Tangier"),
    Tetouan("Tetouan"),
    Casa("Casa");

    City(String value) {
    }

    public static ArrayList<String> all() {
        ArrayList<String> lst = new ArrayList<>();
        lst.add(String.valueOf(City.Tangier));
        lst.add(String.valueOf(City.Tetouan));
        lst.add(String.valueOf(City.Casa));
        return lst;
    }
}
