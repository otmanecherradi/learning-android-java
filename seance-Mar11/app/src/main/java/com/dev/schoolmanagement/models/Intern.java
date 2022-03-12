package com.dev.schoolmanagement.models;

import com.dev.schoolmanagement.models.enums.City;

public class Intern {
    String cin;
    String name;
    City city;

    public Intern(String cin, String name, City city) {
        this.cin = cin;
        this.name = name;
        this.city = city;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
