package me.otmane.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.intellij.lang.annotations.Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import me.otmane.myapplication.models.Client;
import me.otmane.myapplication.models.Command;

public class Database extends SQLiteOpenHelper {
    private static Database instance;

    private static final int currentVersion = 2;
    private final Map<Integer, List<String>> migrations = new HashMap<Integer, List<String>>() {{
        put(2, new ArrayList<String>() {{
            add(getAlterTableScript(Client.TABLE_NAME, "ADD " + Client.PICTURE_FIELD));
        }});
    }};

    public static Database getInstance(Context context) {
        if (instance == null) {
            instance = new Database(context);
        }
        return instance;
    }

    private Database(@Nullable Context context) {
        super(context, "myapplication.db", null, currentVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getCreateTableScript(Client.TABLE_NAME, Client.TABLE_FIELDS));
        db.execSQL(getCreateTableScript(Command.TABLE_NAME, Command.TABLE_FIELDS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int previousVersion, int currentVersion) {
        if (previousVersion == 1) {
            for (String migration : Objects.requireNonNull(migrations.get(2))) {
                db.execSQL(migration);
            }
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        db.setForeignKeyConstraintsEnabled(true);
    }

    @Language("sql")
    private String getCreateTableScript(@Language("sql") String tableName, List<String> fields) {
        return "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                String.join(", ", fields) + ");";
    }

    @Language("sql")
    private String getAlterTableScript(@Language("sql") String tableName, @Language("sql") String cmd) {
        return "ALTER TABLE " + tableName + " " + cmd + ";";
    }

}
