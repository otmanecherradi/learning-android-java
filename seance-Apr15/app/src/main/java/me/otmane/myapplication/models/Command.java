package me.otmane.myapplication.models;

import org.intellij.lang.annotations.Language;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Command {
    @Language("sql")
    public static final String TABLE_NAME = "command";
    public static final String CODE_FIELD_NAME = "code";
    public static final String DATE_FIELD_NAME = "date";
    public static final String CLIENT_ID_FIELD_NAME = "client_id";

    @Language("sql")
    private static final String CODE_FIELD = CODE_FIELD_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT";
    @Language("sql")
    private static final String DATE_FIELD = DATE_FIELD_NAME + " TEXT NOT NULL";
    @Language("sql")
    private static final String CLIENT_ID_FIELD = CLIENT_ID_FIELD_NAME +
            " INTEGER NOT NULL REFERENCES " + Client.TABLE_NAME + "(" + Client.CODE_FIELD_NAME +
            ") ON DELETE CASCADE ON UPDATE NO ACTION";

    public static final List<String> TABLE_FIELDS = Arrays.asList(
            CODE_FIELD, DATE_FIELD, CLIENT_ID_FIELD
    );


    private int code;
    private Date date;
    private int clientCode;

    public Command() {
    }

    public Command(Date date, int clientCode) {
        this.date = date;
        this.clientCode = clientCode;
    }

    public Command(int code, Date date, int clientCode) {
        this.code = code;
        this.date = date;
        this.clientCode = clientCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getClientCode() {
        return clientCode;
    }

    public void setClientCode(int clientCode) {
        this.clientCode = clientCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command)) return false;
        Command command = (Command) o;
        return getCode() == command.getCode();
    }
}
