package me.otmane.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.intellij.lang.annotations.Language;

import java.util.List;

import me.otmane.myapplication.models.Client;
import me.otmane.myapplication.models.Command;

public class Database extends SQLiteOpenHelper {
    private static Database instance;

    public static Database getInstance(Context context) {
        if (instance == null) {
            instance = new Database(context);
        }
        return instance;
    }

    private Database(@Nullable Context context) {
        super(context, "myapplication.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getCreateTableScript(Client.TABLE_NAME, Client.TABLE_FIELDS));
        db.execSQL(getCreateTableScript(Command.TABLE_NAME, Command.TABLE_FIELDS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        db.setForeignKeyConstraintsEnabled(true);
    }

    @Language("sql")
    String getCreateTableScript(@Language("sql") String tableName, List<String> fields) {
        return "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                String.join(", ", fields) + ");";
    }

}
