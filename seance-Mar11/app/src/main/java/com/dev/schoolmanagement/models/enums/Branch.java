package com.dev.schoolmanagement.models.enums;

import java.util.ArrayList;

public enum Branch {
    TDI("TDI"),
    TRI("TRI"),
    TDM("TDM");

    Branch(String value) {
    }

    public static ArrayList<String> all() {
        ArrayList<String> lst = new ArrayList<>();
        lst.add(String.valueOf(Branch.TDI));
        lst.add(String.valueOf(Branch.TRI));
        lst.add(String.valueOf(Branch.TDM));
        return lst;
    }
}
