package com.dev.schoolmanagement.models.enums;

public enum Branch {
    TDI("TDI"),
    TRI("TRI"),
    TDM("TDM");

    Branch(String value) {
    }

    public static Branch[] all() {
        return new Branch[]{Branch.TDI, Branch.TRI, Branch.TDM};
    }

    public static int getPosition(Branch branch) {
        for (int i = 0; i < all().length; i++) {
            if(all()[i] == branch)
                return i;
        }
        return -1;
    }
}
