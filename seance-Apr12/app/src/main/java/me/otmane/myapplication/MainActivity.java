package me.otmane.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Objects;

import me.otmane.myapplication.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {
    MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SQLiteDatabase db = initiateDatabase();

        initiateRecyclerView(db);
        initiateEventListeners(db);
    }

    private SQLiteDatabase initiateDatabase() {
        SQLiteOpenHelper sqLiteOpenHelper = new SQLiteOpenHelper(getApplicationContext(), "database.db", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("CREATE TABLE accounts (" +
                        "fullName TEXT," +
                        "age INTEGER" +
                        ")");
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        };

        return sqLiteOpenHelper.getWritableDatabase();
    }

    private void initiateRecyclerView(SQLiteDatabase db) {
        Person.list.clear();

        Cursor c = db.rawQuery("SELECT * FROM accounts", null);
        while (c.moveToNext()) {
            Person p = new Person(c.getString(0), c.getInt(1));
            Person.list.add(p);
        }
        c.close();

        binding.rv.setAdapter(new PersonRecyclerViewAdapter(Person.list));
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initiateEventListeners(SQLiteDatabase db) {
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person p = new Person(
                        Objects.requireNonNull(binding.fullNameInput.getEditText()).getText().toString(),
                        Integer.parseInt(Objects.requireNonNull(binding.ageInput.getEditText()).getText().toString()));

                ContentValues cv = new ContentValues();
                cv.put("fullName", p.getFullName());
                cv.put("age", p.getAge());
                db.insert("accounts", null, cv);
            }
        });

        initiateRecyclerView(db);
    }


}