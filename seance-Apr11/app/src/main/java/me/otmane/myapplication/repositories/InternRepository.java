package me.otmane.myapplication.repositories;

import java.util.ArrayList;
import java.util.List;

import me.otmane.myapplication.models.Intern;

public class InternRepository {
    private static final List<Intern> internList = new ArrayList<>();

    public static List<Intern> getInternList() {
        return internList;
    }
}
