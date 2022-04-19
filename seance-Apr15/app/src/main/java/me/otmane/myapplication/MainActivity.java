package me.otmane.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

import me.otmane.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.tabhost.setup();

        TabHost.TabSpec spec = binding.tabhost.newTabSpec("t1");
        spec.setIndicator("Client");
        spec.setContent(R.layout.list_clients_fragment);
        binding.tabhost.addTab(spec);

        spec = binding.tabhost.newTabSpec("t2");
        spec.setIndicator("Ajouter");
        binding.tabhost.addTab(spec);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}