package com.dev.schoolmanagement.models;

import androidx.annotation.NonNull;

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

    @NonNull
    @Override
    public String toString() {
        return "Intern{cin=" + cin + ", name=" + name + ", city=" + city + '}';
    }
}
