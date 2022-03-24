package com.dev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.myapplication.adapters.FournisseursAdapter;
import com.dev.myapplication.data.GestionFournisseurs;
import com.dev.myapplication.databinding.ActivityListFournisseurBinding;

public class ListFournisseurActivity extends AppCompatActivity {
    public static final String TAG = "ListFournisseurActivity";

    ActivityListFournisseurBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListFournisseurBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (GestionFournisseurs.getFournisseurs().size() == 0) {
            binding.errorMsg.setText("Pas de founisseur");
            binding.errorMsg.setVisibility(View.VISIBLE);
        } else {
            binding.errorMsg.setVisibility(View.GONE);
        }

        FournisseursAdapter fournisseursAdapter = new FournisseursAdapter(this, GestionFournisseurs.getFournisseurs());

        binding.fournisseurLv.setAdapter(fournisseursAdapter);

        binding.fournisseurLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListFournisseurActivity.this, AddFournisseurActivity.class);
                intent.putExtra(AddFournisseurActivity.INTENT, GestionFournisseurs.getFournisseurs().get(i));

                startActivity(intent);
            }
        });
    }
}