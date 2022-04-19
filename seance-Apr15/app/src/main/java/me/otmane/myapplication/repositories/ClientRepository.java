package me.otmane.myapplication.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import me.otmane.myapplication.db.Database;
import me.otmane.myapplication.models.Client;

public class ClientRepository {
    private static ClientRepository instance;

    private final Database db;

    public static ClientRepository getInstance(Context context) {
        if (instance == null) {
            instance = new ClientRepository(Database.getInstance(context));
        }
        return instance;
    }

    private ClientRepository(Database db) {
        this.db = db;
    }

    public List<Client> all() {
        ArrayList<Client> cls = new ArrayList<>();

        Cursor c = db.getReadableDatabase().rawQuery("select * from " + Client.TABLE_NAME, null);
        while (c.moveToNext()) {
            cls.add(new Client(c.getInt(0), c.getString(1), c.getString(2), c.getString(3)));
        }

        return cls;
    }

    public boolean insert(ContentValues values) {
        return db.getWritableDatabase().insert(Client.TABLE_NAME, null, values) > 0L;
    }

    public Client get(int code) {
        Cursor c = db.getReadableDatabase().rawQuery("select * from " + Client.TABLE_NAME +
                " where " + Client.CODE_FIELD_NAME + " = ?", new String[]{String.valueOf(code)});

        Client client = null;

        if (c.moveToNext()) {
            client = new Client(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
        }

        return client;
    }

    public boolean update(int code, ContentValues values) {
        return db.getWritableDatabase().update(Client.TABLE_NAME, values, "code = ?", new String[]{String.valueOf(code)}) > 0L;
    }

    public boolean delete(int code) {
        return db.getWritableDatabase().delete(Client.TABLE_NAME, "code = ?", new String[]{String.valueOf(code)}) > 0L;
    }
}
