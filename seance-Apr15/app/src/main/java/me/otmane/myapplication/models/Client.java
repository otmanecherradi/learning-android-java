package me.otmane.myapplication.models;

import org.intellij.lang.annotations.Language;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Client {
    @Language("sql")
    public static final String TABLE_NAME = "client";
    public static final String CODE_FIELD_NAME = "code";
    public static final String NAME_FIELD_NAME = "name";
    public static final String PHONE_FIELD_NAME = "phone";
    public static final String ADDRESS_FIELD_NAME = "address";

    @Language("sql")
    private static final String CODE_FIELD = CODE_FIELD_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT";
    @Language("sql")
    private static final String NAME_FIELD = NAME_FIELD_NAME + " TEXT NOT NULL";
    @Language("sql")
    private static final String PHONE_FIELD = PHONE_FIELD_NAME + " TEXT NOT NULL";
    @Language("sql")
    private static final String ADDRESS_FIELD = ADDRESS_FIELD_NAME + " TEXT";

    public static final List<String> TABLE_FIELDS = Arrays.asList(
            CODE_FIELD, NAME_FIELD, PHONE_FIELD, ADDRESS_FIELD
    );

    private int code;
    private String name;
    private String phone;
    private String address;

    public Client() {
    }

    public Client(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Client(int code, String name, String phone, String address) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getCode() == client.getCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getName(), getPhone(), getAddress());
    }
}
